package stack_queue.implemenations;

import java.util.Stack;

class MinStackOptimized {

    private Stack<Long> stack;   // store values (normal or encoded)
    private long currentMin;     // track minimum

    public MinStackOptimized() {
        this.stack = new Stack<>();
    }

    public void push(int value) {
        long val = value;

        if (stack.isEmpty()) {
            stack.push(val);
            currentMin = val;
        } else if (val >= currentMin) {
            // Normal push
            stack.push(val);
        } else {
            // New minimum encountered — store encoded value
            long encodedValue = 2L * val - currentMin;
            stack.push(encodedValue);
            currentMin = val;  // update min
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;

        long top = stack.pop();

        if (top < currentMin) {
            // Encoded value → decode previous min
            long previousMin = 2L * currentMin - top;
            currentMin = previousMin;
        }
        // If top >= currentMin ⇒ normal value, nothing special to do
    }

    public int top() {
        long top = stack.peek();
        if (top >= currentMin) {
            // Normal value
            return (int) top;
        } else {
            // Encoded ⇒ actual top is currentMin
            return (int) currentMin;
        }
    }

    public int getMin() {
        return (int) currentMin;
    }
}
