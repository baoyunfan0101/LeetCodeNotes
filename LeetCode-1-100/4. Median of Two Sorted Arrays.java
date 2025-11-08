// original version
// calculate the total index of the median, then find it using two pointers
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        // consider whether m or n is empty
        if(m == 0)
            return n % 2 == 0? ((double)nums2[n/2 - 1] + nums2[n/2]) / 2: nums2[n/2];
        else if(n == 0)
            return m % 2 == 0? ((double)nums1[m/2 - 1] + nums1[m/2]) / 2: nums1[m/2];
        // both m and n are not empty
        int len = m + n, pos = len / 2 + 1;
        // if len is even (which means len is greater than or equal to 2)
        if(len % 2 == 0) {
            int p1 = 0, p2 = 0, max1 = (int)-1e6, max2 = (int)-1e6; // initialize max1 and max2 to minimum value
            for(int i = 0; i < pos; i ++) {
                if(p1 >= m) { // consider there're still ints left in nums1
                    max1 = max2;
                    max2 = nums2[p2];
                    p2++;
                }
                else if(p2 >= n) {
                    max1 = max2;
                    max2 = nums1[p1];
                    p1++;
                }
                else if(nums1[p1] <= nums2[p2]) {
                    max1 = max2;
                    max2 = nums1[p1];
                    p1++;
                }
                else {
                    max1 = max2;
                    max2 = nums2[p2];
                    p2++;
                }
            }
            return ((double)max1 + max2) / 2;
        }
        else {
            int p1 = 0, p2 = 0, max = (int)-1e6; // initialize max to minimum value
            for(int i = 0; i < pos; i ++) {
                if(p1 >= m) {
                    max = nums2[p2];
                    p2++;
                }
                else if(p2 >= n) {
                    max = nums1[p1];
                    p1++;
                }
                else if(p1 < m && nums1[p1] <= nums2[p2]) {
                    max = nums1[p1];
                    p1++;
                }
                else {
                    max = nums2[p2];
                    p2++;
                }
            }
            return max;
        }
    }
}

/* worse version
// calculate the total index of the median, then find it using binary-like search
class Solution {
    // find the pos-th number
    private double find(int[] nums1, int h1, int[] nums2, int h2, int pos) {
        int m = nums1.length, n = nums2.length;
        //System.out.println("begin\t: h1 = " + h1 + "; h2 = " + h2 + "; pos = " + pos);
        if(h1 >= m)
            return nums2[h2 + pos];
        else if(h2 >= n)
            return nums1[h1 + pos];
        else if(pos == 0)
            return nums1[h1] <= nums2[h2]? nums1[h1]: nums2[h2];
        int temp = pos % 2 == 0? pos / 2 - 1: pos / 2;
        int p1 = (h1 + temp) < m? h1 + temp: m - 1;
        int p2 = (h2 + temp) < n? h2 + temp: n - 1;
        //System.out.println("end\t\t: p1 = " + p1 + "; p2 = " + p2 + "; temp = " + temp);
        if(nums1[p1] <= nums2[p2])
            return find(nums1, p1 + 1, nums2, h2, pos + h1 - p1 - 1);
        else
            return find(nums1, h1, nums2, p2 + 1, pos + h2 - p2 - 1);
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, k = m + n, pos = (k + 1) / 2 - 1;
        if(k % 2 == 1)
            return find(nums1, 0, nums2, 0, pos);
        else
            return (find(nums1, 0, nums2, 0, pos) + find(nums1, 0, nums2, 0, pos + 1)) / 2;
    }
}
end worse version */