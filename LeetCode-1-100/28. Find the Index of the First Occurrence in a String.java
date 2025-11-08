/* original version: regular pattern matching
class Solution {
    public int strStr(String haystack, String needle) {
        int len1 = haystack.length(), len2 = needle.length(), n1 = 0, n2 = 0;
        while (n1 < len1 && n2 < len2)
            if (haystack.charAt(n1) == needle.charAt(n2)) {
                n1++;
                n2++;
            } else {
                n1 = n1 - n2 + 1;
                n2 = 0;
            }
        if (n2 >= len2)
            return n1 - len2;
        return -1;
    }
}
end original version */

// better version: KMP algorithm
class Solution {
    private int[] getNext(String needle) {
        int len = needle.length(), i = 0, j = -1;
        int[] next = new int[len];
        next[0] = -1;
        while (i < len - 1)
            if (j < 0 || needle.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (needle.charAt(i) != needle.charAt(j))
                    next[i] = j;
                else
                    next[i] = next[j];
            } else
                j = next[j];
        return next;
    }

    public int strStr(String haystack, String needle) {
        int[] next = getNext(needle);
        int len1 = haystack.length(), len2 = needle.length(), i = 0, j = 0;
        while (i < len1 && j < len2)
            if (j < 0 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else
                j = next[j];
        if (j >= len2)
            return i - len2;
        return -1;
    }
}