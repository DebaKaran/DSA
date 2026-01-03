package heaps.kth;

import java.util.PriorityQueue;

/**
 * LeetCode 378 - Kth Smallest Element in a Sorted Matrix
 *
 * This solution uses a Max Heap of fixed size K to keep track
 * of the K smallest elements seen so far.
 */
class KthSmallestElementInSortedMatrix {

    /**
     * Returns the Kth smallest element in a sorted matrix.
     *
     * @param matrix sorted matrix (row-wise and column-wise)
     * @param k      position of the smallest element to find (1-based)
     * @return kth smallest element
     *
     * Time Complexity: O(M * N * log K)
     *  - Each of the M*N elements is pushed into the heap
     *  - Heap size is capped at K
     *
     * Space Complexity: O(K)
     *  - Max heap stores at most K elements
     */
    public int findKthSmallest(int[][] matrix, int k) {

        // Max Heap: largest element stays at the top
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        // Process every element in the matrix
        for (int[] row : matrix) {
            for (int value : row) {
                maxHeap.offer(value);

                // Keep only the K smallest elements in the heap
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }

        // Root of max heap is the Kth smallest element
        return maxHeap.peek();
    }
}

