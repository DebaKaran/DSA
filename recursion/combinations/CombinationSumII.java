package recursion.combinations;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumII {

    /**
     * LeetCode 40: Combination Sum II
     * Finds all unique combinations where each number is used at most once.
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates); // Required for duplicate handling and pruning
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * Backtracking helper using loop-based recursion.
     * Time Complexity: O(2^n) in worst case
     * Space Complexity: O(n) for recursion stack
     * 
     * Include and exclude will not work here 
     * due to duplicates as we need to skip over duplicates in the same recursion level
     */
    private void backtrack(
            int[] candidates,
            int remainingTarget,
            int start,
            List<Integer> currentCombination,
            List<List<Integer>> result
    ) {

        // Base case: exact target achieved
        if (remainingTarget == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            // Skip duplicates at the same recursion level
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // Pruning: no need to proceed if candidate exceeds remaining target
            if (candidates[i] > remainingTarget) {
                break;
            }

            // Choose current element
            currentCombination.add(candidates[i]);

            // Move to next index (each number can be used once)
            backtrack(
                    candidates,
                    remainingTarget - candidates[i],
                    i + 1,
                    currentCombination,
                    result
            );

            // Backtrack
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}
