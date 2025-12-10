package twopointers;

import java.util.Set;
import java.util.TreeSet;

/**
 * LeetCode Problem 26: Remove Duplicates from Sorted Array
 * Given a sorted array nums, remove the duplicates in-place such that each
 * element appears only once and returns the new length.
 */
public class RemoveDuplicatesFromSortedArray {

    // Public API (keeps the original method name expected by LeetCode)
    public int removeDuplicates(int[] nums) {
        return removeDuplicatesUsingSet(nums);
    }

    /**
     * Original approach using extra space (TreeSet) to collect unique values.
     *
     * This keeps the array sorted because TreeSet stores elements in order.
     * 
     * Time Complexity: O(n log m) where n is the length of nums and m is the number of unique elements.
     * Space Complexity: O(m) for the TreeSet storing unique elements.
     */
    private int removeDuplicatesUsingSet(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;                 // empty input -> 0 unique elements
        }
        if (nums.length == 1) {
            return 1;                 // single element -> 1 unique element
        }

        int n = nums.length;

        // Use TreeSet to keep unique elements in sorted order automatically.
        // Note: TreeSet has O(log m) insert time and keeps natural ordering.
        Set<Integer> uniqueSet = new TreeSet<>();

        // Collect unique values from the input array
        for (int i = 0; i < n; i++) {
            if (!uniqueSet.contains(nums[i])) { // avoid redundant adds (contains + add is slightly wasteful)
                uniqueSet.add(nums[i]);
            }
        }

        // k = number of unique elements
        int k = uniqueSet.size();
        int i = 0;

        // Overwrite the input array with the unique, sorted values
        for (Integer val : uniqueSet) {
            nums[i++] = val;
        }

        // Return the count of unique elements as required by LeetCode
        return k;
    }
}
