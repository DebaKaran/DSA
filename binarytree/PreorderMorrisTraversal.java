package binarytree;

import java.util.ArrayList;
import java.util.List;

public class PreorderMorrisTraversal {

  // Using Morris Traversal (Preorder: Root → Left → Right)
  /**
     * Preorder traversal of a binary tree using Morris Traversal.
     *
     * Time Complexity: O(n)
     * - Each edge is visited at most twice (once to create a thread, once to remove it).
     * - Hence, linear in the number of nodes.
     *
     * Space Complexity: O(1)
     * - No stack or recursion is used.
     * - Only a few pointers are stored (constant extra space).
     */
public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    TreeNode curr = root;

    // Traverse the tree without recursion or stack
    while (curr != null) {

        // Case 1: If left child exists
        if (curr.left != null) {
            // Find the rightmost node in the left subtree (predecessor)
            TreeNode temp = curr.left;
            while (temp.right != null && temp.right != curr) {
                temp = temp.right;
            }

            // Case 1a: First time visiting this left subtree
            if (temp.right == null) {
                // Preorder → Visit root before going left
                result.add(curr.val);

                // Create a temporary thread back to current node
                temp.right = curr;

                // Move to left child
                curr = curr.left;
            } 
            // Case 1b: Second time visiting (thread already created)
            else {
                // Remove the temporary thread
                temp.right = null;

                // Now go to right subtree
                curr = curr.right;
            }
        } 
        // Case 2: No left child → visit directly and move right
        else {
            result.add(curr.val);
            curr = curr.right;
        }
    }

    return result;
}

}
