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

// a. recursion
class Solution {
    private boolean isSymmetricChildren(TreeNode left, TreeNode right) {
        if (left == null && right != null)
            return false;
        else if (left != null && right == null)
            return false;
        else if (left == null && right == null)
            return true;
        else if (left.val != right.val)
            return false;
        else
            return isSymmetricChildren(left.left, right.right)
                    && isSymmetricChildren(left.right, right.left);
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        else
            return isSymmetricChildren(root.left, root.right);
    }
}