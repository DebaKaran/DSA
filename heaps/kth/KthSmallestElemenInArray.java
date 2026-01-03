package heaps.kth;

import java.util.PriorityQueue;
/**
 * Finds the Kth largest element in an array.
 *
 * Uses a Min Heap of fixed size K.
 * The heap always stores the K largest elements seen so far.
 */
class KthSmallestElemenInArray {

    /**
     * Returns the Kth largest element in the array.
     *
     * @param nums input array
     * @param k    position of the largest element to find (1-based)
     * @return kth largest element
     *
     * Time Complexity: O(N log K)
     *  - Each element is inserted into the heap
     *  - Heap size never exceeds K
     *
     * Space Complexity: O(K)
     *  - Min heap stores at most K elements
     */
    public int findKthLargest(int[] nums, int k) {

        // Min Heap: smallest element at the top
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int value : nums) {
            minHeap.offer(value);

            // Keep only the K largest elements
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Root of min heap is the Kth largest element
        return minHeap.peek();
    }
}

