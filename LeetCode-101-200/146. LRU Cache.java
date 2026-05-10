// a. HashMap + doubly linked list
class LRUCache {
    private class Node {
        int key;
        int val;
        Node prev = null;
        Node next = null;

        Node() {}

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private class MyDeque {
        Node head = new Node();
        Node rear = new Node();

        private void display() {
            Node p = this.head.next;
            while (p != this.rear) {
                System.out.printf("(%d, %d), ", p.key, p.val);
                p = p.next;
            }
            System.out.println();
        }

        private void delete(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void push(Node node) {
            node.prev = this.rear.prev;
            node.next = this.rear;
            this.rear.prev = node;
            node.prev.next = node;
        }

        private Node pop() {
            if (this.head.next == this.rear)
                return null;

            Node node = this.head.next;
            delete(node);
            return node;
        }

        MyDeque() {
            this.head.next = this.rear;
            this.rear.prev = this.head;
        }
    }

    int capacity = 0;
    int size = 0;
    MyDeque queue = new MyDeque();
    Map<Integer, Node> map = new HashMap<Integer, Node>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        int res = -1;
        if (this.map.containsKey(key)) {
            Node node = this.map.get(key);
            this.queue.delete(node);
            this.queue.push(node);
            res = node.val;
        }

        // System.out.printf("Get     %d    : ", key);
        // display();

        return res;
    }

    public void put(int key, int value) {
        if (this.map.containsKey(key)) {
            Node node = this.map.get(key);
            this.queue.delete(node);
            this.size--;
        }

        Node newNode = new Node(key, value);
        this.queue.push(newNode);
        this.map.put(key, newNode);

        if (++this.size > this.capacity) {
            // pop
            Node earliest = this.queue.pop();
            this.map.remove(earliest.key);
            this.size--;
        }

        // System.out.printf("Insert (%d, %d): ", key, value);
        // display();
    }
}

// b. HashMap + doubly linked list (modified)
class LRUCache {
    private class Node {
        int key;
        int val;
        Node prev = null;
        Node next = null;

        Node() {}

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private class MyDeque {
        Node head = new Node();
        Node rear = new Node();

        private void display() {
            Node p = this.head.next;
            while (p != this.rear) {
                System.out.printf("(%d, %d), ", p.key, p.val);
                p = p.next;
            }
            System.out.println();
        }

        private void delete(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void push(Node node) {
            node.prev = this.rear.prev;
            node.next = this.rear;
            this.rear.prev = node;
            node.prev.next = node;
        }

        private Node pop() {
            if (this.head.next == this.rear)
                return null;

            Node node = this.head.next;
            delete(node);
            return node;
        }

        MyDeque() {
            this.head.next = this.rear;
            this.rear.prev = this.head;
        }
    }

    int capacity = 0;
    MyDeque queue = new MyDeque();
    Map<Integer, Node> map = new HashMap<Integer, Node>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        int res = -1;
        if (this.map.containsKey(key)) {
            Node node = this.map.get(key);
            this.queue.delete(node);
            this.queue.push(node);
            res = node.val;
        }

        // System.out.printf("Get     %d    : ", key);
        // display();

        return res;
    }

    public void put(int key, int value) {
        if (this.map.containsKey(key)) {
            Node node = this.map.get(key);
            this.queue.delete(node);
            node.val = value;
            this.queue.push(node);
            this.map.put(key, node);
        }
        else {
            Node newNode = new Node(key, value);
            this.queue.push(newNode);
            this.map.put(key, newNode);

            if (this.map.size() > this.capacity) {
                Node earliest = this.queue.pop();
                this.map.remove(earliest.key);
            }
        }

        // System.out.printf("Insert (%d, %d): ", key, value);
        // display();
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */