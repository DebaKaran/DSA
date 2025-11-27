import java.util.List;

/**
 * Leetcode Problem 203: Remove Linked List Elements
 * https://leetcode.com/problems/remove-linked-list-elements/
 * Remove all elements from a linked list of integers that have value val.  
 * 
 */
class ListNode {
     int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 
class LinkedListRemoveElementsByValue {

    public ListNode removeElements(ListNode head, int val) {
        //return removeElementsBasic(head, val);

        return removeElementsUsingDummyNode(head, val);
        
    }

    // Approach using a dummy (sentinel) node
    // Time Complexity: O(N) where N is the number of nodes in the linked list
    // Space Complexity: O(1) as we are using constant extra space
    
    private ListNode removeElementsUsingDummyNode(ListNode head, int val) {
        // Dummy (sentinel) node before the actual head.
        // This makes deletions at the head position easier and uniform.
        ListNode dummy = new ListNode(0, head);

        // prev always points to the node *before* curr
        ListNode prev = dummy;

        // curr is the node we are currently inspecting
        ListNode curr = head;

        // Traverse the list
        while (curr != null) {

            if (curr.val == val) {
                // Node needs to be removed:
                // skip curr by linking prev to curr.next
                prev.next = curr.next;

                // Optional: detach curr for clarity / GC
                curr.next = null;

                // Move curr forward, prev stays where it is
                curr = prev.next;

            } else {
                // Node should be kept:
                // move both prev and curr forward
                prev = curr;
                curr = curr.next;
            }
        }

        // New head might have changed, return dummy.next
        return dummy.next;
    }

    // Basic approach without using a dummy node
    // Time Complexity: O(N) where N is the number of nodes in the linked list
    // Space Complexity: O(1) as we are using constant extra space

    private ListNode removeElementsBasic(ListNode head, int val) {

        // If the list is empty, nothing to remove
        if (head == null) {
            return head;
        }

        // curr is used to traverse the list
        ListNode curr = head;

        // prev keeps track of the previous valid (non-deleted) node
        // It is null initially because head has no previous node
        ListNode prev = null;

        // Traverse the linked list
        while (curr != null) {

            // Case 1: Current node needs to be removed
            if (curr.val == val) {

                // If the node to delete is the head node
                if (curr == head) {

                    // Move head forward
                    head = curr.next;

                    // Detach current node for safety / clarity
                    curr.next = null;

                    // Reset curr to new head
                    curr = head;

                    // Continue so the new current node is checked again
                    continue;
                }

                // If the node is NOT head (middle or tail node)
                if (prev != null) {

                    // Skip current node by linking prev to curr.next
                    prev.next = curr.next;

                    // Detach current node
                    curr.next = null;

                    // Move curr forward without changing prev
                    curr = prev.next;
                }

            } else {
                // Case 2: Current node is valid, keep it

                // Move prev forward
                prev = curr;

                // Move curr forward
                curr = curr.next;
            }
        }

        // head may have changed, return updated head
        return head;
    }
}

