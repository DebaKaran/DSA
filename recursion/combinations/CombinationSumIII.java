package recursion.combinations;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {

    /**
     * LeetCode 216: Combination Sum III
     * Finds all valid combinations of exactly k numbers (1–9) that sum to n.
     */
    public List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> result = new ArrayList<>();
        backtrack(k, n, 1, new ArrayList<>(), result);
        return result;
    }

    /**
     * Backtracking helper that selects numbers incrementally.
     * 
     * Time Complexity: O(C(9, k)) - combinations of 9 numbers taken k at a time
     * Space Complexity: O(k) - space for the current combination
     * 
     */
    private void backtrack(
            int numbersLeft,
            int remainingSum,
            int startNumber,
            List<Integer> currentCombination,
            List<List<Integer>> result
    ) {

        // Valid solution: exact count and exact sum achieved
        if (numbersLeft == 0 && remainingSum == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }

        // Invalid paths: too many picks or sum exceeded
        if (numbersLeft == 0 || remainingSum < 0) {
            return;
        }

        // Try numbers from startNumber to 9
        for (int num = startNumber; num <= 9; num++) {

            // Prune: numbers are increasing, no need to continue
            if (num > remainingSum) {
                break;
            }

            // Choose
            currentCombination.add(num);

            // Explore
            backtrack(
                    numbersLeft - 1,
                    remainingSum - num,
                    num + 1,
                    currentCombination,
                    result
            );

            // Un-choose (backtrack)
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}
