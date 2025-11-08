/* original version: memorized recursion
// same as problem #95. Unique Binary Search Trees II
// but only record the count of valid BSTs instead of their structures
class Solution {
    private Integer[][] dp;

    private int generate(int min, int max) {
        if (min > max)
            return 0;
        else if (min == max)
            return 1;
        else if (dp[min - 1][max - 1] != null)
            return dp[min - 1][max - 1];

        int numTree = 0;
        for (int mid = min; mid <= max; mid++) {
            int numLeftTree = generate(min, mid - 1);
            int numRightTree = generate(mid + 1, max);
            numTree += ((numLeftTree == 0) ? 1 : numLeftTree) * ((numRightTree == 0) ? 1 : numRightTree);
        }
        dp[min - 1][max - 1] = numTree;
        return numTree;
    }

    public int numTrees(int n) {
        dp = new Integer[n][n];
        return generate(1, n);
    }
}
end original version */

/* original method (modified): memorized recursion
// simplified
class Solution {
    private Integer[][] dp;

    private int generate(int min, int max) {
        if (min >= max)
            return 1;
        else if (dp[min - 1][max - 1] != null)
            return dp[min - 1][max - 1];

        int numTree = 0;
        for (int mid = min; mid <= max; mid++) {
            int numLeftTree = generate(min, mid - 1);
            int numRightTree = generate(mid + 1, max);
            numTree += numLeftTree * numRightTree;
        }
        dp[min - 1][max - 1] = numTree;
        return numTree;
    }

    public int numTrees(int n) {
        dp = new Integer[n][n];
        return generate(1, n);
    }
}
end original method (modified) */

// method 1: dynamic programming
// the number of valid BSTs with n nodes is fixed
class Solution {
    static int[] dp = new int[20];
    static {
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < 20; i++)
            for (int mid = 1; mid <= i; mid++)
                dp[i] += dp[mid - 1] * dp[i - mid];
    }

    public int numTrees(int n) {
        return dp[n];
    }
}