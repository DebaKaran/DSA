import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode Problem: 141. Linked List Cycle
 * https://leetcode.com/problems/linked-list-cycle/
 */

public class LinkedListCycleDetector {
    // Entry method to check if a linked list has a cycle
    public boolean hasCycle(ListNode head) {
        //return detectCycleUsingHashSet(head);

        return detectCycleUsingTwoPointers(head);
    }

    // Detects cycle in a linked list using Floyd’s Two Pointer algorithm
    // Approach: Use slow and fast pointers to detect a cycle
    // Time Complexity: O(N), Space Complexity: O(1)
    
    private boolean detectCycleUsingTwoPointers(ListNode head) {

        // If the list is empty or has only one node, it cannot have a cycle
        if (head == null || head.next == null) {
            return false;
        }

        // Slow pointer moves one step at a time
        ListNode slowPointer = head;

        // Fast pointer moves two steps at a time
        ListNode fastPointer = head.next;

        // Traverse until fast pointer reaches the end
        while (fastPointer != null && fastPointer.next != null) {

            // If slow and fast pointers meet, a cycle exists
            if (slowPointer == fastPointer) {
                return true;
            }

            // Move slow pointer by one step
            slowPointer = slowPointer.next;

            // Move fast pointer by two steps
            fastPointer = fastPointer.next.next;
        }

        // Fast pointer reached the end -> no cycle
        return false;
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
