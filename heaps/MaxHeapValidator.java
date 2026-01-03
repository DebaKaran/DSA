package heaps;

/**
 * GeeksforGeeks - Does array represent Heap
 *
 * This solution checks whether the given array represents
 * a valid Max Heap.
 */
class MaxHeapValidator {

    /**
     * Entry method expected by GFG.
     *
     * @param arr input array
     * @param n   number of elements in the array
     * @return true if array represents a Max Heap, otherwise false
     */
    public boolean countSub(long[] arr, long n) {
        return isValidMaxHeap(arr, (int) n);
    }

    /**
     * Checks whether the array satisfies Max Heap property.
     *
     * Max Heap rule:
     * - Every parent node must be greater than or equal to its children.
     *
     * @param heap array representing the heap
     * @param size number of elements
     * @return true if valid Max Heap
     *
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    private boolean isValidMaxHeap(long[] heap, int size) {

        // Only need to check internal nodes (up to size / 2 - 1)
        for (int parentIndex = 0; parentIndex < size / 2; parentIndex++) {

            int leftChildIndex = 2 * parentIndex + 1;
            if (leftChildIndex < size && heap[leftChildIndex] > heap[parentIndex]) {
                return false;
            }

            int rightChildIndex = 2 * parentIndex + 2;
            if (rightChildIndex < size && heap[rightChildIndex] > heap[parentIndex]) {
                return false;
            }
        }

        return true;
    }
}
