/**
 * LeetCode Problem: 34. Find First and Last Position of Element in Sorted Array
 * URL: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 
 * Take U Forward
 *
 * Count Occurrences in a Sorted Array
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class CountOccurrencesInSortedArray {

    public int countOccurrences(int[] arr, int target) {
        int first = findFirstOccurrence(arr, target);
        if (first == -1) {
            return 0;  // target does not exist
        }

        int last = findLastOccurrence(arr, target);
        return last - first + 1;
    }

    private int findFirstOccurrence(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int val = nums[mid];

            if (val >= target) {
                if (val == target) {
                    result = mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    private int findLastOccurrence(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int val = nums[mid];

            if (val <= target) {
                if (val == target) {
                    result = mid;
                }
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }
}
