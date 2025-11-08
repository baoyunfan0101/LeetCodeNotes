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
// use three pointers, and swap two of them every other step
// create two dummy nodes to handle the linked list uniformly
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy2 = new ListNode(-1, head);
        ListNode dummy1 = new ListNode(-2, dummy2);
        ListNode p1 = dummy1, p2 = dummy2, p3 = head;
        int times = 0;
        while (p3 != null) {
            if (++times % 2 == 0) {
                p1.next = p3;
                p2.next = p3.next;
                p3.next = p2;
                p1 = p3;
                p3 = p2.next;
            } else {
                p1 = p1.next;
                p2 = p2.next;
                p3 = p3.next;
            }
        }
        return dummy2.next;
    }
}