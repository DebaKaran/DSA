
//GFG Problem: https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1

public class PerfectSumSubsequences {

    // Function to calculate the number of subsets with a given sum
    public int perfectSum(int[] nums, int target) {
        return perfectSum(nums, target, 0);
    }
    
    //Time Complexity: O(2^n)
    //Space Complexity: O(n) for the recursion stack
    //TLE for large inputs

    private int perfectSum(int[] nums, int target, int idx) {
        if (idx >= nums.length) {
            return target == 0 ? 1 : 0;
        }
        
        int included = 0;
        if (nums[idx] <= target) {
            included = perfectSum(nums, target - nums[idx], idx + 1);
        }
        
        int excluded = perfectSum(nums, target, idx + 1);
        
        return included + excluded;
    }
}
