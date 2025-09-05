package binarytree;

public class MaximumPathSumBinaryTree {
    // We use an array of size 1 to simulate pass-by-reference,
    // so that recursive calls can update the global maximum.

    public int maxPathSum(TreeNode root) {
        int[] globalMax  = new int[1]; //contains maximum path value
        globalMax [0] = Integer.MIN_VALUE;

        //maxPathSumBT(root, globalMax );
        calculateMaxDownwardPath(root, globalMax);
        return globalMax [0];
    }

     /**
     * Recursive helper method that returns the maximum downward path sum 
     * starting from the current node (can go left OR right, but not both).
     * While computing, it also updates the global maximum with the best 
     * path sum that passes through the current node.
     */
    private int calculateMaxDownwardPath(TreeNode node, int[] globalMax) {
        if (node == null) {
            return 0;
        }

        // Recursively compute the maximum downward path sum from left and right children.
        // If a subtree gives a negative sum, we ignore it by taking max(0, ...).
        int leftDownward = Math.max(0, calculateMaxDownwardPath(node.left, globalMax));
        int rightDownward = Math.max(0, calculateMaxDownwardPath(node.right, globalMax));

        // Case 1: Best path passing through the current node = left + right + node.val
        // This considers paths where current node is the "bridge" between left and right.
        int pathThroughNode = leftDownward + rightDownward + node.val;

        // Update global maximum if this path is better
        globalMax[0] = Math.max(globalMax[0], pathThroughNode);

        // Return to parent: the best single downward path (node + one side).
        // Parent cannot take both left and right, otherwise it would form a fork.
        return node.val + Math.max(leftDownward, rightDownward);
    }

    // Returns the maximum path sum "starting" from the current node
    // and updates the global maximum path sum if needed
    // Time Complexity: O(n)
    // Space Complexity: O(h) where h is the height of the tree

    private int maxPathSumBT(TreeNode node, int[] globalMax ) {
        if(node == null) {
            return 0;
        }

        //find left and right maxPathSum
        int leftMaxPathSum = maxPathSumBT(node.left, globalMax );
        int rightMaxPathSum = maxPathSumBT(node.right, globalMax );


        //Case 1: left + right + node
        int max_from_left_right_current_node = leftMaxPathSum + rightMaxPathSum + node.val;

        //case 2: either left or right
        int max_either_left_or_right = Math.max(leftMaxPathSum, rightMaxPathSum);
        int max_either_left_or_right_with_current_node =  max_either_left_or_right + node.val;

        //case 3: only current node
        int only_current_node = node.val;
        
        int max_case2_or_case1 = Math.max(max_from_left_right_current_node,
        max_either_left_or_right_with_current_node);

        int max_case2_or_case1_or_case3 = Math.max(only_current_node, max_case2_or_case1);

       globalMax [0] = Math.max(globalMax [0], max_case2_or_case1_or_case3);

       return Math.max(max_either_left_or_right_with_current_node, only_current_node);
    }
}
