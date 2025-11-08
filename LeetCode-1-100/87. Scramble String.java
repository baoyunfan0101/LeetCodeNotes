/* wrong version: dynamic programming
// use a 2D array dp[i][j] to store lengths of longest matching substrings starting at i in s1 and j in s2
// repeatedly merge all legal matching substrings until no further merges are possible
// Counterexample: s1 = "hobobyrqd", s2 = "hbyorqdbo"
// this approach may miss valid substrings like "h(ob)obyrqd" vs "hbyorqd(bo)"
// because "ho(bo)byrqd" is already paired with "hbyorqd(bo)" and cannot be split again
class Solution {
    public boolean isScramble(String s1, String s2) {
        char[] a1 = s1.toCharArray(), a2 = s2.toCharArray();
        int len1 = a1.length, len2 = a2.length;
        int[][] dp = new int[len1][len2];

        for (int i = 0; i < len1; i++)
            for (int j = 0; j < len2; j++)
                if (a1[i] == a2[j]) {
                    int t = 1;
                    while (i + t < len1 && j + t < len2 && a1[i + t] == a2[j + t])
                        t++;
                    dp[i][j] = t;
                }

        boolean changed = false;
        do {
            changed = false;
            for (int i = 0; i < len1; i++)
                for (int j = 0; j < len2; j++)
                    if (dp[i][j] > 0) {
                        int t = dp[i][j];
                        if (i + t < len1 && j + t < len2 && dp[i + t][j + t] > 0) {
                            dp[i][j] += dp[i + t][j + t];
                            changed = true;
                        } else if (i + t < len1) {
                            int start = 0;
                            while (start < j) {
                                if (dp[i + t][start] == j - start) {
                                    if (dp[i][start] < t + j - start) {
                                        dp[i][start] = t + j - start;
                                        changed = true;
                                    }
                                    break;
                                }
                                start++;
                            }
                        }
                    }
        } while (changed);

        //System.out.println(Arrays.deepToString(dp));

        return dp[0][0] == len1;
    }
}
end wrong version */

/* original version: dynamic programming
// try all possible legal substrings in increasing order of substring length
class Solution {
    public boolean isScramble(String s1, String s2) {
        char[] a1 = s1.toCharArray(), a2 = s2.toCharArray();
        int len = a1.length;
        int[][] dp = new int[len][len];

        for (int i = 0; i < len; i++)
            for (int j = 0; j < len; j++)
                if (a1[i] == a2[j]) {
                    dp[i][j] = 1;
                }

        for (int t = 2; t <= len; t++) {
            for (int i = 0; i <= len - t; i++)
                for (int j = 0; j <= len - t; j++) {
                    if (dp[i][j] + dp[i + dp[i][j]][j + dp[i][j]] == t)
                        dp[i][j] = t;
                    else
                        for (int mid = 1; mid < t; mid++)
                            if (dp[i + mid][j] == t - mid && dp[i][j + t - mid] == mid) {
                                dp[i][j] = t;
                                break;
                            }
                }
        }

        //System.out.println(Arrays.deepToString(dp));

        return dp[0][0] == len;
    }
}
end original version */

/* wrong version: recursion
// in each iteration, try all possible split positions and recurse on valid ones
// for each split position, create hash maps for s1[0:i+1] and s1[i+1:len] to compare character frequencies
// Note: if a deeper recursive call returns true, return true immediately; otherwise, CONTINUE WITHOUT RETURNING
// Counterexample: s1 = "eebaacbcbcadaaedceaaacadccd", s2 = "eadcaacabaddaceacbceaabeccd"
// Time Limit Exceed
class Solution {
    public int count = 0;

    public boolean isScramble(String s1, String s2) {
        // it's the first time
        if (count++ == 0) {
            Map<Character, Integer> hashMap0 = new HashMap<>();
            for (char c : s1.toCharArray())
                hashMap0.put(c, hashMap0.getOrDefault(c, 0) + 1);

            for (char c : s2.toCharArray()) {
                int n = hashMap0.getOrDefault(c, 0);
                if (n == 0)
                    return false;
                if (n == 1)
                    hashMap0.remove(c);
                else
                    hashMap0.put(c, n - 1);
            }
        }

        if (s1.equals(s2))
            return true;

        int len = s1.length();

        for (int i = 1; i < len; i++) {
            Map<Character, Integer> hashMap1 = new HashMap<>();
            for (char c : s1.substring(0, i).toCharArray())
                hashMap1.put(c, hashMap1.getOrDefault(c, 0) + 1);

            Map<Character, Integer> hashMap2 = new HashMap<>();
            for (char c : s1.substring(i, len).toCharArray())
                hashMap2.put(c, hashMap2.getOrDefault(c, 0) + 1);

            // s1.substring(0, i) vs. s2.substring(0, i)
            boolean legal = true;
            for (char c : s2.substring(0, i).toCharArray()) {
                int n = hashMap1.getOrDefault(c, 0);
                if (n == 0) {
                    legal = false;
                    break;
                }
                if (n == 1)
                    hashMap1.remove(c);
                else
                    hashMap1.put(c, n - 1);
            }

            if (legal && hashMap1.isEmpty()) {
                //System.out.format("\"%s/%s\"\r\n", s1.substring(0, i), s1.substring(i, len));
                //System.out.format("\"%s/%s\"\r\n", s2.substring(0, i), s2.substring(i, len));
                if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i, len), s2.substring(i, len)))
                    return true;
            }

            // s1.substring(i, len) vs. s2.substring(0, len - i)
            legal = true;
            for (char c : s2.substring(0, len - i).toCharArray()) {
                int n = hashMap2.getOrDefault(c, 0);
                if (n == 0) {
                    legal = false;
                    break;
                }
                if (n == 1)
                    hashMap2.remove(c);
                else
                    hashMap2.put(c, n - 1);
            }

            if (legal && hashMap2.isEmpty()) {
                //System.out.format("\"%s/%s\"\r\n", s1.substring(0, i), s1.substring(i, len));
                //System.out.format("\"%s/%s\"\r\n", s2.substring(len - i, len), s2.substring(0, len - i));
                if (isScramble(s1.substring(0, i), s2.substring(len - i, len)) && isScramble(s1.substring(i, len), s2.substring(0, len - i)))
                    return true;
            }
        }

        return false;
    }
}
end wrong version */

