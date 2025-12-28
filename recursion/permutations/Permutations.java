package recursion.permutations;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    /**
     * LeetCode 46: Permutations
     * Generates all permutations of the given array.
     */
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        backtrack(nums, used, new ArrayList<>(), result);
        return result;
    }

    /**
     * Backtracking helper that builds permutations position by position.
     * 
     * Time Complexity: O(n * n!) where n is the length of nums
     * There are n! permutations and Each permutation copy costs O(n)
     * Space Complexity: O(n) for recursion stack and used array
     * 
     */
    private void backtrack(
            int[] nums,
            boolean[] used,
            List<Integer> currentPermutation,
            List<List<Integer>> result
    ) {

        // Base case: one complete permutation built
        if (currentPermutation.size() == nums.length) {
            result.add(new ArrayList<>(currentPermutation));
            return;
        }

        // Try placing each unused element at the current position
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {

                // Choose
                used[i] = true;
                currentPermutation.add(nums[i]);

                // Explore
                backtrack(nums, used, currentPermutation, result);

                // Un-choose (backtrack)
                currentPermutation.remove(currentPermutation.size() - 1);
                used[i] = false;
            }
        }
    }
}
