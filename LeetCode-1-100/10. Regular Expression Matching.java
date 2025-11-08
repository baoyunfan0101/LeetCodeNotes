/* original version: recursion
// always treat a character and the '*' following it as a single unit.
class Solution {
    // retur if sChar matches pChar
    private boolean equals(char sChar, char pChar) {
        switch(pChar) {
            case '.': return true;
            default: return sChar == pChar;
        }
    }

    // recursive function
    private boolean recursive(String s, int i, String p, int j) {
        int sLength = s.length(), pLength = p.length();
        //System.out.println(s.substring(i) + " is compared with " + p.substring(j) + "!");

        // p string reaches the end
        if(j == pLength) {
            if(i == sLength)
                return true;
            else
                return false;
        }

        // s string reaches the end && s[i] != p[j]:
        // left p must be all-'*'
        if(i == sLength || !equals(s.charAt(i), p.charAt(j))) {
            if(j + 1 < pLength && p.charAt(j + 1) == '*') // look ahead to check if the next character is '*'
                return recursive(s, i, p, j + 2);
            else
                return false;
        }
        else {
            if(j + 1 < pLength && p.charAt(j + 1) == '*') // look ahead to check if the next character is '*'
                return recursive(s, i, p, j + 2) // this character with '*' doesn't match anything
                    || recursive(s, i + 1, p, j); // this character with '*' matches a character first
            else
                return recursive(s, i + 1, p, j + 1);
        }
    }

    public boolean isMatch(String s, String p) {
        return recursive(s, 0, p, 0);
    }
}
end original version */

/* better version: memoized recursion
class Solution {
    private static Boolean[][] map;
    private boolean equals(char sChar, char pChar) {
        switch(pChar) {
            case '.': return true;
            default: return sChar == pChar;
        }
    }
    private boolean recursive(String s, int i, String p, int j) {
        int sLength = s.length(), pLength = p.length();
        //System.out.println(s.substring(i) + " is compared with " + p.substring(j) + "!");
        if(map[i][j] != null)
            return map[i][j];
        if(j == pLength) {
            if(i == sLength) {
                map[i][j] = true;
                return true;
            }
            else {
                map[i][j] = false;
                return false;
            }
        }
        if(i == sLength || !equals(s.charAt(i), p.charAt(j))) {
            if(j + 1 < pLength && p.charAt(j + 1) == '*') {
                boolean res = recursive(s, i, p, j + 2);
                map[i][j] = res;
                return res;
            }
            else {
                map[i][j] = false;
                return false;
            }
        }
        else {
            if(j + 1 < pLength && p.charAt(j + 1) == '*') {
                boolean res = recursive(s, i, p, j + 2)
                    || recursive(s, i + 1, p, j);
                map[i][j] = res;
                return res;
            }
            else {
                boolean res = recursive(s, i + 1, p, j + 1);
                map[i][j] = res;
                return res;
            }
        }
    }
    public boolean isMatch(String s, String p) {
        map = new Boolean[s.length() + 1][p.length() + 1];
        return recursive(s, 0, p, 0);
    }
}
end better version */

// better version: dynamic programming
// values of a character and the '*' following it are always equal.
class Solution {
    // retur if sChar matches pChar
    private boolean matches(char sChar, char pChar) {
        switch (pChar) {
            case '.':
                return true;
            default:
                return sChar == pChar;
        }
    }

    public boolean isMatch(String s, String p) {
        int sLength = s.length(), pLength = p.length();
        boolean[][] map = new boolean[sLength + 1][pLength + 1];

        // the first line(when s = "")
        map[0][0] = true; // empty string matches empty string
        for (int j = 1; j <= pLength; j += 2)
            if (j < pLength && p.charAt(j) == '*') {
                map[0][j] = map[0][j - 1]; // j - 2 -> j - 1 (a character)
                map[0][j + 1] = map[0][j - 1]; // j - 2 -> j (the '*' following it)
            } else
                break;

        // sequence numbers start from 1(the i-th character is s[i - 1], so does j)
        int i = 1, j = 1;
        while (i <= sLength) {
            j = 1;
            while (j <= pLength) {
                // if the next character in p is *, put them together(so this case is ended by j += 2)
                if (j < pLength && p.charAt(j) == '*') {
                    // if s[i - 1] matches p[j - 1]
                    if (matches(s.charAt(i - 1), p.charAt(j - 1))) {
                        // 1. map[i - 1][j]:
                        // 1.1. if both s[i - 2] and s[i - 1] can match p[j - 1]
                        //      this ".*" might match "s[i - 2]"(something in previous p), so it can also match "s[i - 2]s[i - 1]"
                        // 1.2. else
                        //      this ".*" match "" in previous p, so now it's used to match "s[i - 1]"
                        // 2. map[i][j - 1]: this ".*" can match "", leave "s[i - 1]" to previous p
                        map[i][j] = map[i - 1][j] || map[i][j - 1];
                        map[i][j + 1] = map[i][j];
                    } else {
                        // s[i - 1] doesn't match p[j - 1], so p[j - 1] must be matched by previous p
                        map[i][j] = map[i][j - 1];
                        map[i][j + 1] = map[i][j];
                    }
                    j += 2;
                } else {
                    if (matches(s.charAt(i - 1), p.charAt(j - 1))) {
                        map[i][j] = map[i - 1][j - 1];
                    } else {
                        map[i][j] = false;
                    }
                    j++;
                }
            }
            i++;
        }
        /*
        for (int a = 0; a < sLength + 1; a++) {
        	for(int b = 0; b < pLength + 1; b++) {
        		System.out.format("%d %d - \"%s\" \"%s\" - %b\r\n", a, b, s.substring(0, a), p.substring(0, b), map[a][b]);
        	}
        }*/
        return map[sLength][pLength];
    }
}