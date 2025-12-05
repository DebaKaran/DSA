
/**
 * LeetCode Problem 61: Rotate List
 * Link: https://leetcode.com/problems/rotate-list/
 * Time Complexity:  O(n) — one pass to find tail/length, then up to another pass to locate new tail (total linear).
 * where n is the number of nodes in the list.
 * 
 * Space Complexity: O(1) as no additional space is used.
 */
public class RotateList {

    /**
     * Rotate the list to the right by k places.
     * Example: [1,2,3,4,5], k=2 -> [4,5,1,2,3]
     */
    public ListNode rotateRight(ListNode head, int k) {
        return rotateRightCircular(head, k);
    }

    /**
     * Optimal approach:
     * 1. Compute length and locate tail.
     * 2. Make the list circular by linking tail.next -> head.
     * 3. Find the new tail at position (length - k % length - 1).
     * 4. Break the circle there and return new head.
     */
    private ListNode rotateRightCircular(ListNode head, int k) {
        // Edge cases: empty list or single-node list -> no change
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 1) compute length and find tail
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Normalize k to avoid unnecessary rotations
        k = k % length;
        if (k == 0) {
            return head;
        }

        // 2) make circular
        tail.next = head;

        // 3) find new tail: node at index (length - k - 1) (0-based)
        int stepsToNewTail = length - k - 1;
        ListNode newTail = head;
        for (int i = 0; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        // 4) new head is next of newTail; break the circle
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}
