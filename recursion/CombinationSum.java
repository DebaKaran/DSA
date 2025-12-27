package recursion;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    /**
     * LeetCode 39: Combination Sum
     * Finds all unique combinations where numbers sum to target.
     */
    public List<List<Integer>> findCombinationSums(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * Backtracking helper that explores combinations.
     * Each element can be reused unlimited times.
     * 
     * Time Complexity: O(2^(target/min(candidates))) in worst case
     * Space Complexity: O(target/min(candidates)) for recursion stack
     * 
     */
    private void backtrack(
            int[] candidates,
            int remainingSum,
            int index,
            List<Integer> currentCombination,
            List<List<Integer>> result
    ) {

        // Base case: valid combination found
        if (remainingSum == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }

        // Base case: no candidates left
        if (index == candidates.length) {
            return;
        }

        // Choice 1: include current element (can reuse same index)
        if (remainingSum >= candidates[index]) {
            currentCombination.add(candidates[index]);
            backtrack(
                    candidates,
                    remainingSum - candidates[index],
                    index,
                    currentCombination,
                    result
            );
            // Backtrack
            currentCombination.remove(currentCombination.size() - 1);
        }

        // Choice 2: exclude current element and move forward
        backtrack(candidates, remainingSum, index + 1, currentCombination, result);
    }
}

