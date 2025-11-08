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

// original version: recursion
// handle edge cases: Integer.MIN_VALUE & Integer.MAX_VALUE
// use Integer (object) instead of int, where null indicates no bound
class Solution {
    private boolean recursive(TreeNode root, Integer min, Integer max) {
        if (root == null)
            return true;

        int val = root.val;
        if ((min == null || min < val) && (max == null || max > val))
            return recursive(root.left, min, val) && recursive(root.right, val, max);
        else
            return false;
    }

    public boolean isValidBST(TreeNode root) {
        return recursive(root, null, null);
    }
}