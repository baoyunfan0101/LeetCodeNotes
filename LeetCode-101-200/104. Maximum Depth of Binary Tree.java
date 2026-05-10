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

// a. DFS
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        else if (root.left == null)
            return maxDepth(root.right) + 1;
        else if (root.right == null)
            return maxDepth(root.left) + 1;
        else
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

// b. BFS
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int cnt = 0;
        List<TreeNode> queue = new LinkedList<TreeNode>();
        queue.addLast(root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            if (len == 0)
                break;

            for (int i = 0; i < len; i++) {
                TreeNode node = queue.removeFirst();
                if (node.left != null)
                    queue.addLast(node.left);
                if (node.right != null)
                    queue.addLast(node.right);
            }

            cnt++;
        }

        return cnt;
    }
}