# LeetCode \#901&#45;1000
* This is a study note for LeetCode problems \#901&#45;1000, containing my various attempts and insights for each problem.  
* To be continued.  

---

## 994. Rotting Oranges

### a. brute force

### b. DFS
* Start DFS from each rotten orange, stopping if another rotten orange can reach them sooner.

### c. BFS
* First push all rotten oranges into a queue, then pop one and push the fresh oranges around it.