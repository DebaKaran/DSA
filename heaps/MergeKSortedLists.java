package heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LeetCode 23 - Merge k Sorted Lists
 *
 * This implementation collects all nodes into a Min Heap
 * after safely detaching their original next pointers,
 * then rebuilds a single sorted list.
 */
class MergeKSortedLists {

    
    public ListNode mergeKLists(ListNode[] lists) {

        return mergeKSortedListsUsingMinHeapAllNodes(lists);
        
    }

    /**
     * Merges k sorted linked lists into one sorted list.
     *
     * @param lists array of sorted linked list heads
     * @return merged sorted linked list
     *
     * Time Complexity: O(N log N)
     * Heap insertions: N log N 
     * Heap removals: N log N
     * (where N = total number of nodes)
     * 
     * Space Complexity: O(N)
     */

    private ListNode mergeKSortedListsUsingMinHeapAllNodes(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Min Heap ordered by node value
        PriorityQueue<ListNode> minHeap =
                new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        // Push all nodes into the heap after detaching next pointers
        for (ListNode head : lists) {
            ListNode current = head;

            while (current != null) {
                ListNode nextNode = current.next; // store next
                current.next = null;              // detach to avoid cycles
                minHeap.offer(current);
                current = nextNode;
            }
        }

        // Build the merged list from heap
        ListNode mergedHead = minHeap.poll();
        ListNode tail = mergedHead;

        while (!minHeap.isEmpty()) {
            ListNode smallestNode = minHeap.poll();
            tail.next = smallestNode;
            tail = smallestNode;
        }

        return mergedHead;
    }

    /**
    * Approach 1: Brute-Force Value Collection and Sorting
    *
    * Brute-force approach:
    * 1. Collect all node values from all lists.
    * 2. Sort the values.
    * 3. Rewrite values back into the existing list nodes in sorted order.
    *
    * Note:
    * - This approach mutates the input lists.
    * - Node structure is preserved; only values are reordered.
    */
    /**
     * Merges k sorted linked lists using brute-force value collection and sorting.
     *
     * @param lists array of sorted linked list heads
     * @return merged sorted linked list
     *
     * Time Complexity: O(N log N)
     *  - Collecting values: O(N)
     *  - Sorting values: O(N log N)
     *  - Rewriting values: O(N)
     *
     * Space Complexity: O(N)
     *  - Stores all node values in an auxiliary list
     */
    private ListNode mergeKSortedListsUsingBruteForce(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }

        // Step 1: Collect all node values
        List<Integer> allValues = new ArrayList<>();
        for (ListNode head : lists) {
            ListNode current = head;
            while (current != null) {
                allValues.add(current.val);
                current = current.next;
            }
        }

        // Step 2: Sort collected values
        Collections.sort(allValues);

        // Step 3: Rewrite sorted values back into the lists
        int valueIndex = 0;

        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;

        for (ListNode head : lists) {
            ListNode current = head;
            tail.next = current;

            while (current != null) {
                current.val = allValues.get(valueIndex++);
                tail = current;
                current = current.next;
            }
        }

        return dummyHead.next;
    }
}
