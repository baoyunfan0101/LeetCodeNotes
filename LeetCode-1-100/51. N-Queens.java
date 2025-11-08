/* original version: backtracking
// use an array of size n to track columns
// use a (2 * n - 1) array to track main diagonals
// use another (2 * n - 1) array to track anti-diagonals
class Solution {
    private int n;
    private boolean[] column;
    private boolean[] diagonal;
    private boolean[] backDiagonal;
    private int[] position;
    private List<List<String>> result;

    private void recordResult() {
        List<String> thisResult = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            StringBuilder thisRow = new StringBuilder();
            thisRow.append(".".repeat(position[i]));
            thisRow.append('Q');
            thisRow.append(".".repeat(n - position[i] - 1));
            thisResult.add(thisRow.toString());
        }
        result.add(thisResult);
    }

    private void backtrack(int idx) {
        if (idx == n) {
            recordResult();
            return;
        }
        for (int i = 0; i < n; i++) {
            int diagonalIdx = i + idx;
            int backDiagonalIdx = n + i - idx - 1;
            if (!column[i] && !diagonal[diagonalIdx] && !backDiagonal[backDiagonalIdx]) {
                position[idx] = i;
                column[i] = true;
                diagonal[diagonalIdx] = true;
                backDiagonal[backDiagonalIdx] = true;
                backtrack(idx + 1);
                column[i] = false;
                diagonal[diagonalIdx] = false;
                backDiagonal[backDiagonalIdx] = false;
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        column = new boolean[n];
        diagonal = new boolean[2 * n - 1]; // '/'
        backDiagonal = new boolean[2 * n - 1]; // '\'
        // queen's position in each row
        position = new int[n];
        result = new ArrayList<List<String>>();
        backtrack(0);
        return result;
    }
}
end original version */

/* method 2: bitwise operation
// use three integers to track columns, diagonals, and anti-diagonals
// for each iteration:
// 1. bitwise OR the three trackers to find available positions for the current row
// 2. update column tracker with the intended position (bitwise OR)
// 3. update diagonal tracker with the intended position (bitwise OR), then shift left by 1
// 4. update anti-diagonal tracker with the intended position (bitwise OR), then shift right by 1
// function stack automatically handles backtracking of tracker states
class Solution {
    private int n;
    private int[] position;
    private List<List<String>> result;

    private void recordResult() {
        List<String> thisResult = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            StringBuilder thisRow = new StringBuilder();
            thisRow.append(".".repeat(position[i]));
            thisRow.append('Q');
            thisRow.append(".".repeat(n - position[i] - 1));
            thisResult.add(thisRow.toString());
        }
        result.add(thisResult);
    }

    private void backtrack(int idx, int column, int diagonal, int backDiagonal) {
        if (idx == n) {
            recordResult();
            return;
        }
        int occupied = column | diagonal | backDiagonal;
        for (int i = 0; i < n; i++) {
            if ((occupied >> (n - 1 - i)) % 2 == 0) {
                position[idx] = i;
                //System.out.format("idx = %d, i = %d, occupied = %s\r\n", idx, i, Integer.toBinaryString(newOccupied));
                backtrack(
                        idx + 1,
                        column | (1 << (n - 1 - i)),
                        (diagonal | (1 << (n - 1 - i))) << 1,
                        (backDiagonal | (1 << (n - 1 - i))) >> 1
                    );
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        // queen's position in each row
        position = new int[n];
        result = new ArrayList<List<String>>();
        backtrack(0, 0, 0, 0);
        return result;
    }
}
end method 2 */

// method 2 (modified): bitwise operation
// same as the previous method
// each time updating the diagonal tracker, keep only the rightmost n bits and discard the overflow bits on the right
// therefore, prune the recursion when all positions are occupied
class Solution {
    private int n;
    private int[] position;
    private List<List<String>> result;
    private int maxOccupied;

    private void recordResult() {
        List<String> thisResult = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            StringBuilder thisRow = new StringBuilder();
            thisRow.append(".".repeat(position[i]));
            thisRow.append('Q');
            thisRow.append(".".repeat(n - position[i] - 1));
            thisResult.add(thisRow.toString());
        }
        result.add(thisResult);
    }

    private void backtrack(int idx, int column, int diagonal, int backDiagonal) {
        if (idx == n) {
            recordResult();
            return;
        }
        int occupied = column | diagonal | backDiagonal;
        if (occupied >= maxOccupied)
            return;
        for (int i = 0; i < n; i++) {
            if ((occupied >> (n - 1 - i)) % 2 == 0) {
                position[idx] = i;
                //System.out.format("idx = %d, i = %d, occupied = %s\r\n", idx, i, Integer.toBinaryString(newOccupied));
                backtrack(
                        idx + 1,
                        column | (1 << (n - 1 - i)),
                        ((diagonal | (1 << (n - 1 - i))) << 1) & maxOccupied,
                        (backDiagonal | (1 << (n - 1 - i))) >> 1
                    );
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        // if all positions are occupied
        maxOccupied = (1 << n) - 1;
        // queen's position in each row
        position = new int[n];
        result = new ArrayList<List<String>>();
        backtrack(0, 0, 0, 0);
        return result;
    }
}