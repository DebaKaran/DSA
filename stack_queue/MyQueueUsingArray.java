package stack_queue;

class MyQueueUsingArray {

    private int size;        // Number of elements currently in queue
    private int[] data;      // Underlying fixed-size array

    public MyQueueUsingArray(int capacity) {
        this.data = new int[capacity]; // Initialize the queue array
        this.size = 0;                 // Queue starts empty
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public void enqueue(int value) {
        // Add element at the end of the queue
        if (isFull()) {
            return; // Ideally throw exception
        }
        data[size] = value; // Insert at position size
        size++;            // Increase element count
    }

    public void dequeue() {
        // Remove element from the front
        if (isEmpty()) {
            return; 
        }

        // Shift elements left by 1 (O(n))
        for (int i = 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--; // Reduce queue size
    }

    public int front() {
        // Fetch first element
        return isEmpty() ? -1 : data[0];
    }

    public int rear() {
        // Fetch last element
        return isEmpty() ? -1 : data[size - 1];
    }
}
