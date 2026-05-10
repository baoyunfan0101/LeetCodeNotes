// a. brute force: Time Limit Exceeded
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < len; i++) {
            int cnt = 0, target = nums[i];
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < target)
                    cnt++;
            }
            res.add(cnt);
        }
        return res;
    }
}

// b. binary search tree: Time Limit Exceeded
class Solution {
    private class Node {
        // Number of integers in this node and its left subtree
        int size = 0;
        int val = Integer.MIN_VALUE;

        Node left = null;
        Node right = null;

        Node(int size, int val) {
            this.size = size;
            this.val = val;
        }
    }

    private Node root = new Node(0, 0);

    private void display() {
        Deque<Node> queue = new ArrayDeque<Node>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node node = queue.poll();
                System.out.printf("(%d,%d) ", node.size, node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            System.out.println();
        }
    }

    private void insert(Node node, int val) {
        if (node == null)
            return;

        if (val == node.val) {
            node.size++;
        }
        else if (val < node.val) {
            node.size++;
            if (node.left == null) {
                node.left = new Node(1, val);
            }
            else {
                insert(node.left, val);
            }
        }
        else {
            if (node.right == null) {
                node.right = new Node(1, val);
            }
            else {
                insert(node.right, val);
            }
        }
    }

    private int querySmaller(Node node, int val) {
        if (node == null)
            return 0;

        if (val > node.val)
            return node.size + querySmaller(node.right, val);

        return querySmaller(node.left, val);
    }

    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        List<Integer> res = new LinkedList<Integer>();

        for (int i = len - 1; i >= 0; i--) {
            res.addFirst(querySmaller(this.root, nums[i]));
            insert(this.root, nums[i]);
            // System.out.printf("insert %d:\r\n", nums[i]);
            // display();
        }

        return res;
    }
}

// c. binary search tree (modified): Time Limit Exceeded
class Solution {
    private class Node {
        int cnt = 0;        // Number of integers in this node
        int leftSize = 0;   // Number of integers in its left subtree
        int val = Integer.MIN_VALUE;

        Node left = null;
        Node right = null;

        Node(int cnt, int leftSize, int val) {
            this.cnt = cnt;
            this.leftSize = leftSize;
            this.val = val;
        }
    }

    private Node root = new Node(0, 0, 0);

    private void display() {
        Deque<Node> queue = new ArrayDeque<Node>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node node = queue.poll();
                System.out.printf("(%d,%d,%d) ", node.cnt, node.leftSize, node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            System.out.println();
        }
    }

    private int queryAndInsert(Node node, int val) {
        if (node == null)
            return 0;

        if (val == node.val) {
            node.cnt++;
            return node.leftSize;
        }
        else if (val < node.val) {
            node.leftSize++;
            if (node.left == null) {
                node.left = new Node(1, 0, val);
                return 0;
            }
            else {
                return queryAndInsert(node.left, val);
            }
        }
        else {
            if (node.right == null) {
                node.right = new Node(1, 0, val);
                return node.cnt + node.leftSize;
            }
            else {
                return node.cnt + node.leftSize + queryAndInsert(node.right, val);
            }
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        List<Integer> res = new LinkedList<Integer>();

        for (int i = len - 1; i >= 0; i--) {
            res.addFirst(queryAndInsert(this.root, nums[i]));
            // System.out.printf("insert %d:\r\n", nums[i]);
            // display();
        }

        return res;
    }
}

// d. AVL tree
class Solution {
    private class Node {
        int cnt = 0;        // Number of integers in this node
        int leftSize = 0;   // Number of integers in its left subtree
        int rightSize = 0;  // Number of integers in its right subtree
        int height = 0;
        int val = Integer.MIN_VALUE;

        Node left = null;
        Node right = null;

        Node(int cnt, int val) {
            this.cnt = cnt;
            this.val = val;
        }
    }

    private int getSize(Node node) {
        if (node == null)
            return 0;
        return node.cnt + node.leftSize + node.rightSize;
    }

    private int getHeight(Node node) {
        return node == null ? -1 : node.height;
    }

    private void updateHeight(Node node) {
        if (node != null) {
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }
    }

    private int getBalance(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    /** LL: rotateRight
     *      A      B
     *     /      / \
     *    B   -> C   A
     *   / \        /
     *  C  (D)    (D)
     */
    private Node rotateRight(Node node) {
        Node newRoot = node.left;  // newRoot: B
        Node movedSubtree = newRoot.right;  // movedSubtree: D

        newRoot.right = node;  // B.right -> A
        node.left = movedSubtree;  // A.left -> D

        node.leftSize = getSize(movedSubtree);
        newRoot.rightSize = getSize(node);

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    /** RR: rotateLeft
     *  A          B
     *   \        / \
     *    B   -> A   C
     *   / \      \
     * (D)  C     (D)
     */
    private Node rotateLeft(Node node) {
        Node newRoot = node.right;  // newRoot: B
        Node movedSubtree = newRoot.left;  // movedSubtree: D

        newRoot.left = node;  // B.left -> A
        node.right = movedSubtree;  // A.right -> D

        node.rightSize = getSize(movedSubtree);
        newRoot.leftSize = getSize(node);

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    /** LR: rotateLeftRight
     *    A          A      C
     *   /          /      / \
     *  B          C      B   A
     *   \    ->  / \  ->    /
     *    C      B   D      D
     *   / \      \
     * (E)  D     (E)
     */
    private Node rotateLeftRight(Node node) {
        node.left = rotateLeft(node.left);
        node.leftSize = getSize(node.left);
        updateHeight(node);
        return rotateRight(node);
    }

    /** RL: rotateRightLeft
     *    A      A          C
     *     \      \        / \
     *      B      C      A   B
     *     /  ->  / \  ->  \
     *    C      D   B      D
     *   / \        /
     *  D  (E)    (E)
     */
    private Node rotateRightLeft(Node node) {
        node.right = rotateRight(node.right);
        node.rightSize = getSize(node.right);
        updateHeight(node);
        return rotateLeft(node);
    }

    private Node rebalance(Node node) {
        updateHeight(node);
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) >= 0) {
                return rotateRight(node);
            } else {
                return rotateLeftRight(node);
            }
        }

        if (balance < -1) {
            if (getBalance(node.right) <= 0) {
                return rotateLeft(node);
            } else {
                return rotateRightLeft(node);
            }
        }

        return node;
    }

    private Node root = null;

    private void display() {
        if (this.root == null)
            return;

        Deque<Node> queue = new ArrayDeque<Node>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node node = queue.poll();
                System.out.printf("(%d,%d) ", node.cnt, node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            System.out.println();
        }
    }

    /**
     * ans[0]: accumulates number of elements smaller than val
     * Returns: new root after insertion and possible rotations
     */
    private Node queryAndInsert(Node node, int val, int[] ans) {
        if (node == null)
            return new Node(1, val);

        if (val == node.val) {
            node.cnt++;
            ans[0] += node.leftSize;
            return node;
        }
        // val < node.val: go further toward node.left
        else if (val < node.val) {
            node.leftSize++;
            node.left = queryAndInsert(node.left, val, ans);
        }
        // val > node.val: go further toward node.right
        else {
            node.rightSize++;
            // all nodes in this node and its left subtree are smaller
            ans[0] += node.cnt + node.leftSize;
            node.right = queryAndInsert(node.right, val, ans);
        }

        return rebalance(node);
    }

    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        List<Integer> res = new LinkedList<Integer>();
        int[] ans = new int[1];

        for (int i = len - 1; i >= 0; i--) {
            ans[0] = 0;
            this.root = queryAndInsert(this.root, nums[i], ans);
            res.addFirst(ans[0]);
            // System.out.printf("insert %d:\r\n", nums[i]);
            // display();
        }

        return res;
    }
}

// e. red-black tree
class Solution {
    private class Node {
        boolean red = true;

        int cnt = 0;        // Number of integers in this node
        int leftSize = 0;   // Number of integers in its left subtree
        int rightSize = 0;  // Number of integers in its right subtree
        int val = Integer.MIN_VALUE;

        Node left = null;
        Node right = null;
        Node parent = null;

        Node(int cnt, int val, Node parent) {
            this.cnt = cnt;
            this.val = val;
            this.parent = parent;
        }
    }

    private Node root = null;

    private int getSize(Node node) {
        if (node == null)
            return 0;
        return node.cnt + node.leftSize + node.rightSize;
    }

    /** uncle is red: recolorRight
     *        g(B)             g(R)
     *        /  \             /  \
     *     p(R)  u(R) ->    p(B)  u(B)
     *     /                /
     *  x(R)             x(R)
     */
    private void recolorRight(Node g) {
        Node p = g.left;
        Node u = g.right;

        g.red = true;
        p.red = false;
        if (u != null)
            u.red = false;
    }

    /** uncle is red: recolorLeft
     *     g(B)             g(R)
     *     /  \             /  \
     *  u(R)  p(R)    -> u(B)  p(B)
     *           \                \
     *           x(R)             x(R)
     */
    private void recolorLeft(Node g) {
        Node p = g.right;
        Node u = g.left;

        g.red = true;
        p.red = false;
        if (u != null)
            u.red = false;
    }

