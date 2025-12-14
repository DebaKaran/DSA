package combinatorics;

/**
 * LeetCode Problem 70: Climbing Stairs
 * https://leetcode.com/problems/climbing-stairs/
 * 
 */
public class ClimbingStairs {

    public int climbStairs(int totalSteps) {
        //return countWaysUsingRecursion(totalSteps);

        // Memo array to store results of subproblems
        int[] memo = new int[totalSteps + 1];

        // Base case: one way to stay at step 0
        memo[0] = 1;

        return countWaysUsingMemoization(totalSteps, memo);
    }

    // O(n) time complexity
    // O(n) space complexity for memoization array
    
    private int countWaysUsingMemoization(int stepsRemaining, int[] memo) {
        // If result already computed, return it
        if (memo[stepsRemaining] != 0) {
            return memo[stepsRemaining];
        }

        // Count ways by taking one step
        int waysWithOneStep =
                countWaysUsingMemoization(stepsRemaining - 1, memo);

        // Count ways by taking two steps (only if valid)
        int waysWithTwoSteps = 0;
        if (stepsRemaining >= 2) {
            waysWithTwoSteps =
                    countWaysUsingMemoization(stepsRemaining - 2, memo);
        }

        // Store and return total number of ways
        memo[stepsRemaining] = waysWithOneStep + waysWithTwoSteps;
        return memo[stepsRemaining];
    }

    // Time Limit Exceeded due to exponential recursion
    // O(2^n) time complexity
    // O(n) space complexity due to recursion stack
    private int countWaysUsingRecursion(int stepsRemaining) {
        // Base case:
        // If no steps remain, one valid way (we've reached the top)
        if (stepsRemaining == 0) {
            return 1;
        }

        // Take one step
        int waysWithOneStep = countWaysUsingRecursion(stepsRemaining - 1);

        // Take two steps (only if possible)
        int waysWithTwoSteps = 0;
        if (stepsRemaining >= 2) {
            waysWithTwoSteps = countWaysUsingRecursion(stepsRemaining - 2);
        }

        // Total ways = ways from both choices
        return waysWithOneStep + waysWithTwoSteps;
    }

}
