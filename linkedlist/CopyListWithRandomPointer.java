import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a node with a random pointer.
 */
class Node {
    int val;
    Node next;
    Node random;

    Node(int val) { this.val = val; }
}

/**
 * Copy a linked list where each node has a random pointer.
 * This class provides the straightforward HashMap-based solution.
 */
public class CopyListWithRandomPointer {

    /**
     * Public API: create a deep copy of a list with next + random pointers.
     */
    public Node copyRandomList(Node head) {
         return copyWithoutHashMap(head);
    }

    /**
     * Interleaving approach (no hashmap):
     * 1) First pass: for each original node, insert its copy right after it.
     *    original: A -> B -> C
     *    becomes:  A -> A' -> B -> B' -> C -> C'
     *
     * 2) Second pass: assign copy.random = original.random.next (if random != null).
     *
     * 3) Third pass: restore original list and extract the copied list.
     *
     * Time: O(n), Extra space: O(1) (excluding output nodes).
     */
    private Node copyWithoutHashMap(Node head) {
        if (head == null) return null;

        // 1) Interleave copied nodes after originals
        Node iter = head;
        while (iter != null) {
            Node copy = new Node(iter.val);
            Node nextNode = iter.next;

            iter.next = copy;
            copy.next = nextNode;

            iter = nextNode;
        }

        // 2) Assign random pointers for the copied nodes
        iter = head;
        while (iter != null) {
            Node copyNode = iter.next;
            Node randomNode = iter.random;
            copyNode.random = (randomNode == null) ? null : randomNode.next;
            // Move to the next original node
            iter = copyNode.next;
        }

        // 3) Separate the interleaved lists: restore original and extract copy list
        Node pseudoHead = new Node(-1);   // dummy head for the copied list
        Node copyTail = pseudoHead;
        iter = head;

        while (iter != null) {
            Node copyNode = iter.next;
            Node nextNode = copyNode.next; // start of next original node

            // append copyNode to copied list
            copyTail.next = copyNode;
            copyTail = copyNode;

            // restore original list link
            iter.next = nextNode;

            // set next for copied node (safety: nextNode may be null)
            copyNode.next = (nextNode == null) ? null : nextNode.next;

            // advance original iterator
            iter = nextNode;
        }

        return pseudoHead.next;
    }

    /**
     * HashMap-based approach:
     * 1) First pass: create a new node for every original node and store mapping original->copy.
     * 2) Second pass: set next and random pointers on copy nodes using the map.
     *
     * Time: O(n), two linear passes over the list.
     * Space: O(n) for the map.
     */
    private Node copyUsingHashMap(Node head) {
        if (head == null) return null;

        // Map original node -> cloned node
        Map<Node, Node> nodeMap = new HashMap<>();

        // First pass: create copy nodes (next/random unset) and store mapping
        Node iter = head;
        while (iter != null) {
            nodeMap.put(iter, new Node(iter.val));
            iter = iter.next;
        }

        // Second pass: assign next and random pointers for copied nodes
        iter = head;
        while (iter != null) {
            Node copy = nodeMap.get(iter);
            copy.next = nodeMap.get(iter.next);     // may be null if iter.next == null
            copy.random = nodeMap.get(iter.random); // may be null
            iter = iter.next;
        }

        // Return head of copied list
        return nodeMap.get(head);
    }
}
