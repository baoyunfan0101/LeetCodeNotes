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

// a. BFS
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<List<Integer>>();

        if (root == null)
            return resList;

        List<TreeNode> queue = new LinkedList<TreeNode>();
        queue.addLast(root);

        int cnt = -1;
        while (true) {
            cnt++;

            int n = queue.size();
            if (n == 0)
                break;

            List<Integer> levelList = new ArrayList<Integer>();

            for (int i = 0; i < n; i++) {
                TreeNode node = queue.removeFirst();

                levelList.addLast(node.val);

                if (node.left != null)
                    queue.addLast(node.left);
                if (node.right != null)
                    queue.addLast(node.right);
            }

            if (cnt % 2 != 0)
                Collections.reverse(levelList);
            resList.add(levelList);
        }

        return resList;
    }
}

// b. BFS (modified)
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<List<Integer>>();

        if (root == null)
            return resList;

        List<TreeNode> queue = new LinkedList<TreeNode>();
        queue.addLast(root);

        int cnt = 0;
        while (true) {
            cnt++;

            int n = queue.size();
            if (n == 0)
                break;

            List<Integer> levelList = new ArrayList<Integer>();

            for (int i = 0; i < n; i++) {
                TreeNode node = queue.removeFirst();

                if (cnt % 2 != 0)
                    levelList.addLast(node.val);
                else
                    levelList.addFirst(node.val);

                if (node.left != null)
                    queue.addLast(node.left);
                if (node.right != null)
                    queue.addLast(node.right);
            }

            resList.add(levelList);
        }

        return resList;
    }
}