
//Leetcode Problem 125: Valid Palindrome
//https://leetcode.com/problems/valid-palindrome/description/

public class PalindromeSolution {
public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        char[] chArray = s.toCharArray();
        //return isPalindromeIterative(chArray);

        return isPalindromeRecursive(chArray, 0, chArray.length - 1);
    }

    // 1: Time Complexity:
    // O(n) - n is the length of the string
    // 2: Space Complexity:
    // O(n) - recursive stack space
    
    private boolean isPalindromeRecursive(char[] chArray, int left, int right) {

        // Base case
        if (left >= right) return true;

        // Skip non-alphanumeric from left
        if (!Character.isLetterOrDigit(chArray[left])) {
            return isPalindromeRecursive(chArray, left + 1, right);
        }

        // Skip non-alphanumeric from right
        if (!Character.isLetterOrDigit(chArray[right])) {
            return isPalindromeRecursive(chArray, left, right - 1);
        }

        // Check characters (case-insensitive)

        if(chArray[left] !=chArray[right]) {
            return false;
        }

        // Move inward
        return isPalindromeRecursive(chArray, left + 1, right - 1);
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
