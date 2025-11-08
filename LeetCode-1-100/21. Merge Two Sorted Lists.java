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
// use a dummy node so the head can be handled uniformly
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode p1 = list1, p2 = list2, previousP = dummy;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                previousP.next = p1;
                previousP = previousP.next;
                p1 = p1.next;
            } else {
                previousP.next = p2;
                previousP = previousP.next;
                p2 = p2.next;
            }
        }
        previousP.next = p1 != null ? p1 : p2;
        return dummy.next;
    }
}