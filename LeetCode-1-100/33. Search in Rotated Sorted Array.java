// original version: binary search
// a special binary search where either the left side or the right side is monotonic
class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int index = (left + right) >> 1;
            //System.out.format("left = %d, right = %d, index = %d\r\n", left, right, index);
            if (nums[index] == target) {
                return index;
            }
            // left section is monotone increasing
            else if (nums[0] <= nums[index]) {
                // target is in left section
                if (nums[0] <= target && target <= nums[index])
                    right = index - 1;
                // target is in right section
                else
                    left = index + 1;
            }
            // right section is monotone increasing
            else {
                // target is in left section
                if (nums[index] <= target && target <= nums[nums.length - 1])
                    left = index + 1;
                // target is in right section
                else
                    right = index - 1;
            }
        }
        return -1;
    }
}