package stack_queue.implemenations;

import java.util.Stack;

/**
 * LeetCode Problem 155: Min Stack
 * Time Complexity: O(1) for all operations
 */
class MinStack {

    // Stack storing (value, min-so-far) pairs
    private Stack<Node> stack;

    // Node holds the current value and the minimum value up to that point
    private static class Node {
        int value;
        int minSoFar;

        Node(int value, int minSoFar) {
            this.value = value;
            this.minSoFar = minSoFar;
        }
    }

    public MinStack() {
        this.stack = new Stack<>();
    }

    public void push(int value) {
        // Determine new minimum
        int newMin = value;
        if (!stack.isEmpty()) {
            newMin = Math.min(value, stack.peek().minSoFar);
        }

        // Push pair of (current value, min till now)
        stack.push(new Node(value, newMin));
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public int top() {
        return stack.isEmpty() ? -1 : stack.peek().value;
    }

    public int getMin() {
        return stack.isEmpty() ? -1 : stack.peek().minSoFar;
    }
}
