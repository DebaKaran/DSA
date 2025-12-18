package dp.subsequences;

/**
 * GFG Problems: Subset Sum Problem
 * https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
 * 
 */
class SubsetSum {

     static Boolean isSubsetSum(int[] numbers, int targetSum) {

        // Start recursion from the last index
        return hasSubsetWithSum(numbers, numbers.length - 1, targetSum);
    }

    /**
     * Recursive helper method to check for subset sum.
     *
     * @param numbers   Input array
     * @param index     Current index being considered
     * @param remainingSum Sum still needed to reach target
     * @return true if a valid subset exists, false otherwise
     *
     * Time Complexity: O(2^n)
     * Space Complexity: O(n) due to recursion stack
     *
     * Note: All test cases are passing, but this approach may lead to TLE for large inputs.
     */
    private static boolean hasSubsetWithSum(
            int[] numbers,
            int index,
            int remainingSum
    ) {
        // If required sum becomes 0, subset is found
        if (remainingSum == 0) {
            return true;
        }

        // Pruning: no need to continue if sum goes negative
        if (remainingSum < 0) {
            return false;
        }

        // Base case: only one element left
        if (index == 0) {
            return remainingSum == numbers[0];
        }

        // Choice 1: include current element
        if (numbers[index] <= remainingSum &&
            hasSubsetWithSum(
                    numbers,
                    index - 1,
                    remainingSum - numbers[index]
            )) {
            return true;
        }

        // Choice 2: exclude current element
        return hasSubsetWithSum(numbers, index - 1, remainingSum);
    }
}

