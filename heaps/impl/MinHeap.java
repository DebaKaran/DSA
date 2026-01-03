package heaps.impl;

/**
 * GeeksforGeeks - Min Heap Implementation
 *
 * Implements a Min Heap using a fixed-size array.
 * Supports insertion, deletion of minimum element,
 * peek, and size operations.
 */
class MinHeap {

    private int[] heap;
    private int size;

    // Constructor
    public MinHeap() {
        this.heap = new int[1000]; // fixed capacity (as per problem constraint)
        this.size = 0;
    }

    /**
     * Inserts an element into the min heap.
     *
     * Time Complexity: O(log N)
     */
    public void push(int value) {
        heap[size] = value;
        heapifyUp(size);
        size++;
    }

    /**
     * Removes the minimum (root) element from the heap.
     *
     * Time Complexity: O(log N)
     */
    public void pop() {
        if (size == 0) return;

        // Replace root with last element
        heap[0] = heap[size - 1];
        size--;

        heapifyDown(0);
    }

    /**
     * Returns the minimum element without removing it.
     *
     * Time Complexity: O(1)
     */
    public int peek() {
        return size == 0 ? -1 : heap[0];
    }

    /**
     * Returns the current size of the heap.
     *
     * Time Complexity: O(1)
     */
    public int size() {
        return size;
    }

    /* ---------------- Heap Helper Methods ---------------- */

    private void heapifyUp(int index) {
        while (hasParent(index)) {
            int parentIndex = getParentIndex(index);

            if (heap[parentIndex] <= heap[index]) {
                break;
            }

            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);

            if (hasRightChild(index)
                    && heap[getRightChildIndex(index)] < heap[smallerChildIndex]) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (heap[index] <= heap[smallerChildIndex]) {
                break;
            }

            swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private boolean hasParent(int index) {
        return index > 0;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
}
