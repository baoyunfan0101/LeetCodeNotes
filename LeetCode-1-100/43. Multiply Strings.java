/* original version
// add two strings by summing their digits
// multiply strings by looking up the multiplication table and adding up the partial products
class Solution {
    private static final String[][] TABLE = new String[][] {
            { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" },
            { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" },
            { "0", "2", "4", "6", "8", "10", "12", "14", "16", "18" },
            { "0", "3", "6", "9", "12", "15", "18", "21", "24", "27" },
            { "0", "4", "8", "12", "16", "20", "24", "28", "32", "36" },
            { "0", "5", "10", "15", "20", "25", "30", "35", "40", "45" },
            { "0", "6", "12", "18", "24", "30", "36", "42", "48", "54" },
            { "0", "7", "14", "21", "28", "35", "42", "49", "56", "63" },
            { "0", "8", "16", "24", "32", "40", "48", "56", "64", "72" },
            { "0", "9", "18", "27", "36", "45", "54", "63", "72", "81" },
    };

    private StringBuilder add(String num1, String num2) {
        if (num1.equals("0"))
            return new StringBuilder(num2);
        else if (num2.equals("0"))
            return new StringBuilder(num1);

        int i = num1.length() - 1, j = num2.length() - 1, carry = 0, temp = 0;
        StringBuilder res = new StringBuilder();
        while (i >= 0 || j >= 0 || carry > 0) {
            if (i < 0 && j < 0) { // num1 and num2 are all set, but there's carry bit
                res.insert(0, carry);
                break;
            } else if (i < 0) // num1 is all set
                temp = (int) num2.charAt(j--) - '0' + carry;
            else if (j < 0) // num2 is all set
                temp = (int) num1.charAt(i--) - '0' + carry;
            else
                temp = (int) num1.charAt(i--) - '0' + num2.charAt(j--) - '0' + carry;
            res.insert(0, temp % 10);
            carry = temp / 10;
            //System.out.format("i = %d, j = %d, num1[i] + num2[j] = %d\r\n", i + 1, j + 1, temp);
        }
        //System.out.format("%s + %s = %s\r\n", num1, num2, res);
        return res;
    }

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";

        int l1 = num1.length(), l2 = num2.length();
        StringBuilder res = new StringBuilder("0");
        for (int i = 0; i < l1; i++) {
            char c1 = num1.charAt(i);
            StringBuilder product = new StringBuilder("0");
            for (int j = 0; j < l2; j++) {
                char c2 = num2.charAt(j);
                if (!product.toString().equals("0"))
                    product.append('0'); // multiply by 10
                //System.out.format("\t%s + %s\r\n", product, TABLE[c1 - '0'][c2 - '0']);
                product = add(product.toString(), TABLE[c1 - '0'][c2 - '0']);
            }
            if (!res.toString().equals("0"))
                res.append('0'); // multiply by 10
            //System.out.format("%s + %s\r\n", res, product);
            res = add(res.toString(), product.toString());
        }
        return res.toString();
    }
}
end original version */

/* method 2: multiplication
// core idea: the product of the i-th digit of num1 and the j-th digit of num2,
// and the product of the j-th digit of num1 and the i-th digit of num2,
// both contribute to the same digit in the final product
class Solution {
    private static final int[][] TABLE = new int[][] {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
            { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18 },
            { 0, 3, 6, 9, 12, 15, 18, 21, 24, 27 },
            { 0, 4, 8, 12, 16, 20, 24, 28, 32, 36 },
            { 0, 5, 10, 15, 20, 25, 30, 35, 40, 45 },
            { 0, 6, 12, 18, 24, 30, 36, 42, 48, 54 },
            { 0, 7, 14, 21, 28, 35, 42, 49, 56, 63 },
            { 0, 8, 16, 24, 32, 40, 48, 56, 64, 72 },
            { 0, 9, 18, 27, 36, 45, 54, 63, 72, 81 },
    };

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";

        int l1 = num1.length(), l2 = num2.length();
        int[] res = new int[l1 + l2];

        for (int i = 0; i < l1; i++)
            for (int j = 0; j < l2; j++)
                res[i + j + 1] += TABLE[num1.charAt(i) - '0'][num2.charAt(j) - '0'];

        int carry = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = l1 + l2 - 1; i > 0; i--) {
            res[i] += carry;
            builder.insert(0, res[i] % 10);
            carry = res[i] / 10;
        }
        if (carry > 0)
            builder.insert(0, carry);

        return builder.toString();
    }
}
end method 2 */

// method 2 (modified): multiplication
// same as the previous version
// give up using the multiplication table
class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";

        int l1 = num1.length(), l2 = num2.length();
        int[] res = new int[l1 + l2];

        for (int i = 0; i < l1; i++)
            for (int j = 0; j < l2; j++)
                res[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');

        int carry = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = l1 + l2 - 1; i > 0; i--) {
            res[i] += carry;
            builder.insert(0, res[i] % 10);
            carry = res[i] / 10;
        }
        if (carry > 0)
            builder.insert(0, carry);

        return builder.toString();
    }
}