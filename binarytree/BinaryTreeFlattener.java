package binarytree;

import java.sql.Time;
import java.util.Stack;

public class BinaryTreeFlattener {

    TreeNode prev = null; // keeps track of already flattened part
    

    public void flatten(TreeNode root) {
        
        //flattenRecursive(root);
        //flattenAndReturnTail(root);
        //flattenBT(root);

        //flattenBTUsingIterative(root);
        flattenBTUsingMorrisTraversal(root);
    }

    // Morris-style flatten binary tree to linked list
    // Time: O(n) → each edge is visited at most twice (once going down, once stitching).

    // Space: O(1) → no recursion, no explicit stack.
    private void flattenBTUsingMorrisTraversal(TreeNode node) {
        TreeNode curr = node;

        while (curr != null) {
            if (curr.left != null) {
                // Find the rightmost node in left subtree
                TreeNode pre = curr.left;
                while (pre.right != null) {
                    pre = pre.right;
                }

                // Stitch original right subtree after rightmost node
                pre.right = curr.right;

                // Shift left subtree to right
                curr.right = curr.left;
                curr.left = null;
            }

            // Move always right (like Morris traversal)
            curr = curr.right;
        }
    }

    //1: Time Complexity:

    // Each node is pushed and popped from the stack exactly once → O(n).

    //2: Space Complexity:

    // In the worst case (skewed tree), the stack holds O(n) nodes.

    // In a balanced tree, the stack height is proportional to O(log n).

    // So worst case: O(n).

    private void flattenBTUsingIterative(TreeNode node) {
        if (node == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);  // start with root

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();

            // Push right child first (so left is processed before right, matching preorder)
            if (curr.right != null) {
                stack.push(curr.right);
            }

            // Push left child
            if (curr.left != null) {
                stack.push(curr.left);
            }

            // If stack is not empty, link current node's right to the next node in preorder
            if (!stack.isEmpty()) {
                curr.right = stack.peek();
            }

            // Always nullify the left pointer to follow linked list structure
            curr.left = null;
        }
    }

    // 1: Time Complexity:

    // Each node is visited exactly once → O(n).

    // 2: Space Complexity:

    // Only recursion stack is used → O(h), where h = tree height.

    // Worst case (skewed tree): O(n). Balanced: O(log n).

    private void flattenBT(TreeNode node) {
        if(node == null) return;

        // Step 1: recurse on right first
        flattenBT(node.right);

        // Step 2: recurse on left
        flattenBT(node.left);

        // Step 3: rewire current node
        node.right = prev; // connect to previously processed head
        node.left = null; // clear left pointer
        prev = node; // update prev to current node
    }


    // 1: Time Complexity:

    // Each node is visited once.

    // No repeated scanning for tails (tail is returned directly).

    // O(n) overall.

    // 2: Space Complexity:

    // No extra data structures, just recursion stack.

    // O(h), where h = height of the tree.

    // Worst case skewed tree: O(n).

    // Balanced tree: O(log n).

    // Helper: flattens the subtree and returns the tail of the flattened list
    private TreeNode flattenAndReturnTail(TreeNode node) {
        if(node == null) {
            return node;
        }

        // Leaf node → tail is itself
        if(node.left == null && node.right == null) {
            return node;
        }

        // Flatten left and right subtrees
        TreeNode leftTail =  flattenAndReturnTail(node.left);
        TreeNode rightTail =  flattenAndReturnTail(node.right);

        // If left subtree exists, stitch it between node and right subtree
        if(leftTail != null) {
            leftTail.right = node.right; // attach right list after leftTail
            node.right = node.left; // move left list to right
            node.left = null;              // nullify left pointer
        }

        // Return the correct tail
        return rightTail != null ? rightTail : leftTail;
        
    }

    

    // 1: Time Complexity:

    // Each node is visited once by recursion → O(n).

    // But finding the tail of the left list can take up to O(n) in the worst case.

    // On a skewed tree, this results in repeated scans → O(n²) worst case.

    // 2: Space Complexity:

    // No extra data structures are used.

    // Only recursion stack: O(h), where h = tree height.

    // Worst case (skewed tree): O(n).

    // Best/average (balanced tree): O(log n).

    private void flattenRecursive(TreeNode node) {
        if(node == null) {
            return;
        }

        // Step 1: flatten left and right subtrees
        flattenRecursive(node.left);
        flattenRecursive(node.right);

        // Step 2: store references
        TreeNode left = node.left;
        TreeNode right = node.right;

        // Step 3: attach left list to root.right
        if(left != null) {
            node.right = left;
            node.left = null;

            // Step 4: find tail of left list
            TreeNode tail = left;
            while(tail.right != null) {
                tail = tail.right;
            }

            // Step 5: attach right list after tail
            tail.right = right;
        }
    }
}
