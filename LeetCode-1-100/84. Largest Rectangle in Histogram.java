/* worng version: brute force
// Time Limit Exceed
class Solution {
    private static final int MAX_HEIGHT = 10000;

    public int largestRectangleArea(int[] heights) {
        int largestArea = 0, smallestHeight = MAX_HEIGHT + 1;
        for (int i = 0; i < heights.length; i++) {
            smallestHeight = MAX_HEIGHT + 1;
            for (int j = i; j < heights.length; j++) {
                smallestHeight = Math.min(smallestHeight, heights[j]);
                largestArea = Math.max(largestArea, smallestHeight * (j - i + 1));
            }
        }
        return largestArea;
    }
}
end worng version */

/* worng version (modified): brute force
// keep track of the highest bar within each interval
// Time Limit Exceed
class Solution {
    private static final int MAX_HEIGHT = 10000;

    public int largestRectangleArea(int[] heights) {
        int largestArea = 0, currentArea = 0, currentHeight = MAX_HEIGHT + 1;
        for (int i = 0; i < heights.length; i++) {
            currentArea = 0;
            currentHeight = MAX_HEIGHT + 1;
            for (int j = i; j < heights.length; j++) {
                if (heights[j] >= currentHeight)
                    currentArea += currentHeight;
                else {
                    largestArea = Math.max(largestArea, currentArea);
                    currentHeight = heights[j];
                    currentArea = currentHeight * (j - i + 1);
                }
            }
            largestArea = Math.max(largestArea, currentArea);
        }
        return largestArea;
    }
}
end worng version (modified) */

/* prompted version: monotonic stack
// push all positions onto the stack, including those with the same height
class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length, largestArea = 0;

        List<int[]> stack = new ArrayList<int[]>();
        // beginning of the stack
        stack.addFirst(new int[] { -1, 0 });

        int currentHeight = 0, lastHeight = 0;
        for (int i = 0; i < len; i++) {
            currentHeight = heights[i];

            // get the rectangle [?, i - 1]
            while (lastHeight > currentHeight) {
                stack.removeFirst();
                int largestHeight = lastHeight;
                int[] leftEnd = stack.get(0);
                lastHeight = leftEnd[1];

                // update largest area
                largestArea = Math.max(largestArea, largestHeight * (i - leftEnd[0] - 1));
                //System.out.format("(%d, %d) %d\r\n", leftEnd[0] + 1, i - 1, largestHeight);
            }

            // update this height
            lastHeight = currentHeight;
            stack.addFirst(new int[] { i, currentHeight });
        }

        // ending of the stack
        currentHeight = 0;

        // get the rectangle [?, len - 1]
        while (lastHeight > currentHeight) {
            stack.removeFirst();
            int largestHeight = lastHeight;
            int[] leftEnd = stack.get(0);
            lastHeight = leftEnd[1];

            // update largest area
            largestArea = Math.max(largestArea, largestHeight * (len - leftEnd[0] - 1));
            //System.out.format("(%d, %d) %d\r\n", leftEnd[0] + 1, len - 1, largestHeight);
        }

        return largestArea;
    }
}
end prompted version */

/* prompted version (modified): monotonic stack
// a position in the stack represents a range where all bars up to the next index share the same remaining height
// hence, each distinct height appears only once in the stack
class Solution {
    public int largestRectangleArea(int[] heights) {
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

            //System.out.format("i = %d: %d\r\n", i, currentHeight);

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
                    //System.out.format("[%d, %d] %d\r\n", leftPos[0], i - 1, lastHeight);

                    // update the top of the stack
                    stack.removeFirst();
                    int[] newPos = stack.get(0);
                    lastHeight = newPos[1];

                    if (lastHeight > currentHeight)
                        leftPos = newPos;
                    else
                        break;
                }

                // after popping: add this height(now left in [leftPos[0], i]) to the stack
                if (lastHeight < currentHeight) {
                    leftPos[1] = currentHeight;
                    lastHeight = currentHeight;
                    stack.addFirst(leftPos);
                }
                i++;
            }

//            // output the stack
//            for (int[] e : stack)
//                System.out.format("(%d, %d) -> ", e[0], e[1]);
//            System.out.println();
        }

        return maxArea;
    }
}
end prompted version (modified) */

// method 2: monotonic stack
// use a monotonic stack to compute only the left and right boundaries for each position
// push each position after popping all trailing bars that are not higher than the current one
class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length, maxArea = 0;

        // keep the left and the right end of every position
        int[] leftEnd = new int[len], rightEnd = new int[len];

        List<int[]> stack = new ArrayList<int[]>();
        // beginning of the stack
        stack.addFirst(new int[] { -1, 0 });

        int i = 0;
        while (i <= len) {
            // consider the ending of the stack
            int currentHeight = i == len ? 0 : heights[i];
            int[] lastPos = stack.get(0);
            int lastIdx = lastPos[0], lastHeight = lastPos[1];

            if (lastHeight < currentHeight) {
                // update the stack
                stack.addFirst(new int[] { i, currentHeight });

                // update the left end array
                leftEnd[i] = lastIdx;

                // iterate
                i++;
            } else {
                // avoid removing (-1, 0)
                if (lastIdx >= 0) {
                    stack.removeFirst();

                    // update the right end array
                    rightEnd[lastIdx] = i;
                } else {
                    // update the stack
                    stack.addFirst(new int[] { i, currentHeight });

//                    // update the left and the right end array
//                    leftEnd[i] = -1;
//                    right[i] = len;

                    // iterate
                    i++;
                }
            }
        }

        // calculate
        for (i = 0; i < len; i++)
            maxArea = Math.max(maxArea, heights[i] * (rightEnd[i] - leftEnd[i] - 1));

//        // output the left and the right end array
//        System.out.println(Arrays.toString(leftEnd));
//        System.out.println(Arrays.toString(rightEnd));

        return maxArea;
    }
}