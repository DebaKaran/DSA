//Leetcode Problem: 907. Sum of Subarray Minimums
// Problem Link: https://leetcode.com/problems/sum-of-subarray-minimums/

public class SubarrayMinimumSumCalculator {
    public int sumSubarrayMins(int[] arr) {
        return calculateUsingBruteForce(arr);
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
