/* original version: regular expression
class Solution {
    public boolean isNumber(String s) {
        // [+-]?            =>  '+'' or '-', one or none
        // [0-9]+           =>  \d, one or more (a normal integer)
        // [0-9]*\\.[0-9]+  =>  \d, zero or more; '.'; \d, one or more (a float which may start with '.')
        // [0-9]+\\.[0-9]*  =>  \d, one or more; '.'; \d, zero or more (a float which may end with '.')
        return s.matches("([+-]?(([0-9]+)|([0-9]*\\.[0-9]+)|([0-9]+\\.[0-9]*)))|([+-]?(([0-9]+)|([0-9]*\\.[0-9]+)|([0-9]+\\.[0-9]*))[Ee]([+-]?[0-9]+))");
    }
}
end original method */

/* original version (modified): regular expression
class Solution {
    public boolean isNumber(String s) {
        // [+-]?                =>  '+'' or '-', one or none
        // \\d+                 =>  \d, one or more (a normal integer)
        // \\d+\\.\\d*          =>  \d, one or more; '.'; \d, zero or more (a float which may end with '.')
        // \\.\\d+              =>  '.'; \d, one or more (a float which starts with '.')
        // ([Ee]([+-]?\\d+))?   =>  'E' or 'e' and a signed integer, one or none
        return s.matches("[+-]?((\\d+)|(\\d+\\.\\d*)|(\\.\\d+))([Ee]([+-]?\\d+))?");
    }
}
end original method (modified) */


// original version (modified): precompiled regular expression
// create a Pattern and a Matcher
// reset the Matcher with the target string before each match attempt
import java.util.regex.*;

class Solution {
    static Pattern pattern = Pattern.compile("[+-]?((\\d+)|(\\d+\\.\\d*)|(\\.\\d+))([Ee]([+-]?\\d+))?");
    static Matcher matcher = pattern.matcher("");

    public boolean isNumber(String s) {
        matcher.reset(s);
        return matcher.matches();
    }
}