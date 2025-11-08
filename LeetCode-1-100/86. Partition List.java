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
// find the first node with value >= x
// insert all subsequent nodes with value < x before that node
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(0, head), pre = dummy, p = head;

        // find the first node with value >= x
        while (p != null) {
            if (p.val < x) {
                pre = p;
                p = p.next;
            } else
                break;
        }

        if (p == null)
            return head;

        // the position for pertition
        ListNode partition = pre;
        pre = p;
        p = p.next;

        while (p != null) {
            if (p.val < x) {
                ListNode temp = p.next;

                // connect partition -> p -> partition.next
                p.next = partition.next;
                partition.next = p;

                // update partition
                partition = p;

                // connect pre -> p.next
                pre.next = temp;
                p = temp;
            } else {
                pre = p;
                p = p.next;
            }
        }

        return dummy.next;
    }
}