//Leetcode Problem: https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/


public class LongestSubarrayWithoutZero {

    public int longestSubarray(int[] nums) {
        return longestSubarrayUsingSlidingWindowBetterApproach(nums);

    }

//Time Complexity: O(n)

// The for loop runs once over the array, and each index is visited at most once.
// Even when you “jump” the windowStart forward, it doesn’t cause re-traversal of elements.

// Space Complexity: O(1)

// You’re using only a fixed number of extra variables (countZeros, result, windowStart, lastZeroIndex), regardless of input size.
private int longestSubarrayUsingSlidingWindowBetterApproach(int[] nums) {
        int n = nums.length;
        int result = 0;
        int windowStart = 0;
        int prevZeroIndex = -1; // index of the last zero we saw

        for(int windowEnd = 0; windowEnd < n; windowEnd++) {

            if(nums[windowEnd] == 0) {
                // Second zero encountered → move windowStart to after the previous zero
                if(prevZeroIndex != -1) {
                    windowStart = prevZeroIndex + 1;
                }
                // mark current zero
                prevZeroIndex = windowEnd;
            }

           
            // window length minus 1 because we must delete one element
            result = Math.max(result, windowEnd - windowStart);
        }

        return result;
    }
}