/**
 * LeetCode Problem 328: Odd Even Linked List
 * https://leetcode.com/problems/odd-even-linked-list/
 */
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        // return oddEvenListWithExtraSpace(head);
         // return oddEvenListWithExtraSpace2(head);
        return oddEvenListWithOutExtraSpace(head);
    }

    // Approach: In-Place Reordering Without Extra Space
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    
    private ListNode oddEvenListWithOutExtraSpace(ListNode head) {
    // If the list is empty or has only one node,
    // odd-even reordering is not needed
    if (head == null || head.next == null) {
        return head;
    }

    // odd pointer starts at the first node (position 1)
    ListNode odd = head;

    // even pointer starts at the second node (position 2)
    ListNode even = head.next;

    // Keep the head of even nodes so we can attach it after odd list at the end
    ListNode evenHead = head.next;

    // Re-link nodes such that:
    // - odd list: 1 -> 3 -> 5 -> ...
    // - even list: 2 -> 4 -> 6 -> ...
    while (even != null && even.next != null) {

        // Link current odd node to the next odd node
        // (skip the current even node)
        odd.next = odd.next.next;

        // Link current even node to the next even node
        // (skip the odd node we just linked)
        even.next = even.next.next;

        // Move odd pointer forward to the next odd node
        odd = odd.next;

        // Move even pointer forward to the next even node
        even = even.next;
    }

    // After separating odds and evens,
    // attach the even list after the last odd node
    odd.next = evenHead;

    // The head of the list remains unchanged
    return head;
}

    // Approach: Using Extra Space (Alternative Implementation)
    // Time Complexity: O(N)    
    // Space Complexity: O(N)
    private ListNode oddEvenListWithExtraSpace2(ListNode head) {

        // If the list is empty or has only one node,
        // odd-even rearrangement is unnecessary
        if (head == null || head.next == null) {
            return head;
        }

        // Dummy node to build the new reordered list
        ListNode newHead = new ListNode();

        // Temporary pointer to keep track of the tail of the new list
        ListNode newHeadTemp = newHead;

        // Pointer to traverse the original list
        ListNode curr = head;

        // -----------------------------
        // First pass: add odd-positioned nodes
        // (1-based odd positions: 1st, 3rd, 5th, ...)
        // -----------------------------
        while (curr != null) {

            // Create a new node with the current node's value
            ListNode nd = new ListNode(curr.val);

            // Append the new node to the result list
            newHeadTemp.next = nd;
            newHeadTemp = nd;

            // Move curr two steps ahead to reach the next odd-positioned node
            curr = curr.next == null ? null : curr.next.next;
        }

        // Start from the second node for even-positioned nodes
        curr = head.next;

        // -----------------------------
        // Second pass: add even-positioned nodes
        // (1-based even positions: 2nd, 4th, 6th, ...)
        // -----------------------------
        while (curr != null) {

            // Create a new node with the current node's value
            ListNode nd = new ListNode(curr.val);

            // Append the new node to the result list
            newHeadTemp.next = nd;
            newHeadTemp = nd;

            // Move curr two steps ahead to reach the next even-positioned node
            curr = curr.next == null ? null : curr.next.next;
        }

        // Return the head of the new reordered list (skip dummy node)
        return newHead.next;
    }

    // Approach: Using Extra Space
    // Time Complexity: O(N)
    // Space Complexity: O(N)
    
    private ListNode oddEvenListWithExtraSpace(ListNode head) {

        // If list is empty or has only one node,
        // odd-even reordering is not required
        if (head == null || head.next == null) {
            return head;
        }

        // Pointer to traverse original list
        ListNode curr = head;

        // Index tracker (0-based):
        // i % 2 == 0 → odd position (1-based)
        // i % 2 == 1 → even position (1-based)
        int i = 0;

        // Dummy node for building odd-position list
        ListNode oddDummy = new ListNode();

        // Dummy node for building even-position list
        ListNode evenDummy = new ListNode();

        // Preserve dummy heads for final return
        ListNode oddDummyHead = oddDummy;
        ListNode evenDummyHead = evenDummy;

        // Traverse the original list
        while (curr != null) {

            // If index is even → treat as odd-position node
            if (i % 2 == 0) {
                oddDummy.next = new ListNode(curr.val); // copy value
                oddDummy = oddDummy.next;               // move odd tail
            } 
            // Else → even-position node
            else {
                evenDummy.next = new ListNode(curr.val); // copy value
                evenDummy = evenDummy.next;               // move even tail
            }

            // Move to next node and increment index
            i++;
            curr = curr.next;
        }

        // Attach even list after odd list
        oddDummy.next = evenDummyHead.next;

        // Return head of reordered list (skip dummy)
        return oddDummyHead.next;
    }
}
