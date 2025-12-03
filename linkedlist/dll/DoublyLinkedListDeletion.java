package dll;

/**
 * GFG - Deletion in Doubly Linked List
 * https://www.geeksforgeeks.org/deletion-doubly-linked-list/
 * 
 */

public class DoublyLinkedListDeletion {

    /**
     * Deletes all occurrences of value x from a doubly linked list
     * and returns the (possibly updated) head.
     */
    static Node deleteAllOccurOfX(Node head, int x) {
        return deleteAllOccurrencesInDoublyList(head, x);
        
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    //Approach: Iterative Traversal and Deletion 
    // where n is the number of nodes in the doubly linked list.
    // We traverse the list once, checking each node's data against x and deleting nodes as needed.
    // No additional space is used that scales with input size, so space complexity is O(1).
    
    private static Node deleteAllOccurrencesInDoublyList(Node head, int x) {

        if (head == null) {
            return null;
        }

        Node currentNode = head;

        while (currentNode != null) {

            // Store next node ahead of time because currentNode may be deleted
            Node nextNode = currentNode.next;

            if (currentNode.data == x) {

                // If current node is the head, move head forward
                if (currentNode.prev == null) {
                    head = nextNode;
                    if (head != null) {
                        head.prev = null;
                    }
                } else {
                    // Link previous node to next node
                    currentNode.prev.next = nextNode;
                }

                if (nextNode != null) {
                    // Link next node back to previous node
                    nextNode.prev = currentNode.prev;
                }

                // Completely detach current node (optional but clean)
                currentNode.next = null;
                currentNode.prev = null;
            }

            // Move to the next node in original sequence
            currentNode = nextNode;
        }

        return head;
    }
    
}
