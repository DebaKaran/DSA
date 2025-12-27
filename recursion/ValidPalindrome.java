package recursion;

public class ValidPalindrome {

    /**
     * LeetCode 125: Valid Palindrome
     * Checks whether the given string is a palindrome,
     * considering only alphanumeric characters and ignoring case.
     */
    public boolean isValidPalindrome(String input) {

        // Null or single-character strings are always palindromes
        if (input == null || input.length() <= 1) {
            return true;
        }

        // Normalize input for case-insensitive comparison
        input = input.toLowerCase();

        return checkPalindromeRecursively(input, 0, input.length() - 1);
    }

    /**
     * Recursive helper method to validate palindrome using two pointers.
     * Time Complexity: O(n)
     * Space Complexity: O(n) due to recursion stack
     * 
     */
    private boolean checkPalindromeRecursively(String s, int left, int right) {

        // Base case: pointers have crossed or met
        if (left >= right) {
            return true;
        }

        // Skip non-alphanumeric character from the left
        if (!Character.isLetterOrDigit(s.charAt(left))) {
            return checkPalindromeRecursively(s, left + 1, right);
        }

        // Skip non-alphanumeric character from the right
        if (!Character.isLetterOrDigit(s.charAt(right))) {
            return checkPalindromeRecursively(s, left, right - 1);
        }

        // Characters must match
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }

        // Move both pointers inward
        return checkPalindromeRecursively(s, left + 1, right - 1);
    }
}
