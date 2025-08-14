import java.util.Arrays;
import java.util.Stack;

// GFG problem: https://www.geeksforgeeks.org/problems/smallest-number-on-left3403/1

public class LeftSmallerElementFinder {
    public int[] leftSmaller(int[] arr) {
        return leftSmallerUsingStack(arr);    
    }
    
    // Time complexity: O(n) due to single pass through the array.
    // Space complexity: O(n) for the stack and result array.
    
    private int[] leftSmallerUsingStack(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < n; i++) {
            int val = nums[i];
            
            while(!stack.isEmpty() && val <= stack.peek()) {
                stack.pop();
            }
            
            if(!stack.isEmpty()) {
                result[i] = stack.peek();
            }
            
            stack.push(val);
        }
        
        return result;
    }
}
