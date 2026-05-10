// a. sort
class Solution {
    public boolean isAnagram(String s, String t) {
        char[] s_a = s.toCharArray();
        Arrays.sort(s_a);
        char[] t_a = t.toCharArray();
        Arrays.sort(t_a);

        return Arrays.equals(s_a, t_a);
    }
}

// b. HashMap
class Solution {
    public boolean isAnagram(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen != tLen)
            return false;

        int[] map = new int[26];
        for (int i = 0; i < sLen; i++)
            map[(int)s.charAt(i) - 'a']++;
        for (int i = 0; i < tLen; i++)
            map[(int)t.charAt(i) - 'a']--;

        for (int i = 0; i < 26; i++)
            if (map[i] != 0)
                return false;
        return true;
    }
}