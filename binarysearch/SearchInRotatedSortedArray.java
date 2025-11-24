/**
 * Leetcode Problem 33: https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * Search in Rotated Sorted Array
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        return searchTarget(nums, target);
    }

    private int searchTarget(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int val = nums[mid];

            if (val == target) {
                return mid;
            }

            // Check which side is sorted
            if (nums[low] <= val) {
                // Left side is sorted
                if (target >= nums[low] && target < val) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // Right side is sorted
                if (target > val && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;  // target not found
    }
}
