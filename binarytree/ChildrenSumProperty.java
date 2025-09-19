package binarytree;

import java.sql.Time;

//https://www.naukri.com/code360/problems/childrensumproperty_790723?leftPanelTabValue=PROBLEM

public class ChildrenSumProperty {
    public static void changeTree(TreeNode  root) {
        convertToChildrenSum(root);
    }

    //post order traversal
    
    //1: Time Complexity

    // Each node is visited once during recursion.

    // At each node, we do O(1) operations (calculate children sum, compare, assign values).

    // Even in the case where we push the difference downward, it still only affects a single child subtree and won’t repeatedly traverse everything unnecessarily.

    // Time Complexity = O(n),
    //where n = number of nodes in the binary tree.

    //2: Space Complexity

    //The function uses recursion, so the extra space is the call stack depth.

    // In the worst case (skewed tree) → recursion depth = O(n).

    // In the best case (balanced tree) → recursion depth = O(log n).

    // Apart from recursion, no extra data structures are used.

    // Space Complexity = O(h),
    // where h = height of the tree.

    // Worst case: O(n)

    // Best/Average (balanced tree): O(log n)
    private static void convertToChildrenSum(TreeNode node) {
        //base case
        if(node == null || (node.left == null && node.right == null)) {
            return;
        }

        // Step 1: Recurse first (post-order)
        convertToChildrenSum(node.left);
        convertToChildrenSum(node.right);

        // Step 2: Calculate children sum
        int leftVal = node.left == null ? 0 : node.left.val;
        int rightVal = node.right == null ? 0 : node.right.val;
        int childrenSum = leftVal + rightVal;

        // Step 3: Fix root value
        if(node.val <= childrenSum) {
            node.val = childrenSum;
        } else {
            int diff = node.val - childrenSum;
            if(node.left != null) {
                node.left.val = node.left.val + diff;
                convertToChildrenSum(node.left);
            } else {
                node.right.val = node.right.val + diff;
                convertToChildrenSum(node.right);
            }
            
        }

        
    }
}
