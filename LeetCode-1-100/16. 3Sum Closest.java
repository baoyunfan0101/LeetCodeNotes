// original version: fixed the first + two pointers with pruning
// the same as Problem 15. 3Sum
// keep track of the minimum diff from target
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);

        int res = 0, minDiff = 20000;
        int i = 0;
        while (i < len - 2) {
            if (nums[i] + nums[i + 1] + nums[i + 2] > target + minDiff)
                return res;
            if (nums[i] + nums[len - 2] + nums[len - 1] > target - minDiff) {
                int head = i + 1, end = len - 1;
                while (head < end) {
                    int sum = nums[i] + nums[head] + nums[end];
                    int diff = Math.abs(target - sum);
                    if (diff == 0)
                        return target;
                    else if (diff < minDiff) {
                        minDiff = diff;
                        res = sum;
                    }
                    if (target > sum)
                        do {
                            head++;
                        } while (head < end && nums[head - 1] == nums[head]);
                    else
                        do {
                            end--;
                        } while (head < end && nums[end] == nums[end + 1]);
                }
            }
            do {
                i++;
            } while (i < len && nums[i - 1] == nums[i]);
        }

//        // output res
//        System.out.println("res:");
//        for(List<Integer> innerList : res) {
//        	System.out.print("[");
//        	for(Integer innerInt : innerList)
//        		System.out.format("%-2d, ", innerInt);
//        	System.out.println("]");
//        }

        return res;
    }
}