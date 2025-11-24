/**
 * Leetcode Problem 34: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * Find First and Last Position of an Element in a Sorted Array
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class FirstAndLastPosition {

    public int[] searchRange(int[] nums, int target) {
        return new int[] {
            findFirstOccurrence(nums, target),
            findLastOccurrence(nums, target)
        };
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
