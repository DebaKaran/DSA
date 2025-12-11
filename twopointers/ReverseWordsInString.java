package twopointers;

/**
 * Solution class for LeetCode 151: Reverse Words in a String.
 * Uses trimming, splitting, and a two-pointer reversal on the words array.
 */
public class ReverseWordsInString {

    // Public API expected by LeetCode
    public String reverseWords(String s) {
        //return reverseWordsTwoPointers(s);

        if(s == null || s.length() == 0) {
            return s; 
        }

        // Step 1: Trim leading/trailing and normalize internal spaces into a single space
        char[] chars = cleanSpaces(s.toCharArray());
        int n = chars.length;

        // Step 2: Reverse the entire character array
        reverse(chars, 0, n - 1);

        // Step 3: Reverse each word inside the reversed array
        reverseEachWord(chars, n);

        return new String(chars);
    }

     // Reverse each word inside the char array
    private void reverseEachWord(char[] arr, int n) {
        int start = 0;

        while (start < n) {
            int end = start; // initialize end to start for each word

            // Move end to the end of the word
            while (end < n && arr[end] != ' ') {
                end++;
            }

            // Reverse the word between start and end - 1
            reverse(arr, start, end - 1);

            // Move to next word (skip the space)
            start = end + 1;
        }
    }

    // Reverse characters from left to right indices
    private void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    private char[] cleanSpaces(final char[] arr) {
        int n = arr.length;
        StringBuilder sb = new StringBuilder();

        int i = 0;

        // Skip leading spaces
        while (i < n && arr[i] == ' ') i++;

        while (i < n) {
            // Copy non-space characters
            while (i < n && arr[i] != ' ') {
                sb.append(arr[i]);
                i++; //
            }

            // Skip intermediate spaces
            while (i < n && arr[i] == ' ') i++;

            // If not end, append a single space
            if (i < n) sb.append(' ');
        }

        return sb.toString().toCharArray();
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

