import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Leetcode problem: https://leetcode.com/problems/permutations/description/
//Leetcode Problem: 46

public class Permuta {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        /**List<Integer> ds = new ArrayList<>();
        int n = nums.length;
        boolean[] visited = new boolean[n]; // Track which elements are already used
        permute(nums, visited, ds, result); */
        permute(nums, 0, result);
        return result;
    }

    //1:  Time Complexity

    // Each recursive call fixes one index and tries all possible numbers at that position.

    // There are n! permutations, and for each permutation, converting the array to a list costs O(n).

    // Total time complexity: O(n × n!)

    // 2: Space Complexity

    // Recursion depth: O(n) (for call stack).

    // Result storage: O(n × n!) (all permutations stored).

    // No extra visited[] is used like the previous approach; swaps are in-place.

    private void permute(int[] nums, int idx, List<List<Integer>> result) {

        // Base case: if we reach the end of the array, convert nums[] into a list
        if (idx >= nums.length) {
            List<Integer> list = Arrays.stream(nums) // convert int[] to IntStream
                                    .boxed()      // convert each int to Integer
                                    .collect(Collectors.toList()); // collect into List<Integer>
            result.add(list); // add permutation to result
            return;
        }

        // Try all possible numbers at current index by swapping
        for (int i = idx; i < nums.length; i++) {
            swap(nums, i, idx);              // place nums[i] at position idx
            permute(nums, idx + 1, result);  // recurse for next index
            swap(nums, i, idx);              // backtrack: restore original array
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 1: Time Complexity

    // There are n! permutations.

    // For each permutation, we spend O(n) to copy the current list (new ArrayList<>(ds)).
    
    // Time = O(n · n!)

    // 2: Space Complexity

    // Visited array: O(n)

    // Recursion depth: O(n) (stack)

    // Result storage: O(n · n!)
    // Space = O(n · n!)

    private void permute(int[] nums, boolean[] visited, List<Integer> ds, List<List<Integer>> result) {
        // Base case: if current permutation is complete
        if (ds.size() == nums.length) {
            result.add(new ArrayList<>(ds)); // Add snapshot of current permutation
            return;
        }

        // Try all unused elements for the next position
        for (int i = 0; i < nums.length; i++) {
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
