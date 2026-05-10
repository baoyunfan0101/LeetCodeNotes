// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

// a. queue
class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> iterator = null;
    Deque<Integer> queue = new ArrayDeque<Integer>();

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (queue.isEmpty()) {
            Integer next = this.iterator.next();
            this.queue.offer(next);
            return next;
        }
        else
            return queue.peek();
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (queue.isEmpty())
            return this.iterator.next();
        else
            return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty() || this.iterator.hasNext();
    }
}

// b. single peeked element
class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> iterator = null;
    Integer peeked = null;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (peeked == null) {
            this.peeked = this.iterator.next();
            return this.peeked;
        }
        else
            return this.peeked;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (this.peeked == null)
            return this.iterator.next();
        else {
            Integer peeked = this.peeked;
            this.peeked = null;
            return peeked;
        }
    }

    @Override
    public boolean hasNext() {
        return peeked != null || this.iterator.hasNext();
    }
}