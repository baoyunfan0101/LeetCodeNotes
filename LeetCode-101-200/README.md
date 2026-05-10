# LeetCode \#101&#45;200
* This is a study note for LeetCode problems \#101&#45;200, containing my various attempts and insights for each problem.  
* To be continued.  

---

## 101. Symmetric Tree

### a. recursion

---

## 102. Binary Tree Level Order Traversal

### a. naive BFS
* Bind each node to its level.

### b. BFS
* Each iteration processes all nodes currently in the queue, which form one level.

---

## 103. Binary Tree Zigzag Level Order Traversal

### a. BFS
* Track the current level; reverse the level list if the level is odd.

### b. BFS (modified)
* Track the current level; add each value to the head of the level list if the level is odd.

---

## 104. Maximum Depth of Binary Tree

### a. DFS

### b. BFS
* Each iteration processes all nodes currently in the queue, which form one level.

---

## 128. Longest Consecutive Sequence

### a. HashSet: Time Limit Exceeded

### b. HashSet start-expansion

### c. HashSet start-expansion (modified)

---

## 146. LRU Cache

### a. HashMap + doubly linked list
* Wrap key and value into a node, so that a hash map can map each key to both its value and its corresponding position in the doubly linked list.

### b. HashMap + doubly linked list (modified)

---

## 173. Binary Search Tree Iterator

### a. sentinel
* Add a sentinel node to the left of the tree.

### b. non-sentinel
* Don't connect the sentinel node to the tree.

### c. stack
* Rely solely on a stack; the top element is the next node.