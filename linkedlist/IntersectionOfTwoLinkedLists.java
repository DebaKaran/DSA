import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode Problem: 160. Intersection of Two Linked Lists
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 */
public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        return findIntersectionUsingHashSet(headA, headB);
    }

    // Helper method to find intersection using a hash set
    // Time Complexity: O(N + M), Space Complexity: O(N)
    
    private ListNode findIntersectionUsingHashSet(ListNode headA, ListNode headB) {

        // If either list is empty, intersection is impossible
        if (headA == null || headB == null) {
            return null;
        }

        // Store all nodes of first list in a hash set
        Set<ListNode> visitedNodes = new HashSet<>();
        ListNode currentA = headA;

        while (currentA != null) {
            visitedNodes.add(currentA);
            currentA = currentA.next;
        }

        // Traverse second list and check for shared node
        ListNode currentB = headB;
        while (currentB != null) {
            if (visitedNodes.contains(currentB)) {
                return currentB;
            }
            currentB = currentB.next;
        }

        // No intersection found
        return null;
    }
}
