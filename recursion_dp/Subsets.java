import java.util.ArrayList;
import java.util.List;

//Leetcode problem: https://leetcode.com/problems/subsets/description/
//Leetcode Problem: 78

public class Subsets {
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();

        // Start backtracking from index 0
        subsets(nums, 0, ds, result);
        return result;
    }

    //1: Time Complexity

    // Each element has 2 choices: include or exclude.

    // Total subsets = 2^n.

    // For each subset, making a copy (new ArrayList<>(ds)) takes O(n) in the worst case.
    // Time = O(n * 2^n)

    // 2: Space Complexity

    // Recursion depth = O(n) (call stack).

    // Result storage = O(n * 2^n) (since there are 2^n subsets, each up to length n).
    // Space = O(n * 2^n)

    private void subsets(int[] nums, int idx, List<Integer> ds, List<List<Integer>> result) {
        if (idx == nums.length) {
            // Base case: processed all elements, add the current subset
            result.add(new ArrayList<>(ds));
            return;
        }

        // Choice 1: include current element
        ds.add(nums[idx]);
        // Recurse with next index
        subsets(nums, idx + 1, ds, result);

        // Backtrack (remove the element before exploring the "not pick" branch)
        ds.remove(ds.size() - 1);

        // Choice 2: exclude current element
        subsets(nums, idx + 1, ds, result);
    }
}
