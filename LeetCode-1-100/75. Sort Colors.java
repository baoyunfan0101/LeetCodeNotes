// original version: quick sort
class Solution {
    private int[] nums;

    private void quickSort(int start, int end) {
        if (start >= end)
            return;

        int left = start, right = end, pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] > pivot)
                right--;
            if (left == right)
                break;
            nums[left++] = nums[right];

            while (left < right && nums[left] <= pivot)
                left++;
            if (left == right)
                break;
            nums[right--] = nums[left];
        }
        nums[left] = pivot;

        quickSort(start, left - 1);
        quickSort(left + 1, end);
    }

    public void sortColors(int[] nums) {
        this.nums = nums;
        quickSort(0, nums.length - 1);
    }
}