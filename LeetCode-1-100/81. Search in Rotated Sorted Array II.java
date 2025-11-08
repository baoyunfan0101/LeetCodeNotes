/* original version: recursive binary search
// if nums[left] == nums[mid] == nums[right], search both sides
class Solution {
    private int[] nums;
    private int target;

    private boolean binarySearch(int left, int right) {
        while (left <= right) {
            int idx = (left + right) >> 1;
            if (nums[idx] == target)
                return true;
            else if (nums[left] < nums[idx]) {
                if (nums[left] == target)
                    return true;
                else if (nums[left] < target && target < nums[idx])
                    return binarySearch(left, idx - 1);
                else
                    return binarySearch(idx + 1, right);
            } else if (nums[idx] < nums[right]) {
                if (nums[right] == target)
                    return true;
                else if (nums[idx] < target && target < nums[right])
                    return binarySearch(idx + 1, right);
                else
                    return binarySearch(left, idx - 1);
            } else
                return binarySearch(left, idx - 1) || binarySearch(idx + 1, right);
        }
        return false;
    }

    public boolean search(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        return binarySearch(0, nums.length - 1);
    }
}
end original version */

// method 1: binary search
// if nums[left] == nums[mid] == nums[right], left++ and right--
class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int idx = (left + right) >> 1;
            if (nums[idx] == target)
                return true;
            else if (nums[left] == nums[idx] && nums[idx] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[idx]) {
                if (nums[left] == target)
                    return true;
                else if (nums[left] < target && target < nums[idx])
                    right = idx - 1;
                else
                    left = idx + 1;
            } else {
                if (nums[right] == target)
                    return true;
                else if (nums[idx] < target && target < nums[right])
                    left = idx + 1;
                else
                    right = idx - 1;
            }
        }
        return false;
    }
}