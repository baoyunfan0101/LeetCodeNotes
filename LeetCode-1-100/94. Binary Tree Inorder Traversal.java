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

// original version
// preorder, inorder, postorder: root appears on the left, in the middle, on the right
class Solution {
    List<Integer> res;

    private void dfs(TreeNode node) {
        if (node == null)
            return;
        dfs(node.left);
        res.add(node.val);
        dfs(node.right);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        res = new ArrayList<Integer>();
        dfs(root);
        return res;
    }
}