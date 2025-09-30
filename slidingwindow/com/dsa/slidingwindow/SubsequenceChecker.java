//Leetcode Problem Link: https://leetcode.com/problems/is-subsequence/

import java.sql.Time;

public class SubsequenceChecker {
     public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0) {
            return true;
        }

        //return isSubsequenceHelper(s, t);
        return isSubsequenceUsingTwoPointer(s, t);
    }
    
    // Time Complexity: O(|t|), since we scan through t at most once.

    // Space Complexity: O(1).
    private boolean isSubsequenceUsingTwoPointer(String s, String t) {
        int n = s.length();
        int m = t.length();
        
        if(m < n) return false;

        //s is a subsequence of t
        int i = 0; //for s
        int j = 0; //for t

       while(i < n && j < m) {
          if(s.charAt(i) == t.charAt(j)) {
            i++;
          }
          j++;
       }

       return i == s.length();

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
