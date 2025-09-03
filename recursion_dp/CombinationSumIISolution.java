import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//LeetCode problem 40

public class CombinationSumIISolution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum(candidates, target, 0, result, list);
        return result;
    }

    //TC: O(2^n)
    //SC: O(n)
    
    private void combinationSum(int[] candidates, int target, int idx, 
        List<List<Integer>> result, List<Integer> list) {
            if(target == 0) {
                result.add(new ArrayList<>(list));
                return;
            }

            for(int i = idx; i < candidates.length; i++) {

                 //skipping
                if(i > idx && candidates[i] == candidates[i - 1]) {
                    continue;
                }

                if(target < candidates[i]) {
                    break;
                }

                list.add(candidates[i]);
                combinationSum(candidates, target - candidates[i], i + 1, result, list);
                list.remove(list.size() - 1);
            }

        }

}
