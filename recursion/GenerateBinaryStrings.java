package recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateBinaryStrings {

    /**
     * Generates all binary strings of length n
     * such that no two consecutive '1's appear.
     * 
     * This problem is similar to generating balanced parentheses,
     * but with a different constraint on character placement.
     * 
     * Time Complexity: O(2^n) in the worst case, as we explore all combinations.
     * Space Complexity: O(n) for the recursion stack and current string storage.
     */
    public List<String> generateBinaryStrings(int n) {

        List<String> result = new ArrayList<>();
        backtrack(n, 0, false, new StringBuilder(), result);
        return result;
    }

    /**
     * Backtracking helper that builds valid binary strings.
     */
    private void backtrack(
            int n,
            int index,
            boolean lastWasOne,
            StringBuilder current,
            List<String> result
    ) {

        // Base case: built a string of length n
        if (index == n) {
            result.add(current.toString());
            return;
        }

        // Choice 1: always allowed to add '0'
        current.append('0');
        backtrack(n, index + 1, false, current, result);
        current.deleteCharAt(current.length() - 1); // backtrack

        // Choice 2: add '1' only if last character was not '1'
        if (!lastWasOne) {
            current.append('1');
            backtrack(n, index + 1, true, current, result);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }
}
