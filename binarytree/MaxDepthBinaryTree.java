package binarytree;

public class MaxDepthBinaryTree {
    public int maxDepth(TreeNode root) {
        //Using Recursion
        return maxDepthR(root);
    }

    private int maxDepthR(TreeNode node) {
        if(node == null) {
            return 0;
        }

        int leftDepth = maxDepthR(node.left);
        int rightDepth = maxDepthR(node.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }
}
