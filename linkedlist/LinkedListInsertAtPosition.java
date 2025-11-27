/**
 * GFG Problem Link: https://practice.geeksforgeeks.org/problems/insert-a-node-at-a-specific-position-in-a-linked-list/1
 * LeetCode Link: N/A
 * Implement a function to insert a new node at a specific position in a singly linked list.
 * 
 * Time Complexity: O(N) where N is the number of nodes in the linked list
 * Space Complexity: O(1) as we are using constant extra space
 * 
 */
class LinkedListInsertAtPosition {

    /**
     * Inserts a node with value `val` at the given 1-based position `pos`
     * in the singly linked list and returns the (possibly updated) head.
     */
    public Node insertPos(Node head, int pos, int val) {

        // Positions less than 0 are considered invalid
        // (Assumes positions are 1-based as per GFG convention)
        if (pos < 0) {
            return head;
        }

        // Create a new node with the given value
        Node node = new Node(val);

        // If insertion position is at the front (position 1),
        // delegate the logic to a helper method
        if (pos == 1) {
            return insertAtFrontBasic(head, node);
        }

        // temp is used to traverse the list
        Node temp = head;

        // prev keeps track of the node just before temp
        Node prev = null;

        // i represents the current position during traversal
        int i = 1;

        // Traverse the list until:
        // - we reach the desired position (pos), or
        // - the end of the list
        while (i < pos && temp != null) {
            prev = temp;
            temp = temp.next;
            i++;
        }

        // If prev is not null, it means we found a valid place
        // to insert the new node
        if (prev != null) {

            // Link the previous node to the new node
            prev.next = node;

            // Link the new node to the current node
            node.next = temp;
        }

        // Head remains unchanged (unless pos == 1, handled earlier)
        return head;
    }

    /**
     * Helper method to insert a node at the front of the list.
     */
    private Node insertAtFrontBasic(Node head, Node node) {

        // Make the new node point to the current head
        node.next = head;

        // Update head to point to the new node
        head = node;

        // Return the updated head
        return head;
    }
}
