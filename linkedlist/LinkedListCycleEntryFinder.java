/**
 * LeetCode Problem 142: Linked List Cycle II
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * 
 */
public class LinkedListCycleEntryFinder {
    // Returns the node where the cycle begins, or null if no cycle exists
    public ListNode findCycleEntry(ListNode head) {
        return detectCycleEntryUsingHashSet(head);
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
