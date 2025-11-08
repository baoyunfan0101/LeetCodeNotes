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
// use (k + 1) pointers, and reverse the last k of them every k steps
// create k dummy nodes to handle the linked list uniformly
class Solution {
//    // output linked list
//    private void printLinkedList(ListNode head) {
//        int times = 0;
//        while (head != null) {
//            System.out.print(head.val + " -> ");
//            head = head.next;
//            if (++times % 10 == 0)
//                System.out.println();
//        }
//        System.out.println("null");
//    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode[] dummies = new ListNode[k];
        ListNode[] p = new ListNode[k + 1];
        p[k] = head;
        dummies[k - 1] = new ListNode(-1, head);
        p[k - 1] = dummies[k - 1];
        for (int i = k - 2; i >= 0; i--) {
            dummies[i] = new ListNode(i - k, dummies[i + 1]);
            p[i] = dummies[i];
        }

        int times = 0;
        while (p[k] != null) {
//            // output p
//            System.out.println("times = " + (times + 1));
//            for (int i = 0; i < k + 1; i++) {
//                System.out.format("p[%d] = %d, ", i, p[i].val);
//            }
//            System.out.println();

            if (++times % k == 0) {
                // swap linked list
                p[0].next = p[k];
                p[1].next = p[k].next;
                for (int i = k; i > 1; i--) {
                    p[i].next = p[i - 1];
                }

//                // output linked list
//                printLinkedList(dummies[k - 1].next);

                // swap p: swap p[i] and p[k - i]
                for (int i = 1; i < (k + 1) / 2; i++) {
                    ListNode temp = p[i];
                    p[i] = p[k - i];
                    p[k - i] = temp;
                }
                p[0] = p[k];
                p[k] = p[k - 1].next;
            } else {
                for (int i = 0; i < k + 1; i++)
                    p[i] = p[i].next;
            }
        }
        return dummies[k - 1].next;
    }
}
end original version */

/* better version
// use (k + 1) pointers, reverse the last k of them, and treat the new end as the start to move k steps at a time
// create a dummy node to handle the linked list uniformly
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode[] p = new ListNode[k + 1];
        p[0] = dummy;

        while (p[0] != null) {
            for (int i = 1; i < k + 1; i++) {
                p[i] = p[i - 1].next;
                if (p[i] == null)
                    return dummy.next;
            }
            p[0].next = p[k];
            p[1].next = p[k].next;
            for (int i = k; i > 1; i--) {
                p[i].next = p[i - 1];
            }
            p[0] = p[1];
        }
        return dummy.next;
    }
}
end better version */

// better version
// use two pointers: p0 points to the end of the previous k-group, and p1 moves forward, making each node point backward
// if p1 finds fewer than k nodes remaining, restore these nodes to their original order
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode p0 = dummy, p1 = head;

        while (p1 != null) {
            ListNode lastNode = p1, nextNode = p1.next;
            // If fewer than k nodes remain, restore them
            for (int i = 1; i < k; i++) {
                if (nextNode == null) {
                    nextNode = lastNode.next;
                    lastNode.next = null;
                    for (int j = i; j > 1; j--) {
                        ListNode currentNode = nextNode;
                        nextNode = nextNode.next;
                        currentNode.next = lastNode;
                        lastNode = currentNode;
                    }
                    return dummy.next;
                }
                ListNode currentNode = nextNode;
                nextNode = nextNode.next;
                currentNode.next = lastNode;
                lastNode = currentNode;
            }
            p0.next = lastNode;
            p1.next = nextNode;
            p0 = p1;
            p1 = p1.next;
        }
        return dummy.next;
    }
}