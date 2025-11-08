/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// original version
// keep track of a (n + 1)-node queue
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Queue<ListNode> q = new LinkedList<ListNode>();
        ListNode p = head;
        for (int i = 0; i < n; i++) {
            q.offer(p);
            p = p.next;
        }
        // if the length of the list equals n(or rather, to remove the 1st node)
        if (p == null) {
            // if the list has only one node
            if (n == 1)
                return null;
            else {
                q.poll();
                return q.peek();
            }
        }
        // the (n + 1)th node
        q.offer(p);
        p = p.next;
        while (p != null) {
            q.poll();
            q.offer(p);
            p = p.next;
        }
        ListNode node = q.poll();
        // if n equals 1, the length of the queue equals 2
        if (n == 1)
            node.next = null;
        else {
            q.poll();
            node.next = q.peek();
        }
        return head;
    }
}

// better version: two pointers
// two pointers are (n + 1) steps apart
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;
        // move forward n steps, and p reaches the (n + 1)th node
        for (int i = 0; i < n; i++)
            p = p.next;
        // if the length of the list equals n(or rather, to remove the 1st node)
        if (p == null)
            return head.next;
        // when p reaches the (n + 2)th node, previousP reaches the 1st node
        p = p.next;
        ListNode previousP = head;
        // when p reaches the end(p == null), previousP reaches the (n + 1)th node from the bottom
        while (p != null) {
            p = p.next;
            previousP = previousP.next;
        }
        previousP.next = previousP.next.next;
        return head;
    }
}