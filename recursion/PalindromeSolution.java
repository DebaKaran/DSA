
//Leetcode Problem 125: Valid Palindrome
//https://leetcode.com/problems/valid-palindrome/description/

public class PalindromeSolution {
 public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        return isPalindromeIterative(s);
    }

    // 1: Time Complexity:
    // O(n) - n is the length of the string
    // 2: Space Complexity:
    // O(1) - constant space
    
    private boolean isPalindromeIterative(String s) {
        char[] chArray = s.toCharArray();
        int n = chArray.length;
        int i = 0;
        int j = n - 1;

        while (i < j) {
            // skip non-alphanumeric from left
            while (i < j && !Character.isLetterOrDigit(chArray[i])) {
                i++;
            }

            // skip non-alphanumeric from right
            while (i < j && !Character.isLetterOrDigit(chArray[j])) {
                j--;
            }

            // compare
            if (chArray[i] != chArray[j]) {
                return false;
            }

            i++;
            j--;
        }
        return true;
    }
}
