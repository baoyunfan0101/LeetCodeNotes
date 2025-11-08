/* original version
// for every character in new string, calculate its position in original string
class Solution {
    public String convert(String s, int numRows) {
        int len = s.length();
        if(numRows == 1 || len <= numRows)
            return s;
        else if(numRows == 2) {
            StringBuilder res = new StringBuilder();
            for(int i = 0; i < len; i += 2)
                res.append(s.charAt(i));
            for(int i = 1; i < len; i += 2)
                res.append(s.charAt(i));
            return res.substring(0, res.length());
        }
        int temp = 2 * numRows - 2; // a "V" pattern contains (2 * n - 2) characters
        StringBuilder res = new StringBuilder();
        // the first line
        for(int i = 0; i < len; i += temp)
            res.append(s.charAt(i));
        // middle lines
        for(int i = 1; i < numRows - 1; i++) {
            res.append(s.charAt(i));
            for(int j = temp; j - i < len; j += temp) {
                res.append(s.charAt(j - i));
                if(j + i < len)
                    res.append(s.charAt(j + i));
            }
        }
        // the last line
        for(int i = numRows - 1; i < len; i += temp)
            res.append(s.charAt(i));
        return res.substring(0, res.length());
    }
}
end original version */

/* better version
// use StringBuffer, instead of StringBuilder
class Solution {
    public String convert(String s, int numRows) {
        int len = s.length();
        if(numRows == 1 || len <= numRows)
            return s;
        else if(numRows == 2) {
            StringBuffer res = new StringBuffer();
            for(int i = 0; i < len; i += 2)
                res.append(s.charAt(i));
            for(int i = 1; i < len; i += 2)
                res.append(s.charAt(i));
            return res.toString();
        }
        int temp = 2 * numRows - 2; // a "V" pattern contains (2 * n - 2) characters
        StringBuffer res = new StringBuffer();
        // the first line
        for(int i = 0; i < len; i += temp)
            res.append(s.charAt(i));
        // middle lines
        for(int i = 1; i < numRows - 1; i++) {
            res.append(s.charAt(i));
            int j = temp;
            while(j + i < len) {
                res.append(s.charAt(j - i));
                res.append(s.charAt(j + i));
                j += temp;
            }
            if(j - i < len)
                res.append(s.charAt(j - i));
        }
        // the last line
        for(int i = numRows - 1; i < len; i += temp)
            res.append(s.charAt(i));
        return res.toString();
    }
}
end better version */

// another better version
// use String.toCharArrya() to convert String to char[] first
class Solution {
    public String convert(String s, int numRows) {
        char[] ca = s.toCharArray();
        int len = ca.length;
        if(numRows == 1 || len <= numRows)
            return s;
        else if(numRows == 2) {
            StringBuffer res = new StringBuffer();
            for(int i = 0; i < len; i += 2)
                res.append(ca[i]);
            for(int i = 1; i < len; i += 2)
                res.append(ca[i]);
            return res.toString();
        }
        int temp = 2 * numRows - 2; // a "V" pattern contains (2 * n - 2) characters
        StringBuffer res = new StringBuffer();
        // the first line
        for(int i = 0; i < len; i += temp)
            res.append(ca[i]);
        // middle lines
        for(int i = 1; i < numRows - 1; i++) {
            res.append(ca[i]);
            int j = temp;
            while(j + i < len) {
                res.append(ca[j - i]);
                res.append(ca[j + i]);
                j += temp;
            }
            if(j - i < len)
                res.append(ca[j - i]);
        }
        // the last line
        for(int i = numRows - 1; i < len; i += temp)
            res.append(ca[i]);
        return res.toString();
    }
}