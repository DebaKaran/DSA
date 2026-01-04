package slidingwindows;

class MaxSumSubarrayOfSizeK {

    public int findMaxSumSubarrayOfSizeK(int[] numbers, int k) {
        return findMaxSumUsingSlidingWindow(numbers, k);
    }

    /**
     * Sliding Window approach to find the maximum sum of
     * any contiguous subarray of size k.
     *
     * @param numbers input array
     * @param k fixed window size
     * @return maximum sum of any subarray of size k
     * 
     * Time Complexity: O(N) where N is the number of elements in the input array.
     * Space Complexity: O(1) as we are using only a constant amount of extra
     */
    private int findMaxSumUsingSlidingWindow(int[] numbers, int k) {

        int windowSum = 0;
        int maximumSum = 0;

        // windowEnd expands the sliding window
        for (int windowEnd = 0; windowEnd < numbers.length; windowEnd++) {

            // Add the next element to the window
            windowSum += numbers[windowEnd];

            // Once we hit window size k, start sliding
            if (windowEnd >= k - 1) {

                // Update maximum sum for the current window
                maximumSum = Math.max(maximumSum, windowSum);

                // Remove the element going out of the window
                windowSum -= numbers[windowEnd - k + 1];
            }
        }

        return maximumSum;
    }
}
