// original version: dynamic programming
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] paths = new int[m][n];
        // initilization
        for (int i = 0; i < m; i++)
            paths[i][0] = 1;
        for (int j = 1; j < n; j++)
            paths[0][j] = 1;
        // dynamic programming
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
        //System.out.println(Arrays.deepToString(paths));
        return paths[m - 1][n - 1];
    }
}