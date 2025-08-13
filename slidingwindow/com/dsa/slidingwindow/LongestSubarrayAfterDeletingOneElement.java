package com.dsa.slidingwindow;

//Leetcode Problem: https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/

public class LongestSubarrayAfterDeletingOneElement {
    public int longestSubarray(int[] nums) {
        //using brute force: O(n ^ 2)
        return longestSubarrayBruteForce(nums);
    }

    //using brute force
    private int longestSubarrayBruteForce(int[] nums) {
        int n = nums.length;
        int maxCount = 0;
        int countZeros = 0;

        for(int i = 0; i < n; i++) {
            if(nums[i] == 0) {
                int count = longestSubarrayBruteForce(nums, i);
                maxCount = Math.max(maxCount, count);
                countZeros++;
            }
        }

        if(countZeros == 0) {
            return n - 1;
        }

        return maxCount;
    }

    private int longestSubarrayBruteForce(int[] nums, int skip_index) {
        int n = nums.length;
         
        int maxCount = 0;
        int count = 0;

        for(int i = 0; i < n; i++) {
            if(i == skip_index) {
                continue;
            }

            if(nums[i] == 0) {
                maxCount = Math.max(maxCount, count);
                count = 0;
            } else {
                count++;
            }
        }
        return Math.max(count, maxCount);
    }
}
