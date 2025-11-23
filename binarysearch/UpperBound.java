/**
 * Upper Bound - Take U Forward
 * Returns the first index i such that nums[i] > x.
 * If no such index exists, returns nums.length.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class UpperBound {
    public int upperBound(int[] nums, int x) {
        //return upperBoundUsingBinarySearch(nums, x);
        return upperBoundCanonicalForm(nums, x);
        
    }

    public int upperBoundCanonicalForm(int[] nums, int x) {
        int low = 0;
        int high = nums.length; // exclusive

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] <= x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low; // first > x or nums.length
    }

    private int upperBoundUsingBinarySearch(int[] nums, int x) {
        int low = 0;
        int high = nums.length - 1;

        int result = nums.length;

        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(nums[mid] > x) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }
}
