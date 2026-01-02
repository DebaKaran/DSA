package heaps;

import java.util.PriorityQueue;

/**
 * Finds the Kth largest element in an unsorted array using a Max Heap.
 *
 * LeetCode: 215. Kth Largest Element in an Array
 */
class KthLargestElementInArray {

    
    /**
     * Returns the Kth largest element in the array.
     *
     * @param nums Input array
     * @param k    Position of the largest element to find (1-based)
     * @return Kth largest element
     *
     * Time Complexity: O(N log N)
     * Building heap: O(N log N) and Removing k-1 elements: O(k log N)
     * Space Complexity: O(N)
     */
    public int findKthLargest(int[] nums, int k) {
        //return findKthLargestUsingSorting(nums, k);

        return findKthLargestUsingMinHeap(nums, k);
    }


    /**
     * Uses a Min Heap of size K.
     *
     * Idea:
     * - Keep only the K largest elements in the heap.
     * - The smallest element in the heap (top) is the Kth largest overall.
     * 
     * Each insertion/removal: log K and Total elements processed: N 
     * so it is: O(N log K) — optimal for this problem
     * 
     * Time Complexity: O(N log K)
     * Space Complexity: O(K)
     */
    private int findKthLargestUsingMinHeap(int[] nums, int k) {

        // Min Heap: smallest element stays at the top
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int value : nums) {
            minHeap.offer(value);

            // Ensure heap size never exceeds K
            if (minHeap.size() > k) {
                minHeap.poll(); // remove smallest
            }
        }

        // Top of heap is the Kth largest element
        return minHeap.peek();
    }


    /**
     * Time Complexity: O(N log N)
     * Building heap: O(N log N) and Removing k-1 elements: O(k log N)
     * Space Complexity: O(N)
    */

    private int findKthLargestUsingSorting(int[] nums, int k) {
        // Max Heap: largest element at the top
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>((a, b) -> Integer.compare(b, a));
                // or: new PriorityQueue<>(Comparator.reverseOrder());

        // Insert all elements into the heap
        for (int num : nums) {
            maxHeap.offer(num);
        }

        // Remove the largest element (k - 1) times
        int removals = k - 1;
        while (removals > 0) {
            maxHeap.poll();
            removals--;
        }

        // The top element is now the Kth largest
        return maxHeap.peek();
    }
}
