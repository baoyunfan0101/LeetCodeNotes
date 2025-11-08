// indelible version
// this piece of code has been on my desktop for years!
// essentially, it's a space-optimized dynamic programming
class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE, currentSum = 0;
        for (int n : nums) {
            currentSum += n;
            maxSum = Math.max(maxSum, currentSum);
            currentSum = Math.max(currentSum, 0);
        }
        return maxSum;
    }
}