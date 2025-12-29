package greedy;

/**
 * LeetCode 55 - Jump Game
 *
 * Greedy approach:
 *  - Track the farthest index that can be reached so far
 *  - If the current index exceeds this reach, the game fails
 */
public class JumpGame {

    /**
     * Determines whether the last index can be reached.
     *
     * @param nums array where nums[i] represents max jump length from index i
     * @return true if last index is reachable, false otherwise
     * 
     * Time Complexity: O(n) where n is the length of nums
     * Space Complexity: O(1) for storing maxReach variable
     * 
     */
    public boolean canReachLastIndex(int[] nums) {

        int maxReach = 0;

        for (int i = 0; i < nums.length; i++) {

            // If current index is beyond reachable range, jump is impossible
            if (i > maxReach) {
                return false;
            }

            // Extend the farthest reachable index
            maxReach = Math.max(maxReach, i + nums[i]);

            // Early exit if last index is already reachable
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }

        return false;
    }
}
