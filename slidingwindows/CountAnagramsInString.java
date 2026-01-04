package slidingwindows;

import java.util.HashMap;
import java.util.Map;

// User function Template for Java

//GFG Problem Link: https://practice.geeksforgeeks.org/problems/count-anagrams-in-string/1

class CountAnagramsInString {

    public int countAnagrams(String pattern, String text) {
        return countAnagramsUsingSlidingWindow(pattern, text);
    }

    /**
     * Counts the number of anagram occurrences of `pattern`
     * in the given `text` using the Sliding Window technique.
     *
     * Sliding Window Invariant:
     * remainingDistinctChars == 0
     *  => all character frequencies are perfectly matched
     */
    private int countAnagramsUsingSlidingWindow(String pattern, String text) {

        // Frequency map for characters in the pattern
        Map<Character, Integer> patternFreqMap = new HashMap<>();
        for (char ch : pattern.toCharArray()) {
            patternFreqMap.put(ch, patternFreqMap.getOrDefault(ch, 0) + 1);
        }

        int patternLength = pattern.length();
        int remainingDistinctChars = patternFreqMap.size(); // key invariant
        int anagramCount = 0;

        int left = 0;

        // Expand window using right pointer
        for (int right = 0; right < text.length(); right++) {

            char enteringChar = text.charAt(right);

            // Process entering character
            if (patternFreqMap.containsKey(enteringChar)) {
                patternFreqMap.put(
                        enteringChar,
                        patternFreqMap.get(enteringChar) - 1
                );

                // Character frequency perfectly matched
                if (patternFreqMap.get(enteringChar) == 0) {
                    remainingDistinctChars--;
                }
            }

            // Shrink window if it exceeds the pattern length
            if (right - left + 1 > patternLength) {
                char exitingChar = text.charAt(left);

                if (patternFreqMap.containsKey(exitingChar)) {

                    // Reintroducing mismatch
                    if (patternFreqMap.get(exitingChar) == 0) {
                        remainingDistinctChars++;
                    }

                    patternFreqMap.put(
                            exitingChar,
                            patternFreqMap.get(exitingChar) + 1
                    );
                }
                left++;
            }

            // Valid anagram found
            if (right - left + 1 == patternLength
                    && remainingDistinctChars == 0) {
                anagramCount++;
            }
        }

        return anagramCount;
    }
}
