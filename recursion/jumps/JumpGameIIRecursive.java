package recursion.jumps;

/**
 * LeetCode 45 - Jump Game II
 *
 * Recursive (brute-force) solution:
 *  - Tries all possible jump paths
 *  - Returns the minimum number of jumps needed to reach the end
 *
 * NOTE:
 * This approach is correct but inefficient (exponential time).
 * It is useful for understanding the problem before optimization.
 */
public class JumpGameIIRecursive {

    /**
     * Returns the minimum number of jumps needed to reach the last index.
     *
     * @param nums array where nums[i] represents max jump length from index i
     * @return minimum number of jumps
     */
    public int minJumpsToReachEnd(int[] nums) {
        return minJumpsFromIndex(nums, 0);
    }

    /**
     * Computes the minimum jumps needed to reach the end starting
     * from the given index.
     *
     * @param nums array of jump lengths
     * @param currentIndex current position in the array
     * @return minimum jumps needed from currentIndex
     * 
     * Time Complexity: O(k^n) where k is the average jump length and n is the number of elements.
     * Space Complexity: O(n) due to recursion stack.
     * 
     * TLE (Time Limit Exceeded) on large inputs.
     * 
     */
    private int minJumpsFromIndex(int[] nums, int currentIndex) {

        // Base case: already at or beyond the last index
        if (currentIndex >= nums.length - 1) {
            return 0;
        }

        // Dead end: cannot move forward
        if (nums[currentIndex] == 0) {
            return Integer.MAX_VALUE;
        }

        int minJumps = Integer.MAX_VALUE;

        // Try all possible jumps from current index
        for (int step = 1; step <= nums[currentIndex]; step++) {
            int nextIndex = currentIndex + step;

            if (nextIndex < nums.length) {
                int jumpsFromNext = minJumpsFromIndex(nums, nextIndex);

                if (jumpsFromNext != Integer.MAX_VALUE) {
                    minJumps = Math.min(minJumps, 1 + jumpsFromNext);
                }
            }
        }

        return minJumps;
    }
}
