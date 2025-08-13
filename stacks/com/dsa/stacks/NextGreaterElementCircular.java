import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElementCircular {
    public int[] nextGreaterElements(int[] nums) {
        // return nextGreaterElementsUsingBruteForce(nums);
         return nextGreaterElementsUsingStack(nums);
    }
    //Time complexity: O(n) due to single pass through the array.
    // Space complexity: O(n) for the stack and result array.
    
     private int[] nextGreaterElementsUsingStack(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1); // default: no greater element
        Stack<Integer> stack = new Stack<>(); // stores indices

        // Iterate twice over the array for circular effect
        for(int i = 2 * n - 1; i >= 0; i--) {
            int idx = i % n;

            while(!stack.isEmpty() && nums[idx] >= nums[stack.peek()]) {
                stack.pop();
            }

            if(!stack.isEmpty()) {
                result[idx] = nums[stack.peek()];
            }
            stack.push(idx);
        }

        return result;
     }

    //Time complexity: O(n^2) due to double iteration over elements.
    // Space complexity: O(n) for storing results.

    private int[] nextGreaterElementsUsingBruteForce(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        for(int i = 0; i < n; i++) {
            boolean isGreaterNumberAvailable = false;
            for(int j = i + 1; j < i + n; j++) {
                int index = j % n;
                if(nums[index] > nums[i]) {
                    result[i] = nums[index];
                    isGreaterNumberAvailable = true;
                    break;
                }
            }
            if(!isGreaterNumberAvailable) {
                result[i] = -1;
            }
        }

        return result;
    }
}
