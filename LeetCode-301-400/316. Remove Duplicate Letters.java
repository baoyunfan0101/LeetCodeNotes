// a. two-direction scanning: Wrong Answer
class Solution {
    private class Node {
        char c;
        Node prev;
        Node next;

        Node() {}

        Node(char c, Node prev) {
            this.c = c;
            this.prev = prev;
        }
    }

    Node head = new Node();
    Node tail = new Node();

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public String removeDuplicateLetters(String s) {
        boolean[] table = new boolean[26];
        int distinct = 0;

        Node p = head;
        for (char c: s.toCharArray()) {
            // Record distinct characters
            if (table[c - 'a'] == false) {
                distinct++;
            }
            table[c - 'a'] = true;

            // Build the doubly linked list
            Node node = new Node(c, p);
            p.next = node;
            p = p.next;
        }
        p.next = tail;
        tail.prev = p;

        while (true) {
            // Select next smallest character
            char c = '\0';
            for (int i = 0; i < 26; i++) {
                if (table[i] == true) {
                    c = (char)('a' + i);
                    table[i] = false;
                    break;
                }
            }
            if (c == '\0')
                break;

            // Remove duplicates in forward traversal
            boolean finded = false;
            p = p.next;
            while (p != tail) {
                if (p.c == c) {
                    if (finded == false) {
                        finded = true;
                    }
                    else {
                        remove(p);
                    }
                }
                p = p.next;
            }

            // Select next largest character
            c = '\0';
            for (int i = 25; i >= 0; i--) {
                if (table[i] == true) {
                    c = (char)('a' + i);
                    table[i] = false;
                    break;
                }
            }
            if (c == '\0')
                break;

            // Remove duplicates in backward traversal
            finded = false;
            p = p.prev;
            while (p != head) {
                if (p.c == c) {
                    if (finded == false) {
                        finded = true;
                    }
                    else {
                        remove(p);
                    }
                }
                p = p.prev;
            }
        }

        // Build the result string from the linked list
        StringBuilder sb = new StringBuilder();
        p = head.next;
        while (p != tail) {
            sb.append(p.c);
            p = p.next;
        }
        return sb.toString();
    }
}

// b. anchored two-direction scanning: Wrong Answer
class Solution {
    private class Node {
        char c;
        Node prev;
        Node next;

        Node() {}

        Node(char c, Node prev) {
            this.c = c;
            this.prev = prev;
        }
    }

    Node head = new Node();
    Node tail = new Node();

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public String removeDuplicateLetters(String s) {
        boolean[] table = new boolean[26];
        int distinct = 0;

        Node p = head;
        for (char c: s.toCharArray()) {
            // Record distinct characters
            if (table[c - 'a'] == false) {
                distinct++;
            }
            table[c - 'a'] = true;

            // Build the doubly linked list
            Node node = new Node(c, p);
            p.next = node;
            p = p.next;
        }
        p.next = tail;
        tail.prev = p;

        Node lastChar = head;
        while (true) {
            char c = '\0';
            for (int i = 0; i < 26; i++) {
                if (table[i] == true) {
                    c = (char)('a' + i);
                    table[i] = false;
                    break;
                }
            }
            if (c == '\0')
                break;

            boolean finded = false;
            Node currChar = null;

            // Remove duplicates in forward traversal from lastChar
            p = lastChar;
            while (p != tail) {
                if (p.c == c) {
                    if (finded == false) {
                        currChar = p;
                        finded = true;
                    }
                    else {
                        remove(p);
                    }
                }
                p = p.next;
            }

            // Remove duplicates in backward traversal from lastChar
            p = lastChar;
            while (p != head) {
                if (p.c == c) {
                    if (finded == false) {
                        currChar = p;
                        finded = true;
                    }
                    else {
                        remove(p);
                    }
                }
                p = p.prev;
            }

            lastChar = currChar;
        }

        // Build the result string from the linked list
        StringBuilder sb = new StringBuilder();
        p = head.next;
        while (p != tail) {
            sb.append(p.c);
            p = p.next;
        }
        return sb.toString();
    }
}

