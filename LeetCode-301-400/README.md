# LeetCode \#301&#45;400
* This is a study note for LeetCode problems \#301&#45;400, containing my various attempts and insights for each problem.  
* To be continued.  

---

## 303. Range Sum Query - Immutable

### a. prefix sum

---

## 304. Range Sum Query 2D - Immutable

### a. prefix sum 2D

---

## 307. Range Sum Query - Mutable

### a. segment tree: bottom-up build, bottom-up update, top-down query

### b. segment tree: top-down build, bottom-up update, top-down query

### c. array-based segment tree: top-down build, top-down update, top-down query

### d. Fenwick tree (BIT)
* `lowbit(i) = i & (-1)`
* `parent = i + lowbit(i)`

---

## 310. Minimum Height Trees

### a. leaf-first BFS: Wrong Answer
* Counter-example:
  * `n = 7`
  * `edges = [[0,1],[1,2],[1,3],[2,4],[3,5],[4,6]]`

### b. iterative leaf trimming (adjacency-matrix): Time Limit Exceeded

### c. iterative leaf trimming (adjacency-list): Time Limit Exceeded

### d. iterative leaf trimming (adjacency-list, modified): Time Limit Exceeded
* Find new leaves among neighbors of the previous leaves

### e. iterative leaf trimming (adjacency-matrix, modified): Time Limit Exceeded

---

## 315. Count of Smaller Numbers After Self

### a. brute force: Time Limit Exceeded

### b. binary search tree: Time Limit Exceeded
* Each Node stores:
  * `size`: number of integers in this node and its left subtree.
* In the worst case (e.g., strictly increasing array), the BST degrades to a linked list.

### c. binary search tree (modified): Time Limit Exceeded
* Each Node stores:
  * `cnt`: number of integers in this node;
  * `leftSize`: number of integers in its left subtree.
* Query for smaller integers and insert **in a single traversal**.

### d. AVL tree
* Each Node stores:
  * `cnt`: number of integers in this node;
  * `leftSize`: number of integers in its left subtree;
  * `rightSize`: number of integers in its right subtree;
  * `height`: height of this node (`1 + Math.max(left.height, right.height)`).
* LL (left subtree of left child is heavy): right rotate.
```
      A      B
     /      / \
    B   -> C   A
   / \        /
  C  (D)    (D)
```
* RR (right subtree of right child is heavy): left rotate.
```
  A          B
   \        / \
    B   -> A   C
   / \      \
 (D)  C     (D)
```
* LR (right subtree of left child is heavy): left rotate on B, then right rotate on A.
```
    A          A      C
   /          /      / \
  B          C      B   A
   \    ->  / \  ->    /
    C      B   D      D
   / \      \
 (E)  D     (E)
```
* RL (left subtree of right child is heavy): right rotate on B, then left rotate on A.
```
    A      A          C
     \      \        / \
      B      C      A   B
     /  ->  / \  ->  \
    C      D   B      D
   / \        /
  D  (E)    (E)
```

### e. red-black tree
* Each Node stores:
  * `red`: whether this node is red (`true`) or black (`false`);
  * `cnt`: number of integers in this node;
  * `leftSize`: number of integers in its left subtree;
  * `rightSize`: number of integers in its right subtree;
  * `parent`: parent of this node.
* Uncle is red: recolor.
```
        g(B)             g(R)
        /  \             /  \
     p(R)  u(R) ->    p(B)  u(B)
     /                /
  x(R)             x(R)
```
```
     g(B)             g(R)
     /  \             /  \
  u(R)  p(R)    -> u(B)  p(B)
           \                \
           x(R)             x(R)
```
* LL (parent is left child of grandparent, node is left child of parent)
```
        g(B)          p(B)
        /  \          /  \
     p(R)  u(B) -> x(R)  g(R)
     /  \                /  \
  x(R)   T1            T1   u(B)
```
* RR (parent is right child of grandparent, node is right child of parent)
```
     g(B)                p(B)
     /  \                /  \
  u(B)  p(R)    ->    g(R)  x(R)
        /  \          /  \
      T1   x(R)    u(B)   T1
```
* LR (parent is left child of grandparent, node is right child of parent)
```
     g(B)            g(B)          x(B)
     /  \            /  \          /  \
  p(R)  u(B)      x(R)  u(B)    p(R)  g(R)
     \       ->   /  \       ->    \  /  \
     x(R)       p(R)  T2          T1  T2 u(B)
     /  \          \
   T1    T2         T1
```
* RL (parent is right child of grandparent, node is left child of parent)
```
     g(B)          g(B)                x(B)
     /  \          /  \                /  \
  u(B)  p(R)    u(B)  x(R)          g(R)  p(R)
        /    ->       /  \    ->    /  \  /
     x(R)           T2   p(R)    u(B) T2  T1
     /  \                /
   T2    T1            T1
```

### f. array-based segment tree: top-down query, bottom-up insert
* Refer to Problem \#307. Range Sum Query - Mutable.

### g. array-based segment tree (modified): bottom-up query and insert
* Traverse upward from the leaf to the root, updating each visited node and accumulating the sums of left siblings along the path.

### h. Fenwick tree (BIT)
* Refer to Problem \#307. Range Sum Query - Mutable.

---

## 316. Remove Duplicate Letters

### a. two-direction scanning: Wrong Answer

### b. anchored two-direction scanning: Wrong Answer

### c. dual-anchor two-direction scanning: Wrong Answer

### d. brute-force backtrack: Time Limit Exceeded

### e. greedy replacement: Wrong Answer

### f. monotonic stack
* Maintain an increasing sequence by popping all larger characters before pushing the current one.
* Ensure each character appears only once:
  * Do not push a character if it is used previously;
  * Do not pop a character if it does not appear later.