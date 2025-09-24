package binarytree;

import java.util.ArrayList;
import java.util.List;

//Leetcode 94: https://leetcode.com/problems/binary-tree-inorder-traversal/description/

public class InorderMorrisTraversal {

    /**
     * Inorder traversal of a binary tree using Morris Traversal.
     *
     * Time Complexity: O(n)
     * - Each edge is visited at most twice (once to create a thread, once to remove it).
     * - Hence, linear in the number of nodes.
     *
     * Space Complexity: O(1)
     * - No stack or recursion is used.
     * - Only a few pointers are stored (constant extra space).
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;

        // Traverse the tree until all nodes are processed
        while (curr != null) {

            // Case 1: If left child exists, find the inorder predecessor
            if (curr.left != null) {
                TreeNode temp = curr.left;

                // Move to the rightmost node in left subtree 
                // (inorder predecessor of curr)
                while (temp.right != null && temp.right != curr) {
                    temp = temp.right;
                }

                // Case 1a: If no thread exists, create it and go left
                if (temp.right == null) {
                    temp.right = curr;   // create a temporary link
                    curr = curr.left;    // move left
                }
                // Case 1b: Thread already exists → remove it, visit node, go right
                else {
                    temp.right = null;   // remove the temporary link
                    result.add(curr.val); // visit current node
                    curr = curr.right;    // move right
                }

            } 
            // Case 2: No left child → directly visit and move right
            else {
                result.add(curr.val);  // visit current node
                curr = curr.right;     // move right
            }
        }

        return result;
    }
}
