/* original version
// core idea: to generate grayCode(n), take grayCode(n-1) and append '0' to each code, then take the reversed grayCode(n-1) and append '1' to each code
// this ensures adjacent codes differ by exactly one bit, either in the original part or in the new trailing bit
class Solution {
    public List<Integer> grayCode(int n) {
        if (n == 1) {
            return new ArrayList<Integer>(List.of(0, 1));
        }

        List<Integer> res = grayCode(n - 1);

        // reversely iterate
        int len = res.size();
        for (int i = len - 1; i >= 0; i--) {
            Integer value = res.get(i);
            value <<= 1;
            res.set(i, value);
            res.add(value + 1);
        }

        return res;
    }
}
end original version */

/* original version (modified)
// append '0' and '1' as trailing bits instead of tailing bits
class Solution {
    private int diff = 1;

    public List<Integer> grayCode(int n) {
        if (n == 1) {
            return new ArrayList<Integer>(List.of(0, 1));
        }

        List<Integer> res = grayCode(n - 1);
        diff *= 2;

        // reversely iterate
        int len = res.size();
        for (int i = len - 1; i >= 0; i--)
            res.add(res.get(i) + diff);

        return res;
    }
}
end original version (modified) */

/* method 2: formula
// use the formula: grayCode(n)[i] = i ^ (i >> 1), valid for any n
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>();

        int len = (int) Math.pow(2, n);
        for (int i = 0; i < len; i++)
            res.add(i ^ (i >> 1));

        return res;
    }
}
end method 2: formula */

// method 2 (modified): formula
// use an Integer[] and convert it to a List<Integer> with Arrays.asList(Integer[])
class Solution {
    public List<Integer> grayCode(int n) {
        int len = (int) Math.pow(2, n);
        Integer[] a = new Integer[len];
        for (int i = 0; i < len; i++)
            a[i] = i ^ (i >> 1);

        return Arrays.asList(a);
    }
}