    /** LL: rotateRight(g)
     *        g(B)          p(B)
     *        /  \          /  \
     *     p(R)  u(B) -> x(R)  g(R)
     *     /  \                /  \
     *  x(R)   T1            T1   u(B)
     */
    private void rotateRight(Node g) {
        Node gp = g.parent;
        Node p = g.left;
        Node T1 = p.right;

        // g.parent <-> p
        p.parent = gp;
        if (gp == null)
            this.root = p;
        else if (gp.left == g)
            gp.left = p;
        else
            gp.right = p;

        // p <-> g
        p.right = g;
        g.parent = p;

        // g <-> T1
        g.left = T1;
        if (T1 != null)
            T1.parent = g;

        // recolor
        g.red = true;
        p.red = false;

        // maintain subtree sizes
        g.leftSize = getSize(T1);
        p.rightSize = getSize(g);
    }

    /** RR: rotateLeft(g)
     *     g(B)                p(B)
     *     /  \                /  \
     *  u(B)  p(R)    ->    g(R)  x(R)
     *        /  \          /  \
     *      T1   x(R)    u(B)   T1
     */
    private void rotateLeft(Node g) {
        Node gp = g.parent;
        Node p = g.right;
        Node T1 = p.left;

        // g.parent <-> p
        p.parent = gp;
        if (gp == null)
            this.root = p;
        else if (gp.left == g)
            gp.left = p;
        else
            gp.right = p;

        // p <-> g
        p.left = g;
        g.parent = p;

        // g <-> T1
        g.right = T1;
        if (T1 != null)
            T1.parent = g;

        // recolor
        g.red = true;
        p.red = false;

        // maintain subtree sizes
        g.rightSize = getSize(T1);
        p.leftSize = getSize(g);
    }

    /** LR: rotateLeft(p), then rotateRight(g)
     *     g(B)            g(B)          x(B)
     *     /  \            /  \          /  \
     *  p(R)  u(B)      x(R)  u(B)    p(R)  g(R)
     *     \       ->   /  \       ->    \  /  \
     *     x(R)       p(R)  T2          T1  T2 u(B)
     *     /  \          \
     *   T1    T2         T1
     */
    private void rotateLeftRight(Node g) {
        rotateLeft(g.left);
        g.leftSize = getSize(g.left);
        rotateRight(g);
    }

    /** RL: rotateRight(p), then rotateLeft(g)
     *     g(B)          g(B)                x(B)
     *     /  \          /  \                /  \
     *  u(B)  p(R)    u(B)  x(R)          g(R)  p(R)
     *        /    ->       /  \    ->    /  \  /
     *     x(R)           T2   p(R)    u(B) T2  T1
     *     /  \                /
     *   T2    T1            T1
     */
    private void rotateRightLeft(Node g) {
        rotateRight(g.right);
        g.rightSize = getSize(g.right);
        rotateLeft(g);
    }

    /**
     * node: newly inserted RED node.
     */
    private void rebalance(Node node) {
        Node x = node;

        while (x != root && x.parent.red == true) {
            Node p = x.parent; // parent
            Node g = p.parent; // grandparent

            if (g == null) {
                break;
            }

            // Case 1: parent is left child of grandparent
            if (p == g.left) {
                Node u = g.right; // uncle

                // Case 1.1: uncle is red -> recolor
                if (u != null && u.red == true) {
                    recolorRight(g);
                    x = g;
                } else {
                    // Case 1.2: LL (node is left child of parent)
                    if (x == p.left)
                        rotateRight(g);
                        // Case 1.3: LR (node is right child of parent)
                    else
                        rotateLeftRight(g);
                    break;
                }
            }
            // Case 2: parent is right child of grandparent
            else {
                Node u = g.left; // uncle

                // Case 2.1: uncle is red -> recolor
                if (u != null && u.red == true) {
                    recolorLeft(g);
                    x = g;
                } else {
                    // Case 2.2: RR (node is right child of parent)
                    if (x == p.right)
                        rotateLeft(g);
                        // Case 2.3: RL (node is left child of parent)
                    else
                        rotateRightLeft(g);
                    break;
                }
            }
        }

        // After recoloring, root might become red.
        // In this case, increase the black height of the whole tree by 1.
        this.root.red = false;
    }

    private void display() {
        if (this.root == null)
            return;

        Deque<Node> queue = new ArrayDeque<Node>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node node = queue.poll();
                System.out.printf("(%d,%d) ", node.cnt, node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            System.out.println();
        }
    }

