// a. prefix sum 2D
class NumMatrix {
    int[][] arr2D = null;
    int[][] prefix2D = null;

    public NumMatrix(int[][] matrix) {
        this.arr2D = matrix;
        int m = this.arr2D.length, n = this.arr2D[0].length;

        this.prefix2D = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += this.arr2D[i][j];
                this.prefix2D[i + 1][j + 1] = this.prefix2D[i][j + 1] + rowSum;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return (
                this.prefix2D[row2 + 1][col2 + 1]
                        - this.prefix2D[row2 + 1][col1]
                        - this.prefix2D[row1][col2 + 1]
                        + this.prefix2D[row1][col1]
        );
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */