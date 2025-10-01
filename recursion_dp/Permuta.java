import java.util.ArrayList;
import java.util.List;

//Leetcode problem: https://leetcode.com/problems/permutations/description/
//Leetcode Problem: 46

public class Permuta {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        int n = nums.length;
        boolean[] visited = new boolean[n]; // Track which elements are already used
        permute(nums, visited, ds, result);
        return result;
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
