package stack_queue.implemenations;

import java.util.Stack;

//LeetCode Problem 232: Implement Queue using Stacks
class StackBasedQueue {

    private Stack<Integer> mainStack;    // Will always hold queue order (front at top)
    private Stack<Integer> helperStack;  // Temporary helper stack

    public StackBasedQueue() {
        this.mainStack = new Stack<>();
        this.helperStack = new Stack<>();
    }

    //Time complexity: O(2 * n) 
    public void enqueue(int value) {
        // Insert element at the end of the queue using stack reversal

        if (mainStack.isEmpty()) {
            mainStack.push(value);
            return;
        }

        // Move all elements to helper stack (reverse order)
        while (!mainStack.isEmpty()) {
            helperStack.push(mainStack.pop());
        }

        // Insert the new element
        mainStack.push(value);

        // Restore original order
        while (!helperStack.isEmpty()) {
            mainStack.push(helperStack.pop());
        }
    }
    //Time complexity: O(1)
    public int dequeue() {
        // Removes element from front of queue
        return mainStack.isEmpty() ? -1 : mainStack.pop();
    }
    //Time complexity: O(1)
    public int front() {
        // Returns front element without removing it
        return mainStack.isEmpty() ? -1 : mainStack.peek();
    }
    //Time complexity: O(1)
    public boolean isEmpty() {
        // Check if queue is empty
        return mainStack.isEmpty();
    }
}
