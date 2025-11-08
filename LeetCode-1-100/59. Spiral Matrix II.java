// original version
class Solution {
    public int[][] generateMatrix(int n) {
        int up = 0, down = n - 1, left = 0, right = n - 1;
        int i = 0, j = 0, count = 1;
        int[][] matrix = new int[n][n];
        matrix[0][0] = count++;
        while (true) {
            up++;
            if (left > right)
                break;
            // left -> right
            while (j < right) {
                j++;
                matrix[i][j] = count++;
            }
            right--;
            if (up > down)
                break;
            // up -> down
            while (i < down) {
                i++;
                matrix[i][j] = count++;
            }
            down--;
            if (left > right)
                break;
            // right -> left
            while (j > left) {
                j--;
                matrix[i][j] = count++;
            }
            left++;
            if (up > down)
                break;
            // down -> up
            while (i > up) {
                i--;
                matrix[i][j] = count++;
            }
        }
        return matrix;
    }
}