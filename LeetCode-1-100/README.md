# LeetCode #1&#45;100
* This is a study note for LeetCode problems #1&#45;100, containing my various attempts and insights for each problem.  
* To be continued.  

---

## 1 .Two Sum

### original version: brute force

### better version: hash map

---

## 2. Add Two Numbers

### original version

### better version
* simplified  

---

## 3. Longest Substring Without Repeating Characters

### original version: brute force

### wrong version
* whenever encounter a duplicate character, clear  
* it's wrong because a valid substring can step over a duplicate character  
* e.g. "abcdaefga" -> "bcdaefg"  

### wrong version
* record each character's last occurence position & current max length  
* it's wrong because only max length of every character is considered, but when combine all characters together?  

### better version: sliding window

---

## 4. Median of Two Sorted Arrays

### original version
* calculate the total index of the median, then find it using two pointers  

### worse version: binary search
* calculate the total index of the median, then find it using binary-like search  

---

## 5. Longest Palindromic Substring

### original version: brute force
* check every substirng  

### better version
* considering every character as the center, try to extend it to both sides  

---

## 6. Zigzag Conversion

### original version
* for every character in new string, calculate its position in original string  

### better version
* use `StringBuffer`, instead of `StringBuilder`  

### another better version
* use `String.toCharArrya()` to convert `String` to `char[]` first  

---

## 7. Reverse Integer

### original version
* enumerate all possibilities using if-else  

### better version
* pop out digit from original integer and put in digit into reversed integer simultaneously  

### better version
* since the first digit of a 10-digit integer must be 1 or 2  
* the units digit of a reversed integer must be 1 or 2  
* at the 9-th time, consider:  
  1. remainder < Integer.MIN_VALUE / 10  
  2. remainder > Integer.MAX_VALUE / 10  

---

## 8. String to Integer (atoi)

### original version
* enumerate all possibilities using switch-case  

---

## 9. Palindrome Number

### original version
* put it into a `String`  

### better version
* reverse the left half (rounded up)  
* if number of digits is even: original == reversed  
* if number of digits is odd: original == reversed / 10  

---

## 10. Regular Expression Matching

### original version: recursion
* always treat a character and the '*' following it as a single unit  

### better version: memoized recursion

### better version: dynamic programming
* values of a character and the '*' following it are always equal.  

---

## 11. Container With Most Water

### original version: two pointers
* initialize two pointers at the leftmost and rightmost positions  
* move the lower pointer inward until a higher elevation is found  

---

## 12. Integer to Roman

### original version
* enumerate using switch-case  

### better version
* enumerate using `static final String[]`  
* use a `StringBuilder`, instead of concatenating strings directly  

---

## 13. Roman to Integer

### original version
* enumerate every character and the character following it  

---

## 14. Longest Common Prefix

### original version
* check the first character of all strings, then the second, and so on  

---

## 15. 3Sum

### wrong version: brute force
* three nested loops to traverse the array  
* sort all answers for deduplication, and check every answer before inserting  
* _Time Limit Exceed_  

### wrong version: hash map for two sum
* build `HashMap`: sum of two integers -> list of index tuples  
* enumerate the third integer  
* sort all answers for deduplication, and check every answer before inserting  
* _Time Limit Exceed_  

