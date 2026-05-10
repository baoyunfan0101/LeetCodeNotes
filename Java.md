# Java Cheatsheet

---

## 1. Arrays & Strings

### Arrays (java.util.Arrays)
```java
int[] nums = new int[n];

Arrays.sort(nums);
Arrays.sort(nums, l, r);

Arrays.fill(nums, x);
Arrays.fill(nums, l, r, x);

Arrays.copyOf(nums, n);
Arrays.copyOfRange(nums, l, r);

Arrays.equals(a, b);
Arrays.deepEquals(a, b);

Arrays.toString(nums);
Arrays.deepToString(grid);

Arrays.binarySearch(nums, x);
Arrays.binarySearch(nums, l, r, x);

nums.length;
```

### String (java.lang.String)
```java
String s = "abc";

s.length();
s.isEmpty();

s.charAt(i);

s.substring(l);
s.substring(l, r);

s.indexOf("x");
s.indexOf("x", fromIndex);
s.lastIndexOf("x");
s.lastIndexOf("x", fromIndex);

s.contains("x");
s.startsWith("x");
s.endsWith("x");

s.equals(t);
s.equalsIgnoreCase(t);
s.compareTo(t);

s.toCharArray();

s.concat(t);
s + t;

s.replace('a', 'b');
s.replace("ab", "cd");
s.replaceAll(regex, replacement);

s.split(regex);

s.toLowerCase();
s.toUpperCase();

s.trim();

String.valueOf(x);
```

### StringBuilder (java.lang)
```java
StringBuilder sb = new StringBuilder();

sb.append(x);
sb.append(str);
sb.appendCodePoint(codePoint);

sb.insert(i, x);
sb.insert(i, str);

sb.deleteCharAt(i);
sb.delete(l, r);

sb.replace(l, r, str);

sb.setCharAt(i, c);
sb.charAt(i);

sb.reverse();

sb.length();
sb.capacity();

sb.substring(l);
sb.substring(l, r);

sb.toString();
```

---

## 2. Hash Structures

### HashMap (java.util.HashMap)
```java
Map<Integer, Integer> map = new HashMap<>();

map.put(k, v);
map.putIfAbsent(k, v);
map.putAll(other);

map.get(k);
map.getOrDefault(k, 0);

map.containsKey(k);
map.containsValue(v);

map.remove(k);
map.remove(k, v);
map.clear();

map.size();
map.isEmpty();

map.keySet();
map.values();
map.entrySet();

map.replace(k, v);
map.replace(k, oldV, newV);

map.computeIfAbsent(k, key -> new ArrayList<>());
map.computeIfPresent(k, (key, val) -> val + 1);
map.compute(k, (key, val) -> val == null ? 1 : val + 1);

map.merge(k, 1, Integer::sum);

map.equals(other);
map.hashCode();

map.put(x, map.getOrDefault(x, 0) + 1);
```

### HashSet (java.util.HashSet)
```java
Set<Integer> set = new HashSet<>();

set.add(x);
set.addAll(other);

set.contains(x);

set.remove(x);
set.removeAll(other);
set.retainAll(other);
set.clear();

set.size();
set.isEmpty();

set.iterator();

set.toArray();
Integer[] arr = set.toArray(new Integer[0]);

set.equals(other);
set.hashCode();
```

---

## 3. List

### ArrayList (java.util.ArrayList)
```java
List<Integer> list = new ArrayList<>();

list.add(x);
list.add(i, x);
list.addAll(other);
list.addAll(i, other);

list.get(i);
list.size();
list.isEmpty();

list.set(i, v);

list.remove(i);
list.remove(Integer.valueOf(x));
list.clear();

list.contains(x);
list.indexOf(x);
list.lastIndexOf(x);

for (int i = 0; i < list.size(); i++)
    System.out.println(list.get(i));

for (int x : list)
    System.out.println(x);

Iterator<Integer> it = list.iterator();
while (it.hasNext())
    System.out.println(it.next());

Collections.sort(list);
Collections.sort(list, Collections.reverseOrder());
list.sort((a, b) -> a - b);

list.toArray();
Integer[] arr = list.toArray(new Integer[0]);

list.subList(l, r);

list.equals(other);
list.hashCode();

list.ensureCapacity(n);
list.trimToSize();
```

