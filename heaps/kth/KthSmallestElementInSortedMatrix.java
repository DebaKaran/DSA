package heaps.kth;

import java.util.PriorityQueue;

/**
 * LeetCode 378 - Kth Smallest Element in a Sorted Matrix
 *
 * Contains multiple approaches for learning and comparison.
 */
class KthSmallestElementInSortedMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        // return findKthSmallestUsingMaxHeap(matrix, k);   // Baseline
        //return findKthSmallestUsingMinHeap(matrix, k);     // Optimal

       return kthSmallestUsingBinarySearch(matrix, k);
    }

    // Approach 3 (Optimal):
    // Uses Binary Search on the value range.
    // Time Complexity: O(N log (max - min))
    // Space Complexity: O(1)
    
    private int kthSmallestUsingBinarySearch(int[][] matrix, int k) {
        int n = matrix.length;

        int low = matrix[0][0];
        int high = matrix[n - 1][n - 1];

        while (low < high) {
            int mid = low + (high - low) / 2;

            // Count how many elements are <= mid
            int count = countLessThanOrEqual(matrix, mid);

            if (count < k) {
                // Kth smallest is larger than mid
                low = mid + 1;
            } else {
                // Kth smallest is <= mid
                high = mid;
            }
        }

        return low;
    }

    /**
     * Counts elements <= target in O(N) time.
     * Uses the sorted row & column property.
     */
    private int countLessThanOrEqual(int[][] matrix, int target) {
        int n = matrix.length;
        int row = n - 1;
        int col = 0;
        int count = 0;

        // Start from bottom-left corner
        while (row >= 0 && col < n) {
            if (matrix[row][col] <= target) {
                // All elements above this are also <= target
                count += row + 1;
                col++;
            } else {
                row--;
            }
        }

        return count;
    }

    /**
     * Approach 1 (Baseline):
     * Uses a fixed-size Max Heap to keep track of the K smallest elements.
     *
     * Time Complexity: O(M * N * log K)
     * Space Complexity: O(K)
     */
    private int findKthSmallestUsingMaxHeap(int[][] matrix, int k) {

        // Max Heap: largest element at the top
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        for (int[] row : matrix) {
            for (int value : row) {
                maxHeap.offer(value);

                // Keep heap size bounded to K
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }

        // Top of max heap is the Kth smallest element
        return maxHeap.peek();
    }

    /**
     * Approach 2 (Optimal):
     * Uses a Min Heap with row pointers (k-way merge).
     *
     * Time Complexity: O(K log N)
     * Space Complexity: O(N)
     *
     * where N = number of rows
     */
    private int findKthSmallestUsingMinHeap(int[][] matrix, int k) {

        // Min Heap storing {value, rowIndex, colIndex}
        PriorityQueue<int[]> minHeap =
                new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Initialize heap with the first element of each row
        for (int row = 0; row < matrix.length; row++) {
            minHeap.offer(new int[] { matrix[row][0], row, 0 });
        }

        int extractedCount = 0;

        // Extract the smallest element K-1 times
        while (extractedCount < k - 1) {
            extractedCount++;

            int[] smallest = minHeap.poll();
            int row = smallest[1];
            int col = smallest[2];

            // Push the next element from the same row, if available
            if (col + 1 < matrix[row].length) {
                minHeap.offer(new int[] {
                        matrix[row][col + 1],
                        row,
                        col + 1
                });
            }
        }

        // The root now contains the Kth smallest element
        return minHeap.peek()[0];
    }
}