/* original method: dynamic programming
// when adding a new character, either treat it as a separate letter or combine it with the trailing character of the current string
class Solution {
    public int numDecodings(String s) {
        char[] a = s.toCharArray();
        int len = a.length;
        int[] dp = new int[len + 1];
        dp[len] = 1;

        for (int i = len - 1; i >= 0; i--) {
            if (a[i] != '0')
                dp[i] += dp[i + 1];

            if (i < len - 1 && (a[i] == '1' || (a[i] == '2' && a[i + 1] <= '6')))
                dp[i] += dp[i + 2];
        }

        //System.out.println(Arrays.toString(dp));

        return dp[0];
    }
}
end original method */

// method 1: space-optimized dynamic programming
// use only three integers
class Solution {
    public int numDecodings(String s) {
        char[] a = s.toCharArray();
        int len = a.length;
        int dp1 = 0, dp2 = 1, dp3 = 0;

        for (int i = len - 1; i >= 0; i--) {
            if (a[i] != '0')
                dp3 += dp2;

            if (i < len - 1 && (a[i] == '1' || (a[i] == '2' && a[i + 1] <= '6')))
                dp3 += dp1;

            dp1 = dp2;
            dp2 = dp3;
            dp3 = 0;
        }

        return dp2;
    }
}