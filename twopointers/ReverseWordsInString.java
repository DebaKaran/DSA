package twopointers;

/**
 * Solution class for LeetCode 151: Reverse Words in a String.
 * Uses trimming, splitting, and a two-pointer reversal on the words array.
 */
public class ReverseWordsInString {

    // Public API expected by LeetCode
    public String reverseWords(String s) {
        return reverseWordsTwoPointers(s);
    }

    /**
     * Reverses the order of words using two-pointers.
     *
     * Time Complexity:  O(n)
     * Space Complexity: O(n)   // due to split() + new string returned
     */
    private String reverseWordsTwoPointers(String s) {
        if (s == null || s.length() == 0) {
            return s;  // nothing to reverse
        }

        // Remove leading/trailing spaces
        s = s.trim();

        // Split by one or more spaces using regex
        String[] words = s.split("\\s+");

        int left = 0;
        int right = words.length - 1;

        // Reverse the array of words in-place using two pointers
        while (left < right) {
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;

            left++;
            right--;
        }

        // Join back into a single string with single spaces
        return String.join(" ", words);
    }
}

