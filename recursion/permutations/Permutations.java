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
        //boolean[] used = new boolean[nums.length];

        //backtrack(nums, used, new ArrayList<>(), result);
        backtrack(nums, 0, result);
        return result;
    }

    /**
     * Backtracking helper that fixes elements position by position
     * by swapping elements in-place.
     * 
     * Time Complexity: O(n * n!) where n is the length of nums
     * There are n! permutations and Each permutation copy costs O(n)
     * Space Complexity: O(n) for recursion stack
     * 
     */
    private void backtrack(
            int[] nums,
            int position,
            List<List<Integer>> result
    ) {

        // Base case: all positions fixed -> one permutation formed
        if (position == nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }

        // Try placing each possible element at the current position
        for (int i = position; i < nums.length; i++) {

            // Place nums[i] at current position
            swap(nums, position, i);

            // Recurse to fix next position
            backtrack(nums, position + 1, result);

            // Backtrack: restore original order
            swap(nums, position, i);
        }
    }

    /**
     * Swaps two elements in the array.
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
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
