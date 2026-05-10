// a. leaf-first BFS: Wrong Answer
class Solution {
    private void display(Deque<Integer> q) {
        int len = q.size();
        for (int i = 0; i < len; i++) {
            int e = q.poll();
            System.out.printf("%d ", e);
            q.offer(e);
        }
        System.out.println();
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1)
            return List.of(0);

        boolean[][] table = new boolean[n][n];
        for (int[] edge: edges) {
            table[edge[0]][edge[1]] = true;
            table[edge[1]][edge[0]] = true;
        }
        // System.out.println(Arrays.deepToString(table));

        // Insert all leaves into the queue
        boolean[] visited = new boolean[n];
        Deque<Integer> q = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (table[i][j]) {
                    cnt++;
                    if (cnt > 1)
                        break;
                }
            }
            if (cnt == 1) {
                visited[i] = true;
                q.offer(i);
            }
        }

        List<Integer> res = null;
        while (!q.isEmpty()) {
            // display(q);
            res = new ArrayList<Integer>(q);
            int len = q.size();
            for (int i = 0; i < len; i++) {
                int node = q.poll();
                for (int j = 0; j < n; j++) {
                    if (table[node][j] && !visited[j]) {
                        visited[j] = true;
                        q.offer(j);
                    }
                }
            }
        }

        return res;
    }
}

// b. iterative leaf trimming (adjacency-matrix): Time Limit Exceeded
class Solution {
    private void display(List<Integer> l) {
        for (int i: l)
            System.out.printf("%d ", i);
        System.out.println();
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        boolean[][] table = new boolean[n][n];
        for (int[] edge: edges) {
            table[edge[0]][edge[1]] = true;
            table[edge[1]][edge[0]] = true;
        }
        // System.out.println(Arrays.deepToString(table));\

        boolean[] visited = new boolean[n];
        List<Integer> res = null;

        // Each time, remove all leaves
        while (true) {
            List<Integer> leaves = new ArrayList<Integer>();

            int nodesCnt = 0, firstNode = -1;
            for (int i = 0; i < n; i++) {
                // Skip visited nodes
                if (visited[i])
                    continue;
                // Record the first node (the only node)
                if (++nodesCnt == 1)
                    firstNode = i;

                int edgesCnt = 0;
                for (int j = 0; j < n; j++) {
                    // Skip visited nodes
                    if (visited[j])
                        continue;

                    if (table[i][j] && ++edgesCnt > 1)
                        break;
                }
                if (edgesCnt == 1)
                    leaves.add(i);
            }

            // Only one node left
            if (nodesCnt == 1)
                leaves.add(firstNode);

            if (leaves.isEmpty())
                break;

            // Mark all leaves as visited
            for (int i: leaves)
                visited[i] = true;

            // display(leaves);
            res = new ArrayList<Integer>(leaves);
        }

        return res;
    }
}

// c. iterative leaf trimming (adjacency-list): Time Limit Exceeded
class Solution {
    private void display(List<Integer> l) {
        for (int i: l)
            System.out.printf("%d ", i);
        System.out.println();
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1)
            return List.of(0);

        Map<Integer, Set<Integer>> nodes = new HashMap<Integer, Set<Integer>>();
        for (int[] edge: edges) {
            int i = edge[0], j = edge[1];

            // put i -> j
            if (nodes.containsKey(i))
                nodes.get(i).add(j);
            else {
                Set<Integer> s = new HashSet<Integer>();
                s.add(j);
                nodes.put(i, s);
            }

            // put j -> i
            if (nodes.containsKey(j))
                nodes.get(j).add(i);
            else {
                Set<Integer> s = new HashSet<Integer>();
                s.add(i);
                nodes.put(j, s);
            }
        }

        List<Integer> res = null;

        // Each time, remove all leaves
        while (true) {
            List<Integer> leaves = new ArrayList<Integer>();

            if (nodes.size() == 1)
                for (int node: nodes.keySet())
                    leaves.add(node);
            else
                for (int node: nodes.keySet()) {
                    Set<Integer> s = nodes.get(node);
                    if (s.size() == 1)
                        leaves.add(node);
                }

            if (leaves.isEmpty())
                break;

            // Remove all leaves
            for (int leaf: leaves) {
                // Remove all its edges
                Set<Integer> s = nodes.get(leaf);
                for (int end: s)
                    nodes.get(end).remove(leaf);

                // Remove itself
                nodes.remove(leaf);
            }

            // display(leaves);
            res = new ArrayList<Integer>(leaves);
        }

