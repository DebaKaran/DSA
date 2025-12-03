import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode Problem 142: Linked List Cycle II
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * 
 */
public class LinkedListCycleEntryFinder {
    // Returns the node where the cycle begins, or null if no cycle exists
    public ListNode findCycleEntry(ListNode head) {
        //return detectCycleEntryUsingHashSet(head);
        return detectCycleEntryUsingTwoPointers(head);
    }

    /**
     * Uses Floyd's fast-slow pointer technique to:
     * 1. Detect if a cycle exists.
     * 2. Find the entry point of the cycle.
     * Time Complexity: O(N), Space Complexity: O(1)
     * Approach: Two Pointers (Floyd's Cycle Detection Algorithm)
     * We use two pointers, one moving twice as fast as the other.
     * If they meet, a cycle exists. To find the entry point, we reset one pointer to the head
     * and move both one step at a time until they meet again.
     * The meeting point is the start of the cycle.
     * 
     */
    private ListNode detectCycleEntryUsingTwoPointers(ListNode head) {

        // If the list is empty, no cycle is possible
        if (head == null) {
            return null;
        }

        ListNode slowPointer = head;
        ListNode fastPointer = head;

        boolean hasCycle = false;

        // Step 1: Detect if a cycle exists
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            if (slowPointer == fastPointer) {
                hasCycle = true;
                break;
            }
        }

        // If no cycle was detected, return null
        if (!hasCycle) {
            return null;
        }

        // Step 2: Find the entry point of the cycle
        slowPointer = head;

        // Move both pointers one step at a time until they meet at the cycle start
        while (slowPointer != fastPointer) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next;
        }

        // Both pointers now point to the cycle entry node
        return slowPointer;
    }

    // Detects the start of the cycle using a HashSet
    // Time Complexity: O(N), Space Complexity: O(N)
    //Approach: HashSet to track visited nodes
    // We traverse the linked list and store each node in a HashSet.
    // If we encounter a node that is already in the HashSet, it indicates the start of the cycle.
    // If we reach the end of the list (null), it means there is no cycle.

    private ListNode detectCycleEntryUsingHashSet(ListNode head) {

        // If the list is empty, no cycle is possible
        if (head == null) {
            return null;
        }

        // Stores references of visited nodes
        Set<ListNode> visitedNodes = new HashSet<>();

        // Pointer used to traverse the linked list
        ListNode currentNode = head;

        // Traverse the list node by node
        while (currentNode != null) {

            // First repeated node is the entry point of the cycle
            if (visitedNodes.contains(currentNode)) {
                return currentNode;
            }

            // Mark the current node as visited
            visitedNodes.add(currentNode);

            // Move to the next node
            currentNode = currentNode.next;
        }

        // No cycle found
        return null;
    }
}
