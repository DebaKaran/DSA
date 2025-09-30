import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Leetcode Problem: 90
//https://leetcode.com/problems/subsets-ii/description/

public class SubsetsWithDuplicates {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        
        // Sort so that duplicates are adjacent → easy to skip later
        Arrays.sort(nums);
        
        // Start backtracking
        subsetsWithDup(nums, 0, ds, result);
        return result;
    }
    
    //1: Time Complexity

    // Each number can either be included or excluded → at most 2^n subsets.

    // Sorting: O(n log n)

    // Building subsets: O(2^n * n) (because copying ds costs up to O(n) per subset).

    // Overall: O(n log n + n * 2^n)

    // 2: Space Complexity

    // Recursion depth: O(n) (call stack).

    // Temporary list ds: O(n).

    // Result storage: O(n * 2^n) (to store all subsets).

    // Overall: O(n * 2^n)
    private void subsetsWithDup(int[] nums, int idx, List<Integer> ds, List<List<Integer>> result) {
        // Add the current subset (every prefix path is valid subset)
        result.add(new ArrayList<>(ds));

        // Explore choices starting from idx
        for (int i = idx; i < nums.length; i++) {
            // Skip duplicates: only take the first occurrence in this recursive call
            if (i != idx && nums[i] == nums[i - 1]) continue;

            // Choose nums[i]
            ds.add(nums[i]);

            // Recurse with next index
            subsetsWithDup(nums, i + 1, ds, result);

            // Backtrack (undo the choice)
            ds.remove(ds.size() - 1);
        }
    }
}
