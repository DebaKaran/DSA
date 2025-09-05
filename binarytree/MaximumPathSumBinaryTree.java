package binarytree;

public class MaximumPathSumBinaryTree {
    // We use an array of size 1 to simulate pass-by-reference,
    // so that recursive calls can update the global maximum.

    public int maxPathSum(TreeNode root) {
        int[] globalMax  = new int[1]; //contains maximum path value
        globalMax [0] = Integer.MIN_VALUE;

        maxPathSumBT(root, globalMax );

        return globalMax [0];
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
