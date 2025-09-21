package binarytree;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;


// Leetcode 106
// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal

public class ConstructTreeFromPostInorder {

    //1: Time Complexity

    // Building the inorder map

    // We iterate once over inorder[].

    // Complexity: O(n)

    // Recursive tree construction

    // Each node is created exactly once.

    // For each node, we do:

    // O(1) lookup in the map

    // Constant amount of arithmetic

    // Across all nodes, the recursion visits each node once.

    // Complexity: O(n)

    // Overall Time Complexity = O(n)

    //2: Space Complexity

    // Inorder map storage

    // Stores n key-value pairs.

    // Complexity: O(n)

    // Recursion call stack

    // Height of recursion = height of tree h.

    // Worst case: skewed tree → O(n)

    // Best/average case: balanced tree → O(log n)

    // 👉 Overall Space Complexity = O(n)
    // (since map dominates even in balanced case)
    public TreeNode buildTree(int[] inorder, int[] postorder) {
         // Build a map of inorder values -> index for O(1) lookup
        Map<Integer, Integer> map = inorderMap(inorder);

        return buildTree(
            postorder, postorder.length - 1, 0, // postorder boundaries
            inorder, 0, inorder.length - 1,    // inorder boundaries
            map
        );
    }


    private TreeNode buildTree(int[] postorder, int postorderStart, int postorderEnd,
                               int[] inorder, int inorderStart, int inorderEnd,
                               Map<Integer, Integer> map) {

        // Base case: no elements left to construct
        if (postorderStart < postorderEnd || inorderStart > inorderEnd) {
            return null;
        }

        // Root is always the last element in postorder
        int rootVal = postorder[postorderStart];
        TreeNode root = new TreeNode(rootVal);

        // Find the index of root in inorder array
        int rootIndex = map.get(rootVal);

        // Number of nodes in the left subtree
        int rightTreeSize = inorderEnd - rootIndex;

        // Recursively build left and right subtrees

        // Build right subtree first
        root.right = buildTree(
            postorder, postorderStart - 1, postorderStart - rightTreeSize,
            inorder, rootIndex + 1, inorderEnd,
            map
        );

        // Then build left subtree
        root.left = buildTree(
            postorder, postorderStart - rightTreeSize - 1, postorderEnd,
            inorder, inorderStart, rootIndex - 1,
            map
        );


        return root;
    }


    private Map<Integer, Integer> inorderMap(int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return map;
    }
}
