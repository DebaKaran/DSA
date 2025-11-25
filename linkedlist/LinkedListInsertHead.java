/**
 * Take U Forward
 * https://takeuforward.org/data-structure/insert-a-node-at-the-head-of
 * GFG Problem: Insert a node at the beginning of a linked list
 * URL: https://www.geeksforgeeks.org/insert-a-node-at-the-begin
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 * 
 */
/*Definition for singly Linked List
class ListNode {
    int val;
    ListNode next;

    ListNode() {
        val = 0;
        next = null;
    }

    ListNode(int data1) {
        val = data1;
        next = null;
    }

    ListNode(int data1, ListNode next1) {
        val = data1;
        next = next1;
    }
}
*/

class LinkedListInsertHead {
    public ListNode insertAtHead(ListNode head, int X) {
        ListNode node = new ListNode(X);
        node.next = head;
        return node;
    }
}