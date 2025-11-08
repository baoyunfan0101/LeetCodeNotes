// original version: backtracking recursion
// implementa a class Status to record the value and valid choices of each position
// on every update, recalculate the valid choices for all positions and track the one with the fewest options
// during each backtracking recursion, update and select the position with the fewest choices, then make a guess
class Solution {
    private static final int[][] AREA = new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
    private char[][] board;
    private Status[][] boardStatus;

    class Status {
        Integer val;
        boolean[] choices;

        // constructor: when c == '.', val == null
        Status(char c) {
            if (c != '.')
                val = (int) c - '1';
        }
    }

    private boolean[] findArea(int x, int y) {
        boolean[] choices = new boolean[9];
        for (int i : AREA[x / 3])
            for (int j : AREA[y / 3])
                if (boardStatus[i][j].val != null && i != x && j != y)
                    choices[boardStatus[i][j].val] = true;
        return choices;
    }

    private boolean[] findRow(int x, int y) {
        boolean[] choices = new boolean[9];
        for (int j = 0; j < 9; j++)
            if (boardStatus[x][j].val != null && j != y)
                choices[boardStatus[x][j].val] = true;
        return choices;
    }

    private boolean[] findColumn(int x, int y) {
        boolean[] choices = new boolean[9];
        for (int i = 0; i < 9; i++)
            if (boardStatus[i][y].val != null && i != x)
                choices[boardStatus[i][y].val] = true;
        return choices;
    }

    private int findAllChoices(int x, int y, boolean[] choices) {
        boolean[] c1 = findArea(x, y);
        boolean[] c2 = findRow(x, y);
        boolean[] c3 = findColumn(x, y);
        int res = 0;
        for (int i = 0; i < 9; i++) {
            choices[i] = c1[i] || c2[i] || c3[i];
            if (!choices[i])
                res++;
        }
        return res;
    }

    boolean updateStatus(Integer[] pos) {
        int leastChoices = 9;
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                // the value of this position is already decided
                if (boardStatus[i][j].val != null)
                    continue;
                // the first time update this position
                if (boardStatus[i][j].choices == null)
                    boardStatus[i][j].choices = new boolean[9];
                int numOfChoices = findAllChoices(i, j, boardStatus[i][j].choices);
                // if there no valid choices, failed
                if (numOfChoices == 0)
                    return false;
                // keep record of which position has got the least choices
                if (numOfChoices < leastChoices) {
                    leastChoices = numOfChoices;
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        return true;
    }

    boolean recursive() {
        Integer[] pos = new Integer[2];
        // failed
        if (!updateStatus(pos))
            return false;
        // nothing changed
        if (pos[0] == null)
            return true;
        // guess
        int n = 0, x = pos[0], y = pos[1];
        //System.out.format("(%d, %d) start trying..\r\n", x, y);
        do {
            // if failed, clear previous value
            boardStatus[x][y].val = null;
            // find an available choice
            while (n < 9 && boardStatus[x][y].choices[n])
                n++;
            if (n == 9) {
                //System.out.format("(%d, %d) cannot find answer!\r\n", x, y);
                return false;
            }
            //System.out.format("(%d, %d) = %d?\r\n", x, y, n + 1);
            boardStatus[x][y].choices[n] = true;
            boardStatus[x][y].val = n;
        } while (!recursive());
        // when guess successfully, get out of the do-while!
        return true;
    }

    public void solveSudoku(char[][] board) {
        boardStatus = new Status[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                boardStatus[i][j] = new Status(board[i][j]);
        if (recursive()) {
            for (int i = 0; i < 9; i++)
                for (int j = 0; j < 9; j++)
                    board[i][j] = Integer.toString(boardStatus[i][j].val + 1).charAt(0);
        } else
            System.out.println("Failed to solve Sudoku!");
    }
}