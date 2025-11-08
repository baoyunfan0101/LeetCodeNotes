/* original version
// enumerate all possibilities using if-else
class Solution {
    public int reverse(int x) {
        int[] l = new int[11];
        if(x < 0)
            l[0] = -1;
        else
            l[0] = 1;
        x = Math.abs(x);
        int length = -1;
        for(int i = 9; i >= 0; i--) {
            l[10 - i] = x / (int)Math.pow(10, i);
            if(l[10 - i] > 0 && length < 0) // the first time enchountering a non-zero
                length = i + 1;
            x %= (int)Math.pow(10, i);
        }
        // if the number might cross the bound
        if(length == 10) {
            int[] maxPos = new int[] {1, 2, 1, 4, 7, 4, 8, 3, 6, 4, 7};
            int[] minNeg = new int[] {-1, 2, 1, 4, 7, 4, 8, 3, 6, 4, 8};
            if(l[0] > 0)
                for(int i = 10; i > 0; i--) {
                    if(l[i] < maxPos[11 - i])
                        break;
                    else if(l[i] > maxPos[11 - i])
                        return 0;
                    else if(i == 1)
                        return 0;
                }
            else
                for(int i = 10; i > 0; i--) {
                    if(l[i] < minNeg[11 - i])
                        break;
                    else if(l[i] > minNeg[11 - i])
                        return 0;
                    else if(i == 1)
                        return 0;
                }
        }
        int res = 0;
        for(int i = 10; i >= 11 - length; i--)
            res += l[i] * (int)Math.pow(10, length + i - 11);
        return res * l[0];
    }
}
end original version */

/* better version
// pop out digit from original integer and put in digit into reversed integer simultaneously
class Solution {
    public int reverse(int x) {
        int res = 0, times = 0;
        while(x != 0) {
            if(times == 9) {
                // at the 9-th time, consider:
                // res < Integer.MIN_VALUE / 10
                // res > Integer.MAX_VALUE / 10

                // within valid range
                if(res > -214748364 && res < 214748364) {}
                // out of valid range
                else if(res > 214748364 || res < -214748364)
                    return 0;
                // on the edge: check the next digit
                else if(x > 7 || x < -8) {
                    return 0;
                }
            }
            int t = x % 10;
            x /= 10;
            res = res * 10 + t;
            times++;
        }
        return res;
    }
}
end better version */

// better version
// since the first digit of a 10-digit integer must be 1 or 2
// the units digit of a reversed integer must be 1 or 2
// at the 9-th time, consider: (1) remainder < Integer.MIN_VALUE / 10; (2) remainder > Integer.MAX_VALUE / 10
class Solution {
    public int reverse(int x) {
        int res = 0, times = 0;
        while(x != 0) {
            if(times == 9 && (res > 214748364 || res < -214748364)) // the units digit must be 1 or 2
                return 0;
            int t = x % 10;
            x /= 10;
            res = res * 10 + t;
            times++;
        }
        return res;
    }
}