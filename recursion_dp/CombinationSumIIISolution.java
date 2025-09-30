import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

//LeetCode 216: https://leetcode.com/problems/combination-sum-iii/description/
public class CombinationSumIIISolution {
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        int[] candidates = new int[]{1,2,3,4,5,6,7,8,9};
        combinationSum3(k, n, candidates, 0, 0, ds, result);
        return result;
    }

    //1: Time Complexity

    // You are exploring all subsets of numbers from 1 to 9.

    // At each index, you have two choices: take or not take.
    // → This gives a recursion tree of size roughly 2^n, where n = 9.

    // But since you also restrict to exactly k numbers and prune when n < 0, the search space is smaller.

    // So, the worst-case time complexity is:
    // O(2^9)=O(512)(constant in practice, since n=9 is fixed)

    // If we generalize for n candidates, it’s:O(2 ^n)

    // Additionally, each valid combination requires copying up to k elements into the result, costing O(k).
    // So final: O(2 ^ n * k)
    // 2:Space Complexity

    // Recursion depth: At most n recursive calls in the stack → O(n)

    // Temporary list (ds): At most size k → O(k)

    // Result storage: In the worst case, number of valid combinations can be large 
    //(but bounded since numbers are from 1–9). 
    //Let’s call total number of solutions m. Storing results costs O(m * k).

    // So total auxiliary (excluding output): O(n+k)
    

    private void combinationSum3(int k, int n, int[] candidates, int index, int totalIndexes, 
                             List<Integer> ds, List<List<Integer>> result) {
        // Base case: if we have picked exactly k numbers
        if (k == totalIndexes) {
            // If the sum is also satisfied, add this combination to result
            if (n == 0) {
                result.add(new ArrayList<>(ds));
            }
            return; // Either way, stop exploring this path
        }

        // If sum goes negative, no point continuing
        if (n < 0) {
            return;
        }

        // If we've run out of numbers to consider, stop
        if (index >= candidates.length) {
            return;
        }

        // ------------------ Choice 1: Take the current number ------------------
        ds.add(candidates[index]); // include current number
        // Move to the next index, increment totalIndexes, and reduce target sum
        combinationSum3(k, n - candidates[index], candidates, index + 1, totalIndexes + 1, ds, result);

        // ------------------ Backtrack & Choice 2: Don't take it ----------------
        ds.remove(ds.size() - 1); // undo the previous choice
        // Move to next index without increasing totalIndexes or reducing sum
        combinationSum3(k, n, candidates, index + 1, totalIndexes, ds, result);
    }
}
