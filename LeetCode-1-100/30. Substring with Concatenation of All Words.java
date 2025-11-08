/* worng version: brute-force permutation
// generate all permutations of words using backtracking, and regex-match each permutation in String s
// the problem is that the time complexity explodes
// Time Limit Exceed
import java.util.regex.*;

class Solution {
    // generate all permutations of the words using backtracking
    private void getSubstring(Queue<String> strList, StringBuilder builder, List<String> resList) {
        int len = strList.size();
        if (len == 0) {
            String resStr = builder.toString();
            for (String str : resList)
                if (str.equals(resStr))
                    return;
            resList.add(builder.toString());
        }
        for (int i = 0; i < len; i++) {
            String str = strList.poll();
            builder.append(str);
            getSubstring(strList, builder, resList);
            builder.delete(builder.length() - str.length(), builder.length());
            strList.offer(str);
        }
    }

    public List<Integer> findSubstring(String s, String[] words) {
        Queue<String> strList = new LinkedList<String>();
        Collections.addAll(strList, words);
        StringBuilder builder = new StringBuilder();
        List<String> substringList = new ArrayList<String>();
        getSubstring(strList, builder, substringList);
        List<Integer> res = new ArrayList<Integer>();
        for (String str : substringList) {
            Matcher m = Pattern.compile(str).matcher(s);
            int lastRes = -1;
            label: while (m.find(lastRes + 1)) {
                lastRes = m.start();
                for (Integer i : res)
                    if (i == lastRes) // auto-unboxing
                        continue label;
                res.add(lastRes);
            }
        }
        return res;
    }
}
end worng version */

/* worng version: backtracking match with sliding window optimization
// regex-match each word in String s, and match the remaining words using backtracking
// for each valid starting position, slide the substring as far as possible
// Time Limit Exceed
import java.util.regex.*;

class Solution {
    private int wordLen;
    private int sLen;

    private boolean findSubstring(String s, int start, Queue<String> strList) {
        int len = strList.size();
        if (len == 0)
            return true;

        for (int i = 0; i < len; i++) {
            String str = strList.poll();
            int newStart = start + wordLen;
            if (newStart <= sLen
                    && str.equals(s.substring(start, newStart))
                    && findSubstring(s, newStart, strList)) {
                //System.out.format("\"%s\".matches(\"%s\", %d) = true\r\n", s, str, start);
                strList.offer(str);
                return true;
            }
            //System.out.format("\"%s\".matches(\"%s\", %d) = false\r\n", s, str, start);
            strList.offer(str);
        }
        return false;
    }

    public List<Integer> findSubstring(String s, String[] words) {
        wordLen = words[0].length();
        sLen = s.length();
        List<Integer> res = new ArrayList<Integer>();
        if (wordLen * words.length > sLen)
            return res;

        Queue<String> strList = new LinkedList<String>();
        Collections.addAll(strList, words);

        for (int i = 0; i < words.length; i++) {
            String str = strList.poll();
            Matcher m = Pattern.compile(str).matcher(s);
            int lastRes = -1;
            label1: while (m.find(lastRes + 1)) {
                lastRes = m.start();
                // there is no enough character to match the left strings
                if (wordLen * words.length > sLen - lastRes)
                    continue;
                // check if the answer is already exist
                for (Integer resInt : res)
                    if (resInt == lastRes)
                        continue label1;
                // matched successfully
                if (findSubstring(s, lastRes + wordLen, strList)) {
                    res.add(lastRes);
                    // try to move the string at the head to the end
                    int head = lastRes, end = lastRes + wordLen * words.length;
                    boolean matched = true;
                    label2: while (matched && end + wordLen <= sLen) {
                        for (int offset = 0; offset < wordLen; offset++, head++, end++)
                            if (s.charAt(head) != s.charAt(end)) {
                                matched = false;
                                break;
                            }
                        if (matched) {
                            // check if the answer is already exist
                            for (Integer resInt : res)
                                if (resInt == head)
                                    continue label2;
                            res.add(head);
                        }
                    }
                }
            }
            strList.offer(str);
        }
        return res;
    }
}
end worng version */

