package recursion.combinations;

public class CombinationSumIV {

    /**
     * LeetCode 377: Combination Sum IV
     * Baseline recursive solution (without memoization).
     */
    public int combinationSum4(int[] nums, int target) {
        return countWaysRecursive(nums, target, 0);
    }

    /**
     * Recursive helper that attempts include/exclude decisions.
     * NOTE: This approach is not optimal and will TLE without memoization.
     * Time Complexity: O(2^(target * n)) in worst case
     * Space Complexity: O(target) for recursion stack
     * 
     */
    private int countWaysRecursive(int[] nums, int remainingTarget, int index) {

        // Base case: exact sum achieved
        if (remainingTarget == 0) {
            return 1;
        }

        // Base case: no numbers left to consider
        if (index >= nums.length) {
            return 0;
        }

        int includeWays = 0;

        // Choice 1: include current number (reset index to allow reuse)
        if (nums[index] <= remainingTarget) {
            includeWays = countWaysRecursive(
                    nums,
                    remainingTarget - nums[index],
                    0
            );
        }

        // Choice 2: exclude current number
        int excludeWays = countWaysRecursive(
                nums,
                remainingTarget,
                index + 1
        );

        return includeWays + excludeWays;
    }
}
