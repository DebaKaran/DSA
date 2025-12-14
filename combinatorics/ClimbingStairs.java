package combinatorics;

/**
 * LeetCode Problem 70: Climbing Stairs
 * https://leetcode.com/problems/climbing-stairs/
 * 
 */
public class ClimbingStairs {

    public int climbStairs(int totalSteps) {
        return countWaysUsingRecursion(totalSteps);
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
