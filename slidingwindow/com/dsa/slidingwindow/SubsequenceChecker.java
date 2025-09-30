//Leetcode Problem Link: https://leetcode.com/problems/is-subsequence/

import java.sql.Time;

public class SubsequenceChecker {
     public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0) {
            return true;
        }

        return isSubsequenceHelper(s, t);
        
    }

    // 1: Time Complexity

    // Outer loop: runs up to s.length() iterations.

    // Inner while loop: j moves forward through t only once (no backtracking).

    // Total work: O(|t|) (not O(|s| × |t|), since j never resets).

    // ✅ Time Complexity: O(|t|)

    // 2: Space Complexity

    // Uses a few variables (i, j, flag) and no extra data structures.

    // Space Complexity: O(1)
    
    private boolean isSubsequenceHelper(String s, String t) {

       int j = 0;

        for (int i = 0; i < s.length(); i++) {
            boolean flag = false;
            while (j < t.length()) {
                if (s.charAt(i) == t.charAt(j)) {
                    flag = true;
                }
                j++;
                if (flag) {
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }

}
