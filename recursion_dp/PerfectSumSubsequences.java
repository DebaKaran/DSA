
//GFG Problem: https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1

import java.util.Arrays;

public class PerfectSumSubsequences {

    // Function to calculate the number of subsets with a given sum
    public int perfectSum(int[] nums, int target) {
        //return perfectSum(nums, target, 0);

        int[][] memos = new int[target + 1][nums.length];
        for(int[] memo : memos) {
            Arrays.fill(memo, -1);
        }
        
        return perfectSumUsingMemo(nums, target, 0, memos);
        
    }
    
    //Time Complexity: O(n * target)
    //Space Complexity: O(n * target) for the memoization table + O(n)
    
    private int perfectSumUsingMemo(int[] nums, int target, int idx, int[][] memos) {
         // Base case
        if(idx >= nums.length) {
            return target == 0 ? 1 : 0;
        }
        
         // Already computed?
        if(memos[target][idx] != -1) {
            return memos[target][idx];
        }
        
        // Option 1: Include current number (if ≤ target)
        int included = 0;
        if(nums[idx] <= target) {
            included = perfectSumUsingMemo(nums, target - nums[idx], idx + 1, memos);
        }
        
        // Option 2: Exclude current number
        int excluded = perfectSumUsingMemo(nums, target, idx + 1, memos);
        
        // Save & return
        return memos[target][idx] = included + excluded;
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
