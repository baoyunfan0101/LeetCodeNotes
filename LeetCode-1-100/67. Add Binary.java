// original version
class Solution {
    public String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1;
        StringBuilder builder = new StringBuilder();
        boolean addOne = false;
        while (i >= 0 && j >= 0) {
            char aChar = a.charAt(i), bChar = b.charAt(j);
            if (addOne) {
                if (aChar == '1' && bChar == '1') {
                    builder.insert(0, '1');
                    addOne = true;
                } else if (aChar == '0' && bChar == '0') {
                    builder.insert(0, '1');
                    addOne = false;
                } else {
                    builder.insert(0, '0');
                    addOne = true;
                }
            } else {
                if (aChar == '1' && bChar == '1') {
                    builder.insert(0, '0');
                    addOne = true;
                } else if (aChar == '0' && bChar == '0') {
                    builder.insert(0, '0');
                    addOne = false;
                } else {
                    builder.insert(0, '1');
                    addOne = false;
                }
            }
            i--;
            j--;
        }
        // if a.length() > b.length()
        while (i >= 0) {
            char aChar = a.charAt(i);
            if (addOne) {
                if (aChar == '1') {
                    builder.insert(0, '0');
                    addOne = true;
                } else {
                    builder.insert(0, '1');
                    addOne = false;
                }
            } else
                builder.insert(0, aChar);
            i--;
        }
        // if a.length() < b.length()
        while (j >= 0) {
            char bChar = b.charAt(j);
            if (addOne) {
                if (bChar == '1') {
                    builder.insert(0, '0');
                    addOne = true;
                } else {
                    builder.insert(0, '1');
                    addOne = false;
                }
            } else
                builder.insert(0, bChar);
            j--;
        }
        // there is still a "one" left
        if (addOne)
            builder.insert(0, '1');
        return builder.toString();
    }
}