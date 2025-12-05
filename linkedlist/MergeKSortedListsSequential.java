
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
        return mergeKListsSequential(lists);
    }

    /**
     * Sequentially merge lists: merge lists[0] with lists[1], then merge result with lists[2], etc.
     * Works in-place by relinking nodes (no per-node allocation).
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
