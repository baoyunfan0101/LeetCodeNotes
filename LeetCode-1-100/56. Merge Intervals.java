/* original version
// override Comparator to sort intervals by their left bound
// iterate through intervals and merge adjacent overlapping ones
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });
        List<int[]> resList = new ArrayList<int[]>();
        int[] currentInterval = null;
        for (int[] interval : intervals) {
//            // output
//            System.out.format("(%d, %d) (%d, %d)\r\n",
//                    currentInterval == null ? -1 : currentInterval[0],
//                    currentInterval == null ? -1 : currentInterval[1],
//                    interval[0],
//                    interval[1]);

            if (currentInterval == null)
                currentInterval = interval;
            else if (currentInterval[1] >= interval[0])
                currentInterval[1] = Math.max(currentInterval[1], interval[1]);
            else {
                resList.add(currentInterval);
                currentInterval = interval;
            }
        }
        if (currentInterval != null)
            resList.add(currentInterval);
        return resList.toArray(int[][]::new);
    }
}
end original version */

// better version
// since all bounds are integers in [0, 10000], map them onto a number axis
// to prevent merging adjacent non-overlapping intervals like [1, 2] and [3, 4], double each bound
class Solution {
    public int[][] merge(int[][] intervals) {
        boolean[] intervalRange = new boolean[20002];
        int minPos = 10000, maxPos = 0;
        for (int[] interval : intervals) {
            minPos = Math.min(minPos, interval[0]);
            maxPos = Math.max(maxPos, interval[1]);
            for (int n = (interval[0] << 1); n < (interval[1] << 1) + 1; n++)
                intervalRange[n] = true;
        }

        List<int[]> resList = new ArrayList<int[]>();
        minPos = (minPos << 1);
        maxPos = (maxPos << 1) + 2;
        int i = minPos;
        while (i < maxPos) {
            int startPos = i >> 1;
            while (i < maxPos && intervalRange[i])
                i++;
            if (i < maxPos)
                resList.add(new int[] { startPos, (i >> 1) });
            while (i < maxPos && !intervalRange[i])
                i++;
        }

        return resList.toArray(int[][]::new);
    }
}