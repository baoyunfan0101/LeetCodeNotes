// original version: dynamic programming
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] paths = new int[m][n];
        // initilization
        if (obstacleGrid[0][0] == 1)
            return 0;
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1)
                break;
            paths[i][0] = 1;
        }
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 1)
                break;
            paths[0][j] = 1;
        }
        // dynamic programming
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (obstacleGrid[i][j] == 0)
                    paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
        //System.out.println(Arrays.deepToString(paths));
        return paths[m - 1][n - 1];
    }
}