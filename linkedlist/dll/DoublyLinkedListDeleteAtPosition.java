package dll;

/**
 * Coding Studion Problem
 * Problem Statement: https://www.codingninjas.com/studio/problems/delete-node-at-a
 * dd-position-in-doubly-linked-list_8230766?challengeSlug=striver-sde-challenge    
 * 
 */
public class DoublyLinkedListDeleteAtPosition {

    static Node deleteNode(Node head, int pos) {

    // If the list is empty OR the position is invalid (negative),
    // nothing can be deleted, so return the original head
    if (head == null || pos < 0) {
        return head;
    }

    // Delegate actual deletion logic to helper method
    return deleteNodeWithDummyNode(head, pos);
}

//Time Complexity: O(N) where N is the number of nodes in the doubly linked list.
//Space Complexity: O(1) as we are using only constant extra space.

private static Node deleteNodeWithDummyNode(Node head, int pos) {

    // Create a dummy node to simplify deletion logic,
    // especially when deleting the original head (pos == 0)
    Node dummyNode = new Node(0);

    // Link dummy node before the actual head
    dummyNode.next = head;
    head.prev = dummyNode;

    int i = 0;

    // Start traversal from the actual head
    Node deletedNode = head;

    // Traverse the list until we reach the node at index `pos`
    while (i < pos) {
        i++;

        if (deletedNode != null) {
            // Move to the next node
            deletedNode = deletedNode.next;
        } else {
            // We've gone past the end of the list,
            // so deletion is not possible
            break;
        }
    }

    // If deletedNode is not null, a valid node exists at index `pos`
    if (deletedNode != null) {

        // Node before the one to be deleted
        Node prevNode = deletedNode.prev;

        // Node after the one to be deleted
        Node nextNode = deletedNode.next;

        // Detach the node to be deleted from both sides
        deletedNode.prev = null;
        deletedNode.next = null;

        // Break the forward link from the previous node
        prevNode.next = null;

        // Reconnect previous node to the next node
        prevNode.next = nextNode;

        // If there is a node after the deleted node,
        // update its prev pointer to point to prevNode
        if (nextNode != null) {
            nextNode.prev = null;
            nextNode.prev = prevNode;
        }
    }

    // The actual head of the updated list is next of dummy node
    Node newHead = dummyNode.next;

    // Remove dummy node influence by resetting prev of new head
    newHead.prev = null;

    // Detach dummy node completely
    dummyNode.next = null;

    // Return the updated head after deletion
    return newHead;
}

}