/* original version: sliding window
// starting from positions 1, 2, ..., (words[0].length - 1), create a HashMap to count words in each window
// slide the window as far as possible
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        int wordLen = words[0].length(), sLen = s.length();
        List<Integer> res = new ArrayList<Integer>();
        if (wordLen * words.length > sLen)
            return res;
        
        // create a standard word map
        Map<String, Integer> standardMap = new HashMap<String, Integer>();
        for (String word : words)
            if (standardMap.containsKey(word))
                standardMap.put(word, standardMap.get(word) + 1);
            else
                standardMap.put(word, 1);

        // start: starting position in string s
        for (int start = 0; start < wordLen; start++) {
            int head = start, end = start + wordLen * words.length;
            if (end > sLen)
                return res;
            // create a new word map
            Map<String, Integer> strMap = new HashMap<String, Integer>(standardMap);
            // add starting strings into the map
            for (int n = 0; n < words.length; n++) {
                String str = s.substring(start + wordLen * n, start + wordLen * (n + 1));
                if (strMap.containsKey(str))
                    strMap.put(str, strMap.get(str) - 1);
                else
                    strMap.put(str, -1);
            }
            // move on
            while (true) {
                // check if this is an answer
                boolean findRes = true;
                for (String str : strMap.keySet())
                    if (strMap.get(str) != 0)
                        findRes = false;
                if (findRes)
                    res.add(head);
                // terminate
                if (end + wordLen > sLen)
                    break;
                // deal with the string at the head and the string at the end
                String headStr = s.substring(head, head + wordLen);
                strMap.put(headStr, strMap.get(headStr) + 1);
                String endStr = s.substring(end, end + wordLen);
                if (strMap.containsKey(endStr))
                    strMap.put(endStr, strMap.get(endStr) - 1);
                else
                    strMap.put(endStr, -1);
                // iterate
                head += wordLen;
                end += wordLen;
            }
        }
        return res;
    }
}
end original version */

// better version
// same as the previous version
// if the count of a word reaches 0, remove it from the HashMap
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        int wordLen = words[0].length(), sLen = s.length();
        List<Integer> res = new ArrayList<Integer>();
        if (wordLen * words.length > sLen)
            return res;

        // create a standard word map
        Map<String, Integer> standardMap = new HashMap<String, Integer>();
        for (String word : words)
            standardMap.put(word, standardMap.getOrDefault(word, 0) + 1);

        // start: starting position in string s
        for (int start = 0; start < wordLen; start++) {
            int head = start, end = start + wordLen * words.length;
            if (end > sLen)
                return res;
            // create a new word map
            Map<String, Integer> strMap = new HashMap<String, Integer>(standardMap);
            // add starting strings into the map
            for (int n = 0; n < words.length; n++) {
                String str = s.substring(start + wordLen * n, start + wordLen * (n + 1));
                int newNum = strMap.getOrDefault(str, 0) - 1;
                if (newNum == 0)
                    strMap.remove(str);
                else
                    strMap.put(str, newNum);
            }
            // move on
            while (true) {
                // check if this is an answer
                if (strMap.isEmpty())
                    res.add(head);
                // terminate
                if (end + wordLen > sLen)
                    break;
                // deal with the string at the head and the string at the end
                String headStr = s.substring(head, head + wordLen);
                int newHeadNum = strMap.getOrDefault(headStr, 0) + 1;
                if (newHeadNum == 0)
                    strMap.remove(headStr);
                else
                    strMap.put(headStr, newHeadNum);
                String endStr = s.substring(end, end + wordLen);
                int newEndNum = strMap.getOrDefault(endStr, 0) - 1;
                if (newEndNum == 0)
                    strMap.remove(endStr);
                else
                    strMap.put(endStr, newEndNum);
                // iterate
                head += wordLen;
                end += wordLen;
            }
        }
        return res;
    }
}