// c. dual-anchor two-direction scanning: Wrong Answer
class Solution {
    private class Node {
        char c;
        Node prev;
        Node next;

        Node() {}

        Node(char c, Node prev) {
            this.c = c;
            this.prev = prev;
        }
    }

    Node head = new Node();
    Node tail = new Node();

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public String removeDuplicateLetters(String s) {
        boolean[] table = new boolean[26];
        int distinct = 0;

        Node p = head;
        for (char c: s.toCharArray()) {
            // Record distinct characters
            if (table[c - 'a'] == false) {
                distinct++;
            }
            table[c - 'a'] = true;

            // Build the doubly linked list
            Node node = new Node(c, p);
            p.next = node;
            p = p.next;
        }
        p.next = tail;
        tail.prev = p;

        Node lastCharS = head;
        Node lastCharL = tail;
        while (true) {
            // Select next smallest character
            char c = '\0';
            for (int i = 0; i < 26; i++) {
                if (table[i] == true) {
                    c = (char)('a' + i);
                    table[i] = false;
                    break;
                }
            }
            if (c == '\0')
                break;

            boolean finded = false;
            Node currChar = null;

            // Remove duplicates in forward traversal from lastCharS
            p = lastCharS;
            while (p != tail) {
                if (p.c == c) {
                    if (finded == false) {
                        currChar = p;
                        finded = true;
                    }
                    else {
                        remove(p);
                    }
                }
                p = p.next;
            }

            // Remove duplicates in backward traversal from lastCharS
            p = lastCharS;
            while (p != head) {
                if (p.c == c) {
                    if (finded == false) {
                        currChar = p;
                        finded = true;
                    }
                    else {
                        remove(p);
                    }
                }
                p = p.prev;
            }

            lastCharS = currChar;

            // Select next largest character
            c = '\0';
            for (int i = 25; i >= 0; i--) {
                if (table[i] == true) {
                    c = (char)('a' + i);
                    table[i] = false;
                    break;
                }
            }
            if (c == '\0')
                break;

            finded = false;
            currChar = null;

            // Remove duplicates in backward traversal from lastCharL
            p = lastCharL;
            while (p != head) {
                if (p.c == c) {
                    if (finded == false) {
                        currChar = p;
                        finded = true;
                    }
                    else {
                        remove(p);
                    }
                }
                p = p.prev;
            }

            // Remove duplicates in forward traversal from lastCharL
            p = lastCharL;
            while (p != tail) {
                if (p.c == c) {
                    if (finded == false) {
                        currChar = p;
                        finded = true;
                    }
                    else {
                        remove(p);
                    }
                }
                p = p.next;
            }

            lastCharL = currChar;
        }

        // Build the result string from the linked list
        StringBuilder sb = new StringBuilder();
        p = head.next;
        while (p != tail) {
            sb.append(p.c);
            p = p.next;
        }
        return sb.toString();
    }
}

// d. brute-force backtrack: Time Limit Exceeded
class Solution {
    char[] arr = null;
    int len = 0;
    boolean[] res = null;

    private void display(boolean[] available) {
        System.out.println(Arrays.toString(available));
    }

    private int compareTo(boolean[] arr1, boolean[] arr2) {
        if (arr1 == null || arr2 == null)
            return 1;

        int m = 0, n = 0;
        while (m < this.len && n < this.len) {
            while (m < this.len && arr1[m] == false)
                m++;
            if (m == this.len)
                break;

            while (n < this.len && arr2[n] == false)
                n++;
            if (n == this.len)
                break;

            if (this.arr[m] > this.arr[n])
                return 1;
            else if (this.arr[m] < this.arr[n])
                return -1;

            m++;
            n++;
        }

        return 0;
    }

