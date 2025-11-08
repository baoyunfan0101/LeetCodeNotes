/* wrong version: recursion
// at each step, try matching s1[n1] with s3[n3] or s2[n2] with s3[n3]; if both match, explore both recursive paths
// Time Limit Exceed
class Solution {
    private char[] a1, a2, a3;
    private int len1, len2, len3;

    private boolean backtrack(int n1, int n2, int n3) {
        while (n1 < len1 && n2 < len2 && n3 < len3) {
            char c1 = a1[n1], c2 = a2[n2], c3 = a3[n3];
            if (c1 == c3 && c2 == c3)
                return backtrack(n1 + 1, n2, n3 + 1) || backtrack(n1, n2 + 1, n3 + 1);
            else if (c1 == c3) {
                n1++;
                n3++;
            } else if (c2 == c3) {
                n2++;
                n3++;
            } else
                return false;
        }
        while (n1 < len1 && n3 < len3) {
            char c1 = a1[n1], c3 = a3[n3];
            if (c1 == c3) {
                n1++;
                n3++;
            } else
                return false;
        }
        while (n2 < len2 && n3 < len3) {
            char c2 = a2[n2], c3 = a3[n3];
            if (c2 == c3) {
                n2++;
                n3++;
            } else
                return false;
        }
        return true;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        a1 = s1.toCharArray();
        a2 = s2.toCharArray();
        a3 = s3.toCharArray();
        len1 = a1.length;
        len2 = a2.length;
        len3 = a3.length;

        if (len1 + len2 != len3)
            return false;

        return backtrack(0, 0, 0);
    }
}
end wrong version */

/* wrong version (modified): memorized recursion
// same as the previous version
// Time Limit Exceed
class Solution {
    private char[] a1, a2, a3;
    private int len1, len2, len3;
    private Boolean[][][] dp;

    private boolean backtrack(int n1, int n2, int n3) {
        if (dp[n1][n2][n3] != null)
            return dp[n1][n2][n3];
        while (n1 < len1 && n2 < len2 && n3 < len3) {
            char c1 = a1[n1], c2 = a2[n2], c3 = a3[n3];
            if (c1 == c3 && c2 == c3) {
                dp[n1][n2][n3] = backtrack(n1 + 1, n2, n3 + 1) || backtrack(n1, n2 + 1, n3 + 1);
                return dp[n1][n2][n3];
            } else if (c1 == c3) {
                n1++;
                n3++;
            } else if (c2 == c3) {
                n2++;
                n3++;
            } else {
                dp[n1][n2][n3] = false;
                return false;
            }
        }
        while (n1 < len1 && n3 < len3) {
            char c1 = a1[n1], c3 = a3[n3];
            if (c1 == c3) {
                n1++;
                n3++;
            } else {
                dp[n1][n2][n3] = false;
                return false;
            }
        }
        while (n2 < len2 && n3 < len3) {
            char c2 = a2[n2], c3 = a3[n3];
            if (c2 == c3) {
                n2++;
                n3++;
            } else {
                dp[n1][n2][n3] = false;
                return false;
            }
        }
        dp[n1][n2][n3] = true;
        return true;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        a1 = s1.toCharArray();
        a2 = s2.toCharArray();
        a3 = s3.toCharArray();
        len1 = a1.length;
        len2 = a2.length;
        len3 = a3.length;

        if (len1 + len2 != len3)
            return false;

        dp = new Boolean[len1 + 1][len2 + 1][len3 + 1];
        return backtrack(0, 0, 0);
    }
}
end original method (modified) */

/* original version: dynamic programming
// dp[i][j] indicates whether s1[0:i] and s2[0:j] can interleave to form s3[0:i+j]
// transition: dp[i][j] is true if (dp[i-1][j] and s1[i-1] == s3[i+j-1]) or (dp[i][j-1] and s2[j-1] == s3[i+j-1])
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] a1 = s1.toCharArray(), a2 = s2.toCharArray(), a3 = s3.toCharArray();
        int len1 = a1.length, len2 = a2.length, len3 = a3.length;
        if (len1 + len2 != len3)
            return false;

        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        for (int i = 1; i <= len1; i++)
            if (a1[i - 1] == a3[i - 1])
                dp[i][0] = true;
            else
                break;
        for (int j = 1; j <= len2; j++)
            if (a2[j - 1] == a3[j - 1])
                dp[0][j] = true;
            else
                break;

        for (int i = 1; i <= len1; i++)
            for (int j = 1; j <= len2; j++)
                if ((dp[i][j - 1] && a2[j - 1] == a3[i + j - 1] || (dp[i - 1][j] && a1[i - 1] == a3[i + j - 1]))
                    dp[i][j] = true;

//        // output
//        for (int i = 1; i <= len1; i++)
//            for (int j = 1; j <= len2; j++)
//                if (dp[i][j])
//                    System.out.format("%d %d\r\n", i, j);

        return dp[len1][len2];
    }
}
end original version */

// original version (modified): space-optimized dynamic programmingß
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] a1 = s1.toCharArray(), a2 = s2.toCharArray(), a3 = s3.toCharArray();
        int len1 = a1.length, len2 = a2.length, len3 = a3.length;
        if (len1 + len2 != len3)
            return false;

        boolean[] dp = new boolean[len2 + 1];
        dp[0] = true;
        for (int j = 1; j <= len2; j++)
            if (a2[j - 1] == a3[j - 1])
                dp[j] = true;
            else
                break;

        for (int i = 1; i <= len1; i++) {
            dp[0] = dp[0] && a1[i - 1] == a3[i - 1];
            for (int j = 1; j <= len2; j++)
                dp[j] = (dp[j - 1] && a2[j - 1] == a3[i + j - 1])
                        || (dp[j] && a1[i - 1] == a3[i + j - 1]);
        }

        return dp[len2];
    }
}