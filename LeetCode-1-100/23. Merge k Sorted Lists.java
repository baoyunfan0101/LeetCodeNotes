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
// find the mininum node among lists, connect it to the end of the result list, and put its next node back into lists
// the problem is to find the mininum node among lists, we have to traverse all nodes in lists, and each node may be visited multiple times
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int numList = lists.length;
        if (numList == 0)
            return null;

        ListNode dummy = new ListNode(0, null);
        ListNode previousP = dummy;
        while (true) {
            int numNonEmptyList = 0;
            int minNode = -1;
            for (int i = 0; i < numList; i++)
                if (lists[i] != null) {
                    numNonEmptyList++;
                    if (minNode < 0 || lists[i].val < lists[minNode].val)
                        minNode = i;
                }
            if (numNonEmptyList == 0)
                break;
            previousP.next = lists[minNode]; // connect minNode to the end of the result list
            previousP = lists[minNode]; // update the end of the result list
            lists[minNode] = lists[minNode].next; // put its next node back into lists
            if (numNonEmptyList == 1)
                break;
        }
        return dummy.next;
    }
}
end original version */

// better version: priority queue
// implement a PriorityQueue and its Comparable
// pop the queue to get the mininum node, and push its next node back into the queue
class MyListNode implements Comparable<MyListNode> {
    ListNode node;

    MyListNode(ListNode node) {
        this.node = node;
    }

    public int compareTo(MyListNode node) {
        return this.node.val - node.node.val;
    }
}

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int numList = lists.length;
        if (numList == 0)
            return null;

        ListNode dummy = new ListNode(0, null);
        ListNode previousP = dummy;
        PriorityQueue<MyListNode> q = new PriorityQueue<MyListNode>();
        for (ListNode node : lists)
            if (node != null)
                q.offer(new MyListNode(node));
        while (!q.isEmpty()) {
            previousP.next = q.poll().node;
            previousP = previousP.next;
            if (previousP.next != null)
                q.offer(new MyListNode(previousP.next));
            if (q.size() == 1) {
                previousP.next = q.poll().node;
                break;
            }
        }
        return dummy.next;
    }
}