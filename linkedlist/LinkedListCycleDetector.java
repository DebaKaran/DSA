import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode Problem: 141. Linked List Cycle
 * https://leetcode.com/problems/linked-list-cycle/
 */

public class LinkedListCycleDetector {
    // Entry method to check if a linked list has a cycle
    public boolean hasCycle(ListNode head) {
        return detectCycleUsingHashSet(head);
    }

    // Detects cycle using a HashSet to track visited nodes
    // Approach: Traverse the list and store visited nodes in a set
    // Time Complexity: O(N), Space Complexity: O(N)
    
    private boolean detectCycleUsingHashSet(ListNode head) {

        // If the list is empty or has only one node, it cannot have a cycle
        if (head == null || head.next == null) {
            return false;
        }

        // Stores references of nodes already visited
        Set<ListNode> visitedNodes = new HashSet<>();

        // Pointer used to traverse the linked list
        ListNode currentNode = head;

        // Traverse the list until the end
        while (currentNode != null) {

            // If current node is already visited, a cycle exists
            if (visitedNodes.contains(currentNode)) {
                return true;
            }

            // Mark the current node as visited
            visitedNodes.add(currentNode);

            // Move to the next node
            currentNode = currentNode.next;
        }

        // Reached the end without revisiting any node -> no cycle
        return false;
    }
}
