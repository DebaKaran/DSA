public class FixedBoundSubarraysCounter {

    public long countSubarrays(int[] nums, int minK, int maxK) {
        int lastMinPos = -1;   // last position where we saw minK
         int lastMaxPos = -1;   // last position where we saw maxK
         int lastBadPos = -1;   // last position where we saw an out-of-bound number

        int n = nums.length;
        long result = 0;

        for(int i = 0; i < n; i++) {
            if(nums[i] == minK) {
                lastMinPos = i;
            }

            if(nums[i] == maxK) {
                lastMaxPos = i;
            }
            
            if(nums[i] > maxK || nums[i] < minK) {
                lastBadPos = i;
                lastMinPos = -1;   
                lastMaxPos = -1;
            }
            if(lastMinPos != -1 && lastMaxPos != -1) {
                 result += (Math.min(lastMinPos, lastMaxPos) - lastBadPos);
            }
           
        }
        return result;
        
    }
}
