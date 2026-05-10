// a. array: Time Limit Exceeded
class MedianFinder {
    List<Integer> l = new ArrayList<Integer>();

    public MedianFinder() {

    }

    public void addNum(int num) {
        int len = this.l.size();

        int left = 0, right = len - 1, res = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            if (this.l.get(mid) == num) {
                res = mid + 1;
                break;
            }
            else if (this.l.get(mid) < num) {
                left = mid + 1;
                res = left;
            }
            else {
                right = mid - 1;
            }
        }
        this.l.add(res, num);
    }

    public double findMedian() {
        int len = this.l.size();
        for (int i = 0; i < len; i++) {
            System.out.print(this.l.get(i) + " ");
        }
        System.out.println();

        if (len == 0)
            return 0;

        if (len % 2 == 0)
            return ((double)this.l.get(len / 2 - 1) + this.l.get(len / 2)) / 2;
        else
            return (double)this.l.get(len / 2);
    }
}

// b. max-heap + min-heap
class MedianFinder {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) -> b - a);
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

    public MedianFinder() {

    }

    public void addNum(int num) {
        if (this.maxHeap.size() == 0) {
            this.maxHeap.offer(num);
        }
        else if (this.maxHeap.size() <= this.minHeap.size()) {
            if (num > this.minHeap.peek()) {
                this.minHeap.offer(num);
                num = this.minHeap.poll();
            }
            this.maxHeap.offer(num);
        }
        else {
            if (num < this.maxHeap.peek()) {
                this.maxHeap.offer(num);
                num = this.maxHeap.poll();
            }
            this.minHeap.offer(num);
        }
    }

    public double findMedian() {
        if (this.maxHeap.size() > this.minHeap.size())
            return this.maxHeap.peek();
        else
            return ((double)this.maxHeap.peek() + this.minHeap.peek()) / 2;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */