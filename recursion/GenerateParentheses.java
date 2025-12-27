package recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    /**
     * LeetCode 22: Generate Parentheses
     * Generates all valid combinations of n pairs of parentheses.
     */
    public List<String> generateParentheses(int n) {

        List<String> result = new ArrayList<>();
        //backtrack(n, 0, new StringBuilder(), result);
        buildParentheses(n, 0, 0, new StringBuilder(), result);
        return result;
    }

    /**
     * Backtracking helper that builds only valid parentheses sequences.
     * Time Complexity: O Catalan number C(n) ~ O(4^n / (n^(3/2)))
     * Space Complexity: O(n) for recursion stack
     */
    private void buildParentheses(
            int n,
            int openCount,
            int closeCount,
            StringBuilder currentSequence,
            List<String> result
    ) {

        // Base case: valid sequence of length 2n built
        if (openCount == n && closeCount == n) {
            result.add(currentSequence.toString());
            return;
        }

        // Choice 1: add '(' if we still can
        if (openCount < n) {
            currentSequence.append('(');
            buildParentheses(n, openCount + 1, closeCount, currentSequence, result);
            currentSequence.setLength(currentSequence.length() - 1); // backtrack
        }

        // Choice 2: add ')' if it keeps the sequence valid
        if (closeCount < openCount) {
            currentSequence.append(')');
            buildParentheses(n, openCount, closeCount + 1, currentSequence, result);
            currentSequence.setLength(currentSequence.length() - 1); // backtrack
        }
    }

    /**
     * Generates all 2^(2n) sequences and validates them.
     * This is a brute-force backtracking approach.
     * 
     * Time Complexity: O(2^(2n) * n) due to generating all sequences and validating each
     * Space Complexity: O(n) for recursion stack and current string
     */
    private void backtrack(
            int n,
            int position,
            StringBuilder current,
            List<String> result
    ) {

        // Base case: built a string of length 2n
        if (position == 2 * n) {
            if (isValidParentheses(current)) {
                result.add(current.toString());
            }
            return;
        }

        // Choice 1: add '('
        current.append('(');
        backtrack(n, position + 1, current, result);
        current.deleteCharAt(current.length() - 1); // backtrack

        // Choice 2: add ')'
        current.append(')');
        backtrack(n, position + 1, current, result);
        current.deleteCharAt(current.length() - 1); // backtrack
    }

    /**
     * Checks if the parentheses sequence is valid.
     */
    private boolean isValidParentheses(StringBuilder s) {

        int balance = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                balance++;
            } else {
                balance--;
            }

            // More closing than opening at any point
            if (balance < 0) {
                return false;
            }
        }

        // All parentheses matched
        return balance == 0;
    }
}

