/* original version: hash map
// sort all strings and use the sorted strings as keys
// use a HashMap to map each sorted string to its corresponding indexes
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        int len = strs.length;

        // create hash map
        Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        for (int i = 0; i < len; i++) {
            char[] tempArray = strs[i].toCharArray();
            Arrays.sort(tempArray);
            String tempString = new String(tempArray);
            if (map.containsKey(tempString)) {
                List<Integer> idxList = map.get(tempString);
                idxList.add(i);
            } else {
                List<Integer> idxList = new ArrayList<Integer>();
                idxList.add(i);
                map.put(tempString, idxList);
            }
        }

//        // output
//        for (String str : map.keySet()) {
//            System.out.print(str + ": ");
//            for (Integer i : map.get(str))
//                System.out.print(i + " ");
//            System.out.println();
//        }

        // produce result
        List<List<String>> res = new ArrayList<List<String>>();
        for (String str : map.keySet()) {
            List<String> innerList = new ArrayList<String>();
            for (Integer i : map.get(str))
                innerList.add(strs[i]);
            res.add(innerList);
        }
        return res;
    }
}
end original version */

// better version: hash map
// similar to the previous version
// use a HashMap to map each sorted string to its original strings
// use HashMap.values() to directly form the result list
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        int len = strs.length;

        // create hash map
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (int i = 0; i < len; i++) {
            char[] tempArray = strs[i].toCharArray();
            Arrays.sort(tempArray);
            String tempString = new String(tempArray);
            if (map.containsKey(tempString)) {
                List<String> strList = map.get(tempString);
                strList.add(strs[i]);
            } else {
                List<String> strList = new ArrayList<String>();
                strList.add(strs[i]);
                map.put(tempString, strList);
            }
        }

        return new ArrayList<List<String>>(map.values());
    }
}