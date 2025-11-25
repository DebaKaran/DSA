/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/**
 * LeetCode Problem Statement: https://leetcode.com/problems/delete-node-in-a-linked-list/
 * Time Complexity: O(N) where N is the number of nodes from the given node to
 * the end of the linked list.
 * Space Complexity: O(1) as we are using constant space.
 * 
 */
public class LinkedListDeleteNodeWithoutHead {

    public void deleteNode(ListNode node) {

        // Best approach (O(1)):
        // Deletes the node by copying the next node's value
        // and skipping the next node.
        deleteNodeUsingNextCopy(node);

        // Alternative approach (O(n)):
        // Shifts values forward until reaching the tail.
        // deleteNodeByShiftingValues(node);
    }

    /**
     * O(1) deletion by copying only the next node's value.
     * This is the official LeetCode 237 approach.
     *
     * Logic:
     * - Copy value from node.next into current node
     * - Bypass node.next so the "next node" is effectively deleted
     *
     * Constraints:
     * - LeetCode guarantees node is NOT the tail, so node.next != null
     */
    private void deleteNodeUsingNextCopy(ListNode node) {
        // Copy next node's value into current node
        node.val = node.next.val;

        // Remove next node by skipping it
        node.next = node.next.next;
    }

    /**
     * O(n) deletion by shifting values from all following nodes.
     * This works but is less efficient than the O(1) version.
     *
     * Logic:
     * - Copy next node's value into current node
     * - Move to next node
     * - Repeat until reaching the tail
     * - Remove the final duplicate tail node
     */
    private void deleteNodeByShiftingValues(ListNode node) {

        // prev holds the node before curr
        ListNode prev = null;

        // curr starts at the node we want to "delete"
        ListNode curr = node;

        // Iterate until curr reaches the last node
        while (curr.next != null) {
            prev = curr;

            // Shift next node's value into curr
            curr.val = curr.next.val;

            // Move forward in list
            curr = curr.next;
        }

        // Now 'curr' is the last node. Remove it.
        prev.next = null;
    }
}

