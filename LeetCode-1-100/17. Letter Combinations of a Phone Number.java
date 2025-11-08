// original version: backtracking
class Solution {
    private static final char[][] dic = new char[][] {
            { 'a', 'b', 'c' },
            { 'd', 'e', 'f' },
            { 'g', 'h', 'i' },
            { 'j', 'k', 'l' },
            { 'm', 'n', 'o' },
            { 'p', 'q', 'r', 's' },
            { 't', 'u', 'v' },
            { 'w', 'x', 'y', 'z' },
    };

    private void recursive(String digits, int i, StringBuilder builder, List<String> res) {
        for (char c : dic[(int) digits.charAt(i) - (int) '2']) {
            builder.append(c);
            if (i < digits.length() - 1) {
                recursive(digits, i + 1, builder, res);
            } else {
                res.add(builder.toString());
            }
            builder.delete(builder.length() - 1, builder.length());
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if (digits.length() == 0)
            return res;
        StringBuilder builder = new StringBuilder();
        recursive(digits, 0, builder, res);
        return res;
    }
}