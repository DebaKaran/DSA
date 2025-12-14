package dp.subsequences;

/**
 * GFG Problems: Subset Sum Problem
 * https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
 * 
 */
class SubsetSum {

    static Boolean isSubsetSum(int[] nums, int targetSum) {
        return checkSubsetSum(nums, 0, 0, targetSum);
    }

    // Helper method to check for subset sum using recursion
    // index: current index in nums
    // currentSum: sum of the current subset
    // targetSum: desired sum to find
    // Returns true if a subset with targetSum exists   
    // Time Complexity: O(2^n) where n is the number of elements in nums
    // Space Complexity: O(n) due to recursion stack
    //Time Limit Exceeded for large inputs
    
    private static boolean checkSubsetSum(int[] nums, int index, int currentSum, int targetSum) {
        // Base case: all elements processed
        if (index == nums.length) {
            return currentSum == targetSum;
        }

        // Choice 1: include current element
        boolean include = checkSubsetSum(
                nums,
                index + 1,
                currentSum + nums[index],
                targetSum
        );

        // Choice 2: exclude current element
        boolean exclude = checkSubsetSum(
                nums,
                index + 1,
                currentSum,
                targetSum
        );

        // If either choice works, subset exists
        return include || exclude;
    }
}

