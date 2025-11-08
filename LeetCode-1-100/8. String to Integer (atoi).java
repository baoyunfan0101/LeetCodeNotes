// original version
// enumerate all possibilities using switch-case
class Solution {
    public int myAtoi(String s) {
        int res = 0;
        boolean startNum = false, sign = true;
        for(Character c : s.toCharArray())
            switch(c) {
                case ' ':if(startNum) if(sign) return res; else return -res; else continue;
                case '+': if(startNum) if(sign) return res; else return -res; else {startNum = true; continue;}
                case '-': if(startNum) if(sign) return res; else return -res; else {startNum = true; sign = false; break;}
                case '0':
                    if(res > 214748364) if(sign) return 2147483647; else return -2147483648;
                    else {startNum = true; res = res * 10; break;}
                case '1':
                    if(res > 214748364) if(sign) return 2147483647; else return -2147483648;
                    else {startNum = true; res = res * 10 + 1; break;}
                case '2':
                    if(res > 214748364) if(sign) return 2147483647; else return -2147483648;
                    else {startNum = true; res = res * 10 + 2; break;}
                case '3':
                    if(res > 214748364) if(sign) return 2147483647; else return -2147483648;
                    else {startNum = true; res = res * 10 + 3; break;}
                case '4':
                    if(res > 214748364) if(sign) return 2147483647; else return -2147483648;
                    else {startNum = true; res = res * 10 + 4; break;}
                case '5':
                    if(res > 214748364) if(sign) return 2147483647; else return -2147483648;
                    else {startNum = true; res = res * 10 + 5; break;}
                case '6':
                    if(res > 214748364) if(sign) return 2147483647; else return -2147483648;
                    else {startNum = true; res = res * 10 + 6; break;}
                case '7':
                    if(res > 214748364) if(sign) return 2147483647; else return -2147483648;
                    else {startNum = true; res = res * 10 + 7; break;}
                case '8':
                    if(res >= 214748364) if(sign) return 2147483647; else return -2147483648;
                    else {startNum = true; res = res * 10 + 8; break;}
                case '9':
                    if(res >= 214748364) if(sign) return 2147483647; else return -2147483648;
                    else {startNum = true; res = res * 10 + 9; break;}
                default: if(sign) return res; else return -res;
            }
        if(sign) return res; else return -res;
    }
}