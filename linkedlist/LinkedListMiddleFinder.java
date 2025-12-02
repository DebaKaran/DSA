/**
 * Leetcode Problem: 876. Middle of the Linked List
 * https://leetcode.com/problems/middle-of-the-linked-list/
 * 
 */
public class LinkedListMiddleFinder {
    public ListNode findMiddle(ListNode head) {
        return getMiddleUsingFastSlowPointers(head);
    }
    // Approach: Use fast and slow pointers to find the middle node
    // Time Complexity: O(N), Space Complexity: O(1)
    
    private ListNode getMiddleUsingFastSlowPointers(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slowPointer = head;
        ListNode fastPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        return slowPointer; // returns second middle for even-length list
    }
}
