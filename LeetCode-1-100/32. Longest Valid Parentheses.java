/* original version
// try every index as a starting position
// implement a stack to find the longest valid parentheses substring from each starting position
class Solution {
    private int largestValidString(String s, int n) {
        int len = s.length(), leftParenthesis = 0, res = n;
        while (leftParenthesis >= 0 && n < len) {
            switch (s.charAt(n)) {
                case '(':
                    leftParenthesis++;
                    break;
                case ')':
                    leftParenthesis--;
                    break;
                default:
            }
            n++;
            if (leftParenthesis == 0)
                res = n;
        }
        return res;
    }

    public int longestValidParentheses(String s) {
        int len = s.length(), res = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                int largestLen = largestValidString(s, i) - i;
                if (largestLen > res)
                    res = largestLen;
            }
        }
        return res;
    }
}
end original version */

/* method 1: dynamic programming
// core idea: surround an existing valid parentheses substring with a new pair of "()"
// memory: dp[i] stores the length of the longest valid parentheses ending at position i
// algorithm: keep track of the index to the left of the longest valid parentheses, and for each position, try to extend leftward
class Solution {
    public int longestValidParentheses(String s) {
        int len = s.length();
        if (len == 0)
            return 0;

        int[] longestValid = new int[len];
        longestValid[0] = 0;
        int res = 0;
        for (int i = 1; i < len; i++) {
            // "(..valid parentheses..)"
            int leftParenthesis = i - 1 - longestValid[i - 1];
            if (s.charAt(i) == '(' || leftParenthesis < 0 || s.charAt(leftParenthesis) != '(')
                longestValid[i] = 0;
            else { // '(' is to the left (and with in the String) && ')' is to the right (and about to be added)
                longestValid[i] = longestValid[i - 1] + 2;
                if (leftParenthesis > 0) // "..valid parentheses..(..valid parentheses..)"
                    longestValid[i] += longestValid[leftParenthesis - 1];
                res = longestValid[i] > res ? longestValid[i] : res;
            }
        }
        //System.out.println(Arrays.toString(longestValid));
        return res;
    }
}
end method 1 */

/* method 2: stack
// core idea: if ')' appears more times than '(', the left part is wasted
// memory: indices
// algorithm: remove valid pairs so that the top of the stack always holds the last unmatched index
class Solution {
    public int longestValidParentheses(String s) {
        int len = s.length();
        if (len == 0)
            return 0;

        LinkedList<Integer> stack = new LinkedList<Integer>();
        int nextEnd = -1, i = 0, res = 0;
        while (i < len) {
            if (s.charAt(i) == '(')
                stack.addFirst(i);
            else {
                if (stack.isEmpty()) {
                    nextEnd = i;
                } else if (s.charAt(stack.removeFirst()) == ')') { // ')' already exists, it's wasted
                    stack.clear();
                    nextEnd = i;
                } else {
                    int validLength;
                    if (stack.isEmpty())
                        validLength = i - nextEnd;
                    else
                        validLength = i - stack.peekFirst();
                    res = validLength > res ? validLength : res;
                }
            }
            i++;
        }
        return res;
    }
}
end method 2 */

// method 3: no extra space needed
// similar to the previous method, but only keep track of the difference between '(' and ')' and the last unmatched index
// when scanning from left to right, it misses cases with too many '(' (e.g., "((((()")
// therefore, scan in reverse as well, and take the maximum result
class Solution {
    public int longestValidParentheses(String s) {
        int len = s.length();
        if (len == 0)
            return 0;

        // from left to right: diff = (number of '(') - (number of ')')
        int diff = 0, nextEnd = -1, res = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(')
                diff++;
            else {
                diff--;
                if (diff == 0) {
                    int validLength = i - nextEnd;
                    res = validLength > res ? validLength : res;
                } else if (diff < 0) {
                    diff = 0;
                    nextEnd = i;
                }
            }
        }

        // from right to left: diff = (number of ')') - (number of '(')
        diff = 0;
        nextEnd = len;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == ')')
                diff++;
            else {
                diff--;
                if (diff == 0) {
                    int validLength = nextEnd - i;
                    res = validLength > res ? validLength : res;
                } else if (diff < 0) {
                    diff = 0;
                    nextEnd = i;
                }
            }
        }
        return res;
    }
}