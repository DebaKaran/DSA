package binarytree;

public class BalancedHeightBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return isBalancedR(root);
        
    }
    
    //Using Recursion
    //Time Complexity: O(n^2) in worst case for skewed tree
    //Space Complexity: O(h) where h is the height of the tree
    
    private boolean isBalancedR(TreeNode node) {
        if(node == null) return true;
        
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        
        if(Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        
        return isBalancedR(node.left) && isBalancedR(node.right);
    }
    
    private int height(TreeNode nd) {
        if(nd == null) {
            return 0;
        }
        
        int leftHeight = height(nd.left);
        int rightHeight = height(nd.right);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
