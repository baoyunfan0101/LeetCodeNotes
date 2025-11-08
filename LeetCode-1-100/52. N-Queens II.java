/* silly version
// reuse problem #51. N-Queens
class Solution {
    // problem #51. N Queens
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

    public int totalNQueens(int n) {
        return solveNQueens(n).size();
    }
}
end silly version */

// better version
// similar to problem #51. N-Queens
// only track the total count of valid solutions
class Solution {
    private int n;
    private int[] position;
    private int maxOccupied;
    private int resultNum = 0;

    private void backtrack(int idx, int column, int diagonal, int backDiagonal) {
        if (idx == n) {
            resultNum++;
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

    public int totalNQueens(int n) {
        this.n = n;
        // if all positions are occupied
        maxOccupied = (1 << n) - 1;
        // queen's position in each row
        position = new int[n];
        backtrack(0, 0, 0, 0);
        return resultNum;
    }
}