/* original version
// core idea: similar to method 3 from problem #32. Longest Valid Parentheses
// scan from left to rignt while keeping track of the current highest bar:
// 1. when encountering a lower bar, trap water equal to the difference in heights
// 2. when encountering a higher bar, the previously recorded trapped water becomes valid, so add it to the total and update the current highest bar
// however, scanning only from left to right misses puddles that appear after the global highest bar
// because the left boundary is too tall to find a matching right boundary
// therefore, scan in reverse as well and conbine the results
class Solution {
    public int trap(int[] height) {
        int len = height.length, res = 0, highestPos = 0;
        if (len <= 2)
            return 0;

        // from left to right
        int i = 0, leftHeight = 0, currentRes = 0;
        while (i < len && height[i] == 0)
            i++;
        // if all heights are 0
        if (i == len)
            return 0;
        highestPos = i;
        leftHeight = height[i++];
        while (i < len) {
            if (height[i] >= leftHeight) {
                res += currentRes;
                //System.out.format("left -> right, i = %d, res += %d\r\n", i, currentRes);
                highestPos = i;
                leftHeight = height[i];
                currentRes = 0;
            } else
                currentRes += leftHeight - height[i];
            i++;
        }

        // from right to left (height[highestPos] <- height[len - 1])
        int rightHeight = 0;
        i = len - 1;
        currentRes = 0;
        while (height[i] == 0)
            i--;
        rightHeight = height[i--];
        while (i >= highestPos) {
            if (height[i] >= rightHeight) {
                res += currentRes;
                //System.out.format("right -> left, i = %d, res += %d\r\n", i, currentRes);
                rightHeight = height[i];
                currentRes = 0;
            } else
                currentRes += rightHeight - height[i];
            i--;
        }
        return res;
    }
}
end original version */

/* method 2: monotonic stack
// for each bar `height[i]`:
// 1. if it's lower than the top of the stack, push it onto the stack
// 2. if it's higher than the top of the stack:
//     2.1. pop the top bar (call it mid), then:
//         width = i - stack.peek() - 1 (distance between the current bar and the new stack top)
//         height = min(height[i], height[stack.peek()]) - height[mid]
//     2.2. repeat the process until the current bar becomes the lowest one
class Solution {
    public int trap(int[] height) {
        int len = height.length;
        LinkedList<Integer> stack = new LinkedList<Integer>();

        int i = 0, res = 0;
        while (i < len) {
            if (!stack.isEmpty()) {
                int topIdx = stack.peekFirst();
                // there is an area between height[nextIdx] and height[i]
                if (height[topIdx] < height[i]) {
                    stack.removeFirst();
                    // there is no higher left wall
                    if (!stack.isEmpty()) {
                        int nextIdx = stack.peekFirst();
                        res += (Math.min(height[nextIdx], height[i]) - height[topIdx]) * (i - nextIdx - 1);
                        //System.out.format("i = %d, res = %d\r\n", i, res);
                        continue;
                    }
                }
            }
            stack.addFirst(i++);
        }

        return res;
    }
}
end method 2 */

// method 3: two pointers
// similar to original version
// always move the lower pointer inward while recording trapped water
class Solution {
    public int trap(int[] height) {
        int len = height.length, left = 0, right = len - 1, leftHeight = 0, rightHeight = 0, res = 0;

        while (left < right) {
            leftHeight = Math.max(height[left], leftHeight);
            rightHeight = Math.max(height[right], rightHeight);
            if (height[left] <= height[right])
                res += leftHeight - height[left++];
            else
                res += rightHeight - height[right--];
        }

        return res;
    }
}