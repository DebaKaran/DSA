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
        return copyUsingHashMap(head);
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
