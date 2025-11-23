/**
 * Leetcode 704: https://leetcode.com/problems/binary-search/
 * Implement a basic binary search algorithm to find the index of a target value in a sorted array.
 * If the target is not found, return -1.
 *
 * Basic Binary Search
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class BinarySearchBasic {

    public int search(int[] nums, int target) {
        return binarySearch(nums, target);
    }

    private int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
