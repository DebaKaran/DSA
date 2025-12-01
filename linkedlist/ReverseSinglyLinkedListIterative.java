import java.util.Stack;

/**
 * LeetCode : Reverse a Singly Linked List Iteratively
 * https://leetcode.com/problems/reverse-linked-list/
 * 
 */
public class ReverseSinglyLinkedListIterative {

    public ListNode reverseList(ListNode head) {
        // Delegate the work to the iterative reverse implementation
        return reverseListIterativeApproach(head);
    }

    /**
     * Iterative Approach to Reverse a Singly Linked List
     * Time Complexity: O(N) where N is the number of nodes in the linked list
     * Space Complexity: O(1) - in-place reversal
     * @param head
     * @return
     */
    private ListNode reverseListIterativeApproach(ListNode head) {

        // If the list is empty or has only one node,
        // reversing is unnecessary
        if (head == null || head.next == null) {
            return head;
        }

        // 'curr' will traverse the list
        ListNode curr = head;

        // 'prev' will hold the reversed part of the list
        ListNode prev = null;

        // Iterate through the list and reverse links one by one
        while (curr != null) {

            // Store next node before breaking the link
            ListNode next = curr.next;

            // Reverse the current node's pointer
            curr.next = prev;

            // Move prev one step forward
            prev = curr;

            // Move curr one step forward using saved next
            curr = next;
        }

        // 'prev' will be the new head of the reversed list
        return prev;
    }

    /**
     * Approach: Using Stack to Reverse a Singly Linked List
     * Time Complexity: O(N) where N is the number of nodes in the linked list
     * Space Complexity: O(N) - stack space
     * 
     * @param head
     * @return
     */
    private ListNode reverseListUsingStack(ListNode head) {

        // Stack to store values of the linked list nodes
        Stack<Integer> s = new Stack<>();

        // Pointer to traverse the list
        ListNode curr = head;

        // First pass: push all node values onto the stack
        while (curr != null) {
            s.push(curr.val);
            curr = curr.next;
        }

        // Reset pointer to head
        curr = head;

        // Second pass: pop values from stack and overwrite node values
        while (curr != null) {
            curr.val = s.pop();
            curr = curr.next;
        }

        // Head remains unchanged; list values are reversed
        return head;
    }
}
