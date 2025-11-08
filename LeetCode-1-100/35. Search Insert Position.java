// original version: binary search
// perform binary search, and return the left pointer (the larger position)
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int index = (left + right) >> 1;
            if (nums[index] == target)
                return index;
            else if (nums[index] < target)
                left = index + 1;
            else
                right = index - 1;
        }
        return left;
    }
}