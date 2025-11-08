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

/* original version: two pointers
// first calculate the length of the linked list to get k % length
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0)
            return head;
        ListNode p = head;
        int len = 0;
        while (p != null) {
            len++;
            p = p.next;
        }
        if (len == 0)
            return null;
        k = k % len;
        ListNode q = head;
        while (k > 0) {
            k--;
            q = q.next;
        }
        p = head;
        while (q.next != null) {
            q = q.next;
            p = p.next;
        }
        q.next = head;
        head = p.next;
        p.next = null;
        return head;
    }
}
end original version */

// better version
// similar to the previous version
// first calculate the cutting position, which allows using one pointer
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0)
            return head;
        ListNode p = head;
        int len = 1;
        while (p.next != null) {
            len++;
            p = p.next;
        }
        // form a ring
        p.next = head;
        // cut at the (len - k % len)th node
        k = len - k % len;
        while (k > 0) {
            k--;
            p = p.next;
        }
        head = p.next;
        p.next = null;
        return head;
    }
}