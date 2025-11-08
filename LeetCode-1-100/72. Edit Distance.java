// original version: dynamic programming
// if word1[i] == word2[j]: dp[i][j] = dp[i - 1][j - 1]
// else: dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1
class Solution {
    public int minDistance(String word1, String word2) {
        char[] word1Array = word1.toCharArray(), word2Array = word2.toCharArray();
        int len1 = word1Array.length, len2 = word2Array.length;
        int[][] dp = new int[len1 + 1][len2 + 1];

        // initialize
        for (int i = 1; i <= len1; i++)
            dp[i][0] = i;
        for (int j = 1; j <= len2; j++)
            dp[0][j] = j;

        for (int i = 1; i <= len1; i++)
            for (int j = 1; j <= len2; j++)
                if (word1Array[i - 1] != word2Array[j - 1])
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                else
                    dp[i][j] = dp[i - 1][j - 1];

        return dp[len1][len2];
    }
}