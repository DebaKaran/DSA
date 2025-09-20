package binarytree;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

// Leetcode 105
// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-travers

class ConstructTreeFromPreInorder {

    //1: Time Complexity

    // Building the inorder map → O(n)

    // We go through the inorder array once.

    // Recursive tree construction

    // Each node is processed once.

    // For every node, we:

    // Find its index in O(1) using the hashmap.

    // Make recursive calls on its left and right subtrees.

    // So total work = O(n).

    // Final Time Complexity = O(n)

    // 2: Space Complexity

    // HashMap for inorder indices → O(n).

    // Recursion stack:

    // In the worst case (skewed tree), recursion depth = n → O(n).

    // In the best case (balanced tree), recursion depth = O(log n).

    // Final Space Complexity = O(n) (due to hashmap + recursion).
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Build a map of inorder values -> index for O(1) lookup
        Map<Integer, Integer> map = inorderMap(inorder);

        return buildTree(
            preorder, 0, preorder.length - 1,  // preorder boundaries
            inorder, 0, inorder.length - 1,    // inorder boundaries
            map
        );
    }

    private TreeNode buildTree(int[] preorder, int preorderStart, int preorderEnd,
                               int[] inorder, int inorderStart, int inorderEnd,
                               Map<Integer, Integer> map) {

        // Base case: no elements left to construct
        if (preorderStart > preorderEnd || inorderStart > inorderEnd) {
            return null;
        }

        // Root is always the first element in preorder
        int rootVal = preorder[preorderStart];
        TreeNode root = new TreeNode(rootVal);

        // Find the index of root in inorder array
        int rootIndex = map.get(rootVal);

        // Number of nodes in the left subtree
        int leftTreeSize = rootIndex - inorderStart;

        // Recursively build left and right subtrees
        root.left = buildTree(
            preorder, preorderStart + 1, preorderStart + leftTreeSize,
            inorder, inorderStart, rootIndex - 1,
            map
        );

        root.right = buildTree(
            preorder, preorderStart + leftTreeSize + 1, preorderEnd,
            inorder, rootIndex + 1, inorderEnd,
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
