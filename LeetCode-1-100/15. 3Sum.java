/* worng version: brute force
// three nested loops to traverse the array
// sort all answers for deduplication, and check every answer before inserting
// Time Limit Exceed
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                label: for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        Integer[] currentArray = new Integer[] { nums[i], nums[j], nums[k] };
                        Arrays.sort(currentArray);
                        List<Integer> currentList = new ArrayList<Integer>();
                        Collections.addAll(currentList, currentArray);
                        for (List<Integer> innerList : res)
                            if (innerList.get(0) == currentList.get(0)
                                    && innerList.get(1) == currentList.get(1)
                                    && innerList.get(2) == currentList.get(2))
                                continue label;
                        res.add(currentList);
                    }
                }
            }
        }
        return res;
    }
}
end worng version */

/* worng version: hash map for two sum
// build HashMap: sum of two integers -> list of index tuples
// enumerate the third integer
// sort all answers for deduplication, and check every answer before inserting
// Time Limit Exceed
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int s = nums[i] + nums[j];
                if (map.containsKey(s)) {
                    List<Integer> n = map.get(s);
                    n.add(nums[i] <= nums[j] ? i : j); // sort the two numbers
                    n.add(nums[i] <= nums[j] ? j : i);
                } else {
                    List<Integer> n = new ArrayList<Integer>();
                    n.add(nums[i] <= nums[j] ? i : j); // sort the two numbers
                    n.add(nums[i] <= nums[j] ? j : i);
                    map.put(s, n);
                }
            }
        }

//        // output map
//        System.out.println("map:");
//        for (Integer i : map.keySet()) {
//            System.out.format("%-2d: ", i);
//            List<Integer> n = map.get(i);
//            for (Integer j : n)
//                System.out.format("%-2d, ", j);
//            System.out.println();
//        }

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < len; i++) {
            if (map.containsKey(-nums[i])) {
                List<Integer> n = map.get(-nums[i]);
                int nLen = n.size();
                label: for (int j = 0; j + 1 < nLen; j += 2) {
                    int n1 = n.get(j), n2 = n.get(j + 1);
                    if (i != n1 && i != n2) {
                        List<Integer> newList = new ArrayList<Integer>();
                        // sort the three numbers(nums[n1] and nums[n2] are already sorted)
                        if (nums[i] <= nums[n1]) {
                            newList.add(nums[i]);
                            newList.add(nums[n1]);
                            newList.add(nums[n2]);
                        } else if (nums[i] <= nums[n2]) {
                            newList.add(nums[n1]);
                            newList.add(nums[i]);
                            newList.add(nums[n2]);
                        } else {
                            newList.add(nums[n1]);
                            newList.add(nums[n2]);
                            newList.add(nums[i]);
                        }
                        // duplicate checking
                        for (List<Integer> innerList : res)
                            if (innerList.get(0) == newList.get(0)
                                    && innerList.get(1) == newList.get(1)
                                    && innerList.get(2) == newList.get(2))
                                continue label;
                        res.add(newList);
                    }
                }
            }
        }

//        // output res
//        System.out.println("res:");
//        for (List<Integer> innerList : res) {
//            System.out.print("[");
//            for (Integer i : innerList)
//                System.out.format("%-2d, ", i);
//            System.out.println("]");
//        }

        return res;
    }
}
end worng version */

/* original version: hash map for number of integer valuess
// build HashMap: integer value, number of this value
// find two integer values on keySet, where value1 <= value2 (when a value is used, it's number - 1)
// calculate the third integer value, and find it on the HashMap
// guarantee value1 <= value2 <= value3 to avoid duplication ('=' is okay there since we always find value1 and value2 on keySet, which mean value2 is different in each loop)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < len; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }

//        // output map
//        System.out.println("map:");
//        for(Integer i : map.keySet()) {
//        	System.out.format("%-2d: %-2d\r\n", i, map.get(i));
//        }

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> keyList = new ArrayList<>(map.keySet());
        int keyLen = keyList.size();
        for (int i = 0; i < keyLen; i++) {
            int key1 = keyList.get(i), value1 = map.get(key1);
            map.put(key1, value1 - 1); // a value1 is used
            for (int j = i; j < keyLen; j++) {
                int key2 = keyList.get(j), value2 = map.get(key2);
                map.put(key2, value2 - 1); // a value2 is used
                if (value2 > 0) { // there is as least one key2 left
                    int sum = -key1 - key2;
                    // guarantee that sum is the largest one to avoid duplication, and assure there is as least one sum left
                    if (sum >= Math.max(key1, key2) && map.containsKey(sum) && map.get(sum) > 0) {
                        res.add(new ArrayList<Integer>(Arrays.asList(key1, key2, sum)));
                    }
                }
                map.put(key2, value2);
            }
            map.put(key1, value1);
        }

//        // output res
//        System.out.println("res:");
//        for(List<Integer> innerList : res) {
//        	System.out.print("[");
//        	for(Integer i : innerList)
//        		System.out.format("%-2d, ", i);
//        	System.out.println("]");
//        }

        return res;
    }
}
end original version */

/* better version: fixed the first + two pointers
// sort the array
// start from the left to pick the first integer
// use two pointers on the remaining right part, indicatin the second and third integers
// move the two pointers inward to search for valid combinations
// shift the first integer rightward and repeat the process
// for each movement (of all three integers), skip duplicate values to avoid duplications
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int i = 0;
        while (i < len) {
            int head = i + 1, end = len - 1;
            while (head < end) {
                int sum = nums[i] + nums[head] + nums[end];
                if (sum == 0)
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[head], nums[end])));
                if (sum <= 0)
                    do {
                        head++;
                    } while (head < end && nums[head - 1] == nums[head]);
                else
                    do {
                        end--;
                    } while (head < end && nums[end] == nums[end + 1]);
            }
            do {
                i++;
            } while (i < len && nums[i - 1] == nums[i]);
        }

//        // output res
//        System.out.println("res:");
//        for(List<Integer> innerList : res) {
//        	System.out.print("[");
//        	for(Integer innerInt : innerList)
//        		System.out.format("%-2d, ", innerInt);
//        	System.out.println("]");
//        }

        return res;
    }
}
end better version */

// better version: fixed the first + two pointers with pruning
// the same as last version
// apply pruning (early termination)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int i = 0;
        while (i < len - 2) {
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0)
                break;
            if (nums[i] + nums[len - 2] + nums[len - 1] >= 0) {
                int head = i + 1, end = len - 1;
                while (head < end) {
                    int sum = nums[i] + nums[head] + nums[end];
                    if (sum == 0)
                        res.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[head], nums[end])));
                    if (sum <= 0)
                        do {
                            head++;
                        } while (head < end && nums[head - 1] == nums[head]);
                    else
                        do {
                            end--;
                        } while (head < end && nums[end] == nums[end + 1]);
                }
            }
            do {
                i++;
            } while (i < len && nums[i - 1] == nums[i]);
        }

//        // output res
//        System.out.println("res:");
//        for(List<Integer> innerList : res) {
//        	System.out.print("[");
//        	for(Integer innerInt : innerList)
//        		System.out.format("%-2d, ", innerInt);
//        	System.out.println("]");
//        }

        return res;
    }
}