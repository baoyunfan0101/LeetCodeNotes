# LeetCode \#201&#45;300
* This is a study note for LeetCode problems \#201&#45;300, containing my various attempts and insights for each problem.  
* To be continued.  

---

## 242. Valid Anagram

### a. sort

### a. hash map

---

## 284. Peeking Iterator

### a. queue
* Use a queue to store peeked elements.

### b. single peeked element
* At most one element is peeked.

---

## 295. Find Median from Data Stream

### a. array: Time Limit Exceeded
* O(log n) to find the position and O(n) to insert

### b. max-heap + min-heap
* Use a max-heap for the lower half and a min-heap for the upper half.