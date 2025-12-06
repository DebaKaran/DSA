package stack_queue;


class MyStack {

    private int top;        // Points to the next insertion position
    private int[] data;     // Underlying array for stack elements

    public MyStack(int capacity) {
        this.top = 0;                   // Stack starts empty
        this.data = new int[capacity];  // Fixed-size stack
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public boolean isFull() {
        return top == data.length;
    }

    public void push(int value) {
        // Insert at the top if space is available
        if (isFull()) {
            // Could throw exception, but using return for simplicity
            return;
        }
        data[top] = value;
        top++; // Move pointer up
    }

    public void pop() {
        // Remove the top element
        if (isEmpty()) {
            return;
        }
        top--; // Pointer simply moves down
    }

    public int peek() {
        // Return the top element without removing it
        if (isEmpty()) {
            return -1;  // Could also throw exception
        }
        return data[top - 1];
    }
}
