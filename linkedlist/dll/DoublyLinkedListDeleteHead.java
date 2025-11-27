package dll;

/**
 * GFG Problem Link: https://practice.geeksforgeeks.org/problems/delete-head-of-doubly-linked-list/1
 * LeetCode Link: N/A
 * 
 * Implement a function to delete the head node of a doubly linked list.
 * Time Complexity: O(1) as we are only updating a few pointers
 * Space Complexity: O(1) as we are using constant extra space
 */
public class DoublyLinkedListDeleteHead {
     /**
     * Deletes the head node of a doubly linked list
     * and returns the new head.
     */
    public static Node deleteHead(Node head) {

        // If the list is empty or has only one node,
        // deleting the head results in an empty list
        if (head == null || head.next == null) {
            return null;
        }

        // The second node will become the new head
        Node newHead = head.next;

        // Disconnect the old head from the list
        head.next = null;

        // Remove backward link from the new head
        newHead.prev = null;

        // Return the updated head of the list
        return newHead;
    }
}
