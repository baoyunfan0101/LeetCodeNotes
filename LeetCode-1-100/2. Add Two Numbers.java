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
    static int plus(ListNode n1, ListNode n2, int carryBit, ListNode sum) {
        if(n1.val + n2.val + carryBit >= 10) {
            sum.val = n1.val + n2.val + carryBit - 10;
            return 1;
        }
        else {
            sum.val = n1.val + n2.val + carryBit;
            return 0;
        }
    }
    static int plus(ListNode n, int carryBit, ListNode sum) {
        if(n == null) {
            sum.val = carryBit;
            return 0;
        }
        else if(n.val + carryBit >= 10) {
            sum.val = n.val + carryBit - 10;
            return 1;
        }
        else {
            sum.val = n.val + carryBit;
            return 0;
        }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        int carryBit = plus(l1, l2, 0, res);
        ListNode p = res, p1 = l1, p2 = l2;
        while(true) {
            if(p1.next == null && p2.next == null) {
                if(carryBit > 0) {
                    p.next = new ListNode(carryBit);
                    p = p.next;
                    break;
                }
                else
                    break;
            }
            else if(p1.next == null) {
                while(p2.next != null) {
                    p2 = p2.next;
                    p.next = new ListNode();
                    carryBit = plus(p2, carryBit, p.next);
                    p = p.next;
                }
            }
            else if (p2.next == null) {
                while(p1.next != null) {
                    p1 = p1.next;
                    p.next = new ListNode();
                    carryBit = plus(p1, carryBit, p.next);
                    p = p.next;
                }
            }
            else {
                p1 = p1.next;
                p2 = p2.next;
                p.next = new ListNode();
                carryBit = plus(p1, p2, carryBit, p.next);
                p = p.next;
            }
        }
        p.next = null;
        return res;
    }
}
end original version */

// better version
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode((l1.val + l2.val) % 10, null);
        int carryInt = (l1.val + l2.val) / 10;
        ListNode p = head, p1 = l1 , p2 = l2;
        while(true) {
            p1 = p1 == null? p1: p1.next;
            p2 = p2 == null? p2: p2.next;
            if(carryInt == 0 && p1 == null && p2 == null)
                break;
            int val1 = p1 == null? 0: p1.val;
            int val2 = p2 == null? 0: p2.val;
            p.next = new ListNode((val1 + val2 + carryInt) % 10, null);
            carryInt = (val1 + val2 + carryInt) / 10;
            p = p.next;
        }
        return head;
    }
}