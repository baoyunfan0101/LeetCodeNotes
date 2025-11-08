/* original version
// if there is one 2, there are C_1^(n - 1) possible combinations, and so on
class Solution {
    private long combination(int a, int b) {
        if (a > b / 2)
            return combination(b - a, b);
        long res = 1;
        for (int i = 0; i < a; i++)
            res = res * (b - i) / (i + 1);
        return res;
    }

    public int climbStairs(int n) {
        int numOfTwo = n / 2 + 1;
        int res = 0;
        for (int i = 0; i < numOfTwo; i++)
            res += combination(i, n - i);
        return res;
    }
}
end original versionß */

/* method 1: dynamic programming
// the final step can be either 1 or 2
class Solution {
    private static int[] map = new int[46];
    static {
        map[1] = 1;
        map[2] = 2;
        for (int i = 3; i <= 45; i++)
            map[i] = map[i - 2] + map[i - 1];
    }

    public int climbStairs(int n) {
        return map[n];
    }
}
end method 1 */

/* method 1: space-optimized dynamic programming
// use only three temporary integers
class Solution {
    public int climbStairs(int n) {
        switch(n) {
            case 1: return 1;
            case 2: return 2;
            case 3: return 3;
            default: break;
        }
        int a = 1, b = 2, c = 3;
        for (int i = 4; i <= n ;i++) {
            a = b;
            b = c;
            c = a + b;
        }
        return c;
    }
}
end method 1 */

// method 1 (modified): space-optimized dynamic programming
// simplified initialization for starting conditions
class Solution {
    public int climbStairs(int n) {
        int a = 0, b = 0, c = 1;
        for (int i = 1; i <= n ;i++) {
            a = b;
            b = c;
            c = a + b;
        }
        return c;
    }
}