/**
 * LeetCode Problem: 81. Search in Rotated Sorted Array II
 * URL: https://leetcode.com/problems/search-in-rotated-sorted-array-ii
 * 
 * Search in Rotated Sorted Array II (LeetCode 81)
 *
 * Time Complexity: O(log n) average, O(n) worst-case due to duplicates
 * Space Complexity: O(1)
 */
public class SearchInRotatedSortedArrayWithDuplicates {

    public boolean search(int[] nums, int target) {
        return searchTarget(nums, target);
    }

    private boolean searchTarget(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int val = nums[mid];

            if (val == target) {
                return true;
            }

            // Key step to handle duplicates
            if (nums[low] == val && nums[high] == val) {
                low++;
                high--;
                continue;
            }

            // Left side is sorted
            if (nums[low] <= val) {
                if (target >= nums[low] && target < val) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // Right side is sorted
            else {
                if (target > val && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return false;
    }
}
