
//LeetCode Problem 125: Valid Palindrome

public class ValidPalindrome {
 
    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return true;
        }

       //return isPalindromeRecursion(s);

       return isPalindromeUsingTwoPointers(s, 0, s.length() - 1);
    }
    // Complexity

    // 1: Time Complexity: O(n)

    // Each character is checked at most once by either l or r.

    // Skipping non-alphanumeric chars doesn’t change the O(n).

    // 2: Space Complexity: O(1)

    // No extra data structures (just a few variables).

    // Much better than the recursive + StringBuilder version (which was O(n)).
    
    private boolean isPalindromeUsingTwoPointers(String s, int l, int r) {
        while(l < r) {
            char cl = s.charAt(l);
            if(!Character.isLetterOrDigit(cl)) {
                l++;
                continue;
            }

            char cr = s.charAt(r);
            if(!Character.isLetterOrDigit(cr)) {
                r--;
                continue;
            }

            // Convert uppercase A–Z to lowercase
            if (cl >= 'A' && cl <= 'Z') {
                cl = (char)(cl + 'a' - 'A');
            }

            if (cr >= 'A' && cr <= 'Z') {
                cr = (char)(cr + 'a' - 'A');
            }

            if(cl != cr) return false;
            l++;
            r--;
        }

        return true;
    }

    // 1: Time Complexity: O(n)

    // Filtering: O(n)

    // Palindrome check: O(n)

    // Total still O(n).

    // 2: Space Complexity: O(n)

    // Filtered string: O(n)

    // Recursion stack: O(n)

    // Combined O(n).
    private boolean isPalindromeRecursion(String s) {
        
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Skip non-alphanumeric characters
            if (!Character.isLetterOrDigit(c)) {
                continue;
            }

            // Convert uppercase A–Z to lowercase
            if (c >= 'A' && c <= 'Z') {
                c = (char)(c + 'a' - 'A');
            }

            result.append(c);
        }
        return isPalindrome(result, 0);
    }
    private boolean isPalindrome(StringBuilder s, int index) {
         if (index >= (s.length() / 2)) {
            return true;
        }

        if(s.charAt(index) != s.charAt(s.length() - 1 -index)) {
            return false;
        }

        return isPalindrome(s, index + 1);
    }
}
