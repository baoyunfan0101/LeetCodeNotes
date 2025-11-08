// original version: fixed the first and the second + two pointers with pruning
// use long to avoid integer overflow
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int i = 0;
        while (i < len - 3) {
            int j = i + 1;
            while (j < len - 2) {
                if ((long) -target + nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > 0)
                    break;
                if ((long) -target + nums[i] + nums[j] + nums[len - 2] + nums[len - 1] >= 0) {
                    int head = j + 1, end = len - 1;
                    while (head < end) {
                        long temp = (long) -target + nums[i] + nums[j] + nums[head] + nums[end];
                        if (temp == 0)
                            res.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[j], nums[head], nums[end])));
                        if (temp <= 0)
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
                    j++;
                } while (j < len - 2 && nums[j - 1] == nums[j]);
            }
            do {
                i++;
            } while (i < len - 3 && nums[i - 1] == nums[i]);
        }

//        // output res
//        System.out.println(res);
//        for(ListInteger innerList: res) {
//        	System.out.print("[");
//        	for(Integer innerInt: innerList)
//        		System.out.format("%-2d, ", innerInt);
//        	System.out.println("]");
//        }

        return res;
    }
}