    private void backtrack(boolean[] available, Map<Character, List<Integer>> map) {
        if (map.isEmpty()) {
            // System.out.print("new:    ");
            // display(available);
            if (compareTo(this.res, available) > 0)
                this.res = available.clone();
            // System.out.print("result: ");
            // display(this.res);
            // System.out.println();
            return;
        }

        List<Character> keySet = new ArrayList<Character>(map.keySet());
        for (char key: keySet) {
            // Get all occurrence indices for current character
            List<Integer> l = map.get(key);
            map.remove(key);

            // Try each occurrence as the chosen position for this character
            for (int i = 0; i < l.size(); i++) {
                for (int j = 0; j < l.size(); j++) {
                    if (j == i)
                        continue;
                    available[l.get(j)] = false;
                }

                backtrack(available, map);

                for (int j = 0; j < l.size(); j++) {
                    available[l.get(j)] = true;
                }
            }

            map.put(key, l);
        }
    }

    public String removeDuplicateLetters(String s) {
        this.arr = s.toCharArray();
        this.len = this.arr.length;

        boolean[] available = new boolean[this.len];
        Arrays.fill(available, true);

        Map<Character, List<Integer>> map = new HashMap<Character, List<Integer>>();
        for (int i = 0; i < this.len; i++) {
            char c = arr[i];
            if (map.containsKey(c)) {
                List<Integer> l = map.get(c);
                l.add(i);
            }
            else {
                List<Integer> l = new ArrayList<Integer>();
                l.add(i);
                map.put(c, l);
            }
        }

        backtrack(available, map);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.len; i++) {
            if (this.res[i] == true)
                sb.append(this.arr[i]);
        }
        return sb.toString();
    }
}

// e. greedy replacement: Wrong Answer
class Solution {
    char[] arr = null;

    private void display(boolean[] available) {
        System.out.println(Arrays.toString(available));
    }

    private int compareTo(boolean[] arr1, boolean[] arr2, int end) {
        if (arr1 == null || arr2 == null)
            return 1;

        int m = 0, n = 0;
        while (m <= end && n <= end) {
            while (m <= end && arr1[m] == false)
                m++;
            if (m > end)
                break;

            while (n <= end && arr2[n] == false)
                n++;
            if (n > end)
                break;

            if (this.arr[m] > this.arr[n])
                return 1;
            else if (this.arr[m] < this.arr[n])
                return -1;

            m++;
            n++;
        }

        return 0;
    }

    public String removeDuplicateLetters(String s) {
        this.arr = s.toCharArray();
        int len = this.arr.length;

        boolean[] available = new boolean[len];
        Arrays.fill(available, true);

        int[] lastPos = new int[26];
        Arrays.fill(lastPos, -1);

        for (int i = 0; i < len; i++) {
            if (lastPos[this.arr[i] - 'a'] < 0) {
                lastPos[this.arr[i] - 'a'] = i;
            }
            else {
                boolean[] newArr = available.clone();
                newArr[lastPos[this.arr[i] - 'a']] = false;
                available[i] = false;
                if (compareTo(available, newArr, i) > 0)
                    available = newArr;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (available[i] == true)
                sb.append(this.arr[i]);
        }
        return sb.toString();
    }
}

// f. monotonic stack
class Solution {
    private void display(Deque<Character> stack) {
        for (char c: stack) {
            System.out.printf("%s ", c);
        }
        System.out.println();
    }

    public String removeDuplicateLetters(String s) {
        char[] arr = s.toCharArray();
        int len = arr.length;

        int[] leftCnt = new int[26];
        for (char c: arr)
            leftCnt[c - 'a']++;

        Deque<Character> stack = new ArrayDeque<Character>();
        boolean[] used = new boolean[26];

        for (int i = 0; i < len; i++) {
            // arr[i] not used yet
            if (used[arr[i] - 'a'] == false) {
                if (stack.isEmpty() || stack.peek() < arr[i]) {
                    stack.push(arr[i]);
                }
                else {
                    // Pop larger characters from the top if they still appear later
                    while (!stack.isEmpty() && stack.peek() >= arr[i] && leftCnt[stack.peek() - 'a'] > 0)
                        used[stack.pop() - 'a'] = false;
                    stack.push(arr[i]);
                }
            }
            leftCnt[arr[i] - 'a']--;
            used[arr[i] - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}