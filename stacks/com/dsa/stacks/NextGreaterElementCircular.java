import java.util.Arrays;

public class NextGreaterElementCircular {
    public int[] nextGreaterElements(int[] nums) {
        return nextGreaterElementsUsingBruteForce(nums);
    }

    //Time complexity: O(n^2) due to double iteration over elements.
    // Space complexity: O(n) for storing results.

    private int[] nextGreaterElementsUsingBruteForce(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        for(int i = 0; i < n; i++) {
            boolean isGreaterNumberAvailable = false;
            for(int j = i + 1; j < i + n; j++) {
                int index = j % n;
                if(nums[index] > nums[i]) {
                    result[i] = nums[index];
                    isGreaterNumberAvailable = true;
                    break;
                }
            }
            if(!isGreaterNumberAvailable) {
                result[i] = -1;
            }
        }

        return result;
    }
}
