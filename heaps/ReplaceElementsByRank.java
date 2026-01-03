package heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Replace Elements by Their Rank
 *
 * Each element is replaced by its rank in the sorted unique array.
 * Duplicate values receive the same rank.
 */
class ReplaceElementsByRank {

    /**
     * Replaces each element in the list with its rank.
     *
     * @param numbers input list of integers
     * @return list of ranks corresponding to original elements
     *
     * Time Complexity: O(N log N)
     *  - All elements are inserted into a min heap
     *  - Each heap operation costs log N
     *
     * Space Complexity: O(N)
     *  - Heap + HashMap to store ranks
     */
    public List<Integer> replaceWithRank(List<Integer> numbers) {

        // Min heap to process elements in sorted order
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int value : numbers) {
            minHeap.offer(value);
        }

        // Map to store value -> rank
        Map<Integer, Integer> valueToRankMap = new HashMap<>();
        int currentRank = 0;

        // Assign ranks in increasing order
        while (!minHeap.isEmpty()) {
            int value = minHeap.poll();

            if (!valueToRankMap.containsKey(value)) {
                currentRank++;
                valueToRankMap.put(value, currentRank);
            }
        }

        // Build result using original order
        List<Integer> result = new ArrayList<>();
        for (int value : numbers) {
            result.add(valueToRankMap.get(value));
        }

        return result;
    }

    /**
     * Replaces each element with its rank in the sorted unique array.
     *
     * @param numbers input list of integers
     * @return list of ranks in original order
     *
     * Time Complexity: O(N log N)
     *  - Sorting dominates
     *
     * Space Complexity: O(N)
     *  - Copy array + HashMap
     */
    public List<Integer> replaceWithRankWithSortedArray(List<Integer> numbers) {

        int n = numbers.size();

        // Step 1: Copy elements to an array
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = numbers.get(i);
        }

        // Step 2: Sort the array
        Arrays.sort(sorted);

        // Step 3: Assign ranks to unique elements
        Map<Integer, Integer> valueToRank = new HashMap<>();
        int rank = 1;

        for (int i = 0; i < n; i++) {
            if (!valueToRank.containsKey(sorted[i])) {
                valueToRank.put(sorted[i], rank++);
            }
        }

        // Step 4: Build result using original order
        List<Integer> result = new ArrayList<>(n);
        for (int value : numbers) {
            result.add(valueToRank.get(value));
        }

        return result;
    }
}

