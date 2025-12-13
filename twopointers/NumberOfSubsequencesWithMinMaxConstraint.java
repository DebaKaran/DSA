package twopointers;

import java.util.Arrays;

/**
 * LeetCode 1498
 * Difficulty: Medium
 * Tags: Array, Two Pointers, Binary Search, Sorting, Recursion, Dynamic Programming
 */
public class NumberOfSubsequencesWithMinMaxConstraint {

    private static final int MOD = 1_000_000_007;

    // Entry point expected by LeetCode
    public int numSubseq(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Start recursion with:
        // index = 0
        // no min/max yet
        // chosen = false (empty subsequence)
        //return countSubsequencesRec(nums, target, 0, 0, 0, false);

        return countSubsequencesTwoPointers(nums, target);
    }

    /**
     * Counts valid subsequences where min + max <= target.
     *
     * Approach:
     * 1. Sort the array
     * 2. Use two pointers (left = min, right = max)
     * 3. If nums[left] + nums[right] <= target,
     *    then all subsequences that include nums[left] and
     *    any subset of elements in (left+1 .. right) are valid.
     *
     * Time Complexity:  O(n log n)
     * Space Complexity: O(n)
     */
    private int countSubsequencesTwoPointers(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;

        int left = 0;
        int right = n - 1;
        long result = 0L;

        // Precompute powers of 2 modulo MOD
        // power[i] = 2^i % MOD
        int[] power = new int[n];
        power[0] = 1;
        for (int i = 1; i < n; i++) {
            power[i] = (power[i - 1] * 2) % MOD;
        }

        // Two-pointer traversal
        while (left <= right) {
            if (nums[left] + nums[right] > target) {
                // Current max too large, try smaller max
                right--;
            } else {
                // nums[left] can be the minimum
                // All subsets of elements between left and right are valid
                result = (result + power[right - left]) % MOD;
                left++;
            }
        }

        return (int) result;
    }

    /**
     * Recursive enumeration of subsequences.
     *
     * @param nums     input array
     * @param target   fixed target value
     * @param index    current index under consideration
     * @param currMin  minimum value in the chosen subsequence (valid only if chosen == true)
     * @param currMax  maximum value in the chosen subsequence (valid only if chosen == true)
     * @param chosen   indicates whether at least one element has been chosen
     *
     * @return number of valid subsequences from index..end combined with previous choices
     * 
     * Recursive (brute-force) solution for educational correctness.
     *  NOTE: This solution is correct but will TLE for large inputs.
     * 
     * Time: O(2^n) where n = nums.length
     * Space: O(n) recursion stack
     */
    private int countSubsequencesRec(
            int[] nums,
            int target,
            int index,
            int currMin,
            int currMax,
            boolean chosen
    ) {

        // Base case: all elements processed
        if (index == nums.length) {
            // Empty subsequence is not counted
            if (!chosen) {
                return 0;
            }

            // Check problem condition: min + max <= target
            return ((long) currMin + (long) currMax <= (long) target) ? 1 : 0;
        }

        long count = 0L;

        // 1: Include nums[index]
        if (!chosen) {
            // First chosen element initializes both min and max
            count += countSubsequencesRec(
                    nums, target,
                    index + 1,
                    nums[index], nums[index],
                    true
            );
        } else {
            // Update min/max when adding another element
            int newMin = Math.min(currMin, nums[index]);
            int newMax = Math.max(currMax, nums[index]);

            count += countSubsequencesRec(
                    nums, target,
                    index + 1,
                    newMin, newMax,
                    true
            );
        }

        // 2: Exclude nums[index]
        // IMPORTANT: chosen state remains unchanged
        count += countSubsequencesRec(
                nums, target,
                index + 1,
                currMin, currMax,
                chosen
        );

        // Apply modulo before returning
        return (int) (count % MOD);
    }
}
