/**
 * GFG Problem Link: https://practice.geeksforgeeks.org/problems/length-of-loop/1
 * LeetCode Problem Link: https://leetcode.com/problems/linked-list-cycle-ii/
 * 
 */
public class LinkedListCycleLengthCalculator {
    /**
     * Returns the length of the cycle in a linked list.
     * If no cycle exists, returns 0.
     */
    public int getCycleLength(Node head) {
        return calculateCycleLengthUsingTwoPointers(head);
    }

    /**
     * Uses Floyd’s fast-slow pointer technique to determine
     * the length of the cycle in the linked list.
     * Time Complexity: O(N), Space Complexity: O(1)
     * Approach: Two Pointers (Floyd's Cycle Detection Algorithm)
     * We use two pointers, one moving twice as fast as the other.
     * If they meet, a cycle exists. We then keep one pointer fixed
     * and move the other pointer around the cycle until it meets the fixed pointer again,
     * counting the number of steps taken to determine the cycle length.    
     * 
     */
    private int calculateCycleLengthUsingTwoPointers(Node head) {

        // If the list is empty, no cycle is possible
        if (head == null) {
            return 0;
        }

        Node slowPointer = head;
        Node fastPointer = head;

        boolean hasCycle = false;

        // Step 1: Detect whether a cycle exists
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            if (slowPointer == fastPointer) {
                hasCycle = true;
                break;
            }
        }

        // If no cycle is detected, return length as 0
        if (!hasCycle) {
            return 0;
        }

        // Step 2: Calculate the length of the cycle
        int cycleLength = 1;
        slowPointer = slowPointer.next;

        // Traverse the cycle until we reach the same node again
        while (slowPointer != fastPointer) {
            slowPointer = slowPointer.next;
            cycleLength++;
        }

        return cycleLength;
    }
}
