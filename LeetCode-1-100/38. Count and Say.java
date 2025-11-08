// original version: recursion
class Solution {
    private String getRLE(String s) {
        StringBuilder builder = new StringBuilder();
        int n = 0, count = 1, len = s.length();
        while (n < len) {
            while (n + 1 < len && s.charAt(n) == s.charAt(n + 1)) {
                n++;
                count++;
            }
            builder.append(count);
            builder.append(s.charAt(n));
            n++;
            count = 1;
        }
        //System.out.format("RLE(%s) = %s\r\n", s, builder);
        return builder.toString();
    }

    public String countAndSay(int n) {
        if (n == 1)
            return "1";
        else
            return getRLE(countAndSay(n - 1));
    }
}