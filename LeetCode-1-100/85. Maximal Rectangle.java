// enlightened version: dynamic programming with monotonic stack
// reuse problem #84. Largest Rectangle in Histogram
// for each row and its consecutive rows above, treat each column's '1's as a bar
class Solution {
    // problem #84. Largest Rectangle in Histogram
    private int largestRectangleArea(int[] heights) {
        int len = heights.length, maxArea = 0;

        List<int[]> stack = new ArrayList<int[]>();
        // beginning of the stack
        stack.addFirst(new int[] { -1, 0 });

        int currentHeight = 0, lastHeight = 0, i = 0;
        while (i <= len) {
            // ending of the stack
            if (i == len)
                currentHeight = 0;
            else
                currentHeight = heights[i];

            if (lastHeight < currentHeight) {
                lastHeight = currentHeight;
                stack.addFirst(new int[] { i, currentHeight });
                i++;
            } else if (lastHeight == currentHeight) {
                i++;
            } else {
                // get the rectangle [stack.get(0)[0], i - 1]
                int[] leftPos = stack.get(0);
                while (true) {
                    // update largest area
                    maxArea = Math.max(maxArea, lastHeight * (i - leftPos[0]));

                    // update the top of the stack
                    stack.removeFirst();
                    int[] newPos = stack.get(0);
                    lastHeight = newPos[1];

                    if (lastHeight > currentHeight)
                        leftPos = newPos;
                    else
                        break;
                }

                // after popping: add this height(now left in [leftPost[0], i]) to the stack
                if (lastHeight < currentHeight) {
                    leftPos[1] = currentHeight;
                    lastHeight = currentHeight;
                    stack.addFirst(leftPos);
                }
                i++;
            }
        }

        return maxArea;
    }

    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] map = new int[n];

        // dynamic programming
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == '1')
                    map[j]++;
                else
                    map[j] = 0;
            maxArea = Math.max(maxArea, largestRectangleArea(map));
        }

        return maxArea;
    }
}