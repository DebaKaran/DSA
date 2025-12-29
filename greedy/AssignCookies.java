package greedy;

import java.util.Arrays;

/**
 * LeetCode 455 - Assign Cookies
 *
 * Greedy strategy:
 *  - Sort children by increasing greed
 *  - Sort cookies by increasing size
 *  - Always try to satisfy the least greedy child
 *    using the smallest sufficient cookie
 */
public class AssignCookies {

    /**
     * Returns the maximum number of content children.
     *
     * @param greedFactors greed factor of each child
     * @param cookieSizes  size of each cookie
     * @return number of content children
     * 
     * Time Complexity A: Sorting children: O(n log n)  B: Sorting cookies: O(m log m) C: Two-pointer scan: O(n + m)

     * Overall: O(n log n + m log m)

        * Space Complexity: O(1) if sorting in place, otherwise O(n + m) for sorted copies
     */
    public int findMaximumContentChildren(int[] greedFactors, int[] cookieSizes) {

        // Sort both arrays to enable greedy matching
        Arrays.sort(greedFactors);
        Arrays.sort(cookieSizes);

        int childIndex = 0;   // pointer for greedFactors
        int cookieIndex = 0;  // pointer for cookieSizes
        int satisfiedChildren = 0;

        // Try to assign cookies greedily
        while (childIndex < greedFactors.length && cookieIndex < cookieSizes.length) {

            // If current cookie can satisfy current child
            if (cookieSizes[cookieIndex] >= greedFactors[childIndex]) {
                satisfiedChildren++;
                childIndex++;   // move to next child
            }
            cookieIndex++;
        }

        return satisfiedChildren;
    }
}