/* wrong version (modified): recursion
// create only two hash maps per iteration
// Counterexample: s1 = "eebaacbcbcadaaedceaaacadccd", s2 = "eadcaacabaddaceacbceaabeccd"
// Time Limit Exceed
class Solution {
    private char[] a1, a2;

    private boolean recursive(int l1, int l2, int len) {
        boolean equals = true;
        for (int i = 1; i < len; i++)
            if (a1[l1 + i] != a2[l2 + i]) {
                equals = false;
                break;
            }
        if (equals)
            return true;

        // initialize:

        // hashMap1: i = 0, a1[l1..l1 + i] - a2[l2..l2 + i] is empty
        Map<Character, Integer> hashMap1 = new HashMap<>();

        // hashMap2: i = 0, a1[l1 + i..l1 + len] - a2[l2..l2 + len - i] is empty as well
        Map<Character, Integer> hashMap2 = new HashMap<>();

        // find out where to divide
        for (int i = 1; i < len; i++) {

            // update hashMap1
            char c1 = a1[l1 + i - 1], c2 = a2[l2 + i - 1];
            int n1 = hashMap1.getOrDefault(c1, 0);
            if (n1 == -1)
                hashMap1.remove(c1);
            else
                hashMap1.put(c1, n1 + 1);
            int n2 = hashMap1.getOrDefault(c2, 0);
            if (n2 == 1)
                hashMap1.remove(c2);
            else
                hashMap1.put(c2, n2 - 1);

            // update hashMap2
            char c3 = a1[l1 + i - 1], c4 = a2[l2 + len - i];
            int n3 = hashMap2.getOrDefault(c3, 0);
            if (n3 == 1)
                hashMap2.remove(c3);
            else
                hashMap2.put(c3, n3 - 1);
            int n4 = hashMap2.getOrDefault(c4, 0);
            if (n4 == -1)
                hashMap2.remove(c4);
            else
                hashMap2.put(c4, n4 + 1);

//            // output hash maps
//            System.out.println("i = " + i);
//            System.out.println("hashMap1: ");
//            for (char c : hashMap1.keySet())
//                System.out.println(c + ": " + hashMap1.get(c));
//            System.out.println("hashMap2: ");
//            for (char c : hashMap2.keySet())
//                System.out.println(c + ": " + hashMap2.get(c));
//            System.out.println();

            // s1.substring(0, i) vs. s2.substring(0, i)
            if (hashMap1.isEmpty()) {
//                // output status
//                System.out.print("\"");
//                for (int idx = 0; idx < i; idx++) {
//                    System.out.print(a1[idx]);
//                }
//                System.out.print("\" vs. \"");
//                for (int idx = 0; idx < i; idx++) {
//                    System.out.print(a2[idx]);
//                }
//                System.out.print("\"\r\n");
                if (recursive(l1, l2, i) && recursive(l1 + i, l2 + i, len - i))
                    return true;
            }

            // s1.substring(i, len) vs. s2.substring(0, len - i)
            if (hashMap2.isEmpty()) {
//                // output status
//                System.out.print("\"");
//                for (int idx = i; idx < len; idx++) {
//                    System.out.print(a1[idx]);
//                }
//                System.out.print("\" vs. \"");
//                for (int idx = 0; idx < len - i; idx++) {
//                    System.out.print(a2[idx]);
//                }
//                System.out.print("\"\r\n");
                if (recursive(l1, l2 + len - i, i) && recursive(l1 + i, l2, len - i))
                    return true;
            }
        }

        return false;
    }

    public boolean isScramble(String s1, String s2) {
        a1 = s1.toCharArray();
        a2 = s2.toCharArray();

        Map<Character, Integer> hashMap0 = new HashMap<>();
        for (char c : a1)
            hashMap0.put(c, hashMap0.getOrDefault(c, 0) + 1);

        for (char c : a2) {
            int n = hashMap0.getOrDefault(c, 0);
            if (n == 0)
                return false;
            hashMap0.put(c, n - 1);
        }

        return recursive(0, 0, s1.length());
    }
}
wrong version (modified) */