    /**
     * Returns: number of elements smaller than val
     */
    private int queryAndInsert(int val) {
        if (this.root == null) {
            this.root = new Node(1, val, null);
            root.red = false; // root is black
            return 0;
        }

        Node cur = this.root;
        Node prev = null;
        int res = 0;

        while (cur != null) {
            prev = cur;

            if (val == cur.val) {
                cur.cnt++;
                res += cur.leftSize;
                return res;
                // val < node.val: go further toward node.left
            } else if (val < cur.val) {
                cur.leftSize++;
                cur = cur.left;
                // val > node.val: go further toward node.right
            } else {
                cur.rightSize++;
                // all nodes in this node and its left subtree are smaller
                res += cur.leftSize + cur.cnt;
                cur = cur.right;
            }
        }

        Node newNode = new Node(1, val, prev);
        if (val < prev.val)
            prev.left = newNode;
        else
            prev.right = newNode;

        rebalance(newNode);

        return res;
    }

    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        List<Integer> res = new LinkedList<Integer>();

        for (int i = len - 1; i >= 0; i--) {
            res.addFirst(queryAndInsert(nums[i]));
            // System.out.printf("insert %d:\r\n", nums[i]);
            // display();
        }

        return res;
    }
}

// f. array-based segment tree: top-down query, bottom-up insert
class Solution {
    private int[] tree = null;
    private int size = 0;

    private Map<Integer, Integer> unique(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        if (len == 0)
            return map;
        map.put(nums[0], 0);

        int idx = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i - 1] < nums[i])
                map.put(nums[i], idx++);
        }
        return map;
    }

    private void insert(int idx) {
        if (idx == 0)
            return;

        this.tree[idx]++;
        insert(idx / 2);
    }

    /**
     * Returns: sum of this.tree[this.size..(this.size + x))
     */
    private int query(int idx, int x, int left, int right) {
        if (x <= left)
            return 0;

        if (right < x)
            return this.tree[idx];

        int mid = (left + right) >> 1;
        return query(idx << 1, x, left, mid) + query(idx << 1 | 1, x, mid + 1, right);
    }

    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        Map<Integer, Integer> map = unique(sorted);

        this.size = 1;
        int mapSize = map.size();
        while(this.size < mapSize)
            this.size <<= 1;
        this.tree = new int[2 * this.size];

        // val -> this.tree[this.size + map.get(val)]
        List<Integer> res = new LinkedList<Integer>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int val = nums[i];
            int x = map.get(val);
            res.addFirst(query(1, x, 0, this.size - 1));
            insert(this.size + x);
        }
        return res;
    }
}

// g. array-based segment tree (modified): bottom-up query and insert
class Solution {
    private int[] tree = null;
    private int size = 0;

    private Map<Integer, Integer> unique(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        if (len == 0)
            return map;
        map.put(nums[0], 0);

        int idx = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i - 1] < nums[i])
                map.put(nums[i], idx++);
        }
        return map;
    }

    /**
     * Returns: sum of this.tree[this.size..(this.size + x))
     */
    private int queryAndInsert(int x) {
        int pos = this.size + x;
        int res = 0;

        while (pos > 0) {
            if ((pos & 1) == 1) {
                res += tree[pos - 1]; // left sibling
            }
            tree[pos]++;
            pos >>= 1;
        }

        return res;
    }

    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        Map<Integer, Integer> map = unique(sorted);

        this.size = 1;
        int mapSize = map.size();
        while(this.size < mapSize)
            this.size <<= 1;
        this.tree = new int[2 * this.size];

        // val -> this.tree[this.size + map.get(val)]
        List<Integer> res = new LinkedList<Integer>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int x = map.get(nums[i]);
            res.addFirst(queryAndInsert(x));
        }
        return res;
    }
}

// h. Fenwick tree (BIT)
class Solution {
    private int[] tree = null;

    private static int lowbit(int i) {
        return i & (-i);
    }

    private void insert(int index) {
        int i = index + 1;
        while (i < this.tree.length) {
            this.tree[i]++;
            i = i + lowbit(i);
        }
    }

    private int query(int index) {
        int res = 0;

        int i = index + 1;
        while (i >= 1) {
            res += this.tree[i];
            i = i - lowbit(i);
        }

        return res;
    }

    private Map<Integer, Integer> unique(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        if (len == 0)
            return map;
        map.put(nums[0], 0);

        int idx = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i - 1] < nums[i])
                map.put(nums[i], idx++);
        }
        return map;
    }

    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        Map<Integer, Integer> map = unique(sorted);

        this.tree = new int[map.size() + 1];

        // val -> this.tree[map.get(val)]
        List<Integer> res = new LinkedList<Integer>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int x = map.get(nums[i]);
            res.addFirst(query(x - 1));
            insert(x);
        }
        return res;
    }
}