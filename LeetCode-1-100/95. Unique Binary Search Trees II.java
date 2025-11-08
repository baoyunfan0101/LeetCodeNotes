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
// in each iteration, try every possible root and recurse on left and right subtrees
class Solution {
    private List<TreeNode> generate(int min, int max) {
        List<TreeNode> treeList = new ArrayList<TreeNode>();

        if (min > max) {
            treeList.add(null);
            return treeList;
        } else if (min == max) {
            treeList.add(new TreeNode(min, null, null));
            return treeList;
        }

        for (int mid = min; mid <= max; mid++) {
            List<TreeNode> leftTreeList = generate(min, mid - 1);
            List<TreeNode> rightTreeList = generate(mid + 1, max);
            for (TreeNode leftRoot : leftTreeList)
                for (TreeNode rightRoot : rightTreeList)
                    treeList.add(new TreeNode(mid, leftRoot, rightRoot));
        }
        return treeList;
    }

    public List<TreeNode> generateTrees(int n) {
        return generate(1, n);
    }
}