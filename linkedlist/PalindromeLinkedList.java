import java.sql.Time;
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

        //return isPalindromeStackBased(head);

        // return isPalindromeWithoutExtraSpace(head);

        return isPalindromeInPlace(head);
    }

    // In-place approach to check for palindrome
    // Time Complexity: O(N), 

    // Find middle → O(N/2)

    // Reverse second half → O(N/2)

    // Compare halves → O(N/2)

    // Restore list → O(N/2)
    
    // Space Complexity: O(1)
    private boolean isPalindromeInPlace(ListNode head) {

        // Step 1: Find the middle using slow & fast pointers
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // Step 2: Reverse the second half of the list
        ListNode secondHalfReversedHead = reverseList(slow.next);

        // Step 3: Compare first half and reversed second half
        ListNode firstHalfPointer = head;
        ListNode secondHalfPointer = secondHalfReversedHead;

        while (secondHalfPointer != null) {
            if (firstHalfPointer.val != secondHalfPointer.val) {
                return false;
            }
            firstHalfPointer = firstHalfPointer.next;
            secondHalfPointer = secondHalfPointer.next;
        }

        // Step 4: Restore the original list structure
        reverseList(secondHalfReversedHead);

        return true;
    }
    // In-place approach to check for palindrome

    //  Time Complexity

    // You do:

    // One pass to find length → O(N)

    // One partial pass to reach middle → O(N) in worst case / but combined still linear

    // One pass to reverse second half → O(N/2)

    // One pass to compare halves → O(N/2)

    // Total is proportional to a constant times N:

    // ✅ Time Complexity: O(N)
    // Space Complexity: O(1)

    // Steps:
    // 1. Calculate length of the linked list
    // 2. Find the middle of the linked list
    // 3. Split the list into two halves
    // 4. Reverse the second half of the list   
    // 5. Compare the first half and the reversed second half
    // Note: The original list structure is modified in this approach.

    private boolean isPalindromeWithoutExtraSpace(ListNode head) {

        // Step 1: Find length of the list
        ListNode current = head;
        int length = 0;
        while (current != null) {
            length++;
            current = current.next;
        }

        // Step 2: Move to the middle region
        // result = index of the "middle" node (for odd)
        // or node just before the second half (for even)
        int middleIndex = (length - 1) / 2;

        current = head;
        ListNode prev = null;

        // Move current to the middle node and prev to the node before it
        while (middleIndex != 0) {
            middleIndex--;
            prev = current;
            current = current.next;
        }

        // Step 3: Split into two halves

        // For odd length: skip the middle node
        // First half: head ... prev
        // Second half: current.next ...
        if (length % 2 != 0) {
            prev.next = null;      // terminate first half before middle
        }

        // newHead is the start of the second half
        ListNode secondHalfHead = current.next;
        // Detach the middle (for odd) or end of first half (for even)
        current.next = null;

        // Step 4: Reverse the second half
        ListNode reversedSecondHalfHead = reverseList(secondHalfHead);

        // Step 5: Compare first half and reversed second half
        ListNode p1 = head;
        ListNode p2 = reversedSecondHalfHead;

        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }

    private ListNode reverseList(ListNode head) {

        // If the list is empty or has only one node,
        // reversing is unnecessary
        if (head == null || head.next == null) {
            return head;
        }

        // 'current' will traverse the list
        ListNode current = head;

        // 'prev' will hold the reversed part of the list
        ListNode prev = null;

        // Iterate through the list and reverse links one by one
        while (current != null) {

            // Store next node before breaking the link
            ListNode next = current.next;

            // Reverse the current node's pointer
            current.next = prev;

            // Move prev one step forward
            prev = current;

            // Move current one step forward using saved next
            current = next;
        }

        // 'prev' will be the new head of the reversed list
        return prev;
    }

    // Stack-based approach to check for palindrome
    // Time Complexity: O(2*n), Space Complexity: O(n)

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
