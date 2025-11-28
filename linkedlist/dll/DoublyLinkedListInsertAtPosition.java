package dll;

/**
 * GFG Problem: https://www.geeksforgeeks.org/problems/insert-a-node-in-doubly-linked-list/1
 * Time Complexity: O(N) where N is the number of nodes in the doubly linked list.
 * Space Complexity: O(1) as we are using only constant extra space.
 * 
 */
public class DoublyLinkedListInsertAtPosition {
    /**
     * Inserts a node with value x into a doubly linked list at a position p.
     *
     * Custom semantics (your design):
     *   p == -1  → insert BEFORE head
     *   p == 0   → insert AFTER head
     *   p > 0    → traverse using while(i < p) and insert after curr
     *
     * Returns the (possibly updated) head of the list.
     */
    Node insertAtPos(Node head, int p, int x) {

        // Create the new node to insert
        Node nd = new Node(x);

        // If the list is empty, simply make this node the head
        // (Note: here p is ignored; in some specs you might want p==0 or 1)
        if (head == null) {
            return nd;
        }

        // If p == -1, insert before the current head (becomes new head)
        if (p == -1) {
            return insertBeforeHead(head, nd);
        }

        // If p == 0, insert after the current head
        if (p == 0) {
            return insertAfterHead(head, nd);
        }

        // For p > 0: traverse the list to find the node at position p
        int i = 0;
        Node curr = head;

        // Move curr forward p steps (or until list ends)
        while (i < p && curr != null) {
            i++;
            curr = curr.next;
        }

        // If we ran out of nodes before reaching position p,
        // do not insert and simply return the original head
        if (curr == null) {
            return head;
        }

        // At this point, curr is the node at position p (based on your traversal logic)
        // We want to insert the new node AFTER curr
        Node nextNode = curr.next;

        // If there is a node after curr, adjust its prev pointer
        // and link it after nd
        if (nextNode != null) {
            nextNode.prev = nd;
            nd.next = nextNode;
        }

        // Link curr to the new node
        curr.next = nd;

        // And link new node back to curr
        nd.prev = curr;

        // Head remains unchanged for p != -1 and non-empty list
        return head;
    }

    /**
     * Inserts nd before the current head and returns the new head.
     */
    private Node insertBeforeHead(Node head, Node nd) {
        // Link new node to the current head
        nd.next = head;

        // Set head's prev pointer to the new node
        head.prev = nd;

        // New node becomes the new head
        return nd;
    }

    /**
     * Inserts nd immediately after the head and returns the same head.
     */
    private Node insertAfterHead(Node head, Node nd) {
        // Node that originally came after head
        Node nextNode = head.next;

        // Link head to new node
        head.next = nd;

        // Link new node back to head
        nd.prev = head;

        // Link new node forward to the original next node
        nd.next = nextNode;

        // If there was a node after head, fix its prev pointer
        if (nextNode != null) {
            nextNode.prev = nd;
        }

        // This line is redundant because nd.next was already set to nextNode above,
        // but it does not break anything:
        nd.next = nextNode;

        // Head does not change in "insert after head"
        return head;
    }
}
