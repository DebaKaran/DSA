package recursion;
import java.util.Stack;

//GFG Problem: https://practice.geeksforgeeks.org/problems/sort-a-stack/1

public class SortStack {

    /**
     * Sorts the stack in ascending order using recursion.
     */
    public void sortStack(Stack<Integer> stack) {
        sortStackRecursively(stack);
    }

    /**
     * Recursively sorts the stack by removing the top element,
     * sorting the remaining stack, and inserting the element
     * back into its correct position using an auxiliary stack.
     * 
     * Time Complexity: O(n^2) in the worst case
     * Space Complexity: O(n) due to the auxiliary stack + recursion stack
     */
    private void sortStackRecursively(Stack<Integer> stack) {

        // Base case: empty stack
        if (stack.isEmpty()) {
            return;
        }

        // Remove top element
        Integer current = stack.pop();

        // Sort remaining stack
        sortStackRecursively(stack);

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
