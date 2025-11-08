// original version: backtracking
// in each iteration, try using the next 1, 2, or 3 characters as an integer
class Solution {
    private char[] a;
    private int len;
    private int[] comb;
    private List<String> res;

    private void backtrack(int iNum, int iChar) {
        if (4 - iNum > len - iChar || (4 - iNum) * 3 < len - iChar)
            return;

        if (iNum == 4 && iChar == len) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (int j = 0; j < 4; j++) {
                int rigntPos = i + comb[j];
                while (i < rigntPos)
                    sb.append(a[i++]);
                if (j < 3)
                    sb.append('.');
            }
            res.add(sb.toString());
            return;
        }

        char c = a[iChar];

        // numLen = 1
        comb[iNum] = 1;
        backtrack(iNum + 1, iChar + 1);
        // numLen = 2
        if (iChar + 1 < len && c > '0') {
            comb[iNum] = 2;
            backtrack(iNum + 1, iChar + 2);
        }
        // numLen = 3
        if (iChar + 2 < len) {
            char c1 = a[iChar + 1], c2 = a[iChar + 2];
            if (c == '1' || (c == '2' && (c1 < '5' || c1 == '5' && c2 < '6'))) {
                comb[iNum] = 3;
                backtrack(iNum + 1, iChar + 3);
            }
        }
    }

    public List<String> restoreIpAddresses(String s) {
        a = s.toCharArray();
        len = a.length;
        comb = new int[4];
        res = new ArrayList<String>();
        backtrack(0, 0);
        return res;
    }
}