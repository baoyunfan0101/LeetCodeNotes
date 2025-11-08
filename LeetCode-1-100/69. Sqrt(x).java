/* original version
class Solution {
    public int mySqrt(int x) {
        for (int i = 0; i <= x; i++)
            if (i > 46340 || i * i > x) // pow(2147483647, 0.5) == 46340
                return i - 1;
        return x;
    }
}
end original version */


// better version: binary search
class Solution {
    public int mySqrt(int x) {
        // to avoid having a divisor of 0
        if (x == 0)
            return 0;

        int start = 1, end = x;
        while (start < end) {
            int idx = (start + end + 1) / 2, quotient = x / idx;
            if (quotient == idx)
                return idx;
            else if (quotient < idx)
                end = idx - 1;
            else
                start = idx;
        }
        return start;
    }
}