/**
 * Leetcode #35: https://leetcode.com/problems/search-insert-position/
 * 
 * Search Insert Position
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
      return searchInsertBSearch(nums, target);
    }

    private int searchInsertBSearch(int[] nums, int target) {
        int low = 0; 
        int high = nums.length; // exclusive

        while(low < high) {
            int mid = low + (high - low)/2;
            int val = nums[mid];

            if(val == target) return mid;
            
            if(val > target) {
                high = mid;     // potential position
            } else {
                low = mid + 1;  // go right
            }
        }

        return high; // insertion index
    }
}
