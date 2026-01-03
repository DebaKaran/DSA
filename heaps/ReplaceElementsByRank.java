package heaps;

import java.util.ArrayList;
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
}

