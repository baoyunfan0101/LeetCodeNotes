// a. segment tree: bottom-up build, bottom-up update, top-down query
class NumArray {
    private class TreeNode {
        int lower = 0;
        int upper = -1;
        int val = 0;

        TreeNode parent = null;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int lower, int upper, int val) {
            this.lower = lower;
            this.upper = upper;
            this.val = val;
        }

        TreeNode(int lower, int upper, int val, TreeNode left, TreeNode right) {
            this.lower = lower;
            this.upper = upper;
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode root = null;
    private TreeNode[] leaves = null;

    private void buildTree(int[] nums) {
        this.leaves = new TreeNode[nums.length];
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>();

        for (int i = 0; i < nums.length; i++) {
            this.leaves[i] = new TreeNode(i, i, nums[i]);
            queue.offer(this.leaves[i]);
        }

        while (queue.size() > 1) {
            int len = queue.size();
            for (int i = 0; i < len - 1; i += 2) {
                TreeNode n1 = queue.poll();
                TreeNode n2 = queue.poll();
                TreeNode parent = new TreeNode(n1.lower, n2.upper, n1.val + n2.val, n1, n2);
                n1.parent = parent;
                n2.parent = parent;
                queue.offer(parent);
            }
            if (len % 2 == 1) {
                TreeNode n = queue.peek();
                queue.offer(queue.poll());
            }
        }

        this.root = queue.poll();
    }

    private void updateTree(int index, int val) {
        TreeNode node = this.leaves[index];
        int diff = node.val - val;

        while (node != null) {
            node.val -= diff;
            node = node.parent;
        }
    }

    private int DFS(TreeNode node, int queryL, int queryR) {
        if (node == null || queryL > queryR)
            return 0;

        // System.out.printf("node:(%d,%d) query:(%d,%d)\r\n", node.lower, node.upper, queryL, queryR);

        if (node.lower == queryL && node.upper == queryR) {
            return node.val;
        }

        if (node.left == null || node.right == null)
            return 0;
        int mid = node.left.upper;
        if (mid + 1 <= queryL)
            return DFS(node.right, queryL, queryR);
        else if (queryR <= mid)
            return DFS(node.left, queryL, queryR);
        else
            return DFS(node.left, queryL, mid) + DFS(node.right, mid + 1, queryR);
    }

    private void display() {
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                System.out.printf("(%d,%d)%d ", node.lower, node.upper, node.val);
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

    public NumArray(int[] nums) {
        buildTree(nums);
        // System.out.println("buildTree:");
        // display();
    }

    public void update(int index, int val) {
        updateTree(index, val);
        // System.out.printf("updateTree (%d, %d):\r\n", index, val);
        // display();
    }

    public int sumRange(int left, int right) {
        return DFS(this.root, left, right);
    }
}

// b. segment tree: top-down build, bottom-up update, top-down query
class NumArray {
    private class TreeNode {
        int lower = 0;
        int upper = -1;
        int val = 0;

        TreeNode parent = null;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int lower, int upper, TreeNode parent) {
            this.lower = lower;
            this.upper = upper;
            this.parent = parent;
        }

        TreeNode(int lower, int upper, int val, TreeNode parent) {
            this.lower = lower;
            this.upper = upper;
            this.val = val;
            this.parent = parent;
        }
    }

    private TreeNode root = null;
    private TreeNode[] leaves = null;

    private TreeNode buildTree(int[] nums, TreeNode[] leaves, int lower, int upper, TreeNode parent) {
        if (lower == upper) {
            leaves[lower] = new TreeNode(lower, upper, nums[lower], parent);
            return leaves[lower];
        }

        TreeNode node = new TreeNode(lower, upper, parent);

        int mid = (lower + upper) / 2;
        node.left = buildTree(nums, leaves, lower, mid, node);
        node.right = buildTree(nums, leaves, mid + 1, upper, node);
        node.val = node.left.val + node.right.val;

        return node;
    }

    private void updateTree(int index, int val) {
        TreeNode node = this.leaves[index];
        int diff = node.val - val;

        while (node != null) {
            node.val -= diff;
            node = node.parent;
        }
    }

    private int DFS(TreeNode node, int queryL, int queryR) {
        if (node == null || queryL > queryR)
            return 0;

        // System.out.printf("node:(%d,%d) query:(%d,%d)\r\n", node.lower, node.upper, queryL, queryR);

        if (node.lower == queryL && node.upper == queryR) {
            return node.val;
        }

        int mid = (node.lower + node.upper) / 2;
        if (mid + 1 <= queryL)
            return DFS(node.right, queryL, queryR);
        else if (queryR <= mid)
            return DFS(node.left, queryL, queryR);
        else
            return DFS(node.left, queryL, mid) + DFS(node.right, mid + 1, queryR);
    }

    private void display() {
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                System.out.printf("(%d,%d)%d ", node.lower, node.upper, node.val);
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

    public NumArray(int[] nums) {
        this.leaves = new TreeNode[nums.length];
        this.root = buildTree(nums, leaves, 0, nums.length - 1, null);
        // System.out.println("buildTree:");
        // display();
    }

    public void update(int index, int val) {
        updateTree(index, val);
        // System.out.printf("updateTree (%d, %d):\r\n", index, val);
        // display();
    }

    public int sumRange(int left, int right) {
        return DFS(this.root, left, right);
    }
}

// c. array-based segment tree: top-down build, top-down update, top-down query
class NumArray {
    private int[] tree = null;
    private int len = 0;

    private void buildTree(int[] nums, int treeIdx, int lower, int upper) {
        if (lower == upper) {
            this.tree[treeIdx] = nums[lower];
            return;
        }

        int mid = (lower + upper) / 2, lChild = treeIdx << 1, rChild = lChild + 1;
        buildTree(nums, lChild, lower, mid);
        buildTree(nums, rChild, mid + 1, upper);

        this.tree[treeIdx] = this.tree[lChild] + this.tree[rChild];
    }

    private void updateTree(int treeIdx, int lower, int upper, int index, int val) {
        if (index == lower && index == upper) {
            this.tree[treeIdx] = val;
            return;
        }

        int mid = (lower + upper) / 2, lChild = treeIdx << 1, rChild = lChild + 1;
        if (index <= mid)
            updateTree(lChild, lower, mid, index, val);
        else
            updateTree(rChild, mid + 1, upper, index, val);

        this.tree[treeIdx] = this.tree[lChild] + this.tree[rChild];
    }

    private int DFS(int treeIdx, int lower, int upper, int queryL, int queryR) {
        if (treeIdx == 0 || queryL > queryR)
            return 0;

        if (lower == queryL && upper == queryR) {
            return this.tree[treeIdx];
        }

        int mid = (lower + upper) / 2, lChild = treeIdx << 1, rChild = lChild + 1;
        if (mid + 1 <= queryL)
            return DFS(rChild, mid + 1, upper, queryL, queryR);
        else if (queryR <= mid)
            return DFS(lChild, lower, mid, queryL, queryR);
        else
            return DFS(lChild, lower, mid, queryL, mid) + DFS(rChild, mid + 1, upper, mid + 1, queryR);
    }

    private void display() {
        Deque<Integer> queue = new ArrayDeque<Integer>();
        queue.offer(1);

        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Integer treeIdx = queue.poll(), lChild = treeIdx << 1, rChild = lChild + 1;
                System.out.printf("%d ", this.tree[treeIdx]);

                if (lChild < this.tree.length) {
                    queue.offer(lChild);
                }
                if (rChild < this.tree.length) {
                    queue.offer(rChild);
                }
            }
            System.out.println();
        }
    }

    public NumArray(int[] nums) {
        // Pad the array to the nearest power of two
        int size = 1;
        this.len = nums.length;
        while (size < this.len) {
            size <<= 1;
        }
        this.tree = new int[2 * size];

        buildTree(nums, 1, 0, this.len - 1);
        // System.out.println("buildTree:");
        // display();
    }

    public void update(int index, int val) {
        updateTree(1, 0, this.len - 1, index, val);
        // System.out.printf("updateTree (%d, %d):\r\n", index, val);
        // display();
    }

    public int sumRange(int left, int right) {
        return DFS(1, 0, this.len - 1, left, right);
    }
}

// d. Fenwick tree (BIT)
class NumArray {
    private int[] nums = null;
    private int[] tree = null;

    private static int lowbit(int i) {
        return i & (-i);
    }

    private void buildTree() {
        for (int i = 0; i < this.nums.length; i++) {
            this.tree[i + 1] = nums[i];
        }

        for (int i = 1; i <= this.nums.length; i++) {
            int parent = i + lowbit(i);
            if (parent <= this.nums.length)
                this.tree[parent] += this.tree[i];
        }
    }

    private void updateTree(int index, int val) {
        int diff = this.nums[index] - val;
        this.nums[index] = val;

        int i = index + 1;
        while (i <= this.nums.length) {
            this.tree[i] -= diff;
            i = i + lowbit(i);
        }
    }

    private int prefix(int index) {
        int res = 0;

        int i = index + 1;
        while (i >= 1) {
            res += this.tree[i];
            i = i - lowbit(i);
        }

        return res;
    }

    private int query(int queryL, int queryR) {
        return prefix(queryR) - prefix(queryL - 1);
    }

    private void display() {
        for (int i = 1; i <= this.nums.length; i++) {
            System.out.printf("tree[%d] -> [%d, %d]: %d\r\n", i, i - lowbit(i) + 1, i, this.tree[i]);
        }
    }

    public NumArray(int[] nums) {
        this.nums = nums;
        this.tree = new int[nums.length + 1];
        buildTree();
        // System.out.println("buildTree:");
        // display();
    }

    public void update(int index, int val) {
        updateTree(index, val);
        // System.out.printf("updateTree (%d, %d):\r\n", index, val);
        // display();
    }

    public int sumRange(int left, int right) {
        return query(left, right);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */