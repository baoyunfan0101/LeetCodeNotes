/* original version
// put it into a String
class Solution {
    public boolean isPalindrome(int x) {
        StringBuilder builder = new StringBuilder();
        while(x != 0) {
            int temp = x % 10;
            builder.append(temp);
            x /= 10;
        }
        String s = builder.toString();
        int len = s.length();
        for(int i = 0; i < len / 2; i++)
            if(s.charAt(i) != s.charAt(len - i - 1))
                return false;
        return true;
    }
}
end original version */

// better version
// reverse the left half (rounded up)
// if number of digits is even: original == reversed
// if number of digits is odd: original == reversed / 10
class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0 || (x > 0 && x % 10 == 0))
            return false;
        int reversed = 0;
        while(x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        return x == reversed || x == reversed / 10;
    }
}