        return res;
    }
}

// d. iterative leaf trimming (adjacency-list, modified): Time Limit Exceeded
class Solution {
    private void display(List<Integer> l) {
        for (int i: l)
            System.out.printf("%d ", i);
        System.out.println();
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1)
            return List.of(0);

        Map<Integer, Set<Integer>> nodes = new HashMap<Integer, Set<Integer>>();
        for (int[] edge: edges) {
            int i = edge[0], j = edge[1];

            // put i -> j
            if (nodes.containsKey(i))
                nodes.get(i).add(j);
            else {
                Set<Integer> s = new HashSet<Integer>();
                s.add(j);
                nodes.put(i, s);
            }

            // put j -> i
            if (nodes.containsKey(j))
                nodes.get(j).add(i);
            else {
                Set<Integer> s = new HashSet<Integer>();
                s.add(i);
                nodes.put(j, s);
            }
        }

        List<Integer> res = new ArrayList<Integer>();
        List<Integer> leaves = new ArrayList<Integer>();
        Set<Integer> neighbors = new HashSet<Integer>(nodes.keySet());

        // Each time, remove all leaves
        while (true) {
            leaves.clear();

            if (nodes.size() == 1)
                for (int node: nodes.keySet())
                    leaves.add(node);
            else // Find new leaves within neighbors of previous leaves
                for (int neighbor: neighbors) {
                    Set<Integer> s = nodes.get(neighbor);
                    if (s.size() == 1)
                        leaves.add(neighbor);
                }

            if (leaves.isEmpty())
                break;

            // Remove all leaves while recording their neighbors
            neighbors.clear();
            for (int leaf: leaves) {
                // Remove all its edges
                Set<Integer> s = nodes.get(leaf);
                for (int end: s) {
                    nodes.get(end).remove(leaf);
                    neighbors.add(end);
                }
            }
            for (int leaf: leaves) {
                // Remove itself
                nodes.remove(leaf);
                neighbors.remove(leaf);
            }

            // display(leaves);
            res.clear();
            res.addAll(leaves);
        }

        return res;
    }
}

// e. iterative leaf trimming (adjacency-matrix, modified): Time Limit Exceeded
class Solution {
    private void display(List<Integer> l) {
        for (int i: l)
            System.out.printf("%d ", i);
        System.out.println();
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1)
            return List.of(0);

        boolean[][] table = new boolean[n][n];
        int[] edgeCnt = new int[n];
        for (int[] edge: edges) {
            int i = edge[0], j = edge[1];
            table[i][j] = true;
            table[j][i] = true;
            edgeCnt[i]++;
            edgeCnt[j]++;
        }

        List<Integer> res = new ArrayList<Integer>();
        List<Integer> leaves = new ArrayList<Integer>();
        Set<Integer> neighbors = new HashSet<Integer>();

        // Each time, remove all leaves
        while (true) {
            leaves.clear();

            // Find new leaves among neighbors of previous leaves
            for (int neighbor: neighbors)
                if (edgeCnt[neighbor] == 1)
                    leaves.add(neighbor);

            if (leaves.isEmpty())
                for (int i = 0; i < n; i++)
                    if (edgeCnt[i] == 1)
                        leaves.add(i);

            if (leaves.isEmpty())
                break;

            // Remove all leaves while recording their neighbors
            neighbors.clear();
            for (int leaf: leaves) {
                // Remove all its edges
                for (int end = 0; end < n; end++) {
                    if (table[leaf][end]) {
                        table[end][leaf] = false;
                        // Prevent the neighbor's degree from dropping to 0
                        if (--edgeCnt[end] == 0)
                            edgeCnt[end]++;
                    }
                    neighbors.add(end);
                }
                edgeCnt[leaf] = 0;
            }

            res.clear();
            res.addAll(leaves);
            // display(leaves);
            // System.out.println(Arrays.toString(edgeCnt));
        }

        return res;
    }
}