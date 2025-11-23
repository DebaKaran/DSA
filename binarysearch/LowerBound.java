/**
 * lowerBound - returns the first index i such that nums[i] >= x.
 * If no such index exists, returns nums.length.
 *
 * Time: O(log n)
 * Space: O(1)
 */
public class LowerBound {

    public int lowerBound(int[] nums, int x) {
        if (nums == null || nums.length == 0) return 0;
        int low = 0;
        int high = nums.length - 1;
        int result = nums.length; // default when not found

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= x) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }
}
