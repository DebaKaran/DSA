package dll;

import java.util.ArrayList;
import java.util.Collections;

    public class DoublyLinkedListPairSumFinder {
    /**
     * Finds all pairs of values in a doubly linked list whose sum equals the target.
     * Each pair is stored as [smaller, larger] and the final result is sorted
     * lexicographically.
     */
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        findPairsWithGivenSumUsingHashing(target, head, result);
        return result;
    }

    /**
     * Helper method that uses hashing to collect all valid pairs into result.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private static void findPairsWithGivenSumUsingHashing(int target,
                                                          Node head,
                                                          ArrayList<ArrayList<Integer>> result) {

        // If the list is empty, there are no pairs to find
        if (head == null) {
            return;
        }

        // Stores values we have already seen while traversing the list
        Set<Integer> seenValues = new HashSet<>();

        // Pointer to traverse the doubly linked list
        Node currentNode = head;

        // Traverse the list once
        while (currentNode != null) {
            int value = currentNode.data;

            // Complement needed to reach target sum
            int complement = target - value;

            // If we have already seen the complement, we found a valid pair
            if (seenValues.contains(complement)) {

                // Ensure pair is stored in sorted order: [smaller, larger]
                int first = Math.min(complement, value);
                int second = Math.max(complement, value);

                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(first);
                pair.add(second);
                result.add(pair);
            }

            // Mark current value as seen for future complements
            seenValues.add(value);

            // Move to the next node
            currentNode = currentNode.next;
        }

        // Sort the list of pairs lexicographically:
        // first by the first element, then by the second
        Collections.sort(result, (p1, p2) -> {
            if (!p1.get(0).equals(p2.get(0))) {
                return p1.get(0) - p2.get(0);
            }
            return p1.get(1) - p2.get(1);
        });
    }
}