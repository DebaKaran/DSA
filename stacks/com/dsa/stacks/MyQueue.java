import java.util.Stack;

//Leetcode Problem 232: Implement Queue using Stacks
//Time Complexity: O(n) for push, O(1) for pop, peek,

/**public class MyQueue {
    Stack<Integer> s1;
    Stack<Integer> s2;
    int size;

    public MyQueue() {
        this.s1 = new Stack<>();
        this.s2 = new Stack<>();
        this.size = 0;
    }
    
    public void push(int x) {
        while(!s1.isEmpty()) {
            s2.push(s1.pop());
        }

        s1.push(x);

        while(!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        size++;
    }
    
    public int pop() {
       if(!s1.isEmpty()) {
            size--;
            return s1.pop();
       }
       return -1;
    }
    
    public int peek() {
        return s1.isEmpty() ? -1 : s1.peek();
    }
    
    public boolean empty() {
        return size == 0;
    }
} */

public class MyQueue {
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public MyQueue() {
        this.inStack = new Stack<>();
        this.outStack = new Stack<>();
    }
    
    // O(1)
    public void push(int x) {
        inStack.push(x);
    }
    
    // Amortized O(1)
    public int pop() {
      shiftStacks();

      return outStack.isEmpty() ? -1 : outStack.pop();
    }
    
     // O(1)
    public int peek() {
      shiftStacks();

      return outStack.isEmpty() ? -1 : outStack.peek();
    }
    
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void shiftStacks() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */