/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// a. sentinel
class BSTIterator {
    Deque<TreeNode> ancestors = new ArrayDeque<TreeNode>();
    TreeNode ptr = null;

    public BSTIterator(TreeNode root) {
        if (root == null) {
            this.ptr = new TreeNode(-1, null, null);
        }

        TreeNode p = root;
        while (p.left != null) {
            this.ancestors.push(p);
            p = p.left;
        }

        this.ptr = new TreeNode(p.val - 1, null, null);
        this.ancestors.push(p);
        p.left = this.ptr;
    }

    public int next() {
        if (this.ptr.right != null) {
            TreeNode p = this.ptr.right;

            while (p.left != null) {
                this.ancestors.push(p);
                p = p.left;
            }

            this.ptr = p;
            return this.ptr.val;
        }
        else if (!this.ancestors.isEmpty()) {
            this.ptr = this.ancestors.pop();
            return this.ptr.val;
        }
        else {
            return -1;
        }
    }

    public boolean hasNext() {
        return this.ptr.right != null || !this.ancestors.isEmpty();
    }
}

// b. non-sentinel
class BSTIterator {
    Deque<TreeNode> ancestors = new ArrayDeque<TreeNode>();
    TreeNode ptr = null;

    public BSTIterator(TreeNode root) {
        if (root == null) {
            this.ptr = new TreeNode(-1, null, null);
            return;
        }

        TreeNode p = root;
        while (p.left != null) {
            this.ancestors.push(p);
            p = p.left;
        }

        this.ptr = new TreeNode(p.val - 1, null, null);
        this.ancestors.push(p);
    }

    public int next() {
        if (this.ptr.right != null) {
            TreeNode p = this.ptr.right;

            while (p.left != null) {
                this.ancestors.push(p);
                p = p.left;
            }

            this.ptr = p;
            return this.ptr.val;
        }
        else if (!this.ancestors.isEmpty()) {
            this.ptr = this.ancestors.pop();
            return this.ptr.val;
        }
        else {
            return -1;
        }
    }

    public boolean hasNext() {
        return this.ptr.right != null || !this.ancestors.isEmpty();
    }
}

// c. stack
class BSTIterator {
    Deque<TreeNode> ancestors = new ArrayDeque<TreeNode>();

    public BSTIterator(TreeNode root) {
        TreeNode p = root;
        while (p != null) {
            this.ancestors.push(p);
            p = p.left;
        }
    }

    public int next() {
        if (this.ancestors.isEmpty())
            return -1;

        TreeNode p = this.ancestors.pop();
        int res = p.val;
        if (p.right != null) {
            p = p.right;
            while (p != null) {
                this.ancestors.push(p);
                p = p.left;
            }
        }
        return res;
    }

    public boolean hasNext() {
        return !this.ancestors.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */