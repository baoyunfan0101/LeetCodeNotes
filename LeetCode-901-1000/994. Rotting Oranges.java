// a. brute force
class Solution {
    private static int m;
    private static int n;

    private boolean existUnrotting(int[][] grid) {
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 1)
                    return true;

        return false;
    }

    public int orangesRotting(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        if (!existUnrotting(grid))
            return 0;

        // create a new array for changing
        int[][] copy = new int[m][n];
        for (int i = 0; i < m; i++)
            copy[i] = grid[i].clone();

        boolean changed = false;
        int steps = 0;
        do {
            steps++;
            changed = false;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] != 1)
                        continue;
                    if ((i > 0 && grid[i - 1][j] == 2)
                            || (j > 0 && grid[i][j - 1] == 2)
                            || (i < m - 1 && grid[i + 1][j] == 2)
                            || (j < n - 1 && grid[i][j + 1] == 2)) {
                        copy[i][j] = 2;
                        changed = true;
                    }
                }
            }

            // record this step
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    grid[i][j] = copy[i][j];

        } while(changed);

        if (existUnrotting(grid))
            return -1;

        return steps - 1;
    }
}

// b. DFS
class Solution {
    private static int m;
    private static int n;

    private boolean existUnrotting(int[][] grid) {
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 1)
                    return true;

        return false;
    }

    private void dfs(int[][] grid, int i, int j, int val){
        grid[i][j] = val;
        if (i > 0 && (grid[i - 1][j] == 1 || grid[i - 1][j] > val + 1))
            dfs(grid, i - 1, j, val + 1);
        if (j > 0 && (grid[i][j - 1] == 1 || grid[i][j - 1] > val + 1))
            dfs(grid, i, j - 1, val + 1);
        if (i < m - 1 && (grid[i + 1][j] == 1 || grid[i + 1][j] > val + 1))
            dfs(grid, i + 1, j, val + 1);
        if (j < n - 1 && (grid[i][j + 1] == 1 || grid[i][j + 1] > val + 1))
            dfs(grid, i, j + 1, val + 1);
    }

    public int orangesRotting(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        if (!existUnrotting(grid))
            return 0;

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 2)
                    dfs(grid, i, j, 2);

        // find the largest value
        int maxVal = -1;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 1)
                    return -1;
                else if (grid[i][j] > maxVal)
                    maxVal = grid[i][j];

        return maxVal - 2;
    }
}

// c. BFS
class Solution {
    private static int m;
    private static int n;

    private boolean existUnrotting(int[][] grid) {
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 1)
                    return true;

        return false;
    }

    public int orangesRotting(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // each element: (x, y, step_cnt)
        LinkedList<int[]> queue = new LinkedList<int[]>();

        // add all rotten oranges into the queue
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2)
                    queue.addLast(new int[]{i, j, 0});
            }

        int step = 0;
        while (!queue.isEmpty()) {
            int[] next = queue.removeFirst();
            int i = next[0];
            int j = next[1];
            step = next[2];

            if (i > 0 && grid[i - 1][j] == 1) {
                grid[i - 1][j] = 2;
                queue.addLast(new int[]{i - 1, j, step + 1});
            }
            if (j > 0 && grid[i][j - 1] == 1) {
                grid[i][j - 1] = 2;
                queue.addLast(new int[]{i, j - 1, step + 1});
            }
            if (i < m - 1 && grid[i + 1][j] == 1) {
                grid[i + 1][j] = 2;
                queue.addLast(new int[]{i + 1, j, step + 1});
            }
            if (j < n - 1 && grid[i][j + 1] == 1) {
                grid[i][j + 1] = 2;
                queue.addLast(new int[]{i, j + 1, step + 1});
            }
        }

        if (existUnrotting(grid))
            return -1;

        return step;
    }
}