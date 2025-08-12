//Leetcode link: https://leetcode.com/problems/contains-duplicate-ii/

import java.util.HashSet;
import java.util.Set;

class ContainsNearbyDuplicateChecker {

    //Time complexity is O(n) and space is O(k).
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(k == 0) return false;
        int n = nums.length;
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < n; i++) {
            if(set.contains(nums[i])) {
                return true;
            }

            set.add(nums[i]);

            if(i >= k) {
                set.remove(nums[i - k]);
            }
        }

        return false;
    }
}