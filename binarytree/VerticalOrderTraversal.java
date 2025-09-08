package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

//LeetCode Problem 987: https://leetcode.com/problems/binary-tree-vertical-order-traversal/

public class VerticalOrderTraversal {

//Let N = number of nodes in the tree

//Step 1: BFS traversal
 // We visit each node exactly once.
 // Work per node = O(1) (insertion into map).
 // ✅ Time = O(N)

 // Step 2: Sorting inside each column

// In the worst case (skewed tree), all nodes can fall into the same column.

 // Sorting all nodes = O(N log N)

 // In general, it’s Σ (kᵢ log kᵢ) where kᵢ = number of nodes in column i.

 // Since Σ kᵢ = N, the upper bound is O(N log N).

// Step 3: Building result

 // Iterating through all columns and lists = O(N)

 // Total Time Complexity: O(N log N)

 //Space Complexity

    // Map storage → stores all nodes with (row, val) → O(N)

    //Queue (BFS) → at most O(N)

    // Result list → O(N)

    // Total Space = O(N)
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        //Key is the vertical position
        Map<Integer, List<int[]>> map = new TreeMap<>();
        Pair rootPair = new Pair(root, 0, 0);

        Queue<Pair> queue = new LinkedList<>();
        queue.add(rootPair);

        while(!queue.isEmpty()) {
            Pair topPair = queue.remove();
            TreeNode temp = topPair.node;
            List<int[]> ds = map.get(topPair.col);

            if(ds == null) {
                ds = new ArrayList<>();
            } 

            ds.add(new int[] { topPair.row, temp.val});

            if(temp.left != null) {
                queue.add(new Pair(temp.left, topPair.row + 1, topPair.col - 1));
            }

            if(temp.right != null) {
                queue.add(new Pair(temp.right, topPair.row + 1, topPair.col + 1));
            }

            map.put(topPair.col, ds);
        }

        for(List<int[]> list : map.values()) {
            list.sort((a,b) -> {
                if(a[0] == b[0]) return a[1] - b[1]; // same row -> smaller value first
                return (a[0] - b[0]) ;                  // smaller row first
            });

            List<Integer> colList = new ArrayList<>();
            for(int[] arr : list) {
                colList.add(arr[1]);
            }
            result.add(colList);
        }

        return result;
    }

    class Pair {
        TreeNode node;
        int row;
        int col;

        public Pair(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }
}
