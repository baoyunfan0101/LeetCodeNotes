/* original version: two pointer
// count consecutive identical elements
class Solution {
    public int removeDuplicates(int[] nums) {
        int count = 0, current = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                if (++count >= 2)
                    continue;
            } else {
                count = 0;
            }
            nums[++current] = nums[i];
        }
        return ++current;
    }
}
end original version */

// original version (modified): two pointer
// same as the previous version
// if nums[left - 2] == nums[right], assign nums[left] = nums[right]
class Solution {
    public int removeDuplicates(int[] nums) {
        int current = 2;
        for (int i = 2; i < nums.length; i++) {
            // if there are already two identical numbers
            // (nums[current - 2], nums[current - 1])
            if (nums[current - 2] == nums[i])
                continue;
            nums[current++] = nums[i];
        }
        return Math.min(nums.length, current);
    }
}