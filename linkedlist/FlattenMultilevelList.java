import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Node for the 2D linked structure:
 * - `next` links to the next column
 * - `bottom` links downwards in the same column
 */
class Node {
    int data;
    Node next;
    Node bottom;

    Node(int data) {
        this.data = data;
    }
}
/**
 * Flatten a multilevel linked list (columns connected by `next`, each column a sorted list via `bottom`)
 * by extracting all values, sorting them, and building a single bottom-linked list.
 *
 * This implementation intentionally uses extra space (list of values).
 * 
 * GFG problem: https://www.geeksforgeeks.org/flattening-a-linked-list/
 * 
 */
public class FlattenMultilevelList {

    /**
     * Public API: flatten the 2D list into a single sorted bottom-linked list.
     */
    public Node flatten(Node root) {
        //return flattenUsingValueCollection(root);
        return flattenBySequentialMerges(root);
    }

    /**
     * Sequentially merge each column into resultHead.
     * This method mutates the original structure (it severs `next` links as it goes)
     * and reuses the nodes via `bottom` pointers to build the flattened list.
     * Time complexity: O(N*K) where N is average length of each column and K is number of columns.
     * Space complexity: O(1) additional space (in-place merging).
     * 
     */
    private Node flattenBySequentialMerges(Node root) {
        // Empty or single-column -> already flattened
        if (root == null || root.next == null) {
            return root;
        }

        Node resultHead = null;   // accumulated merged list (bottom-linked)
        Node column = root;       // pointer to iterate columns

        while (column != null) {
            // Detach this column from the columns chain to avoid cycles or double traversal
            Node nextColumn = column.next;
            column.next = null; // isolate current column

            // Merge the isolated column (a bottom-linked sorted list) into resultHead
            resultHead = mergeTwoSortedColumns(resultHead, column);

            // Advance to next column
            column = nextColumn;
        }

        return resultHead;
    }

    /**
     * Merge two sorted bottom-linked lists (nd1 and nd2) by reusing nodes.
     * Returns head of merged bottom-linked list.
     *
     * This function:
     * - does not allocate new list nodes (relinks existing nodes)
     * - handles null inputs gracefully
     */
    private Node mergeTwoSortedColumns(Node nd1, Node nd2) {
        if (nd1 == null && nd2 == null) return null;
        if (nd1 == null) return nd2;
        if (nd2 == null) return nd1;

        // Dummy node simplifies attaching nodes to the merged result
        Node dummy = new Node(-1);
        Node tail = dummy;

        // Standard merge loop: attach smaller node, advance its pointer
        while (nd1 != null && nd2 != null) {
            if (nd1.data <= nd2.data) {
                tail.bottom = nd1;
                tail = nd1;
                nd1 = nd1.bottom;
            } else {
                tail.bottom = nd2;
                tail = nd2;
                nd2 = nd2.bottom;
            }
        }

        // Append any remaining nodes from either column
        if (nd1 != null) {
            tail.bottom = nd1;
        }
        if (nd2 != null) {
            tail.bottom = nd2;
        }

        return dummy.bottom; // head of merged bottom-linked list
    }
}

    /**
     * Implementation details:
     * 1) Traverse each column (next pointers) and each column's bottom list; collect all node values.
     * 2) Sort the collected values.
     * 3) Build a new singly bottom-linked list from the sorted values and return its head.
     *
     * This preserves node values but allocates new Node objects for the flattened list.
     * 
     * Time complexity: O(N log N) where N is total number of nodes.
     * Explanation: collecting values is O(N), sorting them is O(N log N), reconstructing list is O(N).
     * 
     * Space complexity: O(N) for storing values and new nodes.
     * the ArrayList stores all N values; new nodes allocated for the output also require O(N) space.
     */
    private Node flattenUsingValueCollection(Node root) {
        if (root == null) {
            return null;
        }

        // 1) Collect all values from the 2D structure
        List<Integer> values = new ArrayList<>();
        Node column = root;
        while (column != null) {
            Node down = column;
            while (down != null) {
                values.add(down.data);
                down = down.bottom;
            }
            column = column.next;
        }

        // 2) Sort all collected values
        Collections.sort(values);

        // 3) Re-build a single bottom-linked list from sorted values
        Node dummy = new Node(-1);
        Node tail = dummy;
        for (int v : values) {
            Node n = new Node(v);
            tail.bottom = n;
            tail = n;
        }

        return dummy.bottom;
    }
}
