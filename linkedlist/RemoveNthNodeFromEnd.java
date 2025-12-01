
/**
 * GFG : Remove Nth Node from End of Linked List
 * https://practice.geeksforgeeks.org/problems/remove-nth-node-from-end-of-linked-list
 * 
 */
public class RemoveNthNodeFromEnd {
    
    public ListNode removeNthNodeFromEnd(ListNode head, int n) {
        return removeNthNodeFromEndBruteForce(head, n);
    }
    
    // Approach: Brute Force - Two Passes
    // Time Complexity: O(L) where L is the length of the linked list
    // Space Complexity: O(1)

    private ListNode removeNthNodeFromEndBruteForce(ListNode head, int n) {

        // Edge cases: empty list or invalid n
        if (head == null || n < 0) {
            return head;
        }

        // Step 1: Traverse list to calculate its length
        ListNode current = head;
        int length = 0;

        while (current != null) {
            length++;
            current = current.next;
        }

        // Step 2: Find index of node to delete from start (0-based)
        int indexFromStart = length - n;

        // If n is greater than length, no deletion possible
        if (indexFromStart < 0) return head;

        // If deleting the head node
        if (indexFromStart == 0) return head.next;

        // Step 3: Traverse again to reach node before deletion point
        current = head;
        while (indexFromStart != 1) {
            indexFromStart--;
            current = current.next;
        }

        // Step 4: Delete the target node
        ListNode nodeToDelete = current.next;
        current.next =  nodeToDelete.next;

        return head;
    }
}
