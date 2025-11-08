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
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-101, head);
        ListNode p = head, pre = dummy;
        while (p != null) {
            if (p.next != null && p.next.val == p.val)
                pre.next = p.next;
            else
                pre = p;
            p = p.next;
        }
        return dummy.next;
    }
}