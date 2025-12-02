import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode Problem: 160. Intersection of Two Linked Lists
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 */
public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //return findIntersectionUsingHashSet(headA, headB);

        return findIntersectionUsingLength(headA, headB);
    }

    // Helper method to find intersection using lengths
    //Approach: Calculate lengths of both lists, align them, and traverse together
    // Time Complexity: O(N + M), Space Complexity: O(1)

    private ListNode findIntersectionUsingLength(ListNode headA, ListNode headB) {

        // If either list is empty, intersection is impossible
        if (headA == null || headB == null) {
            return null;
        }

        // Step 1: Compute lengths of both lists
        int lengthA = 0;
        ListNode currentA = headA;

        int lengthB = 0;
        ListNode currentB = headB;

        while (currentA != null) {
            lengthA++;
            currentA = currentA.next;
        }

        while (currentB != null) {
            lengthB++;
            currentB = currentB.next;
        }

        // Step 2: Reset pointers to list heads
        currentA = headA;
        currentB = headB;

        // Step 3: Advance the longer list to align both pointers
        while (lengthA > lengthB) {
            currentA = currentA.next;
            lengthA--;
        }

        while (lengthB > lengthA) {
            currentB = currentB.next;
            lengthB--;
        }

        // Step 4: Traverse together to find intersection
        while (currentA != null) {
            if (currentA == currentB) {
                return currentA;
            }
            currentA = currentA.next;
            currentB = currentB.next;
        }

        // No intersection
        return null;
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
