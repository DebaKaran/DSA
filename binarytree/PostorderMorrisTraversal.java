package binarytree;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Leetcode 145: https://leetcode.com/problems/binary-tree-postorder-traversal/description/

public class PostorderMorrisTraversal {

    //1: Time Complexity: O(n)

    // Each node’s edge is visited at most twice:

    // The first visit is to create a temporary thread.

    // The second visit is to remove the thread.
    //Sorting the result at the end takes O(n) time.

    // Therefore, for n nodes, the traversal is linear, i.e., O(n).

    //2: Space Complexity: O(1)
    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;

        // Traverse the tree without recursion or stack
        //Instead of root-> left-> right, do root->right->left
        //then reverse it to get the postOrder result
        while (curr != null) {

            // Case 1: If right child exists
            if (curr.right != null) {
                // Find the Leftmost node in the right subtree (predecessor)
                TreeNode temp = curr.right;
                while (temp.left != null && temp.left != curr) {
                    temp = temp.left;
                }

                // Case 1a: First time visiting this right subtree
                if (temp.left == null) {
                    // Preorder → Visit root before going left
                    result.add(curr.val);

                    // Create a temporary thread back to current node
                    temp.left = curr;

                    // Move to left child
                    curr = curr.right;
                } 
                // Case 1b: Second time visiting (thread already created)
                else {
                    // Remove the temporary thread
                    temp.left = null;

                    // Now go to right subtree
                    curr = curr.left;
                }
            } 
            // Case 2: No left child → visit directly and move right
            else {
                result.add(curr.val);
                curr = curr.left;
            }
        }
        Collections.reverse(result);
        return result;
    }
}
