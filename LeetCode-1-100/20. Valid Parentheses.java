// original version
// implement a stack
class Solution {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0)
            return false;
        List<Character> stack = new LinkedList<Character>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.addFirst(c);
                    break;
                case ')':
                    if (!stack.isEmpty() && stack.getFirst() == '(')
                        stack.removeFirst();
                    else
                        return false;
                    break;
                case ']':
                    if (!stack.isEmpty() && stack.getFirst() == '[')
                        stack.removeFirst();
                    else
                        return false;
                    break;
                case '}':
                    if (!stack.isEmpty() && stack.getFirst() == '{')
                        stack.removeFirst();
                    else
                        return false;
                    break;
            }
        }
        return stack.isEmpty();
    }
}