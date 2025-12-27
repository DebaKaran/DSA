package recursion;

import java.util.Stack;

/** 
 * GFG Problem : Reverse a Stack using Recursion
 * https://practice.geeksforgeeks.org/problems/reverse-a-stack/1
*/
public class ReverseStack {
    /**
     * Reverses the given stack using recursion.
     */
    public static void reverseStack(Stack<Integer> stack) {
        //reverseStackHelper(stack);
         reverseStackRecursively(stack);
    }

    /**
     * Recursively reverses the stack by removing the top element,
     * reversing the remaining stack, and inserting the removed
     * element at the bottom.
     * 
     * Time Complexity: O(n^2) due to repeated pop/push operations
     * Space Complexity: O(n) for recursion stack 
     * No auxiliary data structures used
     */
    private static void reverseStackRecursively(Stack<Integer> stack) {

        // Base case: empty stack
        if (stack.isEmpty()) {
            return;
        }

        // Step 1: Remove top element
        Integer top = stack.pop();

        // Step 2: Reverse remaining stack
        reverseStackRecursively(stack);

        // Step 3: Insert removed element at the bottom
        insertAtBottom(stack, top);
    }

    /**
     * Inserts the given element at the bottom of the stack
     * using recursion.
     */
    private static void insertAtBottom(Stack<Integer> stack, Integer element) {

        // Base case: stack is empty, insert element
        if (stack.isEmpty()) {
            stack.push(element);
            return;
        }

        // Remove current top
        Integer current = stack.pop();

        // Recursive call to reach bottom
        insertAtBottom(stack, element);

        // Restore previous elements
        stack.push(current);
    }

    /**
     * Recursive helper method to reverse the stack.
     * This implementation uses an auxiliary array to
     * temporarily store elements after recursive calls.
     * 
     * Time Complexity: O(n^2) due to repeated pop/push operations
     * Space Complexity: O(n) for recursion stack + O(n) for auxiliary array
     */
    private static void reverseStackHelper(Stack<Integer> stack) {

        // Base case: empty stack
        if (stack.isEmpty()) {
            return;
        }

        // Remove top element
        Integer top = stack.pop();

        // Reverse remaining stack
        reverseStackHelper(stack);

        // Store current stack elements temporarily
        int size = stack.size();
        int[] buffer = new int[size];

        for (int i = 0; i < size; i++) {
            buffer[i] = stack.pop();
        }

        // Push the originally popped element to bottom
        stack.push(top);

        // Restore remaining elements
        for (int i = size - 1; i >= 0; i--) {
            stack.push(buffer[i]);
        }
    }
}
