/* silly version: backtracking
// in each iteration, locate a zero and recurse
// on returning, set its entire row and column to zero
class Solution {
    private int[][] matrix;
    private int m, n;

    private void backtarck(int i, int j) {
        // find a zero
        while (i < m && matrix[i][j] != 0) {
            // get to next position
            if (j < n - 1)
                j++;
            else {
                i++;
                j = 0;
            }
        }

        // stop recursion
        if (i == m)
            return;

        backtarck(j < n - 1 ? i : i + 1, j < n - 1 ? j + 1 : 0);

        // set zero
        for (int a = 0; a < m; a++)
            matrix[a][j] = 0;
        for (int b = 0; b < n; b++)
            matrix[i][b] = 0;
    }

    public void setZeroes(int[][] matrix) {
        this.matrix = matrix;
        m = matrix.length;
        n = matrix[0].length;
        backtarck(0, 0);
    }
}
end silly version */

/* method 2: two signals
// basic idea: use two arrays to mark zeroes in each row and column
// optimization: reuse the first row and first column as markers
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        // remember if the first row and the first column has a zero
        boolean row0 = false, col0 = false;
        for (int j = 0; j < n; j++)
            if (matrix[0][j] == 0)
                row0 = true;
        for (int i = 0; i < m; i++)
            if (matrix[i][0] == 0)
                col0 = true;

        // find all zeroes
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
        //System.out.println(Arrays.deepToString(matrix));

        // set zero
        for (int j = 1; j < n; j++)
            if (matrix[0][j] == 0)
                for (int i = 1; i < m; i++)
                    matrix[i][j] = 0;
        for (int i = 1; i < m; i++)
            if (matrix[i][0] == 0)
                for (int j = 1; j < n; j++)
                    matrix[i][j] = 0;

        if (row0)
            for (int j = 0; j < n; j++)
                matrix[0][j] = 0;
        if (col0)
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;
    }
}
end method 2 */

// better version
// for each row:
// 1. for each column, if it's zero, set the column above to zero and simulate zeros in the last row
// 2. track whether the last row contains a zero, and set it to zero after processing
class Solution {
    public void setZeroes(int[][] matrix) {
        boolean lastRowIsZero = false;
        int i = 0, j = 0, m = matrix.length, n = matrix[0].length;
        while (i < m) {
            boolean thisRowIsZero = false;

            j = 0;
            while (j < n) {
                if (matrix[i][j] == 0) {
                    thisRowIsZero = true;
                    for (int idxRow = 0; idxRow <= i; idxRow++) // set the column above to zero
                        matrix[idxRow][j] = 0;
                }
                else if (i > 0) {
                    // at this time, the last row is not set to zero because of the zeroes in it
                    // thus, the zeroes in it is due to its column is set to zero
                    // simulate the last row is to keep all column zero information
                    if (matrix[i - 1][j] == 0)
                        matrix[i][j] = 0;
                }
                j++;
            }

            // after column zero set, set row zero
            if (lastRowIsZero)
                for (int idxCol = 0; idxCol < n; idxCol++) // set the last row to zero
                    matrix[i - 1][idxCol] = 0;
            lastRowIsZero = thisRowIsZero;

            i++;
        }
        if (lastRowIsZero)
            for (int idxCol = 0; idxCol < n; idxCol++) // set the last row to zero
                matrix[i - 1][idxCol] = 0;

    }
}