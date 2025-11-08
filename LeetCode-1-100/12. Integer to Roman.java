/* original version
// enumerate using switch-case
class Solution {
    private String thousandsDigit(int n) {
        switch(n) {
            case 0: return "";
            case 1: return "M";
            case 2: return "MM";
            case 3: return "MMM";
            default: return "(thousandsDigit() failed!)";
        }
    }
    private String hundredsDigit(int n) {
        switch(n) {
            case 0: return "";
            case 1: return "C";
            case 2: return "CC";
            case 3: return "CCC";
            case 4: return "CD";
            case 5: return "D";
            case 6: return "DC";
            case 7: return "DCC";
            case 8: return "DCCC";
            case 9: return "CM";
            default: return "(hundredsDigit() failed!)";
        }
    }
    private String tensDigit(int n) {
        switch(n) {
            case 0: return "";
            case 1: return "X";
            case 2: return "XX";
            case 3: return "XXX";
            case 4: return "XL";
            case 5: return "L";
            case 6: return "LX";
            case 7: return "LXX";
            case 8: return "LXXX";
            case 9: return "XC";
            default: return "(tensDigit() failed!)";
        }
    }
    private String unitsDigit(int n) {
        switch(n) {
            case 0: return "";
            case 1: return "I";
            case 2: return "II";
            case 3: return "III";
            case 4: return "IV";
            case 5: return "V";
            case 6: return "VI";
            case 7: return "VII";
            case 8: return "VIII";
            case 9: return "IX";
            default: return "(unitsDigit() failed!)";
        }
    }
    public String intToRoman(int num) {
        return thousandsDigit(num / 1000) + hundredsDigit(num / 100 % 10) + tensDigit(num / 10 % 10) + unitsDigit(num % 10);
    }
}
end original version */

// better version
// enumerate using static final String[]
// use a StringBuilder, instead of concatenating strings directly
class Solution {
    private static final String[] thousands = {"", "M", "MM", "MMM"};
    private static final String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private static final String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    public String intToRoman(int num) {
        StringBuilder builder = new StringBuilder();
        if(num >= 1000) {
            int t = num / 1000;
            builder.append(thousands[t]);
            num %= 1000;
        }
        if(num >= 100) {
            int t = num / 100;
            builder.append(hundreds[t]);
            num %= 100;
        }
        if(num >= 10) {
            int t = num / 10;
            builder.append(tens[t]);
            num %= 10;
        }
        builder.append(units[num]);
        return builder.toString();
    }
}