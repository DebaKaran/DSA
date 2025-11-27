// Time Complexity: O(N) where N is the number of nodes in the linked list
// Space Complexity: O(1) as we are using constant extra space

public class LinkedListInsertAtPositionCodingStudio {
    /**
     * Inserts a node with value `val` at 1-based position `pos`
     * in the linked list whose head is `head`.
     *
     * Returns the (possibly updated) head of the list.
     */

    public Node insertPos(Node head, int pos, int val) {

        // Positions less than 0 are treated as invalid.
        // (Typically, positions should be >= 1 in 1-based indexing.)
        if (pos < 0) {
            return head;
        }

        // Create a new node with the given value
        Node node = new Node(val);

        // If insertion is at the front (position 1),
        // delegate to helper for inserting at head
        if (pos == 1) {
            return insertAtFrontBasic(head, node);
        }

        // temp is used to traverse the list
        Node temp = head;

        // prev will point to the node just before the insertion point
        Node prev = null;

        // i tracks the current 1-based position during traversal
        int i = 1;

        // Traverse the list until:
        // - we reach the desired position `pos`, or
        // - we hit the end of the list (temp becomes null)
        while (i < pos && temp != null) {
            prev = temp;       // remember the previous node
            temp = temp.next;  // move to next node
            i++;
        }

        // If prev is not null, we have a valid node before the insertion point.
        // Insert `node` between `prev` and `temp`.
        if (prev != null) {
            // Link prev to the new node
            prev.next = node;

            // New node should point to the current node at position `pos`
            // (or null if we are appending at the end)
            node.next = temp;
        }

        // For pos > 1, head itself does not change in this implementation.
        return head;
    }

    /**
     * Helper method to insert a given node at the front of the list.
     */
    private Node insertAtFrontBasic(Node head, Node node) {

        // Make the new node point to the current head
        node.next = head;

        // Update head to the new node
        head = node;

        // Return the updated head
        return head;
    }
}
