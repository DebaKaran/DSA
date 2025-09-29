
//LeetCode Problem 125: Valid Palindrome

public class ValidPalindrome {
    // 1: Time Complexity: O(n)

    // Filtering: O(n)

    // Palindrome check: O(n)

    // Total still O(n).

    // 2: Space Complexity: O(n)

    // Filtered string: O(n)

    // Recursion stack: O(n)

    // Combined O(n).
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        StringBuilder filtered = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // keep only alphanumeric
            if (!Character.isLetterOrDigit(c)) {
                continue;
            }

            // convert uppercase to lowercase
            if (c >= 'A' && c <= 'Z') {
                c = (char) (c + 'a' - 'A');
            }

            filtered.append(c);
        }

        return isPalindromeRecursive(filtered, 0);
    }

    private boolean isPalindromeRecursive(StringBuilder s, int index) {
        if (index >= s.length() / 2) {
            return true;
        }
        if (s.charAt(index) != s.charAt(s.length() - 1 - index)) {
            return false;
        }
        return isPalindromeRecursive(s, index + 1);
    }
}
