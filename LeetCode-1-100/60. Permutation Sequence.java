// original version
// there are 2^(n - 1) permutations starting with 1, so k / 2^(n - 1) determines the first digit, and similarly for the rest
class Solution {
    private int recursive(int n, int k, int idx, int factorial, boolean[] map, StringBuilder builder) {
        if (idx < n)
            k = recursive(n, k, idx + 1, factorial * idx, map, builder);

        int t = 0, count = 1;
        while (t < k - factorial) {
            t += factorial;
            count++;
        }
        for (int i = 1; i < 10; i++) {
            if (map[i] == true)
                continue;
            count--;
            if (count == 0) {
                builder.append(i);
                map[i] = true;
                break;
            }
        }
        return k - t;
    }

    public String getPermutation(int n, int k) {
        StringBuilder builder = new StringBuilder();
        recursive(n, k, 1, 1, new boolean[10], builder);
        return builder.toString();
    }
}