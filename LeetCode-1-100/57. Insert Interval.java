// original version
// iterate to find left intervals, merged intervals, and right intervals
// convert a list to an array using: List<int[]>.toArray(int[][]::new)
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0)
            return new int[][] { newInterval };
//        // make both start and end arranged in ascending order
//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] a, int[] b) {
//                return a[0] - b[0] != 0 ? a[1] - b[1] : a[0] - b[0];
//            }
//        });
        List<int[]> resList = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; i++)
            if (intervals[i][1] < newInterval[0]) {
                resList.add(intervals[i]);
                if (i == intervals.length - 1)
                    resList.add(newInterval);
            } else {
                int leftEnd = Math.min(intervals[i][0], newInterval[0]);
                int j = i;
                while (j < intervals.length && intervals[j][1] < newInterval[1])
                    j++;
                if (j < intervals.length && intervals[j][0] <= newInterval[1])
                    resList.add(new int[] { leftEnd, intervals[j++][1] });
                else
                    resList.add(new int[] { leftEnd, newInterval[1] });
                for (; j < intervals.length; j++)
                    resList.add(intervals[j]);
                break;
            }
        return resList.toArray(int[][]::new);
    }
}

/* worse version
// simplified
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> resList = new ArrayList<int[]>();
        int leftEnd = newInterval[0], rightEnd = newInterval[1];
        boolean addNew = false;
        for (int i = 0; i < intervals.length; i++)
            if (intervals[i][0] <= newInterval[1] && intervals[i][1] >= newInterval[0]) {
                leftEnd = Math.min(leftEnd, intervals[i][0]);
                rightEnd = Math.max(rightEnd, intervals[i][1]);
            } else {
                if (!addNew && intervals[i][0] > rightEnd) {
                    resList.add(new int[] { leftEnd, rightEnd });
                    addNew = true;
                }
                resList.add(intervals[i]);
            }
        if (!addNew)
            resList.add(new int[] { leftEnd, rightEnd });
        return resList.toArray(int[][]::new);
    }
}
end worse version */