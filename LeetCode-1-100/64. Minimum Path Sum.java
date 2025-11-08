/* worng version: backtracking
// Time Limit Exceed
class Solution {
    private int[][] grid;
    private int m;
    private int n;
    private int minSum = Integer.MAX_VALUE;

    private void backtrack(int i, int j, int sum) {
        sum += grid[i][j];
        if (i == m - 1 && j == n - 1) {
            minSum = Math.min(minSum, sum);
            return;
        }
        if (i < m - 1)
            backtrack(i + 1, j, sum);
        if (j < n - 1)
            backtrack(i, j + 1, sum);
        sum -= grid[i][j];
    }

    public int minPathSum(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        backtrack(0, 0, 0);
        return minSum;
    }
}
end worng version */

// original version: dynamic programming
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // paths[i][j]: the shortest path from grid[0][0] to grid[i][j]
        int[][] paths = new int[m][n];
        // initilization
        paths[0][0] = grid[0][0];
        for (int i = 1; i < m; i++)
            paths[i][0] = grid[i][0] + paths[i - 1][0];
        for (int j = 1; j < n; j++)
            paths[0][j] = grid[0][j] + paths[0][j - 1];
        // dynamic programming
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                paths[i][j] = grid[i][j] + Math.min(paths[i - 1][j], paths[i][j - 1]);
        //System.out.println(Arrays.deepToString(paths));
        return paths[m - 1][n - 1];
    }
}