package greedy.jumps;

/**
 * LeetCode 45 - Jump Game II
 *
 * Greedy (Window Expansion) solution:
 *  - Treat reachable indices as BFS layers
 *  - Each window represents indices reachable with the same number of jumps
 *  - Expand the window greedily to minimize total jumps
 * 
 * Recursion and DP solutions exist but are less efficient.
 * and it is availeble in recursion.jumps package.
 * NOTE:
 * This is the optimal solution with O(n) time complexity.
 */
public class JumpGameII {

    /**
     * Returns the minimum number of jumps needed to reach the last index.
     *
     * @param nums array where nums[i] represents max jump length from index i
     * @return minimum number of jumps
     */
    public int minJumpsToReachEnd(int[] nums) {
        return minJumpsUsingGreedyWindow(nums);
    }

    /**
     * Greedy window expansion approach.
     *
     * @param nums input jump array
     * @return minimum number of jumps
     * Time Complexity: O(n) where n is the length of nums
     * Space Complexity: O(1) for storing window pointers and counters
     * 
     */
    private int minJumpsUsingGreedyWindow(int[] nums) {

        int windowStart = 0;
        int windowEnd = 0;
        int jumps = 0;
        int n = nums.length;

        // Continue expanding windows until the last index is reachable
        while (windowEnd < n - 1) {

            int farthestReach = 0;

            // Explore all indices in the current window
            for (int i = windowStart; i <= windowEnd; i++) {
                farthestReach = Math.max(farthestReach, i + nums[i]);
            }

            // Move to the next window (next jump layer)
            windowStart = windowEnd + 1;
            windowEnd = farthestReach;
            jumps++;
        }

        return jumps;
    }
}
