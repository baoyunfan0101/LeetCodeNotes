// a. prefix sum
class NumArray {
    int[] arr = null;
    int[] prefix = null;

    public NumArray(int[] nums) {
        this.arr = nums;
        int len = this.arr.length;

        this.prefix = new int[len + 1];
        this.prefix[0] = 0;
        for (int i = 0; i < len; i++) {
            this.prefix[i + 1] = this.arr[i] + this.prefix[i];
        }
    }

    public int sumRange(int left, int right) {
        return this.prefix[right + 1] - this.prefix[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */