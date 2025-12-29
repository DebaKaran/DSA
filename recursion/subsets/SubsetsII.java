package recursion.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {

    /**
     * LeetCode 90: Subsets II
     * Generates all unique subsets of the given array.
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        // Sort to bring duplicates together
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        generateUniqueSubsets(nums, 0, new ArrayList<>(), result);

        return result;
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

            // Skip duplicates at the same recursion level
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }

            // Include current element
            currentSubset.add(nums[i]);

            // Recurse with next index
            generateUniqueSubsets(nums, i + 1, currentSubset, result);

            // Backtrack
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}
