// original version
// initialize two pointers at the leftmost and rightmost positions
// move the lower pointer inward until a higher elevation is found
class Solution {
    public int maxArea(int[] height) {
        int ln = 0, rn = height.length - 1;
        int lh = height[ln], rh = height[rn]; 
        int res = Math.min(lh, rh) * (rn - ln);
        while(ln < rn) {
            //System.out.println(ln + " " + rn);
            if(height[ln] > lh) {
                lh = height[ln];
                int s = Math.min(lh, rh) * (rn - ln);
                if(s > res)
                    res = s;
            }
            else if(height[rn] > rh) {
                rh = height[rn];
                int s = Math.min(lh, rh) * (rn - ln);
                if(s > res)
                    res = s;
            }
            if(lh <= rh)
                ln++;
            else
                rn--;
        }
        return res;
    }
}