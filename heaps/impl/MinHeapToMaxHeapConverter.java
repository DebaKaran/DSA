package heaps.impl;

/**
 * GeeksforGeeks - Convert Min Heap to Max Heap
 *
 * This class converts a given Min Heap into a Max Heap
 * in-place using bottom-up heapification.
 */
class MinHeapToMaxHeapConverter {

    /**
     * Converts a Min Heap array into a Max Heap array.
     *
     * @param size number of elements in the heap
     * @param heap array representing the min heap
     *
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    static void convertMinToMaxHeap(int size, int[] heap) {

        // Start heapification from the last non-leaf node
        for (int index = (size / 2) - 1; index >= 0; index--) {
            heapifyDown(heap, index, size);
        }
    }

    /**
     * Restores Max Heap property starting from a given index.
     *
     * @param heap  array representation of heap
     * @param index current node index
     * @param size  heap size
     */
    private static void heapifyDown(int[] heap, int index, int size) {

        while (2 * index + 1 < size) {

            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int largerChildIndex = leftChildIndex;

            // Choose the larger child
            if (rightChildIndex < size
                    && heap[rightChildIndex] > heap[leftChildIndex]) {
                largerChildIndex = rightChildIndex;
            }

            // If parent is already larger, heap property holds
            if (heap[index] >= heap[largerChildIndex]) {
                break;
            }

            // Swap parent with larger child
            swap(heap, index, largerChildIndex);
            index = largerChildIndex;
        }
    }

    /**
     * Swaps two elements in the array.
     */
    private static void swap(int[] heap, int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
}
