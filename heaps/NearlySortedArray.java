package heaps;

import java.util.PriorityQueue;

/**
 * GeeksforGeeks - Nearly Sorted Array
 *
 * An array is nearly sorted if every element is at most
 * K positions away from its target position.
 *
 * This solution uses a Min Heap of size (K + 1).
 */
class NearlySortedArray {

    /**
     * Sorts a nearly sorted array in-place.
     *
     * @param nums input array (nearly sorted)
     * @param k    maximum distance of any element from its sorted position
     *
     * Time Complexity: O(N log K)
     * Space Complexity: O(K)
     */
    public void sortNearlySortedArray(int[] nums, int k) {

        // Min Heap to keep the smallest of the next K+1 elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int index = 0;

        // Step 1: Push first K+1 elements into the heap
        for (int i = 0; i <= k && i < nums.length; i++) {
            minHeap.offer(nums[i]);
        }

        // Step 2: Process remaining elements
        for (int i = k + 1; i < nums.length; i++) {
            nums[index++] = minHeap.poll();
            minHeap.offer(nums[i]);
        }

        // Step 3: Empty the heap
        while (!minHeap.isEmpty()) {
            nums[index++] = minHeap.poll();
        }
    }
}
