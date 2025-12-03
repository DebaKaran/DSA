/**
 * Leetcode Problem 2095: Delete the Middle Node of a Linked List
 * https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
 * 
 */

public class LinkedListMiddleDeletion {

    /**
     * Deletes the middle node of the linked list and returns the head.
     * If there is only one node, returns null.
     */
    public ListNode deleteMiddle(ListNode head) {
        //return deleteMiddleUsingLength(head); 
        return deleteMiddleUsingTwoPointers(head);
    }

    /**
     * Uses the fast–slow pointer technique to find and remove the middle node.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    private ListNode deleteMiddleUsingTwoPointers(ListNode head) {

        // If there is only one node, deleting the middle node returns null
        if (head == null || head.next == null) {
            return null;
        }

        // Slow pointer moves one step at a time (will land on middle)
        ListNode slowPointer = head;

        // Fast pointer moves two steps at a time
        ListNode fastPointer = head;

        // Tracks the node just before the middle node
        ListNode previousNode = null;

        // Move fast by 2 and slow by 1 until fast reaches the end
        while (fastPointer != null && fastPointer.next != null) {
            previousNode = slowPointer;
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        // slowPointer now points to the middle node
        // Remove it by skipping it in the list
        previousNode.next = slowPointer.next;

        return head;
    }

    //Approach 1: Using Length Calculation
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    private ListNode deleteMiddleUsingLength(ListNode head) {
        // If there is only one node, deleting the middle returns null
        if (head == null || head.next == null) {
            return null;
        }

        // Step 1: Find the length of the linked list
        int length = 0;
        ListNode currentNode = head;
        while (currentNode != null) {
            length++;
            currentNode = currentNode.next;
        }

        // Middle node index (0-based). For n = 7 -> 3, for n = 2 -> 1
        int middleIndex = length / 2;

        // Step 2: Traverse to the node just before the middle node
        currentNode = head;
        for (int i = 0; i < middleIndex - 1; i++) {
            currentNode = currentNode.next;
        }

        // currentNode is the previous node of the middle node
        // Skip the middle node
        currentNode.next = currentNode.next.next;

        return head;
    }
}

