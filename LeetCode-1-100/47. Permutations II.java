// original version: backtracking
// similar to problem #46. Permutations - original version: backtracking
// sort the array first, then skip duplicate values within the same iteration
class Solution {
    private void recursive(List<Integer> permutation, int idx, List<List<Integer>> res) {
        int len = permutation.size();
        if (idx == len) {
            res.add(new ArrayList<Integer>(permutation));
            return;
        }
        int i = idx, temp;
        while (i < len) {
            temp = permutation.remove(i);
            permutation.add(idx, temp);
            recursive(permutation, idx + 1, res);
            permutation.remove(idx);
            permutation.add(i, temp);
            // skip the same value
            while (i + 1 < len && permutation.get(i) == permutation.get(i + 1))
                i++;
            i++;
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        recursive(Arrays.stream(nums).boxed().collect(Collectors.toList()), 0, res);
        return res;
    }
}