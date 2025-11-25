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
class LinkedListDeleteNodeWithoutHead {

    public void deleteNode(ListNode node) {

        // prev will track the node just before curr
        ListNode prev = null;

        // curr starts at the node we want to "delete"
        // but since we don't have the previous pointer,
        // we shift values left until the tail.
        ListNode curr = node;

        // Move through the list until curr reaches the last node.
        // At each step:
        // - Copy value of next node into current node
        // - Move forward
        while (curr.next != null) {
            prev = curr;

            // Copy next node's value into current node
            curr.val = curr.next.val;

            // Move to next node
            curr = curr.next;
        }

        // Now curr is at the LAST node.
        // prev is the second last node.
        // Remove the tail by disconnecting it.
        prev.next = null;   // tail removed

        // Node deletion complete (effectively removed 'node')
    }
}
