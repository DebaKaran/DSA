package stack_queue.implemenations;

import java.util.LinkedList;
import java.util.Queue;

//LeetCode Problem 225: Implement Stack using Queues

class QueueBasedStack {

    private Queue<Integer> mainQ;   // Main queue that always holds stack order
    private Queue<Integer> helperQ; // Temporary helper queue

    public QueueBasedStack() {
        this.mainQ = new LinkedList<>();
        this.helperQ = new LinkedList<>();
    }

    //Time complexity: O(2 * n)
    public void push(int value) {
        // Insert new element in LIFO order using queue behavior

        if (mainQ.isEmpty()) {
            mainQ.add(value);
            return;
        }

        // Move all existing elements to helper queue
        while (!mainQ.isEmpty()) {
            helperQ.add(mainQ.poll());
        }

        // Insert the new element first (it becomes top)
        mainQ.add(value);

        // Move elements back to main queue preserving order
        while (!helperQ.isEmpty()) {
            mainQ.add(helperQ.poll());
        }
    }

    //Time complexity: O(1)
    public int pop() {
        // Removes the top element of the simulated stack
        return mainQ.isEmpty() ? -1 : mainQ.poll();
    }

    //Time complexity: O(1)
    public int top() {
        // Returns the top without removing
        return mainQ.isEmpty() ? -1 : mainQ.peek();
    }

    //Time complexity: O(1)
    public boolean isEmpty() {
        // Check stack emptiness
        return mainQ.isEmpty();
    }
}
