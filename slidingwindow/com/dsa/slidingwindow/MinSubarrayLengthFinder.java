package com.dsa.slidingwindow;

//https://leetcode.com/problems/minimum-size-subarray-sum/

//Variable-size sliding window
//(What your code does — window size changes dynamically to meet a condition)

//Problem example: Find the smallest subarray with sum ≥ target.

//Key points:

// Window size changes depending on sum condition.

// Two pointers (windowStart and windowEnd) move independently.

//Used for problems where "at least" / "at most" conditions apply.


public class MinSubarrayLengthFinder {
    public int minSubArrayLen(int target, int[] nums) {
    int result = Integer.MAX_VALUE;
    int sum = 0;

    for (int windowStart = 0, windowEnd = 0; windowEnd < nums.length; windowEnd++) {
        sum += nums[windowEnd]; // expand window

        // shrink window as long as condition is met
        while (sum >= target) {
            result = Math.min(result, windowEnd - windowStart + 1);
            sum -= nums[windowStart++];
        }
    }
    return result == Integer.MAX_VALUE ? 0 : result;
}

}
