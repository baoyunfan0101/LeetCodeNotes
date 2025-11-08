// original version
// at the end of a permutation, there is an increasing suffix (e.g., ...1245)
// find the element right before this increasing slope (e.g., ..31245)
// find the smallest element in the suffix that is greater than this element, and swap them (e.g., ..41235)
// special case 1: for 54321, '2' is the pivot, and "1" is the suffix
// special case 2: for 12345, no pivot exists (set its index to -1), and "12345" is the suffix
class Solution {
    public void nextPermutation(int[] nums) {
        // preDown: the index of the num that is prior to the last downslope
        // greaterThanPreDown: the index of the num that is nearestly greater than nums[preDown]
        int preDown = -1, greaterThanPreDown = 0;
        for (int i = 1; i < nums.length; i++)
            if (nums[i] > nums[i - 1]) {
                preDown = i - 1;
                greaterThanPreDown = i;
            } else if (preDown < 0 || nums[i] > nums[preDown])
                greaterThanPreDown = i;

        // swap nums[preDown] & nums[greaterThanPreDown]
        if (preDown >= 0) {
            int temp = nums[preDown];
            nums[preDown] = nums[greaterThanPreDown];
            nums[greaterThanPreDown] = temp;
        }

        // reverse nums[(preDown + 1)..(nums.length - 1)]
        int left = preDown + 1, right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}