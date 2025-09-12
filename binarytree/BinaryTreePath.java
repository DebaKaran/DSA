package binarytree;

import java.nio.file.Path;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

//LeetCode
//https://leetcode.com/problems/binary-tree-paths/

public class BinaryTreePath {

// Time Complexity

// Each node is visited once → O(n) for traversal (n = number of nodes).

// For every leaf, you build a path string:

// Building a string from a list of integers costs up to O(h) where h = tree height.

// In the worst case (skewed tree), h ≈ n.

// If there are L leaves, total cost of building all strings = O(L * h).

// Total Time = O(n + L * h) ≈ O(n * h) worst case.

// Balanced tree: h = log n, so closer to O(n log n).

// Skewed tree: h = n, so O(n^2).

// 2: Space Complexity

// Recursion stack: O(h) (height of tree).

// Path list (ds): also O(h).

//Result storage: needs O(L * h) for final strings.

// Overall: O(h + L * h).

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        //dfs(root, new ArrayList<>(), result);
        dfs1(root, "", result);

        return result;
    }

     // Time Complexity

    // Each node is still visited once → O(n) for traversal.

    // At each recursive call, you create a new string (path + node.val + "->").

    // String concatenation is O(k) where k = current path length.

    // Over the recursion tree, this also gives O(L * h) total.

    // Total Time = O(n * h) (same as list approach).

    // 2: Space Complexity

    // Recursion stack: O(h).

    // Intermediate strings: each recursive call creates a copy of the path string, up to O(h) per path.

    // Result storage: O(L * h) for final strings.

    // Overall: O(h + L * h).
    private void dfs1(TreeNode node, String path, List<String> result) {
        if(node == null) return;

        if(node.left == null && node.right == null) {
            result.add(path + node.val);
        } else {
            dfs1(node.left, path  + node.val + "->", result);
            dfs1(node.right, path + node.val + "->", result);
        }
    }

    private void dfs(TreeNode node, List<Integer> path, List<String> result) {
        if (node == null) return;

        // add current node to path
        path.add(node.val);

        // if leaf node, build path string
        if (node.left == null && node.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                sb.append(path.get(i));
                if (i < path.size() - 1) sb.append("->");
            }
            result.add(sb.toString());
        } else {
            dfs(node.left, path, result);
            dfs(node.right, path, result);
        }

        // backtrack
        path.remove(path.size() - 1);
    }
}
