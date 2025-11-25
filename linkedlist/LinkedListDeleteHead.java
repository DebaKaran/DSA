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

/**
 * Take u forward:  
 * Delete Head of Linked List
 * Given the head of a linked list, delete the head node and return the new head of the linked list.    
 * The linked list is non-empty.    
 * 
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 * 
 */
class LinkedListDeleteHead {
    public ListNode deleteHead(ListNode head) {
        if(head == null) return head;
        ListNode temp = head.next;
        head.next = null;
        return temp;
    }
}
