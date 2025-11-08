// original version
// check the first character of all strings, then the second, and so on
class Solution {
    public String longestCommonPrefix(String[] strs) {
        int len = strs[0].length();
        for(String s: strs)
            len = Math.min(len, s.length());
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < len; i++) {
            char c = strs[0].charAt(i);
            for(String s: strs)
                if(s.charAt(i) != c)
                    return builder.toString();
            builder.append(c);
        }
        return builder.toString();
    }
}