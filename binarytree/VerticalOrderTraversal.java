package binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

//LeetCode Problem 987: https://leetcode.com/problems/binary-tree-vertical-order-traversal/

public class VerticalOrderTraversal {

//Time Complexity: O(N log N) where N is the number of nodes in the tree
//(collect in O(n), sort O(n log n), grouping O(n))
//Space Complexity: O(N) for the storage of nodes
//(list to store nodes O(n), recursion stack O(h) where h is the height of the tree)
public List<List<Integer>> verticalTraversal(TreeNode root) {
    List<int[]> nodes = new ArrayList<>(); // {col, row, val}

    //Using DFS 
    dfs(root, 0, 0, nodes);

    // Sort by col, then row, then val
    Collections.sort(nodes, (a, b) -> {
        if(a[0] != b[0]) return a[0] - b[0]; //col
        if(a[1] != b[1]) return a[1] - b[1]; //row
        return a[2] - b[2];
    });

    List<List<Integer>> result = new ArrayList<>();
    int prevCol = Integer.MIN_VALUE;

    for(int[] node : nodes) {
        if(node[0] != prevCol) {
            result.add(new ArrayList<>());
            prevCol = node[0];
        }

        result.get(result.size() - 1).add(node[2]);
    }

    return result;
}

private void dfs(TreeNode node, int row, int col, List<int[]> nodes) {
    if(node == null) {
        return;
    }

    nodes.add(new int[]{col, row, node.val});
    dfs(node.left, row + 1, col - 1, nodes);
    dfs(node.right, row + 1, col + 1, nodes);
}


//Let N = number of nodes in the tree

//Step 1: BFS traversal
 // We visit each node exactly once.
 // Work per node = O(1) (insertion into map).
 // Time = O(N)

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
    public List<List<Integer>> verticalTraversal1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        //Key is the vertical position
        Map<Integer, List<int[]>> map = new TreeMap<>();
        Tuple rootPair = new Tuple(root, 0, 0);

        Queue<Tuple> queue = new LinkedList<>();
        queue.add(rootPair);

        while(!queue.isEmpty()) {
            Tuple topPair = queue.remove();
            TreeNode temp = topPair.node;
            List<int[]> ds = map.get(topPair.col);

            if(ds == null) {
                ds = new ArrayList<>();
            } 

            ds.add(new int[] { topPair.row, temp.val});

            if(temp.left != null) {
                queue.add(new Tuple(temp.left, topPair.row + 1, topPair.col - 1));
            }

            if(temp.right != null) {
                queue.add(new Tuple(temp.right, topPair.row + 1, topPair.col + 1));
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

    class Tuple {
        TreeNode node;
        int row;
        int col;

        public Tuple(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }
}
