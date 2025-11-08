/* original version
// sort the array and traverse it from the beginning
// time complexity: O(n log n)
class Solution {
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < res)
                continue;
            else if (nums[i] == res)
                res++;
            else
                break;
        }
        return res;
    }
}
end original version */

/* method 1: hash map
// build a hash map based on the existing array:
// 1. assign an invalid large number to all negative integers to make all values non-negative
// 2. if positive integer i exists, set `nums[i] *= -1`
// 3. find the first positive integer from the beginning; its index minus 1 indicates the result
class Solution {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length, impossibleNum = len + 1;
        // make all numbers greater than or equal to 0
        for (int i = 0; i < len; i++)
            if (nums[i] <= 0)
                nums[i] = impossibleNum;
        // mark existed numbers
        for (int i = 0; i < len; i++) {
            int temp = Math.abs(nums[i]);
            if (1 <= temp && temp <= len && nums[temp - 1] > 0)
                nums[temp - 1] *= -1;
        }
        //System.out.println(Arrays.toString(nums));
        // find answer
        for (int i = 0; i < len; i++)
            if (nums[i] >= 0)
                return i + 1;
        return len + 1;
    }
}
end method 1 */

// method 2: swap
// core idea: the only correct position for a positive integer `i` is at `nums[i - 1]`
// for each integer `nums[i]` in the array (denoted as `val`):
// 1. if it's invalid (`val <= 0 || val > nums.length`), don’t bother with it
// 2. if it's valid:
//     2.1. place it at `nums[val - 1]`, unless `nums[val - 1]` already equals `val`
//     2.2. repeat the process for the original integer at `nums[val - 1]`
// the loop will terminate in at most `nums.length` iterations, after which all values are in place
// find the first integer that doesn’t match its index; it indicates the result
class Solution {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // if 1 <= nums[i] <= len, make nums[nums[i] - 1] = nums[i]
            int val = nums[i];
            while (1 <= val && val <= len && nums[val - 1] != val) {
                int temp = nums[val - 1];
                nums[val - 1] = val;
                val = temp;
            }
        }
        //System.out.println(Arrays.toString(nums));
        for (int i = 0; i < len; i++)
            if (nums[i] != i + 1)
                return i + 1;
        return len + 1;
    }
}