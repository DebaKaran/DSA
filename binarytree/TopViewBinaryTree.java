package binarytree;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class TopViewBinaryTree {

    // Function to return a list of nodes visible from the top view
    // from left to right in Binary Tree.
    // Time Complexity: O(N) where N is the number of nodes in the tree
    // Space Complexity: O(N) for the storage of nodes
    // (map to store nodes O(n), recursion stack O(h) where h is the height of the tree)
    static ArrayList<Integer> topView(TreeNode root) {
        
        // col → [row, value]
        Map<Integer, int[]> map = new TreeMap<>();
        dfs(root, 0, 0, map);
        
        ArrayList<Integer> result = new ArrayList<>();
        
        for(int[] entry : map.values()) {
            result.add(entry[1]);
        }
        
        return result;
        
    }
    
    // DFS to populate the map with top view nodes
    // col is the horizontal distance, row is the depth
    // If a column is not present in the map or the current row is less than the stored row,
    // we update the map with the current node's value

    private static void dfs(TreeNode node, int row, int col, Map<Integer, int[]> map) {
        if(node == null) {
            return;
        }
        
        if(!map.containsKey(col) || row < map.get(col)[0]) {
             map.put(col, new int[]{row, node.val});
        }
        
        dfs(node.left, row + 1, col - 1, map);
        dfs(node.right, row + 1, col + 1, map);
    }
}
