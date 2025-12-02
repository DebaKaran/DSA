import java.util.Stack;

/**
 * Leetcode - Palindrome Linked List
 * https://leetcode.com/problems/palindrome-linked-list/
 * Given the head of a singly linked list, return true if it is a palindrome.
 * 
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        // Empty list or single node list is always a palindrome
        if (head == null || head.next == null) {
            return true;
        }

        return isPalindromeStackBased(head);
    }

    // Stack-based approach to check for palindrome
    // Time Complexity: O(n), Space Complexity: O(n)
    
    private boolean isPalindromeStackBased(ListNode head) {

        // Stack to store node values
        Stack<Integer> stack = new Stack<>();

        // Pointer to traverse the list
        ListNode current = head;

        // Push all node values onto the stack
        while (current != null) {
            stack.push(current.val);
            current = current.next;
        }

        // Reset pointer to head for comparison
        current = head;

        // Compare each node value with stack's top value
        while (current != null) {
            int topValue = stack.pop();
            if (current.val != topValue) {
                return false;
            }
            current = current.next;
        }

        return true;
    }
}
