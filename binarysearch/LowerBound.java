/**
 * lowerBound - returns the first index i such that nums[i] >= x.
 * If no such index exists, returns nums.length.
 *
 * Time: O(log n)
 * Space: O(1)
 */
public class LowerBound {

    public int lowerBound(int[] nums, int x) {
       //return lowerBoundUsingBinarySearch(nums, x);
       return lowerBoundUsingCanonicalForm(nums, x);
     }

     /**
     * lowerBound (canonical form)
     * Returns the first index i such that nums[i] >= x.
     * If no such index exists, returns nums.length.
     *
     * Time: O(log n)
     * Space: O(1)
     */
    
     private int lowerBoundUsingCanonicalForm(int[] nums, int x) {
        int low = 0; 
        int high = nums.length; // notice 'high' is exclusive

        while(low < high) {
            int mid = low + (high - low)/2;
            int val = nums[mid];

            if(val >= x) {
                high = mid; // notice 'high' is exclusive
            } else {
                low = mid + 1;
            }
        }
        return low; // low == high; index of first >= x, or nums.length
     }

     private int lowerBoundUsingBinarySearch(int[] nums, int x) {
        int result = nums.length; // default when not found
        int low = 0; 
        int high = nums.length - 1;

        while(low <= high) {
            int mid = low + (high - low)/2;
            int val = nums[mid];

            if(val >= x) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
     }
}
