package recursion.subsets;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    /**
     * LeetCode 78: Subsets
     * Generates all possible subsets of the given array.
     */
    public List<List<Integer>> generateSubsets(int[] nums) {

        List<List<Integer>> allSubsets = new ArrayList<>();
       /**backtrack(nums, 0, result, new ArrayList<>());

        return allSubsets; */
        generateUniqueSubsets(nums, 0, new ArrayList<>(), allSubsets);

        return allSubsets;
    }  

    /**
     * Backtracking helper.
     *
     * At every recursion level, the current subset itself
     * is a valid subset and must be added to the result.
     * 
     * Time Complexity: O(N * 2^N) where N is the length of nums.
     * Space Complexity: O(N * 2^N) for storing all subsets.
     */
    private void generateUniqueSubsets(
            int[] nums,
            int startIndex,
            List<Integer> currentSubset,
            List<List<Integer>> result
    ) {

        // Every recursion state represents a valid subset
        result.add(new ArrayList<>(currentSubset));

        for (int i = startIndex; i < nums.length; i++) {

            // Include current element
            currentSubset.add(nums[i]);

            // Recurse with next index
            generateUniqueSubsets(nums, i + 1, currentSubset, result);

            // Backtrack
            currentSubset.remove(currentSubset.size() - 1);
        }
    }

    /**
     * Backtracking helper method.
     * At each index, we decide whether to include or exclude the element.
     * 
     * Time Complexity: O(n * 2^n), where n is the number of elements in nums.
     * There are 2ⁿ subsets and Copying each subset takes up to O(n)
     * 
     * Space Complexity: O(n), the maximum depth of the recursion tree.
     */
    private void backtrack(
            int[] nums,
            int index,
            List<List<Integer>> allSubsets,
            List<Integer> currentSubset
    ) {

        // Base case: all decisions made
        if (index == nums.length) {
            allSubsets.add(new ArrayList<>(currentSubset));
            return;
        }

        // Choice 1: include current element
        currentSubset.add(nums[index]);
        backtrack(nums, index + 1, allSubsets, currentSubset);

        // Backtrack: remove last element
        currentSubset.remove(currentSubset.size() - 1);

        // Choice 2: exclude current element
        backtrack(nums, index + 1, allSubsets, currentSubset);
    }
}
