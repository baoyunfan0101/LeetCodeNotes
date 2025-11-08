// original version: backtracking
// keep track of (1) number of used left prentheses, (2) difference between left and right prentheses
class Solution {
    public void recursive(List<String> list, StringBuilder builder, int maxParentheses, int currentParentheses, int leftParentheses) {
        if (currentParentheses == maxParentheses && leftParentheses == 0) {
            list.add(builder.toString());
            return;
        }
        if (currentParentheses < maxParentheses) {
            builder.append('(');
            recursive(list, builder, maxParentheses, currentParentheses + 1, leftParentheses + 1);
            builder.delete(builder.length() - 1, builder.length());
        }
        if (leftParentheses > 0) {
            builder.append(')');
            recursive(list, builder, maxParentheses, currentParentheses, leftParentheses - 1);
            builder.delete(builder.length() - 1, builder.length());
        }
        return;
    }

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        recursive(list, builder, n, 0, 0);
        return list;
    }
}