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
        return deleteMiddleUsingLength(head);
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

