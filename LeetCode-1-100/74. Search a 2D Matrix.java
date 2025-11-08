/* original version: brute force
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length, i = 0, j = 0;
        while (i < m && matrix[i][0] <= target)
            i++;
        if (--i < 0)
            return false;
        while (j < n) {
            if (matrix[i][j] < target)
                j++;
            else if (matrix[i][j] == target)
                return true;
            else
                break;
        }
        return false;
    }
}
end original version */

// method 1: double binary search
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int leftM = 0, rightM = matrix.length - 1, leftN = 0, rightN = matrix[0].length - 1;

        while (leftM < rightM) {
            int i = (leftM + rightM + 1) >> 1;
            if (matrix[i][0] < target)
                leftM = i;
            else if (matrix[i][0] > target)
                rightM = i - 1;
            else
                return true;
        }

        while (leftN <= rightN) {
            int j = (leftN + rightN) >> 1;
            if (matrix[leftM][j] < target)
                leftN = j + 1;
            else if (matrix[leftM][j] > target)
                rightN = j - 1;
            else
                return true;
        }

        return false;
    }
}