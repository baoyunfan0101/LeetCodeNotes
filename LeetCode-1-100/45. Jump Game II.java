// original version
// suppose the minimum steps to reach the current position i is s, the farthest position reachable in (s + 1) steps is (i + nums[i])
// continue searching and keep updating the farthest reachable position
// when reaching the farthest position of s steps, the range for (s + 1) steps can no longer be extended, so start finding the range for (s + 2) steps
class Solution {
    public int jump(int[] nums) {
        int currentIdx = 0, lastIdx = 0, currentStep = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > lastIdx) {
                lastIdx = currentIdx;
                currentStep++;
            }
            currentIdx = Math.max(i + nums[i], currentIdx);
        }
        return currentStep;
    }
}