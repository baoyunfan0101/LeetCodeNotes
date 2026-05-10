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

// a. naive BFS
class Solution {
    private class Pair {
        TreeNode node;
        int level;

        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<List<Integer>>();

        if (root == null)
            return resList;

        List<Pair> queue = new LinkedList<Pair>();
        queue.addLast(new Pair(root, 0));

        List<Integer> levelList = new ArrayList<Integer>();
        int currLevel = 0;

        while (!queue.isEmpty()) {
            Pair p = queue.removeFirst();
            TreeNode node = p.node;
            int level = p.level;

            // insert into result list
            if (level > currLevel) {
                currLevel++;
                resList.add(levelList);
                levelList = new ArrayList<Integer>();
                levelList.add(node.val);
            }
            else {
                levelList.add(node.val);
            }

            // update queue
            if (node.left != null)
                queue.addLast(new Pair(node.left, level + 1));
            if (node.right != null)
                queue.addLast(new Pair(node.right, level + 1));
        }

        if (!levelList.isEmpty())
            resList.add(levelList);

        return resList;
    }
}

// b. BFS
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<List<Integer>>();

        if (root == null)
            return resList;

        List<TreeNode> queue = new LinkedList<TreeNode>();
        queue.addLast(root);

        while (true) {
            int n = queue.size();
            if (n == 0)
                break;

            List<Integer> levelList = new ArrayList<Integer>();

            for (int i = 0; i < n; i++) {
                TreeNode node = queue.removeFirst();

                // insert into result list
                levelList.add(node.val);

                // update queue
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