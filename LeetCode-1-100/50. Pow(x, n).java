// original version: binary recursion
// use recursion and halve the exponent in each iteration
class Solution {
    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        else if (n < 0) {
            double temp = myPow(x, n / 2);
            if (n % 2 == 0)
                return temp * temp;
            else
                return temp * temp / x;
        } else {
            double temp = myPow(x, n / 2);
            if (n % 2 == 0)
                return temp * temp;
            else
                return temp * temp * x;
        }
    }
}