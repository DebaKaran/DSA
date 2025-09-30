public class SubsequenceSumCounter {
    
    // https://takeuforward.org/plus/dsa/problems/count-all-subsequences-with-sum-k
    public int countSubsequenceWithTargetSum(int[] nums, int k) {
        // Start recursion from index 0
        return countSubsequenceWithTargetSum(nums, k, 0);
    }

    // 1: Time Complexity  :    O(2^n)
    // 2: Space Complexity :    O(n)  (recursion stack)
    // n = length of input array nums
    private int countSubsequenceWithTargetSum(int[] nums, int k, int index) {
        // Base case: reached the end of the array
        if (index == nums.length) {
            // If remaining target k is 0, this is a valid subsequence
            return (k == 0) ? 1 : 0;
        }

        // If target becomes negative, no valid subsequence can be formed
        if (k < 0) {
            return 0;
        }

        // ------------------ Choice 1: Take current element ------------------
        // Reduce target by nums[index] and move to next index
        int taken = countSubsequenceWithTargetSum(nums, k - nums[index], index + 1);

        // ------------------ Choice 2: Do NOT take current element ------------------
        // Keep target unchanged and move to next index
        int notTaken = countSubsequenceWithTargetSum(nums, k, index + 1);

        // Total subsequences = sum of both choices
        return taken + notTaken;
    }

}
