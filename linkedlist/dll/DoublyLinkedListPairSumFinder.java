package dll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

    public class DoublyLinkedListPairSumFinder {
    /**
     * Finds all pairs of values in a doubly linked list whose sum equals the target.
     * Each pair is stored as [smaller, larger] and the final result is sorted
     * lexicographically.
     */
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        //findPairsWithGivenSumUsingHashing(target, head, result);
        //findPairsWithGivenSumWithoutUsingExtraSpace(target, head, result);
        findPairsWithGivenSumUsingTwoPointers(target, head, result);
        return result;
    }

    /**
     * Uses two pointers (head and tail) moving towards each other to
     * find all pairs whose sum equals the target.
     * Assumes the doubly linked list is sorted in non-decreasing order.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    private static void findPairsWithGivenSumUsingTwoPointers(int target,
                                                              Node head,
                                                              ArrayList<ArrayList<Integer>> result) {

        // If the list is empty or has only one node, no pairs can exist
        if (head == null || head.next == null) {
            return;
        }

        // Left pointer starts from the head (smallest value)
        Node leftPointer = head;

        // Right pointer starts from the tail (largest value)
        Node rightPointer = head;
        while (rightPointer.next != null) {
            rightPointer = rightPointer.next;
        }

        // Move both pointers towards each other
        while (leftPointer != null &&
               rightPointer != null &&
               leftPointer != rightPointer &&
               rightPointer.next != leftPointer) {

            int sum = leftPointer.data + rightPointer.data;

            if (sum == target) {
                // Found a pair whose sum equals target
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(leftPointer.data);   // smaller or equal
                pair.add(rightPointer.data);  // larger or equal
                result.add(pair);

                // Move both pointers inward to find more pairs
                leftPointer = leftPointer.next;
                rightPointer = rightPointer.prev;

            } else if (sum < target) {
                // Need a larger sum -> move left pointer towards larger values
                leftPointer = leftPointer.next;

            } else { // sum > target
                // Need a smaller sum -> move right pointer towards smaller values
                rightPointer = rightPointer.prev;
            }
        }
    }

    /**
     * Brute-force (with pruning) approach using two nested loops.
     * Assumes the doubly linked list is sorted in non-decreasing order.
     * Time Complexity: O(n^2) in worst case so it is failing for last 10 test cases
     * Space Complexity: O(1)
     */
    private static void findPairsWithGivenSumWithoutUsingExtraSpace(int target,
                                                                    Node head,
                                                                    ArrayList<ArrayList<Integer>> result) {

        // If the list is empty, there are no pairs to find
        if (head == null) {
            return;
        }

        // Outer pointer to pick the first element of the pair
        Node firstPointer = head;

        // Traverse the list with the first pointer
        while (firstPointer != null) {
            int value = firstPointer.data;

            // Since list is sorted, if current value already exceeds target
            // then no further pairs are possible starting from this or later nodes
            if (value > target) {
                break;
            }

            int complement = target - value;

            // Inner pointer to search for the complement on the right side
            Node secondPointer = firstPointer.next;

            while (secondPointer != null) {

                // If complement is found, we record the pair
                if (secondPointer.data == complement) {
                    ArrayList<Integer> pair = new ArrayList<>();
                    pair.add(value);      // first (smaller or equal)
                    pair.add(complement); // second
                    result.add(pair);
                    break; // avoid duplicate pair for this firstPointer
                }

                // Since the list is sorted, once secondPointer.data > complement,
                // no further nodes can form a valid pair with 'value'
                if (secondPointer.data > complement) {
                    break;
                }

                // Move inner pointer forward
                secondPointer = secondPointer.next;
            }

            // Move outer pointer forward to try next base value
            firstPointer = firstPointer.next;
        }
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