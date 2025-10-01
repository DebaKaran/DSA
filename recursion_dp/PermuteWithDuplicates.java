import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//Leetcode Problem: 47
//https://leetcode.com/problems/permutations-ii/description/

public class PermuteWithDuplicates {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        /**List<Integer> ds = new ArrayList<>();
        int n = nums.length;
        boolean[] visited = new boolean[n]; // Track which elements are already used
        permute(nums, visited, ds, result); */
        return result;
    }

     private void permute(int[] nums, int idx, List<List<Integer>> result) {

        // Base case: if we reach the end of the array, convert nums[] into a list
        if (idx >= nums.length) {
            List<Integer> list = Arrays.stream(nums) // convert int[] to IntStream
                    .boxed() // convert each int to Integer
                    .collect(Collectors.toList()); // collect into List<Integer>
            result.add(list); // add permutation to result
            return;
        }

        // HashSet to track which numbers have been used at this recursion depth
        // This is required because in swap-based recursion, duplicates can appear
        // in different positions due to swaps. Using 'seen' ensures we don't pick
        // the same value more than once for the current position 'idx'.
        Set<Integer> seen = new HashSet<>();

        for (int i = idx; i < nums.length; i++) {

            // Skip if this number has already been used at this recursion level
            if (seen.contains(nums[i])) {
                continue;
            }

            seen.add(nums[i]);       // mark number as used for this depth
            swap(nums, i, idx);      // place nums[i] at position idx
            permute(nums, idx + 1, result); // recurse for next index
            swap(nums, i, idx);      // backtrack: restore original array
        }
    }

    // Helper method to swap two elements in an array
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // ⏱1: Time Complexity

    // There are n! / (k1! * k2! * ... * kr!) unique permutations, where k1, k2, … are counts of duplicates.

    // For each valid permutation, constructing/copying costs O(n).

    // So overall O(n × n! / (k1! * k2! * … * kr!)).

    // In the worst case (all unique elements) → O(n × n!).

    // 2: Space Complexity

    // Recursion depth: O(n).

    // visited[] array: O(n).

    // Result storage: O(n × n! / (k1! * k2! * … * kr!)).

    // Overall → O(n × n! / (k1! * k2! * … * kr!)).

    private void permute(int[] nums, boolean[] visited, List<Integer> ds, List<List<Integer>> result) {
        // Base case: if current permutation is complete
        if (ds.size() == nums.length) {
            result.add(new ArrayList<>(ds)); // Add snapshot of current permutation
            return;
        }

        // Try all unused elements for the next position
        for (int i = 0; i < nums.length; i++) {
            // Skip duplicates:
            // 1. nums[i] == nums[i-1] → current number is duplicate
            // 2. !visited[i-1] → only skip if the previous duplicate wasn't used in this path
            if(i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }

            if (!visited[i]) {
                // Choose nums[i]
                visited[i] = true;
                ds.add(nums[i]);

                // Explore further
                permute(nums, visited, ds, result);

                // Backtrack (undo the choice)
                visited[i] = false;
                ds.remove(ds.size() - 1);
            }
        }
    }
}
