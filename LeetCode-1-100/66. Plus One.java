// original version
// handle special cases such as 999 + 1
class Solution {
    public int[] plusOne(int[] digits) {
        int end = digits.length - 1;
        while (end >= 0) {
            if (digits[end] == 9) {
                digits[end] = 0;
                end--;
            } else {
                digits[end]++;
                break;
            }
        }
        if (end == -1) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            for (int i = 1; i <= digits.length; i++)
                newDigits[i] = 0;
            digits = newDigits;
        }
        return digits;
    }
}