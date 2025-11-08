// original version
// enumerate every character and the character following it
class Solution {
    public int romanToInt(String s) {
        int res = 0;
        char lastChar = ' ';
        for(char c: s.toCharArray()) {
            switch(c) {
                case 'M':
                    if(lastChar == 'C')
                        res += 800;
                    else
                        res += 1000;
                    break;
                case 'D':
                    if(lastChar == 'C')
                        res += 300;
                    else
                        res += 500;
                    break;
                case 'C':
                    if(lastChar == 'X')
                        res += 80;
                    else
                        res += 100;
                    break;
                case 'L':
                    if(lastChar == 'X')
                        res += 30;
                    else
                        res += 50;
                    break;
                case 'X':
                    if(lastChar == 'I')
                        res += 8;
                    else
                        res += 10;
                    break;
                case 'V':
                    if(lastChar == 'I')
                        res += 3;
                    else
                        res += 5;
                    break;
                default:
                    res += 1;
            }
            lastChar = c;
        }
        return res;
    }
}