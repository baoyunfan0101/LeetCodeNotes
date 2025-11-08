/* original version
// create a HashSet to check for duplicates
// use a pointer to track the current position
// the problem is that it doesn't take advantage of the array's non-decreasing property
class Solution {
    public int removeDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int k = 0;
        for (int i : nums)
            if (!set.contains(i)) {
                set.add(i);
                nums[k++] = i;
            }
        return k;
    }
}
end original version */

/* original version (modified)
// avoid assigning an integer to itself
class Solution {
    public int removeDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int len = nums.length, k = 0;
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            if (!set.contains(num)) {
                set.add(num);
                if (i != k)
                    nums[k] = num;
                k++;
            }
        }
        return k;
    }
}
end original version (modified) */

// better version
// a duplicate is equal to its previous integer
class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length, k = 1;
        for (int i = 1; i < len; i++)
            if (nums[i - 1] != nums[i]) {
                if (i != k)
                    nums[k] = nums[i];
                k++;
            }
        return k;
    }
}