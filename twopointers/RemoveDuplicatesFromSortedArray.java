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
        // return removeDuplicatesWithoutExtraSpace(nums);
        // return removeDuplicatesUsingSet(nums);
        return removeDuplicatesWithoutExtraSpaceImproved(nums);
    }

    private int removeDuplicatesWithoutExtraSpaceImproved(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // slow pointer: position of last unique element
        int slow = 0;

        // fast pointer scans through array
        for (int fast = 1; fast < nums.length; fast++) {

            // When a new unique element is found
            if (nums[fast] != nums[slow]) {
                slow++;                 // advance slow pointer
                nums[slow] = nums[fast]; // place new unique element
            }
        }

        // Length of unique portion is slow + 1
        return slow + 1;
    }

    /**
     * Removes duplicates from a sorted array using an in-place logic.
     * Uses two pointers (slow & fast), scanning through consecutive duplicates.
     *
     * Time Complexity:  O(n)
     * Space Complexity: O(1)
     */
    private int removeDuplicatesWithoutExtraSpace(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;   // no elements → no uniques
        }
        if (nums.length == 1) {
            return 1;   // single element → already unique
        }

        int n = nums.length;

        int slow = 0;   // position where next unique value should be written
        int fast = 0;   // scanning pointer
        int uniqueCount = 0;

        while (fast < n) {

            // Move fast pointer forward as long as duplicates continue
            while (fast + 1 < n && nums[fast] == nums[fast + 1]) {
                fast++;   // skip duplicate values
            }

            // Now nums[fast] is the last occurrence of a unique block
            // Swap unique element into correct "slow" position
            int temp = nums[slow];
            nums[slow] = nums[fast];
            nums[fast] = temp;

            slow++;        // move slow to next empty slot
            fast++;        // move fast to next group
            uniqueCount++; // we've placed one more unique value
        }

        return uniqueCount;
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
