/* original version
// for each position, find its valid choices by combining the valid choices from the corresponding area, row, and column
class Solution {
    private char[][] board;
    private static final int[][] AREA = new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };

    private boolean[] findArea(int x, int y) {
        boolean[] choices = new boolean[9];
        for (int i : AREA[x / 3])
            for (int j : AREA[y / 3])
                if (board[i][j] != '.' && i != x && j != y)
                    choices[(int) board[i][j] - '1'] = true;
        return choices;
    }

    private boolean[] findRow(int x, int y) {
        boolean[] choices = new boolean[9];
        for (int j = 0; j < 9; j++)
            if (board[x][j] != '.' && j != y)
                choices[(int) board[x][j] - '1'] = true;
        return choices;
    }

    private boolean[] findColumn(int x, int y) {
        boolean[] choices = new boolean[9];
        for (int i = 0; i < 9; i++)
            if (board[i][y] != '.' && i != x)
                choices[(int) board[i][y] - '1'] = true;
        return choices;
    }

    private boolean[] findAllChoices(int x, int y) {
        boolean[] c1 = findArea(x, y);
        boolean[] c2 = findRow(x, y);
        boolean[] c3 = findColumn(x, y);
        boolean[] choices = new boolean[9];
        for (int i = 0; i < 9; i++)
            choices[i] = c1[i] || c2[i] || c3[i];
        return choices;
    }

    public boolean isValidSudoku(char[][] board) {
        this.board = board;
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    continue;
                boolean[] choices = findAllChoices(i, j);
                if (choices[(int) board[i][j] - '1'])
                    return false;
            }
        return true;
    }
}
end original version */

// better version
// for each area, row, and column, check for duplicates
class Solution {
    private static final int[][] AREA = new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };

    public boolean isValidSudoku(char[][] board) {
        // check rows
        for (int i = 0; i < 9; i++) {
            boolean[] check = new boolean[9];
            for (int j = 0; j < 9; j++)
                if (board[i][j] != '.') {
                    if (check[(int) board[i][j] - '1'])
                        return false;
                    else
                        check[(int) board[i][j] - '1'] = true;
                }
        }

        // check columns
        for (int j = 0; j < 9; j++) {
            boolean[] check = new boolean[9];
            for (int i = 0; i < 9; i++)
                if (board[i][j] != '.') {
                    if (check[(int) board[i][j] - '1'])
                        return false;
                    else
                        check[(int) board[i][j] - '1'] = true;
                }
        }

        // check areas
        for (int a = 0; a < 3; a++)
            for (int b = 0; b < 3; b++) {
                boolean[] check = new boolean[9];
                for (int i : AREA[a])
                    for (int j : AREA[b]) {
                        if (board[i][j] != '.') {
                            if (check[(int) board[i][j] - '1'])
                                return false;
                            else
                                check[(int) board[i][j] - '1'] = true;
                        }
                    }

            }
        return true;
    }
}