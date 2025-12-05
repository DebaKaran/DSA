import java.util.PriorityQueue;

/**
 * Merge K sorted linked lists by repeatedly merging lists pairwise (sequentially).
 * This implementation reuses nodes (no new node allocations during merge).
 * 
 * Leetcode problem: https://leetcode.com/problems/merge-k-sorted-lists/
 * Time complexity: O(N*K) where N is average length of each list and K is number of lists.
 * Space complexity: O(1) additional space (in-place merging).
 */
public class MergeKSortedListsSequential {

    /**
     * Public API: merge an array of sorted linked lists and return the head of the merged list.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        //return mergeKListsSequential(lists);
        return mergeKListsWithMinHeap(lists);
    }

     /**
     * Merge k sorted lists using a min-heap that stores the current head nodes.
     * Time: O(N log k), Space: O(k) for the heap (k = number of lists, N = total nodes).
     */
    private ListNode mergeKListsWithMinHeap(ListNode[] lists) {
        // Handle empty input array
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Min-heap keyed by node value; stores (value, node) pairs
        PriorityQueue<NodeEntry> minHeap = new PriorityQueue<>();

        // Initialize heap with the head of each non-empty list
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.add(new NodeEntry(node.val, node));
            }
        }

        // Dummy head simplifies result list construction
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        // Extract the smallest node and push its successor (if any)
        while (!minHeap.isEmpty()) {
            NodeEntry entry = minHeap.remove();
            ListNode smallest = entry.node;

            // Append the smallest node to the result list (reusing node)
            tail.next = smallest;
            tail = smallest;

            // If the extracted node has a next, push it to the heap
            if (smallest.next != null) {
                minHeap.add(new NodeEntry(smallest.next.val, smallest.next));
            }
        }

        // Ensure the new tail points to null (safety in case original nodes linked elsewhere)
        tail.next = null;

        return dummy.next;
    }

    /**
     * Simple helper pair that allows PriorityQueue to order entries by node value.
     */
    private static class NodeEntry implements Comparable<NodeEntry> {
        final int val;
        final ListNode node;

        NodeEntry(int val, ListNode node) {
            this.val = val;
            this.node = node;
        }

        @Override
        public int compareTo(NodeEntry other) {
            return Integer.compare(this.val, other.val);
        }
    }
    /**
     * Sequentially merge lists: merge lists[0] with lists[1], then merge result with lists[2], etc.
     * Works in-place by relinking nodes (no per-node allocation).
     * 
     * Time complexity: O(N*(K ^ 2)), Space complexity: O(1).
     */
    private ListNode mergeKListsSequential(ListNode[] lists) {
        // Handle empty input
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Start with the first list and merge subsequent lists into it
        ListNode mergedHead = lists[0];
        for (int i = 1; i < lists.length; i++) {
            mergedHead = mergeTwoSortedListsByReusingNodes(mergedHead, lists[i]);
        }
        return mergedHead;
    }

    /**
     * Merge two sorted linked lists by re-linking nodes (no allocations).
     * Returns head of merged list.
     */
    private ListNode mergeTwoSortedListsByReusingNodes(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // dummy head simplifies edge cases
        ListNode tail = dummy;

        ListNode p1 = l1;
        ListNode p2 = l2;

        // Merge by always attaching the smaller of p1 and p2
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

        // Attach any remaining nodes from the non-empty list
        tail.next = (p1 != null) ? p1 : p2;

        return dummy.next;
    }
}
