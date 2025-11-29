/**
 * LeetCode Problem 328: Odd Even Linked List
 * https://leetcode.com/problems/odd-even-linked-list/
 */
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        return oddEvenListWithExtraSpace(head);
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
