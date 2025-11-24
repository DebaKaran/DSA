
/**
 * LeetCode Problem 153: Find Minimum in Rotated Sorted Array
 * URL: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array
 * 
 * Find Minimum in Rotated Sorted Array (LeetCode 153)
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        return findMinimum(nums);
    }

    private int findMinimum(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int result = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int val = nums[mid];
            
            //find the sorted array section
            if(nums[low] <= val) {
                //left part is sorted
                result = result <= nums[low] ? result : nums[low];
                low = mid + 1;
            } else {
                //right part is sorted
                result = result <= val ? result : val;
                high = mid - 1;
            }
        }
        return result;
    }
}
