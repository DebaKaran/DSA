package recursion.jumps;

import java.util.Arrays;

/**
 * LeetCode 45 - Jump Game II
 *
 * Memoized (Top-Down DP) solution:
 *  - Computes minimum jumps needed from each index
 *  - Avoids recomputation using caching
 *
 * NOTE:
 * This solution is correct but not optimal.
 * It serves as a bridge between recursion and greedy.
 */
public class JumpGameIIMemoized {

    /**
     * Returns the minimum number of jumps needed to reach the last index.
     *
     * @param nums array where nums[i] represents max jump length from index i
     * @return minimum number of jumps
     */
    public int minJumpsToReachEnd(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return minJumpsFromIndex(nums, 0, memo);
    }

    /**
     * Computes the minimum number of jumps needed to reach the end
     * starting from the given index.
     *
     * @param nums input array
     * @param index current position
     * @param memo memoization array
     * @return minimum jumps from index
     * 
     * Time Complexity: O(n^2) in the worst case.
     * Space Complexity: O(n) due to memoization array and recursion stack.
     */
    private int minJumpsFromIndex(int[] nums, int index, int[] memo) {

        // Base case: already at or beyond last index
        if (index >= nums.length - 1) {
            return 0;
        }

        // Dead end: cannot move forward
        if (nums[index] == 0) {
            return Integer.MAX_VALUE;
        }

        // Return cached result if available
        if (memo[index] != -1) {
            return memo[index];
        }

        int minJumps = Integer.MAX_VALUE;

        // Try all possible jumps from current index
        for (int step = 1; step <= nums[index]; step++) {
            int nextIndex = index + step;

            if (nextIndex < nums.length) {
                int jumpsFromNext = minJumpsFromIndex(nums, nextIndex, memo);

                if (jumpsFromNext != Integer.MAX_VALUE) {
                    minJumps = Math.min(minJumps, 1 + jumpsFromNext);
                }
            }
        }

        memo[index] = minJumps;
        return minJumps;
    }
}

