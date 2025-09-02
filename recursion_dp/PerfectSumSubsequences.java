
//GFG Problem: https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1

import java.util.Arrays;

public class PerfectSumSubsequences {

    // Function to calculate the number of subsets with a given sum
    public int perfectSum(int[] nums, int target) {
       int n = nums.length;
        //return perfectSum(nums, target, n - 1);
      
        int[][] memos = new int[n][target + 1];
        for(int[] memo : memos) {
            Arrays.fill(memo, -1);
        }
        
        return perfectSumUsingMemo(nums, target, n - 1, memos);
        
    }
    
    //Time Complexity: O(n * target)
    //Space Complexity: O(n * target) for the memoization table + O(n)
    
    private int perfectSumUsingMemo(int[] nums, int target, int idx, int[][] memos) {
        if(target < 0) return 0;  // pruning, no valid subset if target went negative
        
        if(idx < 0) return target == 0 ? 1 : 0;  // base condition
        
         // Already computed?
        if(memos[idx][target] != -1) {
            return memos[target][target];
        }
        
        // Option 1: Include current number (if ≤ target)
        int included = perfectSumUsingMemo(nums, target - nums[idx], idx - 1, memos);
        
        // Option 2: Exclude current number
         int excluded = perfectSumUsingMemo(nums, target, idx - 1, memos);
        
        // Save & return
        return memos[idx][target] = included + excluded;
    }
    
    //Time Complexity: O(2^n)
    //Space Complexity: O(n) for the recursion stack
    //TLE for large inputs

    private int perfectSum(int[] nums, int target, int idx) {
       if(target < 0) return 0;  // pruning, no valid subset if target went negative
        
        if(idx < 0) return target == 0 ? 1 : 0;  // base condition
        
        int included = perfectSum(nums, target - nums[idx], idx - 1);
        
        int excluded = perfectSum(nums, target, idx - 1);
        
        return included + excluded;
    }
}
