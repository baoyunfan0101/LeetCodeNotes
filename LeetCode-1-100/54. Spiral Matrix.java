// original version
// move in four directions during each loop iteration
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int up = 0, down = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        int i = 0, j = 0;
        List<Integer> res = new ArrayList<Integer>();
        res.add(matrix[0][0]);
        while (true) {
            up++;
            if (left > right)
                break;
            // left -> right
            while (j < right) {
                j++;
                res.add(matrix[i][j]);
                //System.out.format("left  -> right: (%d, %d)\r\n", i, j);
            }
            right--;
            if (up > down)
                break;
            // up -> down
            while (i < down) {
                i++;
                res.add(matrix[i][j]);
                //System.out.format("up    -> down : (%d, %d)\r\n", i, j);
            }
            down--;
            if (left > right)
                break;
            // right -> left
            while (j > left) {
                j--;
                res.add(matrix[i][j]);
                //System.out.format("right -> left : (%d, %d)\r\n", i, j);
            }
            left++;
            if (up > down)
                break;
            // down -> up
            while (i > up) {
                i--;
                res.add(matrix[i][j]);
                //System.out.format("down  -> up   : (%d, %d)\r\n", i, j);
            }
        }
        return res;
    }
}