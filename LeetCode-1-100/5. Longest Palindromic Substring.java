/* original version
// check every substirng
class Solution {
    private boolean isPalindrome(String s, int head, int end) {
        while(head < end)
            if(s.charAt(head++) != s.charAt(end--))
                return false;
        return true;
    }
    public String longestPalindrome(String s) {
        int len = s.length();
        for(int i = len; i > 1; i--)
            for(int j = 0; j < len - i + 1; j++)
                if(isPalindrome(s, j, j + i - 1))
                    return s.substring(j, j + i);
        return s.substring(0, 1);
    }
}
end original version */

// better version
// considering every character as the center, try to extend it to both sides
class Solution {
    public String longestPalindrome(String s) {
        int len = s.length(), maxLength = 1, maxIndex = 0;
        // The length of the substring is an even number.
        for(int i = 0; i < len - 1; i++) {
            int head = i, end = i + 1, l = 0;
            while(head >= 0 && end < len)
                if(s.charAt(head--) == s.charAt(end++))
                    l += 2;
                else
                    break;
            if(l > maxLength) {
                maxLength = l;
                maxIndex = i - l / 2 + 1;
            }
        }
        // The length of the substring is an odd number, starting from current maxLength / 2
        for(int i = maxLength / 2; i < len; i++) {
            int head = i - 1, end = i + 1, l = 1;
            while(head >= 0 && end < len)
                if(s.charAt(head--) == s.charAt(end++))
                    l += 2;
                else
                    break;
            if(l > maxLength) {
                maxLength = l;
                maxIndex = i - l / 2;
            }
        }
        return s.substring(maxIndex, maxIndex + maxLength);
    }
}