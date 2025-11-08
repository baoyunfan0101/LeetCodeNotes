// original version: backtracking
// in each iteration, choose whether to include the current element or not
class Solution {
    private int[] nums;
    private int len;
    private List<Integer> subset;
    private List<List<Integer>> res;

    private void backtrack(int idx) {
        if (idx == len) {
            res.add(new ArrayList<Integer>(subset));
            return;
        }

        // ignore nums[idx]
        backtrack(idx + 1);

        // choose nums[idx]
        subset.addLast(nums[idx]);
        backtrack(idx + 1);
        subset.removeLast();
    }

    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        len = nums.length;
        subset = new ArrayList<Integer>();
        res = new ArrayList<List<Integer>>();
        backtrack(0);
        return res;
    }
}