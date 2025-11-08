/* original version
// consider all special cases, and make both dividend and divisor positive
// pick out each bit of the dividend, divide by the divisor, put the remainder back, and repeat
class Solution {
    public int divide(int dividend, int divisor) {
        // the sign of the quotient
        boolean positive = (dividend > 0) ^ (divisor < 0);

        // due to the limitation of the integer range, the dividend might be 2147483648 and needs to be adjusted by +1
        boolean addOne = false;

        // list all special cases, and make both dividend and divisor positive
        if (dividend == 0)
            return 0;
        else if (divisor == Integer.MIN_VALUE) {
            if (dividend == Integer.MIN_VALUE)
                return 1;
            else
                return 0;
        } else if (divisor == 1)
            return dividend;
        else if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE)
                return Integer.MAX_VALUE;
            else
                return ~dividend + 1; // -dividend
        } else if (dividend == Integer.MIN_VALUE) { // -2147483648 -> 2147483647 + 1
            dividend = Integer.MAX_VALUE;
            addOne = true;
        } else
            dividend = dividend >= 0 ? dividend : ~dividend + 1;
        divisor = divisor > 0 ? divisor : ~divisor + 1;
        //System.out.format("divide(): dividend = %d, divisor = %d\r\n", dividend, divisor);

        // divide
        int quotient = 0;
        for (int i = 30; i >= 0; i--) {
            int tDividend = dividend >> i; // pick out bits 2 to 32 of the dividend (the 1st bit is the sign bit)
            if (tDividend == 0)
                continue;
            else
                dividend = (dividend << (32 - i) >>> (32 - i)); // the remaining digits
            //System.out.format("i = %d: %d / %d ", i, tDividend, divisor);
            int tQuotient = 0;
            if (tDividend >= divisor) {
                tDividend -= divisor; // count the quotient, while calculating the remainder
                tQuotient++;
            }
            if (i == 0 && addOne && tDividend + 1 == divisor) // the quotient increases by 1 when the ones digit of the dividend increases by 1
                tQuotient++;
            //System.out.format("= %d\r\n", tQuotient);
            dividend += tDividend << i; // add the remainder
            quotient = (quotient << 1) + tQuotient; // update the quotient
            //System.out.format("dividend = %d, quotient = %d\r\n", dividend, quotient);
        }

        // recover the sign
        if (!positive)
            return ~quotient + 1;
        return quotient;
    }
}
end original version */

/* worse version
// same as the previous version
// emunarate 1, 11, 111, ... as masks using `static final int[]`
class Solution {
    private static final int[] DIVIDEND_MAP = {
            0,
            1,
            3,
            7,
            15,
            31,
            63,
            127,
            255,
            511,
            1023,
            2047,
            4095,
            8191,
            16383,
            32767,
            65535,
            131071,
            262143,
            524287,
            1048575,
            2097151,
            4194303,
            8388607,
            16777215,
            33554431,
            67108863,
            134217727,
            268435455,
            536870911,
            1073741823,
    };

    public int divide(int dividend, int divisor) {
        boolean positive = (dividend > 0) ^ (divisor < 0);
        boolean addOne = false;
        if (dividend == 0)
            return 0;
        else if (divisor == Integer.MIN_VALUE) {
            if (dividend == Integer.MIN_VALUE)
                return 1;
            else
                return 0;
        } else if (divisor == 1)
            return dividend;
        else if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE)
                return Integer.MAX_VALUE;
            else
                return ~dividend + 1;
        } else if (dividend == Integer.MIN_VALUE) { // -2147483648 -> 2147483647 + 1
            dividend = Integer.MAX_VALUE;
            addOne = true;
        } else
            dividend = dividend >= 0 ? dividend : ~dividend + 1;
        divisor = divisor > 0 ? divisor : ~divisor + 1;
        //System.out.format("divide(): dividend = %d, divisor = %d\r\n", dividend, divisor);
        int quotient = 0;
        for (int i = 30; i >= 0; i--) {
            int tDividend = dividend >> i;
            if (tDividend == 0)
                continue;
            //System.out.format("i = %d: %d / %d ", i, tDividend, divisor);
            int tQuotient = 0;
            if (tDividend >= divisor) {
                tDividend -= divisor;
                tQuotient++;
            }
            if (i == 0 && addOne && tDividend + 1 == divisor) {
                tDividend = 0;
                tQuotient++;
            }
            //System.out.format("= %d\r\n", tQuotient);
            dividend = (dividend & DIVIDEND_MAP[i]) + (tDividend << i);
            quotient = (quotient << 1) + tQuotient;
            //System.out.format("dividend = %d, quotient = %d\r\n", dividend, quotient);
        }
        if (!positive)
            return ~quotient + 1;
        return quotient;
    }
}
end worse version */

// better version: binary search
// make both dividend and divisor negative
// try each bit of the quotient using binary search
class Solution {
    // judge if quotient * divisor >= dividend, among which quotient >= 0, divisor < 0, dividend < 0
    private boolean greaterThan(int quotient, int divisor, int dividend) {
        if (quotient == 0)
            return true;
        int product = 0, step = divisor;
        while (true) {
            //System.out.format("quotient = %d, step = %d, product = %d\r\n", quotient, step, product);
            // the last bit of quotient is 1
            if ((quotient & 1) == 1) {
                // make sure: current product >= dividend
                if (product < dividend - step)
                    return false;
                product += step;
            }
            quotient >>= 1;
            if (quotient != 0) {
                // make sure: 2 * step >= dividend
                if (step < dividend - step)
                    return false;
                step += step;
            } else
                break;
        }
        return product >= dividend;
    }

    public int divide(int dividend, int divisor) {
        if (dividend == 0)
            return 0;
        else if (divisor == Integer.MIN_VALUE) {
            if (dividend == Integer.MIN_VALUE)
                return 1;
            else
                return 0;
        } else if (divisor == 1)
            return dividend;
        else if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE)
                return Integer.MAX_VALUE;
            else
                return ~dividend + 1;
        }
        boolean positive = (dividend > 0) ^ (divisor < 0);
        dividend = dividend <= 0 ? dividend : ~dividend + 1;
        divisor = divisor < 0 ? divisor : ~divisor + 1;
        if (dividend > divisor)
            return 0;
        //System.out.format("divide(): dividend = %d, divisor = %d\r\n", dividend, divisor);
        int diff = 0b00100000000000000000000000000000; // diff = Math.pow(2, 29)
        int quotient = 0b01000000000000000000000000000000, maxQuotient = quotient; // quotient = Math.pow(2, 30)
        boolean findQuotient = false;
        while (true) {
            //System.out.format("diff = %d, quotient = %d\r\n", diff, quotient);
            //System.out.format("greaterThan(%d, %d, %d) = %s\r\n", quotient, divisor, dividend, greaterThan(quotient, divisor, dividend));
            if (greaterThan(quotient, divisor, dividend)) {
                findQuotient = true;
                maxQuotient = quotient;
                quotient += diff;
            } else
                quotient -= diff;
            if (diff == 0)
                break;
            diff >>= 1;
        }
        if (findQuotient)
            quotient = maxQuotient;
        if (positive)
            return quotient;
        return ~quotient + 1;
    }
}