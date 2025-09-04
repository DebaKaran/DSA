package binarytree;

public class BalancedHeightBinaryTree {
    public boolean isBalanced(TreeNode root) {
        //return isBalancedR(root);
        return checkHeight(root) != -1;
        
    }
    
    //Instead of calling height() separately for each node, 
    //we compute the height and balance status in a single recursion (bottom-up).
    
    // returns height if subtree is balanced, else -1
    //time Complexity: O(n)
    //Space Complexity: O(h) where h is the height of the tree

    private int checkHeight(TreeNode node) {
        if(node == null) {
            return 0;
        }
        
        int leftHeight = checkHeight(node.left);
        if(leftHeight == -1) {
            return -1;
        }
        
        int rightHeight = checkHeight(node.right);
        if(rightHeight == -1) {
            return -1;
        }
        
        if(Math.abs(rightHeight - leftHeight) > 1) {
            return -1;
        }
        
        return Math.max(leftHeight, rightHeight) + 1;
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
