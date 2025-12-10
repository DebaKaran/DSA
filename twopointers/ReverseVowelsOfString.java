package twopointers;

class ReverseVowelsOfString {

    // Main API method: clean name, clear intent
    public String reverseVowels(String input) {
        return reverseVowelsTwoPointers(input);
    }

    /**
     * Two-pointer solution for reversing only vowels in a string.
     *
     * Time Complexity:  O(n)
     * Space Complexity: O(n)  // because char[] copy is used
     */
    private String reverseVowelsTwoPointers(final String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }

        int left = 0;
        int right = input.length() - 1;
        char[] result = input.toCharArray();  // work on the array directly

        while (left < right) {

            // Move left pointer until a vowel is found
            while (left < right && !isVowel(result[left])) {
                left++;
            }

            // Move right pointer until a vowel is found
            while (left < right && !isVowel(result[right])) {
                right--;
            }

            // Swap vowels
            char temp = result[left];
            result[left] = result[right];
            result[right] = temp;

            left++;
            right--;
        }

        return new String(result);
    }

    // Utility: check vowel in O(1) using indexOf
    private boolean isVowel(final char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }
}
