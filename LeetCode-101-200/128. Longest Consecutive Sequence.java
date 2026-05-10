// a. HashSet min-max range scan: Time Limit Exceeded
class Solution {
    public int longestConsecutive(int[] nums) {
        int len = nums.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<Integer>();
        for (int i: nums) {
            set.add(i);
            min = Math.min(min, i);
            max = Math.max(max, i);
        }

        Integer startNum = null;
        int maxLen = 0;
        for (int i = min; i <= max; i++) {
            if (set.contains(i)) {
                if (startNum == null) {
                    startNum = i;
                }
                maxLen = Math.max(maxLen, i - startNum + 1);
            }
            else {
                startNum = null;
            }
        }

        return maxLen;
    }
}

// b. HashSet start-expansion
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i: nums) {
            set.add(i);
        }

        int maxLen = 0;
        for (Integer i: set) {
            if (!set.contains(i - 1)) {
                int startNum = i;
                do {
                    maxLen = Math.max(maxLen, i - startNum + 1);
                } while (set.contains(++i));
            }
        }

        return maxLen;
    }
}

// c. HashSet start-expansion (modified)
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i: nums) {
            set.add(i);
        }

        int maxLen = 0;
        for (Integer i: set) {
            if (!set.contains(i - 1)) {
                int len = 0;
                do {
                    i++;
                    len++;
                } while (set.contains(i));
                maxLen = Math.max(maxLen, len);
            }
        }

        return maxLen;
    }
}