package recursion;

public class SubsequenceSumExists {

    /**
     * Checks whether there exists a subsequence whose sum equals targetSum.
     */
    public boolean existsSubsequenceWithSum(int[] nums, int targetSum) {
        return exists(nums, targetSum, 0);
    }

    /**
     * Recursive helper using include/exclude strategy.
     * Time Complexity: O(2^n) where n is the number of elements in nums.
     * Space Complexity: O(n) due to recursion stack.
     */
    private boolean exists(int[] nums, int targetSum, int index) {

        // Base case: target achieved
        if (targetSum == 0) {
            return true;
        }

        // Base case: no elements left
        if (index == nums.length) {
            return false;
        }

        // Choice 1: include current element if possible
        if (targetSum >= nums[index]) {
            if (exists(nums, targetSum - nums[index], index + 1)) {
                return true;
            }
        }

        // Choice 2: exclude current element
        return exists(nums, targetSum, index + 1);
    }
}
