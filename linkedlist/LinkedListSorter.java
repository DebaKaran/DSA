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
        return sortListUsingArray(head);
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
