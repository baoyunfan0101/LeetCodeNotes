/* silly version: next permutation
// reuse problem #31. Next Permutation
// convert int[] nums to List<Integer>: Arrays.stream(nums).boxed().collect(Collectors.toList())
class Solution {
    // problem #31. Next Permutation
    private void nextPermutation(int[] nums) {
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

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int[] firstNums = nums.clone();
        boolean isSame = true;
        do {
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            nextPermutation(nums);
            isSame = true;
            for (int i = 0; i < len; i++)
                if (firstNums[i] != nums[i]) {
                    isSame = false;
                    break;
                }
        } while (!isSame);
        return res;
    }
}
end silly version */

/* original version: backtracking
// in each iteration, pick one integer from a list
class Solution {
    private void recursive(List<Integer> numsList, List<Integer> permutationList, List<List<Integer>> res) {
        int len = numsList.size();
        if (len == 0)
            res.add(new ArrayList<Integer>(permutationList));
        for (int i = 0; i < len; i++) {
            int temp = numsList.get(i);
            numsList.remove(i);
            permutationList.add(temp);
            recursive(numsList, permutationList, res);
            permutationList.removeLast();
            numsList.add(i, temp);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        recursive(Arrays.stream(nums).boxed().collect(Collectors.toList()), new ArrayList<Integer>(), res);
        return res;
    }
}
end original version */

// method 1: backtracking
// in each iteration, swap every integer after idx into idx instead of using a separate list for remaining integers
// in short, integers left of idx are used; those to the right are unused
class Solution {
    private void recursive(List<Integer> permutation, int idx, List<List<Integer>> res) {
        int len = permutation.size();
        if (idx == len) {
            res.add(new ArrayList<Integer>(permutation));
            return;
        }
        for (int i = idx; i < len; i++) {
            Collections.swap(permutation, idx, i);
            recursive(permutation, idx + 1, res);
            Collections.swap(permutation, idx, i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        recursive(Arrays.stream(nums).boxed().collect(Collectors.toList()), 0, res);
        return res;
    }
}