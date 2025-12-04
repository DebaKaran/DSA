/**
 * LeetCode Problem 25: Reverse Nodes in k-Group
 * Link: https://leetcode.com/problems/reverse-nodes-in-k-group/
 * 
 */
public class ReverseNodesInKGroup {

    /**
     * Reverses nodes of a linked list in groups of size k.
     * If the number of nodes is not a multiple of k, the remaining nodes at the end are left as-is.
     *
     * @param head head of the linked list
     * @param k    group size
     * @return new head after reversing in k-sized groups
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        return reverseInKGroups(head, k);
    }

    /**
     * Iterative helper that processes the list group by group.
     * Time Complexity: O(n), where n is the number of nodes in the list.
     * Space Complexity: O(1) as no additional space is used.
     * 
     */
    private ListNode reverseInKGroups(ListNode head, int k) {

        // Edge cases: empty list, single node, or invalid k
        if (head == null || head.next == null || k <= 1) {
            return head;
        }

        // groupStart points to the first node of the current group to be reversed
        ListNode groupStart = head;

        // groupPrev points to the tail of the already processed (reversed) part
        // initially null because we have not processed any group yet
        ListNode groupPrev = null;

        // Will hold head of the next group
        ListNode nextGroupHead;

        // Traverse the list group by group
        while (groupStart != null) {

            // Find the k-th node from groupStart to determine if a full group exists
            ListNode groupEnd = getKthNode(groupStart, k);

            // If fewer than k nodes remain, do not reverse this last part
            if (groupEnd == null) {
                if (groupPrev != null) {
                    groupPrev.next = groupStart; // attach remaining nodes as-is
                }
                break; // no more groups to process
            }

            // Next group will start after groupEnd
            nextGroupHead = groupEnd.next;

            // Temporarily terminate current group so reverse operates only on this segment
            groupEnd.next = null;

            // Reverse current k-sized group: [groupStart ... groupEnd]
            ListNode reversedHead = reverseList(groupStart);

            // If this is the first group, update head of the whole list
            if (groupStart == head) {
                head = reversedHead;
            } else if (groupPrev != null) {
                // Otherwise, connect previous group's tail to the new head of this group
                groupPrev.next = reversedHead;
            }

            // After reversal:
            // groupStart has become the tail of the reversed group
            groupPrev = groupStart;

            // Move to the next group
            groupStart = nextGroupHead;
        }

        return head;
    }

    /**
     * Returns the k-th node starting from 'start' (inclusive).
     * If there are fewer than k nodes from start to the end, returns null.
     */
    private ListNode getKthNode(ListNode start, int k) {
        int count = 1;
        ListNode current = start;

        while (current != null && count < k) {
            current = current.next;
            count++;
        }

        return current; // may be null if fewer than k nodes
    }

    /**
     * Reverses a singly linked list and returns the new head.
     */
    private ListNode reverseList(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        ListNode current = node;
        ListNode previous = null;

        // Standard iterative reverse of a singly linked list
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }

        return previous; // new head after reversal
    }
}
