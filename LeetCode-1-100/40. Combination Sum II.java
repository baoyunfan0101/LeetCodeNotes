/* wrong version: backtracking for each candidate
// same as problem #39. Combination Sum
// Time Limit Exceed
class Solution {
    private void recursive(int[] candidates, int target, int maxPos, List<List<Integer>> resList,
            List<Integer> tempList) {
        // have found an answer
        if (target == 0) {
            // check if it's repetitive
            for (List<Integer> list : resList)
                if (list.size() == tempList.size()) {
                    int size = list.size();
                    boolean existed = true;
                    for (int i = 0; i < size; i++) {
                        if (list.get(i) != tempList.get(i)) {
                            existed = false;
                            break;
                        }
                    }
                    if (existed)
                        return;
                }
            resList.add(new ArrayList<Integer>(tempList));
            return;
        }
        // be unable to find an answer
        else if (target < candidates[0])
            return;
        // start from current max position
        for (int i = maxPos; i >= 0; i--)
            if (candidates[i] <= target) {
                tempList.add(candidates[i]);
                recursive(candidates, target - candidates[i], i - 1, resList, tempList);
                tempList.removeLast();
            }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> resList = new ArrayList<List<Integer>>();
        List<Integer> tempList = new ArrayList<Integer>();
        recursive(candidates, target, candidates.length - 1, resList, tempList);
        return resList;
    }
}
end wrong version */

/* wrong version: backtracking for each value
// count each value and its occurrence (a single value may appear multiple times)
// in each recursion, decide whether to use the current value (the idx-th value):
// 1. if not used, move to the next value (idx += 1) and CANNOT move back
// 2. if used, keep the same index (idx = idx) and CAN use this value again, decreasing its remaining count by 1
// Time Limit Exceed
class Solution {
    private void recursive(List<int[]> list, int target, int idx, List<List<Integer>> resList, List<Integer> tempList) {
        // have found an answer
        if (target == 0) {
            resList.add(new ArrayList<Integer>(tempList));
            return;
        }
        // there are no candidates left
        if (idx == list.size())
            return;
        // 1. do not use the idx-th candidate
        recursive(list, target, idx + 1, resList, tempList);
        // 2. use the idx-th candidate
        int[] current = list.get(idx);
        // there are no the idx-th candidates left
        if (current[1] == 0 || target < current[0])
            return;
        tempList.add(current[0]);
        current[1]--;
        recursive(list, target - current[0], idx, resList, tempList);
        current[1]++;
        tempList.removeLast();
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // create a candidates list (each value and its occurrence)
        List<int[]> list = new ArrayList<int[]>();
        for (int i : candidates) {
            boolean findCandidate = false;
            for (int[] candidate : list)
                if (candidate[0] == i) {
                    candidate[1]++;
                    findCandidate = true;
                }
            if (!findCandidate)
                list.add(new int[] { i, 1 });
        }

        // create result set
        List<List<Integer>> resList = new ArrayList<List<Integer>>();
        List<Integer> tempList = new ArrayList<Integer>();
        recursive(list, target, 0, resList, tempList);
        return resList;
    }
}
end wrong version */

/* original version: backtracking for each value using a hash map
// same as the previous version
// manually create a hash map for each value and its occurrence
class Solution {
    private void recursive(int[] map, int target, int idx, List<List<Integer>> resList, List<Integer> tempList) {
        // have found an answer
        if (target == 0) {
            resList.add(new ArrayList<Integer>(tempList));
            return;
        }
        // there are no candidates left
        if (idx == 31)
            return;
        // 1. do not use the idx-th candidate
        recursive(map, target, idx + 1, resList, tempList);
        // 2. use the idx-th candidate
        // there are no the idx-th candidates left or the idx-th candidate is impossible
        if (map[idx] == 0 || target < idx)
            return;
        tempList.add(idx);
        map[idx]--;
        recursive(map, target - idx, idx, resList, tempList);
        map[idx]++;
        tempList.removeLast();
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int[] map = new int[31];
        for (int i : candidates) {
            // 1 <= target <= 30
            if (i > 30)
                continue;
            map[i]++;
        }
        List<List<Integer>> resList = new ArrayList<List<Integer>>();
        List<Integer> tempList = new ArrayList<Integer>();
        recursive(map, target, 1, resList, tempList);
        return resList;
    }
}
end original version */

// original version (modified): backtracking for each value using a hash map
// same as the previous version
// pruning has been optimized
class Solution {
    private void recursive(int[] map, int target, int idx, List<List<Integer>> resList, List<Integer> tempList) {
        // have found an answer
        if (target == 0) {
            resList.add(new ArrayList<Integer>(tempList));
            return;
        }
        // there are no possible candidates left
        if (target < idx || idx == 31)
            return;
        // 1. do not use the idx-th candidate
        recursive(map, target, idx + 1, resList, tempList);
        // 2. use the idx-th candidate
        // there are no the idx-th candidates left
        if (map[idx] == 0)
            return;
        tempList.add(idx);
        map[idx]--;
        recursive(map, target - idx, idx, resList, tempList);
        map[idx]++;
        tempList.removeLast();
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int[] map = new int[31];
        for (int i : candidates) {
            // 1 <= target <= 30
            if (i > 30)
                continue;
            map[i]++;
        }
        List<List<Integer>> resList = new ArrayList<List<Integer>>();
        List<Integer> tempList = new ArrayList<Integer>();
        recursive(map, target, 1, resList, tempList);
        return resList;
    }
}