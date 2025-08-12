import java.util.Arrays;
//Leetcode Problem: https://leetcode.com/problems/k-radius-subarray-averages/description/
public class KRadiusSubarrayAverages {
    public int[] getAverages(int[] nums, int k) {
        if(k == 0) return nums;

        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        if(n < (2 * k + 1)) {
            return result;
        }

        long sum = 0;
        for(int i = 0; i < (2 * k); i++) {
            sum += nums[i];
        }
        
        for(int windowStart = 0, windowEnd = 2 * k; windowEnd < n; windowEnd++) {
            sum += nums[windowEnd]; // add the end of window element
            result[k + windowStart] = (int)(sum / (2 * k + 1));
            sum -= nums[windowStart]; 
            windowStart++;
        }
        return result;
    }

}
