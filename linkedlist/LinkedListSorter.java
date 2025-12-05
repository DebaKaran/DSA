import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Leetcode #148: Sort List
 * 
 * Sort a singly linked list by extracting values to a list, sorting them,
 * and writing them back into the nodes.
 *
 * This implementation intentionally uses extra space (ArrayList).
 */
public class LinkedListSorter {

    /**
     * Public entry that sorts the list using an extra-space approach.
     */
    public ListNode sortList(ListNode head) {
        //return sortListUsingArray(head);
        return mergeSort(head);
    }

    /**
     * Top-down merge sort: split the list into two halves, sort each half,
     * then merge the sorted halves by re-linking existing nodes.
     * Time Complexity: O(n log n)
     * Space Complexity: O(log n) due to recursion stack
     */
    private ListNode mergeSort(ListNode head) {
        // Base case: zero or one node is already sorted
        if (head == null || head.next == null) {
            return head;
        }

        // 1) Find the midpoint (left-middle) so we split into two roughly equal halves
        ListNode midPrev = getLeftMiddle(head);   // returns node before right-half head
        ListNode rightHead = midPrev.next;        // start of right half

        // 2) Cut the list into two halves
        midPrev.next = null;

        // 3) Recursively sort both halves
        ListNode leftSorted = mergeSort(head);
        ListNode rightSorted = mergeSort(rightHead);

        // 4) Merge the two sorted halves by reusing nodes
        return mergeByReusingNodes(leftSorted, rightSorted);
    }

    /**
     * Merge two sorted lists by relinking nodes (no new allocations).
     * Returns head of merged list.
     */
    private ListNode mergeByReusingNodes(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // dummy head simplifies edge cases
        ListNode tail = dummy;

        ListNode p1 = l1;
        ListNode p2 = l2;

        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                tail.next = p1;
                tail = p1;
                p1 = p1.next;
            } else {
                tail.next = p2;
                tail = p2;
                p2 = p2.next;
            }
        }

        // Attach the remaining nodes (one of p1 or p2 may be non-null)
        tail.next = (p1 != null) ? p1 : p2;

        return dummy.next;
    }

    /**
     * Find the node that will act as the end of the left half (left-middle).
     * With fast initialized to head.next, slow ends up at left-middle for even lengths.
     * We return that node so the split point is midPrev.next -> start of right half.
     */
    private ListNode getLeftMiddle(ListNode head) {
        // For lists of size >= 2, this returns the node just before the right half
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * Extract node values into an ArrayList, sort the list of values,
     * and then overwrite the nodes' values in order.
     *
     * This keeps node identities the same (no relinking), but uses O(n) extra space.
     * Time Complexity: O(n log n) due to sorting.
     * Space Complexity: O(n) for the ArrayList.
     * 
     */
    private ListNode sortListUsingArray(ListNode head) {

        // Trivial cases: empty or single node -> already sorted
        if (head == null || head.next == null) {
            return head;
        }

        // 1) Extract values into a list
        List<Integer> values = new ArrayList<>();
        ListNode cursor = head;
        while (cursor != null) {
            values.add(cursor.val);
            cursor = cursor.next;
        }

        // 2) Sort the extracted values
        Collections.sort(values);

        // 3) Write sorted values back into the original nodes
        int i = 0;
        cursor = head;
        while (cursor != null) {
            cursor.val = values.get(i++);
            cursor = cursor.next;
        }

        return head;
    }
}
