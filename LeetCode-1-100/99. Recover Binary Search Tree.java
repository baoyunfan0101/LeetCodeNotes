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

/* silly verison: brute force
// reuse problem #98. Validate Binary Search Tree
// try swapping each pair of nodes and check validity after each swap
class Solution {
    private TreeNode[] nodes = new TreeNode[1000];
    private int count = 0;

    // problem #98. Validate Binary Search Tree
    private boolean isValidBST_DFS(TreeNode root, Integer min, Integer max) {
        if (root == null)
            return true;

        int val = root.val;
        if ((min == null || min < val) && (max == null || max > val))
            return isValidBST_DFS(root.left, min, val) && isValidBST_DFS(root.right, val, max);
        else
            return false;
    }

    private boolean isValidBST(TreeNode root) {
        return isValidBST_DFS(root, null, null);
    }

    // append all nodes into TreeNode[] nodes
    private void DFS(TreeNode root) {
        if (root != null) {
            nodes[count++] = root;
            DFS(root.left);
            DFS(root.right);
        }

    }

    public void recoverTree(TreeNode root) {
        DFS(root);

        for (int i = 0; i < count - 1; i++)
            for (int j = i + 1; j < count; j++) {
                // swap their values
                int tVal = nodes[i].val;
                nodes[i].val = nodes[j].val;
                nodes[j].val = tVal;
                if (isValidBST(root))
                    return;
                nodes[j].val = nodes[i].val;
                nodes[i].val = tVal;
            }
    }
}
end silly verison */

/* method 1: inorder traversal
// for a valid BST, the inorder traversal must be strictly increasing
// find all indices i where inorder[i].val < inorder[i - 1].val:
// 1. if there are two such indices i and j, swap inorder[i - 1] and inorder[j]
// 2. if there is only one, swap inorder[i - 1] and inorder[i]
class Solution {
    private TreeNode[] inorder = new TreeNode[1000];
    private int count = 0;

    // append all nodes into TreeNode[] nodes in inorder
    private void DFS(TreeNode root) {
        if (root == null)
            return;

        DFS(root.left);
        inorder[count++] = root;
        DFS(root.right);
    }

    public void recoverTree(TreeNode root) {
        DFS(root);

        int[] invalidIdx = new int[]{-1, -1};

        for (int i = 1; i < count; i++) {
            if (inorder[i].val < inorder[i - 1].val) {
                invalidIdx[1] = i;
                if (invalidIdx[0] < 0)
                    invalidIdx[0] = i - 1;
                else
                    break;
            }
        }

        int tVal = inorder[invalidIdx[0]].val;
        inorder[invalidIdx[0]].val = inorder[invalidIdx[1]].val;
        inorder[invalidIdx[1]].val = tVal;
    }
}
end method 1 */

// method 2: inorder traversal without storing the entire sequence
// similar to the previous version
// identify the two invalid nodes during the inorder traversal process
class Solution {
    private TreeNode lastNode = null;
    private TreeNode[] invalid = new TreeNode[2];

    // traverse all nodes and return if two invalid nodes are finded
    private boolean DFS(TreeNode root) {
        if (root == null)
            return false;

        if (DFS(root.left))
            return false;

        if (lastNode == null)
            lastNode = root;
        else if (root.val < lastNode.val) {
            invalid[1] = root;
            if (invalid[0] == null)
                invalid[0] = lastNode;
            else
                return true;
        }
        lastNode = root;

        if (DFS(root.right))
            return false;

        return false;
    }

    public void recoverTree(TreeNode root) {
        DFS(root);

        int tVal = invalid[0].val;
        invalid[0].val = invalid[1].val;
        invalid[1].val = tVal;
    }
}