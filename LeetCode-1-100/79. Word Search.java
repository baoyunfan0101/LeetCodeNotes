// original method: dfs
// find the starting position and perform DFS while marking visited cells
// in each step, explore four directions and backtrack afterward
class Solution {
    private char[][] board;
    private char[] wordArray;
    private int m, n, len;
    private boolean[][] unavailibility;

    private boolean dfs(int i, int j, int idx) {
        // end recursion
        if (idx == len)
            return true;

        // left
        if (i > 0 && !unavailibility[i - 1][j] && board[i - 1][j] == wordArray[idx]) {
            unavailibility[i - 1][j] = true;
            if (dfs(i - 1, j, idx + 1))
                return true;
            unavailibility[i - 1][j] = false;
        }

        // right
        if (i + 1 < m && !unavailibility[i + 1][j] && board[i + 1][j] == wordArray[idx]) {
            unavailibility[i + 1][j] = true;
            if (dfs(i + 1, j, idx + 1))
                return true;
            unavailibility[i + 1][j] = false;
        }

        // up
        if (j > 0 && !unavailibility[i][j - 1] && board[i][j - 1] == wordArray[idx]) {
            unavailibility[i][j - 1] = true;
            if (dfs(i, j - 1, idx + 1))
                return true;
            unavailibility[i][j - 1] = false;
        }

        // down 
        if (j + 1 < n && !unavailibility[i][j + 1] && board[i][j + 1] == wordArray[idx]) {
            unavailibility[i][j + 1] = true;
            if (dfs(i, j + 1, idx + 1))
                return true;
            unavailibility[i][j + 1] = false;
        }

        return false;
    }

    public boolean exist(char[][] board, String word) {
        this.board = board;
        wordArray = word.toCharArray();
        m = board.length;
        n = board[0].length;
        len = word.length();
        unavailibility = new boolean[m][n];

        // find the start
        char start = wordArray[0];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (board[i][j] == start) {
                    unavailibility[i][j] = true;
                    if (dfs(i, j, 1))
                        return true;
                    unavailibility[i][j] = false;
                }

        return false;
    }
}