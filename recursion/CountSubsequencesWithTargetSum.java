package recursion;

public class CountSubsequencesWithTargetSum {

    /**
     * Counts the number of subsequences whose sum equals targetSum.
     */
    public int countSubsequencesWithSum(int[] nums, int targetSum) {
        return countWays(nums, targetSum, 0);
    }

    /**
     * Recursive helper that explores include/exclude choices.
     * Time Complexity: O(2^n) where n is the number of elements in nums.
     * Space Complexity: O(n) due to recursion stack.
     * 
     */
    private int countWays(int[] nums, int targetSum, int index) {

        // If target sum achieved, one valid subsequence found
        if (targetSum == 0) {
            return 1;
        }

        // If no elements left but target not achieved
        if (index == nums.length) {
            return 0;
        }

        int include = 0;

        // Choice 1: include current element if possible
        if (targetSum >= nums[index]) {
            include = countWays(nums, targetSum - nums[index], index + 1);
        }

        // Choice 2: exclude current element
        int exclude = countWays(nums, targetSum, index + 1);

        return include + exclude;
    }
}

