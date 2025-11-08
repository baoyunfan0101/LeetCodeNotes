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
// if an element appears more than once, remove all of its duplicates
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-101, head);
        ListNode p = head, pre = dummy;
        while (p != null) {
            int count = 0;
            while (p.next != null && p.next.val == p.val) {
                p = p.next;
                count++;
            }
            if (count == 0)
                pre = p;
            else
                pre.next = p.next;
            p = p.next;
        }
        return dummy.next;
    }
}