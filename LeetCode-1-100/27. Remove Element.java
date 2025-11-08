/* original version
class Solution {
    public int removeElement(int[] nums, int val) {
        int len = nums.length, k = 0;
        for (int i = 0; i < len; i++)
            if (nums[i] != val) {
                if (i != k)
                    nums[k] = nums[i];
                k++;
            }
        return k;
    }
}
end original version */

// better version: two pointers
// the left pointer searches for a position not equal to val, while the right pointer searches for a position equal to val
// assign the right to the left
class Solution {
    public int removeElement(int[] nums, int val) {
        int len = nums.length, left = 0, right = len - 1;
        while (left <= right) {
            if (nums[left] != val) {
                left++;
                continue;
            }
            if (nums[right] == val) {
                right--;
                continue;
            }
            nums[left] = nums[right];
            left++;
            right--;
        }
        return left;
    }
}