package com.dsa.slidingwindow;

//Leetcode Problem: https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/
public class MaxConsecutiveOnesAfterDeletion {
 public int longestSubarray(int[] nums) {
        
        //using sliding window: O(n)
        if(nums == null || nums.length == 0) {  
            return 0;
        }
        return longestSubarrayUsingSlidingWindow(nums);
    }

    private int longestSubarrayUsingSlidingWindow(int[] nums) {
        int n = nums.length;
        int countZeros = 0;
        int result = 0;
        int windowStart = 0;

        for(int windowEnd = 0; windowEnd < n; windowEnd++) {
            if(nums[windowEnd] == 0) {
                countZeros++;
            }

            while(countZeros > 1) {
                if(nums[windowStart] == 0) {
                    countZeros--;
                }
                windowStart++;
            }
            // window length minus 1 because we must delete one element
            result = Math.max(result, windowEnd - windowStart);
        }
     
        return result;
    }
}