### original version: hash map for number of integer values
* build `HashMap`: integer value, number of this value  
* find two integer values on `keySet`, where value1 <= value2 (when a value is used, it's number - 1)  
* calculate the third integer value, and find it on the `HashMap`  
* guarantee value1 <= value2 <= value3 to avoid duplications ('=' is okay there since we always find value1 and value2 on `keySet`, which mean value2 is different in each loop)  

### better version: fixed the first + two pointers
* sort the array  
  1. start from the left to pick the first integer  
  2. use two pointers on the remaining right part, indicatin the second and third integers  
  3. move the two pointers inward to search for valid combinations  
  4. shift the first integer rightward and repeat the process  
* for each movement (of all three integers), skip duplicate values to avoid duplications  

### better version: fixed the first + two pointers with pruning
* same as the previous version  
* apply pruning (early termination)  

---

## 16. 3Sum Closest

### original version: fixed the first + two pointers with pruning
* same as problem #15. 3Sum  
* keep track of the minimum diff from target  

---

## 17. Letter Combinations of a Phone Number

### original version: backtracking

---

## 18. 4Sum

### original version: fixed the first and the second + two pointers with pruning
* use long to avoid integer overflow  

---

## 19. Remove Nth Node From End of List

### original version: keep track of a (n + 1)-node queue

### better version: two pointers
* two pointers are (n + 1) steps apart  

---

## 20. Valid Parentheses

### original version
* implement a stack  

---

## 21. Merge Two Sorted Lists

### original version
* use a dummy node so the head can be handled uniformly  

---

## 22. Generate Parentheses

### original version: backtracking
* keep track of:  
    1. number of used left prentheses  
    2. difference between left and right prentheses  

---

## 23. Merge k Sorted Lists

### original version
* find the mininum node among lists, connect it to the end of the result list, and put its next node back into lists  
* the problem is to find the mininum node among lists, we have to traverse all nodes in lists, and each node may be visited multiple times  

### better version: priority queue
* implement a `PriorityQueue` and its `Comparable`  
* pop the queue to get the mininum node, and push its next node back into the queue  

---

## 24. Swap Nodes in Pairs

### original version
* use three pointers, and swap two of them every other step  
* create two dummy nodes to handle the linked list uniformly  

---

## 25. Reverse Nodes in k-Group

### original version
* use (k + 1) pointers, and reverse the last k of them every k steps  
* create k dummy nodes to handle the linked list uniformly  

### better version
* use (k + 1) pointers, reverse the last k of them, and treat the new end as the start to move k steps at a time  
* create a dummy node to handle the linked list uniformly  

### better version
* use two pointers: p0 points to the end of the previous k-group, and p1 moves forward, making each node point backward  
* if p1 finds fewer than k nodes remaining, restore these nodes to their original order  

---

## 26. Remove Duplicates from Sorted Array

### original version
* create a HashSet to check for duplicates  
* use a pointer to track the current position  
* the problem is that it doesn't take advantage of the array's non-decreasing property  

### original version (modified)
* same as the previous version  
* avoid assigning an integer to itself  

### better version
* a duplicate is equal to its previous integer  

---

## 27. Remove Element

### original version

### better version
* the left pointer searches for a position not equal to val, while the right pointer searches for a position equal to val  
* assign the right to the left  

---

## 28. Find the Index of the First Occurrence in a String

### original version: regular pattern matching

### better version: KMP algorithm

---

## 29. Divide Two Integers

### original version
* consider all special cases, and make both dividend and divisor positive  
* pick out each bit of the dividend, divide by the divisor, put the remainder back, and repeat  

### worse version
* same as the previous version  
* emunarate 0b1, 0b11, 0b111, ... as masks using `static final int[]`  

### better version: binary search
* make both dividend and divisor negative  
* try each bit of the quotient using binary search  

---

## 30. Substring with Concatenation of All Words

### wrong version: brute-force permutation
* generate all permutations of the words using backtracking, and regex-match each permutation in `String` s  
* the problem is that the time complexity explodes and duplicate permutations are not handled  
* _Time Limit Exceed_  

### wrong version: backtracking match with sliding window optimization
* regex-match each word in String s, and match the remaining words using backtracking  
* for each valid starting position, slide the substring as far as possible  
* _Time Limit Exceed_  

### original version: sliding window
* starting from positions 1, 2, ..., (words[0].length - 1), create a HashMap to count words in each window  
* slide the window as far as possible  

### original version (modified): sliding window
* same as the previous version  
* if the count of a word reaches 0, remove it from the `HashMap`  

---

## 31. Next Permutation

### original version
* at the end of a permutation, there is an increasing suffix (e.g., ...**1245**)  
* find the element right before this increasing slope (e.g., ..**3**1245)  
* find the smallest element in the suffix that is greater than this element, and swap them (e.g., ..**4**12**3**5)  
* sepcial cases:  
  1. for 54321, '2' is the pivot, and "1" is the suffix  
  2. for 12345, no pivot exists (set its index to -1), and "12345" is the suffix  

---

## 32. Longest Valid Parentheses

### original version
* try every index as a starting position  
* implement a stack to find the longest valid parentheses substring from each starting position  

### method 1: dynamic programming
* **core idea**: surround an existing valid parentheses substring with a new pair of "()"  
* **memory**: dp[i] stores the length of the longest valid parentheses ending at position i  
* **algorithm**: keep track of the index to the left of the longest valid parentheses, and for each position, try to extend leftward  

### method 2: stack
* **core idea**: if ')' appears more times than '(', the left part is wasted  
* **memory**: indices  
* **algorithm**: remove valid pairs so that the top of the stack always holds the last unmatched index  

### method 3: no extra space needed
* similar to the previous method, but only keep track of the difference between '(' and ')' and the last unmatched index  
* when scanning from left to right, it misses cases with too many '(' (e.g., "((((()")  
* therefore, scan in reverse as well, and take the maximum result  

---

## 33. Search in Rotated Sorted Array

### original version: binary search
* a special binary search where either the left side or the right side is monotonic  

---

## 34. Find First and Last Position of Element in Sorted Array

### original version: binary search
* first pass: when mid >= target, move left (while recording the first glimpse of target, which is the rightmost position)  
* second pass: start from that position, and when mid <= target, move right  

---

## 35. Search Insert Position

### original version: binary search
* perform binary search, and return the left pointer (the larger position)  

---

## 36. Valid Sudoku

### original version
* for each position, find its valid choices by combining the valid choices from the corresponding area, row, and column  

### better version
* for each area, row, and column, check for duplicates  

---

## 37. Sudoku Solver

### original version: backtracking recursion
* implementa a class `Status` to record the value and valid choices of each position  
* on every update, recalculate the valid choices for all positions and track the one with the fewest options  
* during each backtracking recursion, update and select the position with the fewest choices, then make a guess  

---

## 38. Count and Say

### original version: recursion

---

## 39. Combination Sum

### original version: backtracking
* sort the candidates and start from the largest one  
* in each recursion, decide on (a single occurrence of) a value to include in the combination, ensuring it is smaller than the previous one  

---

## 40. Combination Sum II

### wrong version: backtracking for each candidate
* same as problem #39. Combination Sum  
* _Time Limit Exceed_  

### wrong version: backtracking for each value
* count each value and its occurrence (a single value may appear multiple times)  
* in each recursion, decide whether to use the current value (the idx-th value):  
  1. if not used, move to the next value (idx += 1) and CANNOT move back  
  2. if used, keep the same index (idx = idx) and CAN use this value again, decreasing its remaining count by 1  
* _Time Limit Exceed_  

### original version: backtracking for each value using a hash map
* same as the previous version  
* manually create a hash map for each value and its occurrence  

### original version (modified): backtracking for each value using a hash map
* same as the previous version  
* pruning has been optimized  

---

## 41. First Missing Positive

### original version
* sort the array and traverse it from the beginning  
* **time complexity**: O(n log n)  

### method 1: hash map
* build a hash map based on the existing array:  
  1. assign an invalid large number to all negative integers to make all values non-negative  
  2. if positive integer i exists, set `nums[i] *= -1`  
  3. find the first positive integer from the beginning; its index minus 1 indicates the result  

### method 2: swap
* **core idea**: the only correct position for a positive integer `i` is at `nums[i - 1]`  
* for each integer `nums[i]` in the array (denoted as `val`):  
  1. if it's invalid (`val <= 0 || val > nums.length`), don’t bother with it  
  2. if it's valid:  
     1. place it at `nums[val - 1]`, unless `nums[val - 1]` already equals `val`  
     2. repeat the process for the original integer at `nums[val - 1]`  
* the loop will terminate in at most `nums.length` iterations, after which all values are in place  
* find the first integer that doesn’t match its index; it indicates the result  

---

## 42. Trapping Rain Water

### original version
* **core idea**: similar to problem #32. Longest Valid Parentheses - method 3  
* scan from left to rignt while keeping track of the current highest bar:  
  1. when encountering a lower bar, trap water equal to the difference in heights  
  2. when encountering a higher bar, the previously recorded trapped water becomes valid, so add it to the total and update the current highest bar  
* however, scanning only from left to right misses puddles that appear after the global highest bar  
* because the left boundary is too tall to find a matching right boundary  
* therefore, scan in reverse as well and conbine the results  

### method 2: monotonic stack
* for each bar `height[i]`:  
  1. if it's lower than the top of the stack, push it onto the stack  
  2. if it's higher than the top of the stack:  
     1. pop the top bar (call it mid), then:  
        * width = i - stack.peek() - 1 (distance between the current bar and the new stack top)  
        * height = min(height[i], height[stack.peek()]) - height[mid]  
     2. repeat the process until the current bar becomes the lowest one  

### method 3: two pointers
* similar to original version  
* always move the lower pointer inward while recording trapped water  

---

## 43. Multiply Strings

### original version
* add two strings by summing their digits  
* multiply strings by looking up the multiplication table and adding up the partial products  

### method 2: multiplication
* **core idea**: the product of the i-th digit of num1 and the j-th digit of num2, and the product of the j-th digit of num1 and the i-th digit of num2, both contribute to the same digit in the final product  

### method 2 (modified): multiplication
* same as the previous version  
* give up using the multiplication table  

---

## 44. Wildcard Matching

### original version: dynamic programming
* similar to problem #10. Regular Expression Matching  

---

## 45. Jump Game II

### original version
* suppose the minimum steps to reach the current position i is s, the farthest position reachable in (s + 1) steps is (i + nums[i])  
* continue searching and keep updating the farthest reachable position  
* when reaching the farthest position of s steps, the range for (s + 1) steps can no longer be extended, so start finding the range for (s + 2) steps  

---

## 46. Permutations

### silly version: next permutation
* reuse problem #31. Next Permutation  
* convert `int[] nums` to `List<Integer>`: `Arrays.stream(nums).boxed().collect(Collectors.toList())`  

### original version: backtracking
* in each iteration, pick one integer from a list  

### method 1: backtracking
* in each iteration, swap every integer after idx into idx instead of using a separate list for remaining integers  
* in short, integers left of idx are used; those to the right are unused  

---

## 47. Permutations II

### original version: backtracking
* similar to problem #46. Permutations - original version: backtracking  
* sort the array first, then skip duplicate values within the same iteration  

---

## 48. Rotate Image

### original version
* divide the square into four triangles arranged (like a windmill!)  

---

## 49. Group Anagrams

### original version: hash map
* sort all strings and use the sorted strings as keys  
* use a `HashMap` to map each sorted string to its corresponding indexes  

### better version: hash map
* similar to the previous version  
* use a `HashMap` to map each sorted string to its original strings  
* use `HashMap.values()` to directly form the result list  

---

## 50. Pow(x, n)

### original version: original version: binary recursion
* use recursion and halve the exponent in each iteration  

---

## 51. N-Queens

### original version: backtracking
* use an array of size n to track columns  
* use a (2 * n - 1) array to track main diagonals  
* use another (2 * n - 1) array to track anti-diagonals  

### method 2: bitwise operation
* use three integers to track columns, diagonals, and anti-diagonals  
* for each iteration:  
  1. bitwise OR the three trackers to find available positions for the current row  
  2. update column tracker with the intended position (bitwise OR)  
  3. update diagonal tracker with the intended position (bitwise OR), then shift left by 1  
  4. update anti-diagonal tracker with the intended position (bitwise OR), then shift right by 1  
* function stack automatically handles backtracking of tracker states  

### method 2 (modified): bitwise operation
* same as the previous method  
* each time updating the diagonal tracker, keep only the rightmost n bits and discard the overflow bits on the right  
* therefore, prune the recursion when all positions are occupied  

---

## 52. N-Queens II

### silly version
* reuse problem #51. N-Queens  

### better version
* similar to problem #51. N-Queens  
* only track the total count of valid solutions  

---

## 53. Maximum Subarray

### indelible version
* this piece of code has been on my desktop for years!  
* essentially, it's a space-optimized dynamic programming  

---

## 54. Spiral Matrix

### original version
* move in four directions during each loop iteration  

---

## 55. Jump Game

### original version
* iterate from the end and keep track of the leftmost reachable index  

### better version
* similar to the previous version  
* iterate from the start and keep track of the rightmost reachable index  
* stop looping once the rightmost reachable index exceeds the last index  

---

## 56. Merge Intervals

### original version
* override `Comparator` to sort intervals by their left bound  
* iterate through intervals and merge adjacent overlapping ones  

### better version
* since all bounds are integers in [0, 10000], map them onto a number axis  
* to prevent merging adjacent non-overlapping intervals like [1, 2] and [3, 4], double each bound  

---

## 57. Insert Interval

### original version
* iterate to find left intervals, merged intervals, and right intervals  
* convert a list to an array using: `List<int[]>.toArray(int[][]::new)`  

### worse version
* simplified  

---

## 58. Length of Last Word

### original version

---

## 59. Spiral Matrix II

### original version
* same as problem #54. Spiral Matrix  

---

## 60. Permutation Sequence

### original version
* there are 2^(n - 1) permutations starting with 1, so k / 2^(n - 1) determines the first digit, and similarly for the rest  

---

## 61. Rotate List

### original version: two pointers
* first calculate the length of the linked list to get k % length  

### better version
* similar to the previous version  
* first calculate the cutting position, which allows using one pointer  

---

## 62. Unique Paths

### original version: dynamic programming

---

## 63. Unique Paths II

### original version: dynamic programming

---

## 64. Minimum Path Sum

### worng version: backtracking
* _Time Limit Exceed_  

### original version: dynamic programming

---

## 65. Valid Number

### original version: regular expression

### original version (modified): regular expression

### original version (modified): precompiled regular expression
* create a `Pattern` and a `Matcher`  
* reset the `Matcher` with the target string before each match attempt  

---

## 66. Plus One

### original version
* handle special cases such as 999 + 1  

---

## 67. Add Binary

### original version

---

## 68. Text Justification

### original version
* each word is inherently followed by one space  
* dstribute remaining spaces evenly, handling the last line and single-word lines separately  

---

## 69. Sqrt(x)

### original version

### better version: binary search

---

## 70. Climbing Stairs

### original version
* if there is one 2, there are C_1^(n - 1) possible combinations, and so on  

### method 1: dynamic programming
* the final step can be either 1 or 2  

### method 1: space-optimized dynamic programming
* use only three temporary integers  

### method 1 (modified): space-optimized dynamic programming
* simplified initialization for starting conditions  

---

## 71. Simplify Path

### original version: stack
* split the string by '/' (including multiple consecutive '/')  
* use a stack to record directory and file names  

### original version (modified): stack
* same as the previous version  

---

## 72. Edit Distance

### original version: dynamic programming
* use `String.toCharArrya()` to convert `String` to `char[]` first  
* if `word1[i] == word2[j]`: `dp[i][j] = dp[i - 1][j - 1]`  
* else: `dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1`  

---

## 73. Set Matrix Zeroes

### original version: recursion
* in each iteration, locate a zero and recurse  
* on returning, set its entire row and column to zero  

### method 2: two signals
* basic idea: use two arrays to mark zeroes in each row and column  
* optimization: reuse the first row and first column as markers  

### better version
for each row:  
  1. for each column, if it's zero, set the column above to zero and simulate zeros in the last row  
  2. track whether the last row contains a zero, and set it to zero after processing  

---

## 74. Search a 2D Matrix

### original version: brute force

### method 1: double binary search

---

## 75. Sort Colors

### original version: quick sort

---

## 76. Minimum Window Substring

### original version: sliding window
* build a hash map from string t  
* use a sliding window to traverse s while updating the hash map  

### original version (modified 1): sliding window
* same as the previous version  
* preprocess string s to keep only characters that appear in string t  

---

## 77. Combinations

### original version: backtracking
* in each iteration, choose whether to include the current integer or not  

---

## 78. Subsets

### original version: backtracking
* in each iteration, choose whether to include the current element or not  

---

## 79. Word Search

### original method: dfs
* find the starting position and perform DFS while marking visited cells  
* in each step, explore four directions and backtrack afterward  

---

## 80. Remove Duplicates from Sorted Array II

### original version: two pointer
* count consecutive identical elements  

### original version (modified): two pointer
* same as the previous version  
* if `nums[left - 2] == nums[right]`, assign `nums[left] = nums[right]`  

---

## 81. Search in Rotated Sorted Array II

### original version: recursive binary search
* if `nums[left] == nums[mid] == nums[right]`, search both sides  

### method 1: binary search
* if `nums[left] == nums[mid] == nums[right]`, `left++` and `right--`  

---

## 82. Remove Duplicates from Sorted List II

### original version
* if an element appears more than once, remove all of its duplicates  

---

## 83. Remove Duplicates from Sorted List

### original version

---

## 84. Largest Rectangle in Histogram

### worng version: brute force
* _Time Limit Exceed_  

### worng version (modified): brute force
* keep track of the highest bar within each interval  
* _Time Limit Exceed_  

### prompted version: monotonic stack
* push all positions onto the stack, including those with the same height  

### prompted version (modified): monotonic stack
* a position in the stack represents a range where all bars up to the next index share the same remaining height  
* hence, each distinct height appears only once in the stack  

### method 2: monotonic stack
* use a monotonic stack to compute only the left and right boundaries for each position  
* push each position after popping all trailing bars that are not higher than the current one  

---

## 85. Maximal Rectangle

### enlightened version: dynamic programming with monotonic stack
* reuse problem #84. Largest Rectangle in Histogram  
* for each row and its consecutive rows above, treat each column's '1's as a bar  

---

## 86. Partition List

### original version
* find the first node with value >= `x`  
* insert all subsequent nodes with value < `x` before that node  

---

## 87. Scramble String

### wrong version: dynamic programming
* use a 2D array `dp[i][j]` to store lengths of longest matching substrings starting at `i` in `s1` and `j` in `s2`  
* repeatedly merge all legal matching substrings until no further merges are possible  
* _Counterexample_: `s1` = "hobobyrqd", `s2` = "hbyorqdbo"  
* this approach may miss valid substrings like "h(ob)obyrqd" vs "hbyorqd(bo)"  
* because "ho(bo)byrqd" is already paired with "hbyorqd(bo)" and cannot be split again  

### original version: dynamic programming
* try all possible legal substrings in increasing order of substring length  

### wrong version: recursion
* in each iteration, try all possible split positions and recurse on valid ones  
* for each split position, create hash maps for `s1[0:i+1]` and `s1[i+1:len]` to compare character frequencies  
* _Note_: if a deeper recursive call returns true, return true immediately; otherwise, CONTINUE WITHOUT RETURNING  
* _Counterexample_: `s1` = "eebaacbcbcadaaedceaaacadccd", `s2` = "eadcaacabaddaceacbceaabeccd"  
* _Time Limit Exceed_  

### wrong version (modified): recursion
* create only two hash maps per iteration  
* _Counterexample_: `s1` = "eebaacbcbcadaaedceaaacadccd", `s2` = "eadcaacabaddaceacbceaabeccd"  
* _Time Limit Exceed_  

### method 1: memorized recursion
* same as the prevoius version, but cache recursive results  

---

## 88. Merge Sorted Array

### original version

---

## 89. Gray Code

### original version
* core idea: to generate `grayCode(n)`, take `grayCode(n-1)` and append '0' to each code, then take the reversed `grayCode(n-1)` and append '1' to each code  
* this ensures adjacent codes differ by exactly one bit, either in the original part or in the new trailing bit  

### original version (modified)
* append '0' and '1' as trailing bits instead of tailing bits  

### method 2: formula
* use the formula: `grayCode(n)[i] = i ^ (i >> 1)`, valid for any n  

### method 2 (modified): formula
* use `Integer[]` and convert it to `List<Integer>` with `Arrays.asList(Integer[])`  

---

## 90. Subsets II

### original version: backtracking
* first, build a manual hash map to record the occurrences of each value  
* this removes the sequence order and retains only the values  
* in each iteration, choose either the current value (if available) or the next one  

### method 1: enumerate binary numbers
* for a set of length `len`, each integer in `[0, 1 << len)` represents a subset via its binary bits  
* sort the array, and for duplicates, choose only from their first occurrence or skip them  

---

## 91. Decode Ways

### original method: dynamic programming
* when adding a new character, either treat it as a separate letter or combine it with the trailing character of the current string  

### method 1: space-optimized dynamic programming
* use only three integers  

---

## 92. Reverse Linked List II

### original version

### original version (modified)
* use one fewer pointer  

---

## 93. Restore IP Addresses

### original version: backtracking
* in each iteration, try using the next 1, 2, or 3 characters as an integer  

---

## 94. Binary Tree Inorder Traversal

### original version
* preorder, inorder, postorder: root appears on the left, in the middle, on the right  

---

## 95. Unique Binary Search Trees II

### original version: recursion
* in each iteration, try every possible root and recurse on left and right subtrees  

---

## 96. Unique Binary Search Trees

### original version: memorized recursion
* same as problem #95. Unique Binary Search Trees II  
* but only record the count of valid BSTs instead of their structures  

### original method (modified): memorized recursion
* simplified  

### method 1: dynamic programming
* the number of valid BSTs with n nodes is fixed  

---

## 97. Interleaving String

### wrong version: recursion
* at each step, try matching `s1[n1]` with `s3[n3]` or `s2[n2]` with `s3[n3]`; if both match, explore both recursive paths  
* _Time Limit Exceed_  

### wrong version (modified): memorized recursion
* same as the previous version  
* _Time Limit Exceed_  

### original version: dynamic programming
* `dp[i][j]` indicates whether `s1[0:i]` and `s2[0:j]` can interleave to form `s3[0:i+j]`  
* transition: `dp[i][j]` is true if (`dp[i-1][j]` and `s1[i-1] == s3[i+j-1]`) or (`dp[i][j-1]` and `s2[j-1] == s3[i+j-1]`)  

### original version (modified): space-optimized dynamic programming

---

## 98. Validate Binary Search Tree

### original version: recursion
* handle edge cases: `Integer.MIN_VALUE` & `Integer.MAX_VALUE`  
* use `Integer` (object) instead of `int`, where `null` indicates no bound  

---

## 99. Recover Binary Search Tree

### silly verison: brute force
* reuse problem #98. Validate Binary Search Tree  
* try swapping each pair of nodes and check validity after each swap  

### method 1: inorder traversal
* for a valid BST, the inorder traversal must be strictly increasing  
* find all indices i where inorder[i].val < inorder[i - 1].val:  
  1. if there are two such indices, swap their values  
  2. if there is only one, swap inorder[i - 1] and inorder[i]  

### method 2: inorder traversal without storing the entire sequence
* similar to the previous version  
* identify the two invalid nodes during the inorder traversal process  

---

## 100. Same Tree

### original version: recursion