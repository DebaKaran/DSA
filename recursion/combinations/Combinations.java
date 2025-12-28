package recursion.combinations;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    /**
     * LeetCode 77: Combinations
     * Generates all combinations of k numbers chosen from 1 to n.
     */
    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> result = new ArrayList<>();
        backtrack(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    /**
     * Backtracking helper that builds combinations incrementally.
     * Time Complexity: O(C(n, k)) where C is the binomial coefficient
     * 
     * A: There are C(n, k) combinations possible. Each combination takes O(k) time to construct.
     * 
     * Space Complexity: O(k) for recursion stack
     */
    private void backtrack(
            int n,
            int k,
            int start,
            List<Integer> currentCombination,
            List<List<Integer>> result
    ) {

        // Base case: required k elements selected
        if (k == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }

        // Pruning: not enough numbers left to fill k slots
        if (start > n - k + 1) {
            return;
        }

        // Choice 1: include current number
        currentCombination.add(start);
        backtrack(n, k - 1, start + 1, currentCombination, result);
        currentCombination.remove(currentCombination.size() - 1); // backtrack

        // Choice 2: exclude current number
        backtrack(n, k, start + 1, currentCombination, result);
    }
}
