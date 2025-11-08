// original version: binary search
// first pass: when mid >= target, move left (while recording the first glimpse of target, which is the rightmost position)
// second pass: start from that position, and when mid <= target, move right
class Solution {
    public int[] searchRange(int[] nums, int target) {
        // find left end
        int left = 0, right = nums.length - 1;
        int leftEnd = 0, rightPos = -1;
        while (left <= right) {
            int index = (left + right) >> 1;
            if (nums[index] == target) {
                rightPos = rightPos == -1 ? index : rightPos;
                leftEnd = index;
                right = index - 1;
            } else if (nums[index] < target)
                left = index + 1;
            else
                right = index - 1;
        }
        if (rightPos == -1)
            return new int[] { -1, -1 };

        // find right end
        left = rightPos;
        right = nums.length - 1;
        int rightEnd = 0;
        while (left <= right) {
            int index = (left + right) >> 1;
            if (nums[index] == target) {
                rightEnd = index;
                left = index + 1;
            } else
                right = index - 1;
        }

        return new int[] { leftEnd, rightEnd };
    }
}