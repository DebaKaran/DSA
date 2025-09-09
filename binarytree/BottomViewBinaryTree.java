package binarytree;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class BottomViewBinaryTree {

    public ArrayList<Integer> bottomView(TreeNode root) {
       // col → [row, value]
        Map<Integer, int[]> map = new TreeMap<>();
        dfs(root, 0, 0, map);
        
        ArrayList<Integer> result = new ArrayList<>();
        
        for(int[] entry : map.values()) {
            result.add(entry[1]);
        }
        
        return result;
    }
    
    private static void dfs(TreeNode node, int row, int col, Map<Integer, int[]> map) {
        if(node == null) {
            return;
        }
        
         // Always keep the node with the max row (lowest node) for this col
        if(!map.containsKey(col) || row >= map.get(col)[0]) {
             map.put(col, new int[]{row, node.val});
        }
        
        dfs(node.left, row + 1, col - 1, map);
        dfs(node.right, row + 1, col + 1, map);
    }
}
