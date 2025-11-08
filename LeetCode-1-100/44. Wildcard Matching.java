// original version: dynamic programming
// similar to problem #10. Regular Expression Matching
class Solution {
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        if (sLen == 0 && pLen == 0)
            return true;
        else if (sLen != 0 && pLen == 0)
            return false;

        boolean[][] map = new boolean[sLen + 1][pLen + 1];
        // initialize
        map[0][0] = true;
        for (int j = 0; j < pLen; j++)
            if (p.charAt(j) == '*')
                map[0][j + 1] = map[0][j];

        for (int j = 0; j < pLen; j++) {
            char c = p.charAt(j);
            if (c == '*')
                for (int i = 0; i < sLen; i++)
                    map[i + 1][j + 1] = map[i][j + 1] || map[i + 1][j];
            else if (c == '?')
                for (int i = 0; i < sLen; i++)
                    map[i + 1][j + 1] = map[i][j];
            else
                for (int i = 0; i < sLen; i++)
                    if (c == s.charAt(i))
                        map[i + 1][j + 1] = map[i][j];
        }

        //System.out.println(Arrays.deepToString(map));
        return map[sLen][pLen];
    }
}