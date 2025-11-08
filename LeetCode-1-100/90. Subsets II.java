/* original version: backtracking
// first, build a manual hash map to record the occurrences of each value
// this removes the sequence order and retains only the values
// in each iteration, choose either the current value (if available) or the next one
class Solution {
    static final int VALUE_NUM = 21;
    private int[] map;
    private List<List<Integer>> res;
    private List<Integer> set;

    private void backtrack(int idx) {
        if (idx == VALUE_NUM) {
            res.add(new ArrayList<Integer>(set));
            return;
        }
        if (map[idx] > 0) {
            map[idx]--;
            set.addLast(idx - 10);
            backtrack(idx);
            set.removeLast();
            map[idx]++;
        }
        backtrack(idx + 1);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        map = new int[VALUE_NUM];
        for (int i : nums)
            map[i + 10]++;

        set = new ArrayList<Integer>();
        res = new ArrayList<List<Integer>>();
        backtrack(0);
        return res;
    }
}
end original version */

// method 1: enumerate binary numbers
// for a set of length len, each integer in [0, 1 << len) represents a subset via its binary bits
// sort the array, and for duplicates, choose only from their first occurrence or skip them
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        label: for (int i = 0; i < (1 << len); i++) {
            int choices = i;
            List<Integer> set = new ArrayList<Integer>();
            boolean choosePre = true;
            for (int pos = 0; pos < len; pos++) {
                if (choices == 0)
                    break;
                if (choices % 2 == 1) {
                    if (!choosePre && nums[pos] == nums[pos - 1])
                        continue label;
                    choosePre = true;
                    set.add(nums[pos]);
                } else {
                    choosePre = false;
                }
                choices >>= 1;
            }
            res.add(set);
        }

        return res;
    }
}