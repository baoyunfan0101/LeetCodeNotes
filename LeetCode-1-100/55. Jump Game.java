/* original version
// iterate from the end and keep track of the leftmost reachable index
class Solution {
    public boolean canJump(int[] nums) {
        int reachedIdx = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--)
            if (i + nums[i] >= reachedIdx)
                reachedIdx = i;
        return reachedIdx == 0;
    }
}
end original version */

// better version
// similar to the previous version
// iterate from the start and keep track of the rightmost reachable index
// stop looping once the rightmost reachable index exceeds the last index
class Solution {
    public boolean canJump(int[] nums) {
        int reachable = 0, i = 0, maxIdx = nums.length - 1;
        while (i <= maxIdx && reachable < maxIdx && i <= reachable) {
            int t = nums[i] + i++;
            reachable = Math.max(t, reachable);
        }
        return reachable >= maxIdx;
    }
}