### LinkedList (java.util.LinkedList)
```java
LinkedList<Integer> list = new LinkedList<>();

list.add(x);
list.add(i, x);
list.addFirst(x);
list.addLast(x);
list.addAll(other);
list.addAll(i, other);

list.get(i);
list.getFirst();
list.getLast();
list.size();
list.isEmpty();

list.set(i, v);

list.remove(i);
list.remove(Integer.valueOf(x));
list.removeFirst();
list.removeLast();
list.clear();

list.contains(x);
list.indexOf(x);
list.lastIndexOf(x);

for (int i = 0; i < list.size(); i++)
    System.out.println(list.get(i));

for (int x : list)
    System.out.println(x);

Iterator<Integer> it = list.iterator();
while (it.hasNext())
    System.out.println(it.next());

list.offer(x);
list.offerFirst(x);
list.offerLast(x);

list.poll();
list.pollFirst();
list.pollLast();

list.peek();
list.peekFirst();
list.peekLast();

list.push(x);
list.pop();

list.toArray();
Integer[] arr = list.toArray(new Integer[0]);

list.subList(l, r);

list.equals(other);
list.hashCode();
```

---

## 4. Queue

### Queue (java.util.Queue)
```java
Queue<Integer> q = new LinkedList<>();

q.offer(x);
q.add(x);

q.poll();
q.remove();

q.peek();
q.element();

q.size();
q.isEmpty();

q.contains(x);

q.clear();

Iterator<Integer> it = q.iterator();
while (it.hasNext())
    System.out.println(it.next());

q.toArray();
Integer[] arr = q.toArray(new Integer[0]);

q.equals(other);
q.hashCode();
```

---

## 5. Heap

### PriorityQueue (java.util.PriorityQueue)
```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(x);
pq.add(x);

pq.poll();
pq.remove();
pq.remove(x);

pq.peek();
pq.element();

pq.size();
pq.isEmpty();

pq.contains(x);

pq.clear();

Iterator<Integer> it = pq.iterator();
while (it.hasNext())
    System.out.println(it.next());

pq.toArray();
Integer[] arr = pq.toArray(new Integer[0]);

pq.equals(other);
pq.hashCode();
```

### Max Heap
```java
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
```

---

## 6. Stack

### Stack (use Deque)
```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(x); // addFirst(x)
stack.pop(); // removeFirst()
stack.peek(); // peekFirst()

stack.size();
stack.isEmpty();

stack.contains(x);

stack.clear();

Iterator<Integer> it = stack.iterator();
while (it.hasNext())
    System.out.println(it.next());

stack.toArray();
Integer[] arr = stack.toArray(new Integer[0]);

stack.equals(other);
stack.hashCode();
```

---

## 7. Deque

### Deque (java.util.ArrayDeque)
```java
Deque<Integer> dq = new ArrayDeque<>();

dq.addFirst(x);
dq.addLast(x);

dq.offerFirst(x);
dq.offerLast(x);
dq.offer(x); // offerLast(x)

dq.removeFirst();
dq.removeLast();
dq.remove(); // removeFirst()

dq.pollFirst();
dq.pollLast();
dq.poll(); // pollFirst()

dq.getFirst();
dq.getLast();

dq.peekFirst();
dq.peekLast();
dq.peek(); // peekFirst()

dq.size();
dq.isEmpty();

dq.contains(x);

dq.clear();

Iterator<Integer> it = dq.iterator();
while (it.hasNext())
    System.out.println(it.next());

Iterator<Integer> dit = dq.descendingIterator();
while (dit.hasNext())
    System.out.println(dit.next());

dq.toArray();
Integer[] arr = dq.toArray(new Integer[0]);

dq.equals(other);
dq.hashCode();
```

---

## 8. Ordered Map / Set

### TreeMap (java.util.TreeMap)
```java
TreeMap<Integer, Integer> map = new TreeMap<>();

map.put(k, v);
map.putIfAbsent(k, v);
map.putAll(other);

map.get(k);
map.getOrDefault(k, 0);

map.containsKey(k);
map.containsValue(v);

map.remove(k);
map.remove(k, v);
map.clear();

map.firstKey();
map.lastKey();

map.ceilingKey(x);
map.floorKey(x);
map.higherKey(x);
map.lowerKey(x);

map.firstEntry();
map.lastEntry();

map.ceilingEntry(x);
map.floorEntry(x);
map.higherEntry(x);
map.lowerEntry(x);

map.pollFirstEntry();
map.pollLastEntry();

map.size();
map.isEmpty();

map.keySet();
map.navigableKeySet();
map.values();
map.entrySet();

map.subMap(l, true, r, true);
map.headMap(x, true);
map.tailMap(x, true);

map.descendingMap();

map.equals(other);
map.hashCode();
```

### TreeSet (java.util.TreeSet)
```java
TreeSet<Integer> set = new TreeSet<>();

set.add(x);
set.addAll(other);

set.contains(x);

set.remove(x);
set.pollFirst();
set.pollLast();
set.clear();

set.first();
set.last();

set.ceiling(x);
set.floor(x);
set.higher(x);
set.lower(x);

set.size();
set.isEmpty();

set.subSet(l, true, r, true);
set.headSet(x, true);
set.tailSet(x, true);

set.iterator();
set.descendingIterator();

set.toArray();
Integer[] arr = set.toArray(new Integer[0]);

set.equals(other);
set.hashCode();
```

