/**
 * Take U Forward
 * Floor and Ceil Finder using Binary Search
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class FloorAndCeilFinder {

    public int[] getFloorAndCeil(int[] nums, int x) {
        int floor = findFloor(nums, x);
        int ceil = findCeil(nums, x);
        return new int[] { floor, ceil };
    }

    private int findFloor(int[] nums, int x) {
        int low = 0;
        int high = nums.length - 1;
        int resultIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int val = nums[mid];

            if (val == x) {
                return val;                   // floor is the number itself
            }

            if (val < x) {
                resultIndex = mid;           // potential floor (value < x)
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return resultIndex == -1 ? -1 : nums[resultIndex];
    }

    private int findCeil(int[] nums, int x) {
        int low = 0;
        int high = nums.length - 1;
        int resultIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int val = nums[mid];

            if (val == x) {
                return val;                   // ceil is the number itself
            }

            if (val > x) {
                resultIndex = mid;           // potential ceil (value > x)
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return resultIndex == -1 ? -1 : nums[resultIndex];
    }
}
