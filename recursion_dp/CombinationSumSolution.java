import java.util.ArrayList;
import java.util.List;

//LeetCode 39
class CombinationSumSolution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        combinationSum(candidates, target, 0, result, list);
        return result;
    }   

    //TC: O(2^n)
    //SC: O(n)
    
    private void combinationSum(int[] candidates, int target, int idx, 
        List<List<Integer>> result, List<Integer> list) {
            if(idx >= candidates.length) {
                if(target == 0) {
                    result.add(new ArrayList<>(list));
                }
                return;
            }

            if(target == 0) {
                result.add(new ArrayList<>(list));
                return;
            }
            
            if(target < 0) {
                return;
            }

            //included 
            list.add(candidates[idx]);
            combinationSum(candidates, target - candidates[idx], idx, result, list);

            //excluded - before wehave to backtrack
            list.remove(list.size() - 1);
            combinationSum(candidates, target, idx + 1, result, list);

        }
}