---

## 9. Utilities

### Collections (java.util.Collections)
```java
Collections.sort(list);
Collections.sort(list, Collections.reverseOrder());

Collections.reverse(list);
Collections.shuffle(list);

Collections.max(list);
Collections.min(list);

Collections.binarySearch(list, x);

Collections.fill(list, x);

Collections.swap(list, i, j);

Collections.frequency(list, x);

Collections.copy(dest, src);

Collections.nCopies(n, x);

Collections.disjoint(a, b);
```

### Comparator
```java
Arrays.sort(arr, (a, b) -> a[0] - b[0]);

list.sort((a, b) -> a - b);
list.sort(Comparator.reverseOrder());

Collections.sort(list, (a, b) -> a - b);
```

### Math (java.lang.Math)
```java
Math.max(a, b);
Math.min(a, b);

Math.abs(x);

Math.pow(a, b);
Math.sqrt(x);

Math.ceil(x);
Math.floor(x);
Math.round(x);

Math.random();

Math.signum(x);
```

---

## 10. OJ Input Patterns

### Single Case
1. Input: one line, two integers
```
3 5
```
3. Implementation:
```java
import java.util.*;

Scanner sc = new Scanner(System.in);
int a = sc.nextInt();
int b = sc.nextInt();
```

---

### EOF Loop
1. Input: multiple lines, each with two integers, until EOF
```
1 2
3 4
5 6
```
3. Implementation:
```java
import java.util.*;

Scanner sc = new Scanner(System.in);
while (sc.hasNextInt()) {
    int a = sc.nextInt();
    int b = sc.nextInt();
}
```

```java
import java.io.*;

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String line;
while ((line = br.readLine()) != null) {
    String[] parts = line.split(" ");
    int a = Integer.parseInt(parts[0]);
    int b = Integer.parseInt(parts[1]);
}
```

---

### Fixed T
1. Input: first line T, followed by T lines of two integers
```
3
1 2
3 4
5 6
```
3. Implementation:
```java
import java.util.*;

Scanner sc = new Scanner(System.in);
int T = sc.nextInt();
while (T-- > 0) {
    int a = sc.nextInt();
    int b = sc.nextInt();
}
```

---

### Per-Line Variable Length
1. Input: one line, variable number of integers
```
1 2 3 4
```
3. Implementation:
```java
import java.io.*;

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String[] parts = br.readLine().split(" ");
for (String p : parts) {
    int x = Integer.parseInt(p);
}
```

---

### Multi-Line Variable Length (EOF)
1. Input: multiple lines, each line variable number of integers
```
1 2 3
4 5
6
```
3. Implementation:
```java
import java.io.*;

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String line;
while ((line = br.readLine()) != null) {
    String[] parts = line.split(" ");
    for (String p : parts) {
        int x = Integer.parseInt(p);
    }
}
```

---

### Sentinel Termination
1. Input: multiple lines, terminated by sentinel (e.g. 0 0)
```
1 2
3 4
0 0
```
3. Implementation:
```java
import java.util.*;

Scanner sc = new Scanner(System.in);
while (true) {
    int a = sc.nextInt();
    int b = sc.nextInt();
    if (a == 0 && b == 0) break;
}
```

---

### Count + Data (Same Line)
1. Input: first integer n, followed by n integers in same line
```
5 1 2 3 4 5
```
3. Implementation:
```java
import java.util.*;

Scanner sc = new Scanner(System.in);
int n = sc.nextInt();
for (int i = 0; i < n; i++) {
    int x = sc.nextInt();
}
```

---

### Count + Data (Next Line)
1. Input: first line n, next line contains n integers
```
5
1 2 3 4 5
```
3. Implementation:
```java
import java.io.*;

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
int n = Integer.parseInt(br.readLine());
String[] parts = br.readLine().split(" ");
for (int i = 0; i < n; i++) {
    int x = Integer.parseInt(parts[i]);
}
```

---

### Line-Based String
1. Input: one full line string
```
hello world
```
3. Implementation:
```java
import java.util.*;

Scanner sc = new Scanner(System.in);
String s = sc.nextLine();
```

---

### Mixed Input
1. Input: integer followed by line string
```
3
hello world
```
3. Implementation:
```java
import java.util.*;

Scanner sc = new Scanner(System.in);
int n = sc.nextInt();
sc.nextLine(); // consume newline
String s = sc.nextLine();
```

---

### Multiple Test Cases (Line + Split)
1. Input: multiple lines, each line contains space-separated integers
```
1 2 3
4 5 6
```
3. Implementation:
```java
import java.io.*;

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String line;
while ((line = br.readLine()) != null) {
    String[] parts = line.split(" ");
}
```

---

## Imports

```java
import java.util.*;
```