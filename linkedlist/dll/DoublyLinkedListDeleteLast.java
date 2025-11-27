package dll;

/**
 * GFG Problem Link: https://practice.geeksforgeeks.org/problems/delete-last-node-of-doubly-linked-list/1
 * LeetCode Link: N/A
 * 
 * Implement a function to delete the last node of a doubly linked list.
 * Time Complexity: O(N) where N is the number of nodes in the doubly linked list
 * Space Complexity: O(1) as we are using constant extra space
 */
public class DoublyLinkedListDeleteLast {
    /**
     * Deletes the last node of a doubly linked list
     * and returns the head of the updated list.
     */
    public static Node deleteLastNode(Node head) {

        // If the list is empty, nothing to delete
        if (head == null) {
            return head;
        }

        // currentNode will traverse to the last node
        Node currentNode = head;

        // prevNode keeps track of the node before currentNode
        Node prevNode = null;

        // Traverse until currentNode points to the last node
        while (currentNode.next != null) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }

        // If prevNode is not null, list has more than one node
        if (prevNode != null) {

            // Detach the last node from the list
            prevNode.next = null;

            // Remove backward link of the deleted node
            currentNode.prev = null;

            // Head remains unchanged
            return head;
        }

        // If prevNode is null, the list had only one node
        // Deleting the last node results in an empty list
        return null;
    }
}
