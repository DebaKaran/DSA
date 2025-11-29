package dll;

import java.util.Stack;


public class DoublyLinkedListReverse {
    public ListNode reverseDLL(ListNode head) {

        // If list is empty or has only one node,
        // reversing it does nothing
        if (head == null) return head;

        // Reverse using a stack of node values
        //return reverseDLLUsingStack(head);

        return reverseDLLBySwappingLinks(head);
    }

     /**
     * Reverses a doubly linked list in-place by swapping
     * the prev and next pointers of each node.
     *
     * Returns the new head of the reversed list.
     * 
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    private ListNode reverseDLLBySwappingLinks(ListNode head) {

        // If the list is empty or has one node, no change needed
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head;
        ListNode newHead = null;   // will store the new head after reversal

        // Traverse the list and swap prev/next for each node
        while (curr != null) {

            // Store original next before we overwrite it
            ListNode nextNode = curr.next;

            // Swap the next and prev pointers of the current node
            curr.next = curr.prev;
            curr.prev = nextNode;

            // Update newHead to the current node; after the loop,
            // this will be the last non-null node processed
            newHead = curr;

            // Move to the original next node (now stored in nextNode)
            curr = nextNode;
        }

        // newHead now points to the original tail, which is the
        // head of the reversed list. Its prev is already correct (null),
        // and the rest of the links have been flipped.
        return newHead;
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
