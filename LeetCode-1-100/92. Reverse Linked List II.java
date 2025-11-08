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

/* original version
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        int len = right - left;

        ListNode dummy = new ListNode(-501, head), p = dummy;
        while (--left > 0)
            p = p.next;

        ListNode p0 = p;
        p = p.next;
        ListNode p1 = p, pre = p;

        while (len-- >= 0) {
            ListNode t = p.next;
            p.next = pre;
            pre = p;
            p = t;
        }

        p0.next = pre;
        p1.next = p;

        return dummy.next;
    }
}
end original version */

// original version (modified)
// use one fewer pointer
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        int len = right - left;

        ListNode dummy = new ListNode(-501, head), p = dummy;
        while (--left > 0)
            p = p.next;

        ListNode p0 = p;
        p = p.next;
        ListNode p1 = p;

        while (len-- > 0) {
            p = p1.next;
            p1.next = p.next;
            p.next = p0.next;
            p0.next = p;
        }

        return dummy.next;
    }
}