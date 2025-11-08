// original version: backtracking
// sort the candidates and start from the largest one
// in each recursion, decide on (a single occurrence of) a value to include in the combination, ensuring it is smaller than the previous one
class Solution {
    private void recursive(int[] candidates, int target, int maxPos, List<List<Integer>> resList,
            List<Integer> tempList) {
        // have found an answer
        if (target == 0) {
            resList.add(new ArrayList<Integer>(tempList));
            return;
        }
        // be unable to find an answer
        else if(target < candidates[0])
            return;
        // start from current max position
        for (int i = maxPos; i >= 0; i--)
            if (candidates[i] <= target) {
                tempList.add(candidates[i]);
                recursive(candidates, target - candidates[i], i, resList, tempList);
                tempList.removeLast();
            }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> resList = new ArrayList<List<Integer>>();
        List<Integer> tempList = new ArrayList<Integer>();
        recursive(candidates, target, candidates.length - 1, resList, tempList);
        return resList;
    }
}