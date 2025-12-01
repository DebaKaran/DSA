
/**
 * GFG : Remove Nth Node from End of Linked List
 * https://practice.geeksforgeeks.org/problems/remove-nth-node-from-end-of-linked-list
 * 
 */
public class RemoveNthNodeFromEnd {
    
    public ListNode removeNthNodeFromEnd(ListNode head, int n) {
        //return removeNthNodeFromEndBruteForce(head, n);
        return removeNthNodeFromEndUsingTwoPointers(head, n);
    }

    // Approach: Two Pointers - Single Pass
    // Time Complexity: O(L) where L is the length of the linked list
    // Space Complexity: O(1)
    
    private ListNode removeNthNodeFromEndUsingTwoPointers(ListNode head, int n) {

        // Edge case: empty list or invalid n
        if (head == null || n < 0) {
            return head;
        }

        // Fast and slow pointers both start at head
        ListNode fast = head;
        ListNode slow = head;

        // Move fast pointer n steps ahead
        int steps = 0;
        while (fast != null && steps < n) {
            fast = fast.next;
            steps++;
        }

        // If fast becomes null after moving n steps,
        // it means we need to delete the head node
        if (fast == null) {
            return head.next;
        }

        // Move both pointers until fast reaches the last node
        // slow will then point to the node just before the target node
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Delete the nth node from the end
        ListNode nodeToDelete = slow.next;
        slow.next = (nodeToDelete != null) ? nodeToDelete.next : null;

        // Return the updated head
        return head;
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