// method 1: memorized recursion
// same as the prevoius version, but cache recursive results
class Solution {
    private char[] a1, a2;
    private Boolean[][][] dp;

    private boolean recursive(int l1, int l2, int len) {
        if (dp[l1][l2][len - 1] != null)
            return dp[l1][l2][len - 1];

        boolean equals = true;
        for (int i = 1; i < len; i++)
            if (a1[l1 + i] != a2[l2 + i]) {
                equals = false;
                break;
            }
        if (equals) {
            dp[l1][l2][len - 1] = true;
            return true;
        }

        // initialize:

        // hashMap1: i = 0, a1[l1..l1 + i] - a2[l2..l2 + i] is empty
        Map<Character, Integer> hashMap1 = new HashMap<>();

        // hashMap2: i = 0, a1[l1 + i..l1 + len] - a2[l2..l2 + len - i] is empty as well
        Map<Character, Integer> hashMap2 = new HashMap<>();

        // find out where to divide
        for (int i = 1; i < len; i++) {

            // update hashMap1
            char c1 = a1[l1 + i - 1], c2 = a2[l2 + i - 1];
            int n1 = hashMap1.getOrDefault(c1, 0);
            if (n1 == -1)
                hashMap1.remove(c1);
            else
                hashMap1.put(c1, n1 + 1);
            int n2 = hashMap1.getOrDefault(c2, 0);
            if (n2 == 1)
                hashMap1.remove(c2);
            else
                hashMap1.put(c2, n2 - 1);

            // update hashMap2
            char c3 = a1[l1 + i - 1], c4 = a2[l2 + len - i];
            int n3 = hashMap2.getOrDefault(c3, 0);
            if (n3 == 1)
                hashMap2.remove(c3);
            else
                hashMap2.put(c3, n3 - 1);
            int n4 = hashMap2.getOrDefault(c4, 0);
            if (n4 == -1)
                hashMap2.remove(c4);
            else
                hashMap2.put(c4, n4 + 1);

            /* output hash maps
            System.out.println("i = " + i);
            System.out.println("hashMap1: ");
            for (char c : hashMap1.keySet())
                System.out.println(c + ": " + hashMap1.get(c));
            System.out.println("hashMap2: ");
            for (char c : hashMap2.keySet())
                System.out.println(c + ": " + hashMap2.get(c));
            System.out.println();*/

            // s1.substring(0, i) vs. s2.substring(0, i)
            if (hashMap1.isEmpty()) {
                /* output status
                System.out.print("\"");
                for (int idx = 0; idx < i; idx++) {
                    System.out.print(a1[idx]);
                }
                System.out.print("\" vs. \"");
                for (int idx = 0; idx < i; idx++) {
                    System.out.print(a2[idx]);
                }
                System.out.print("\"\r\n");*/
                if (recursive(l1, l2, i) && recursive(l1 + i, l2 + i, len - i)) {
                    dp[l1][l2][len - 1] = true;
                    return true;
                }
            }

            // s1.substring(i, len) vs. s2.substring(0, len - i)
            if (hashMap2.isEmpty()) {
                /* output status
                System.out.print("\"");
                for (int idx = i; idx < len; idx++) {
                    System.out.print(a1[idx]);
                }
                System.out.print("\" vs. \"");
                for (int idx = 0; idx < len - i; idx++) {
                    System.out.print(a2[idx]);
                }
                System.out.print("\"\r\n");*/
                if (recursive(l1, l2 + len - i, i) && recursive(l1 + i, l2, len - i)) {
                    dp[l1][l2][len - 1] = true;
                    return true;
                }
            }
        }

        dp[l1][l2][len - 1] = false;
        return false;
    }

    public boolean isScramble(String s1, String s2) {
        a1 = s1.toCharArray();
        a2 = s2.toCharArray();

        Map<Character, Integer> hashMap0 = new HashMap<>();
        for (char c : a1)
            hashMap0.put(c, hashMap0.getOrDefault(c, 0) + 1);

        for (char c : a2) {
            int n = hashMap0.getOrDefault(c, 0);
            if (n == 0)
                return false;
            hashMap0.put(c, n - 1);
        }

        int len = s1.length();
        dp = new Boolean[len][len][len];
        return recursive(0, 0, len);
    }
}