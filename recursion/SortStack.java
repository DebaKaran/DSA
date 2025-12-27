package recursion;
import java.util.Stack;

//GFG Problem: https://practice.geeksforgeeks.org/problems/sort-a-stack/1

public class SortStack {

    /**
     * Sorts the stack in ascending order using recursion.
     */
   public void sortStack(Stack<Integer> st) {
        
        //sortStackHelper(st);
        sortStackRecursively(st);
    }
    
    /**
     * Recursively sorts the stack by removing the top element,
     * sorting the remaining stack, and inserting the element
     * back into its correct position.
     * 
     * Time Complexity: O(n^2) in the worst case
     * Space Complexity: O(n) due to recursion stack
     * No Auxiliary Data Structure Used
     */
    private void sortStackRecursively(Stack<Integer> stack) {

        // Base case: empty stack
        if (stack.isEmpty()) {
            return;
        }

        // Remove top element
        Integer value = stack.pop();

        // Sort remaining stack
        sortStackRecursively(stack);

        // Insert removed element into the sorted stack
        insertInSortedOrder(stack, value);
    }

    /**
     * Inserts the given value into the stack such that
     * the stack remains sorted in ascending order.
     */
    private void insertInSortedOrder(Stack<Integer> stack, Integer value) {

        // Base case: correct position found
        if (stack.isEmpty() || value >= stack.peek()) {
            stack.push(value);
            return;
        }

        // Remove top element and recurse
        Integer top = stack.pop();
        insertInSortedOrder(stack, value);

        // Restore previous elements
        stack.push(top);
    }

    /**
     * Recursively sorts the stack by removing the top element,
     * sorting the remaining stack, and inserting the element
     * back into its correct position using an auxiliary stack.
     * 
     * Time Complexity: O(n^2) in the worst case
     * Space Complexity: O(n) due to the auxiliary stack + recursion stack
     */
    private void sortStackHelper(Stack<Integer> stack) {

        // Base case: empty stack
        if (stack.isEmpty()) {
            return;
        }

        // Remove top element
        Integer current = stack.pop();

        // Sort remaining stack
        sortStackHelper(stack);

        // Auxiliary stack to find correct position
        Stack<Integer> bufferStack = new Stack<>();

        // Move elements larger than current to auxiliary stack
        while (!stack.isEmpty() && current < stack.peek()) {
            bufferStack.push(stack.pop());
        }

        // Insert current element at correct position
        stack.push(current);

        // Restore moved elements
        while (!bufferStack.isEmpty()) {
            stack.push(bufferStack.pop());
        }
    }
}
