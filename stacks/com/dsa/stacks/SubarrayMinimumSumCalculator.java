//Leetcode Problem: 907. Sum of Subarray Minimums
// Problem Link: https://leetcode.com/problems/sum-of-subarray-minimums/

import java.util.Arrays;
import java.util.Stack;

public class SubarrayMinimumSumCalculator {
    public int sumSubarrayMins(int[] arr) {
        //Brute Force - time limit exceed issue
        //return sumSubarrayMinsUsingBruteForce(arr);

        return calculateMinimumSubarraySumUsingNSLAndNSR(arr);
    }

    // This method calculates the sum of minimums of all subarrays using the Next Smaller to Left (NSL)
    // and Next Smaller to Right (NSR) approach. It finds the indices of the nearest smaller elements
    // to the left and right for each element, calculates the number of subarrays where each element is the minimum,
    // and accumulates the total sum of these minimums. The result is returned modulo 1,000,000,007.
    
    // Time complexity: O(n) for finding NSL and NSR, and O(n) for the final calculation.
    // Space complexity: O(n) for storing NSL and NSR indices.  
    // This approach is efficient and avoids the time limit exceed issue seen in brute force methods.
    
    private int calculateMinimumSubarraySumUsingNSLAndNSR(int[] nums) {
        final long MOD = 1_000_000_007;
        int[] nsl = findNextSmallerToLeftIndices(nums);
        int[] nsr = findNextSmallerToRightIndices(nums);
        int n = nums.length;
        long sum = 0;

        for(int i = 0; i < n; i++) {
            int leftLen = i - nsl[i];
            int rightLen = nsr[i] - i;

            long totalSubArrayCount = (leftLen * rightLen) % MOD;
            long totalSubarraySum = (totalSubArrayCount * nums[i]) % MOD;

            sum = (sum + totalSubarraySum) % MOD;
        }

        return (int)sum;
    }

    private int[] findNextSmallerToRightIndices(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, n); // Default: n means "no smaller element found on the right"
        Stack<Integer> stack = new Stack<>(); // Will store indices, not values

        for (int i = n - 1; i >= 0; i--) {
            int val = nums[i];

            // Pop until we find a smaller value or stack becomes empty
            while (!stack.isEmpty() && val <= nums[stack.peek()]) {
                stack.pop();
            }

            // If stack not empty, top is the nearest smaller element's index
            if (!stack.isEmpty()) {
                result[i] = stack.peek();
            }

            // Push current index to be a candidate for future elements
            stack.push(i);
        }

        return result;
    }

    private int[] findNextSmallerToLeftIndices(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1); // Default: -1 means "no smaller element found on the left"
        Stack<Integer> stack = new Stack<>(); // Will store indices, not values

        for (int i = 0; i < n; i++) {
            int val = nums[i];

            // Pop until we find a smaller value or stack becomes empty
            while (!stack.isEmpty() && val < nums[stack.peek()]) {
                stack.pop();
            }

            // If stack not empty, top is the nearest smaller element's index
            if (!stack.isEmpty()) {
                result[i] = stack.peek();
            }

            // Push current index to be a candidate for future elements
            stack.push(i);
        }

        return result;
    }

    // This method calculates the sum of minimums of all subarrays using a brute force approach.
    // It iterates through all possible subarrays, finds the minimum for each, and accum
    // ulates the total sum. The result is returned modulo 1,000,000,007.
    // Time complexity: O(n^2) due to nested loops. 
    // Space complexity: O(1) as no additional space is used.
    // The brute force method is simple but inefficient for large inputs.

     private int calculateUsingBruteForce(int[] arr) {
        final int MOD = 1_000_000_007;
        int n = arr.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int minVal = arr[i];
            for (int j = i; j < n; j++) {
                minVal = Math.min(minVal, arr[j]);
                sum = (sum + minVal) % MOD;
            }
        }
        return sum;
    }
}
