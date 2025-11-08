// original version
// divide the square into four triangles arranged (like a windmill!)
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[] map = new int[n];
        for (int i = 0; i < map.length; i++)
            map[i] = n - i - 1;
        for (int i = 0; i < n - 1; i++)
            for (int j = i; j < n - 1 - i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[map[j]][i];
                matrix[map[j]][i] = matrix[map[i]][map[j]];
                matrix[map[i]][map[j]] = matrix[j][map[i]];
                matrix[j][map[i]] = temp;
            }
        //System.out.println(Arrays.deepToString(matrix));
    }
}