package recursion;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    /**
     * LeetCode 78: Subsets
     * Generates all possible subsets of the given array.
     */
    public List<List<Integer>> generateSubsets(int[] nums) {

        List<List<Integer>> allSubsets = new ArrayList<>();
        backtrack(nums, 0, allSubsets, new ArrayList<>());

        return allSubsets;
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
