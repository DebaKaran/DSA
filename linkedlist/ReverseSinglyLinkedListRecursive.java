/**
 * LeetCode Problem 206: Reverse Linked List (Recursive)
 * https://leetcode.com/problems/reverse-linked-list/
 * 
 */
public class ReverseSinglyLinkedListRecursive {
    public ListNode reverseList(ListNode head) {
        // Delegate the work to the recursive reverse implementation
        return reverseListRecursive(head);
    }
    /**
     * Recursive Approach to Reverse a Singly Linked List
     * Time Complexity: O(N) where N is the number of nodes in the linked list
     * Space Complexity: O(N) - due to recursion stack
     * 
     * @param head
     * @return
     */
    private ListNode reverseListRecursive(ListNode head) {

        // Base case: empty list or last node becomes new head
        if (head == null || head.next == null) {
            return head;
        }

        // Recursively reverse the remaining list
        ListNode newHead = reverseListRecursive(head.next);

        // Store the next node before rewiring
        ListNode nextNode = head.next;

        // Reverse the link
        nextNode.next = head;

        // Break the original forward link
        head.next = null;

        // newHead always points to the head of reversed list
        return newHead;
    }
}
