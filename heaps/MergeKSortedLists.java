package heaps;

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
}
