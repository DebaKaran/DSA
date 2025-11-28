package dll;

import java.util.Stack;


public class DoublyLinkedListReverse {
    public ListNode reverseDLL(ListNode head) {

        // If list is empty or has only one node,
        // reversing it does nothing
        if (head == null) return head;

        // Reverse using a stack of node values
        return reverseDLLUsingStack(head);
    }

    /**
     * Reverses the doubly linked list by using a stack
     * to reverse the node *values* (not the pointers).
     *
     * The structure (prev/next links) of the list remains the same,
     * only the data values are reversed.
     */
    private ListNode reverseDLLUsingStack(ListNode head) {

        // Stack to store the data values of each node
        Stack<Integer> stack = new Stack<>();

        // First traversal: push all node data onto the stack
        ListNode temp = head;

        while (temp != null) {
            stack.push(temp.data);
            temp = temp.next;
        }

        // Second traversal: pop values from stack and
        // assign back to nodes in forward order
        temp = head;

        while (temp != null) {
            temp.data = stack.pop(); // assign reversed data
            temp = temp.next;
        }

        // The head pointer itself does not change;
        // only node data has been reversed
        return head;
    }
}
