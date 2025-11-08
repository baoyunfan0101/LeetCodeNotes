/* original version: sliding window
// build a hash map from string t
// use a sliding window to traverse s while updating the hash map
class Solution {
    public String minWindow(String s, String t) {
        char[] sArray = s.toCharArray(), tArray = t.toCharArray();
        int sLen = sArray.length, tLen = tArray.length;

        // create hash map
        int numOfCharType = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < tLen; i++)
            if (map.containsKey(tArray[i]))
                map.put(tArray[i], map.get(tArray[i]) - 1);
            else {
                numOfCharType--;
                map.put(tArray[i], -1);
            }

        // slide window
        int left = 0, right = 0, minLeft = 0, minLength = Integer.MAX_VALUE;
        while (right < sLen) {
            // right slides to find an answer
            do {
                if (map.containsKey(sArray[right])) {
                    int temp = map.get(sArray[right]);
                    if (temp == -1)
                        numOfCharType++;
                    map.put(sArray[right], temp + 1);
                }
                right++;
            } while (right < sLen && numOfCharType < 0);

            // right reaches the end and hasn't find an answer
            if (numOfCharType < 0 && right == sLen)
                break;

            // find an answer
            if (numOfCharType == 0 && right - left < minLength) {
                minLeft = left;
                minLength = right - left;
            }

            // left slides to make the answer shorter
            while (left < right && numOfCharType == 0) {
                if (map.containsKey(sArray[left])) {
                    int temp = map.get(sArray[left]);
                    if (temp == 0)
                        numOfCharType--;
                    map.put(sArray[left], temp - 1);
                }
                left++;
            }

            // find the shortest answer
            if (numOfCharType < 0 && right - left + 1 < minLength) {
                minLeft = left - 1;
                minLength = right - left + 1;
            }
        }

        // hasn't found an answer
        if (minLength > sLen)
            return "";

        return s.substring(minLeft, minLeft + minLength);
    }
}
end original version */

// original version (modified 1): sliding window
// same as the previous version
// preprocess string s to keep only characters that appear in string t
class Solution {
    public String minWindow(String s, String t) {
        char[] sArray = s.toCharArray(), tArray = t.toCharArray();
        int sLen = sArray.length, tLen = tArray.length;

        // create hash map
        int numOfCharType = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < tLen; i++)
            if (map.containsKey((int) tArray[i]))
                map.put((int) tArray[i], map.get((int) tArray[i]) - 1);
            else {
                numOfCharType--;
                map.put((int) tArray[i], -1);
            }

        // preprocess string s
        List<int[]> predS = new ArrayList<int[]>();
        for (int i = 0; i < sLen; i++)
            if (map.containsKey((int) sArray[i]))
                predS.add(new int[] { i, (int) sArray[i] });
        int predSLen = predS.size();

        // slide window
        int left = 0, right = 0, leftPos = 0, rightPos = 0, minLeft = 0, minLength = Integer.MAX_VALUE;
        while (right < predSLen) {
            leftPos = predS.get(left)[0]; // update the actual position of left

            // right slides to find an answer
            do {
                int[] currentPos = predS.get(right);
                rightPos = currentPos[0]; // update the actual position of right

                int temp = map.get(currentPos[1]);
                if (temp == -1)
                    numOfCharType++;
                map.put(currentPos[1], temp + 1);
                right++;
            } while (right < predSLen && numOfCharType < 0);

            // right reaches the end and hasn't find an answer
            if (numOfCharType < 0 && right == predSLen)
                break;

            // find an answer
            if (numOfCharType == 0 && rightPos - leftPos + 1 < minLength) {
                minLeft = leftPos;
                minLength = rightPos - leftPos + 1;
            }

            // left slides to make the answer shorter
            while (left < right && numOfCharType == 0) {
                int[] currentPos = predS.get(left);
                leftPos = currentPos[0]; // update the actual position of left

                int temp = map.get(currentPos[1]);
                if (temp == 0)
                    numOfCharType--;
                map.put(currentPos[1], temp - 1);
                left++;
            }

            // find the shortest answer
            if (numOfCharType < 0 && rightPos - leftPos + 1 < minLength) {
                minLeft = leftPos;
                minLength = rightPos - leftPos + 1;
            }
        }

        // hasn't found an answer
        if (minLength > sLen)
            return "";

        return s.substring(minLeft, minLeft + minLength);
    }
}