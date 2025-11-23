/**
 * Leetcode 704: https://leetcode.com/problems/binary-search/
 * Implement a basic binary search algorithm to find the index of a target value in a sorted array.
 * If the target is not found, return -1.
 *
 * Basic Binary Search
 */
public class BinarySearchBasic {

    public int search(int[] nums, int target) {
        return binarySearch(nums, target);
    }

    //Time Complexity: O(log n)
    // Space Complexity: O(1)

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

    /**
     * Recursive Binary Search
     *
     * Time Complexity: O(log n)
     * - Each recursive call halves the search space.
     *
     * Space Complexity: O(log n)
     * - Recursion stack grows in proportion to the height of the recursion tree.
     * - Worst case depth is still O(log n), since the array is halved every step.
    */

    private int recursiveBinarySearch(int[] nums, int target, int low, int high) {
        if (low > high) {
            return -1; // Base case: target not found
        }

        int mid = low + (high - low) / 2;

        if (nums[mid] == target) {
            return mid; // Target found
        } else if (nums[mid] < target) {
            return recursiveBinarySearch(nums, target, mid + 1, high); // Search in the right half
        } else {
            return recursiveBinarySearch(nums, target, low, mid - 1); // Search in the left half
        }
    }
}
