package dll;

/**
 * GFG Problem: Remove duplicates from a sorted doubly linked list
 * Link: https://www.geeksforgeeks.org/remove-duplicates-sorted-doubly
 * -linked-list/
 * 
 */
public class DoublyLinkedListDuplicateRemover {
    /**
     * Removes duplicate nodes from a sorted doubly linked list.
     * Only the first occurrence of each value is kept.
     *
     * @param head head of the sorted doubly linked list
     * @return head of the modified list with duplicates removed
     * 
     * Time Complexity: O(n), where n is the number of nodes in the list.
     * Space Complexity: O(1) as no additional space is used.
     */
    
    public Node removeDuplicates(Node head) {

        // If the list is empty or has only one node, nothing to remove
        if (head == null || head.next == null) {
            return head;
        }

        // Pointer to traverse the list
        Node currentNode = head;

        // Traverse the list until the end
        while (currentNode != null) {

            // Start from the next node of currentNode
            Node nextDistinctNode = currentNode.next;

            // Skip all nodes that have the same value as currentNode
            while (nextDistinctNode != null && nextDistinctNode.data == currentNode.data) {
                // Detach duplicate node from the list (optional but clean)
                nextDistinctNode.prev = null;
                nextDistinctNode = nextDistinctNode.next;
            }

            // Link currentNode to the first node with a different value
            currentNode.next = nextDistinctNode;

            // If such a node exists, link it back to currentNode
            if (nextDistinctNode != null) {
                nextDistinctNode.prev = currentNode;
            }

            // Move currentNode forward to continue checking
            currentNode = nextDistinctNode;
        }

        return head;
    }
}
