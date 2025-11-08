// original version: backtracking
// in each iteration, choose whether to include the current integer or not
class Solution {
    private int n, k;
    private List<Integer> comb;
    private List<List<Integer>> res;

    private void backtrack(int idx, int maxInt) {
        if (idx == k) {
            res.addLast(new ArrayList<Integer>(comb));
            return;
        }

        for (int i = maxInt + 1; i <= n - k + idx + 1; i++) {
            comb.addLast(i);
            backtrack(idx + 1, i);
            comb.removeLast();
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        comb = new ArrayList<Integer>();
        res = new ArrayList<List<Integer>>();
        backtrack(0, 0);
        return res;
    }
}