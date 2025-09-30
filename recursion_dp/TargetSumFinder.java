//Leetcode Problem 494: Target Sum
// Leetcode Problem Link: https://leetcode.com/problems/target-sum/

public class TargetSumFinder {
    public int findTargetSumWays(int[] nums, int target) {
        return findTargetSumWays(nums, target, 0);
    }
    // 1: Time Complexity  :    O(2^n)
    // 2: Space Complexity :    O(n)  (recursion stack)
    
    private int findTargetSumWays(int[] nums, int target, int idx) {
        if (idx == nums.length) {
            return target == 0 ? 1 : 0;
        }

        // Assign '+' to current number → decrease target
        int plusSign = findTargetSumWays(nums, target - nums[idx], idx + 1);

        // Assign '-' to current number → increase target
        int minusSign = findTargetSumWays(nums, target + nums[idx], idx + 1);

        return plusSign + minusSign;
    }
}
