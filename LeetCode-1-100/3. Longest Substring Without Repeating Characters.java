/* original version: brute force
class Solution {
    // longest valid substring starting from pos
    int longestSubstring(String s, int pos) {
        Set<Character> set = new HashSet<Character>();
        int maxLength = 0, len = s.length();
        for(int i = pos; i < len; i++) {
            char c = s.charAt(i);
            if(set.contains(c))
                return maxLength;
            else {
                set.add(c);
                maxLength++;
            }
        }
        return maxLength;
    }
    public int lengthOfLongestSubstring(String s) {
        int res = 0, len = s.length();
        for(int i = 0; i < len; i++) {
            int temp = longestSubstring(s, i);
            res = res > temp? res: temp;
            if(res == len - i)
                break;
        }
        return res;
    }
}
end original version */

/* wrong version
// whenever encounter a duplicate character, clear
// it's wrong because a valid substring can step over a duplicate character
// e.g. "abcdaefga" -> "bcdaefg"
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0, len = s.length();
        Set<Character> set = new HashSet<Character>();
        for(char c : s.toCharArray()) {
            if(set.contains(c)) {
                set.clear();
                res = 0;
            }
            else {
                set.add(c);
                res++;
            }
        }
        return res;
    }
}
end wrong version  */

/* wrong version
// record each character's last occurence position & current max length
// it's wrong because only max length of every character is considered, but when combine all characters together?
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, int[]> map = new HashMap<Character, int[]>();
        int len = s.length();
        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {
                int[] temp = map.get(c);
                temp[1] = (i - temp[0]) > temp[1]? (i - temp[0]): temp[1];
                temp[0] = i;
            }
            else {
                map.put(c, new int[]{i, 1}); // last occurence position & current max length
            }
        }
        int res = len;
        for(char c: map.keySet()) {
            int[] temp = map.get(c);
            res = temp[1] < res? temp[1]: res;
        }
        return res;
    }
}
end wrong version */

// better version: sliding window
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int res = 0, len = s.length(), head = 0;
        for(int end = 0; end < len; end++) {
            char c = s.charAt(end);
            // System.out.println("----- -----");
            // System.out.println("Now end = " + end);
            if(map.containsKey(c)) {
                int temp = map.get(c) + 1;
                head = temp > head? temp: head;
                map.put(c, end);
                // System.out.println("contain " + c);
                // System.out.println("head = " + head);
            }
            else {
                map.put(c, end);
                // System.out.println("not contain " + c);
                // System.out.println("head = " + head);
            }
            int currentLength = end - head + 1;
            res = currentLength > res? currentLength: res;
            // System.out.println("res = " + res);
        }
        return res;
    }
}