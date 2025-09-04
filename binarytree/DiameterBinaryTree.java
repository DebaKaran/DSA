package binarytree;

/**
 * Diameter of a Binary Tree
 * 
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. 
 * This path may or may not pass through the root. 
 *  For example, the diameter of the following tree is 5, the length of the path 4-2-1-3-6 or 5-2-1-3-6.
 *  
 * */

public class DiameterBinaryTree {

    int diameter(TreeNode root) {
       
        return maxDiameter(root);
    }
    
    // Function to calculate the maximum diameter   
    // of the binary tree
    // Time Complexity: O(n^2)
    // Space Complexity: O(h) where h is the height of the tree
    //TLE issue for large input
    
    private int maxDiameter(TreeNode node) {
        if(node == null) {
            return 0;
        }
        
        int dia = 0;
        
        int lh = height(node.left);
        int rh = height(node.right);
        
        int viaNode = lh + rh;
        
        dia = Math.max(dia, viaNode);
        
        int leftDia = maxDiameter(node.left);
        int rightDia = maxDiameter(node.right);
        
        int leftOrRightDia = Math.max(leftDia, rightDia);
        
        return Math.max(dia, leftOrRightDia);
    }
    
    private int height(TreeNode node) {
        if(node == null) {
            return 0;
        }
        
        int lh = height(node.left);
        int rh = height(node.right);
        
        return Math.max(lh, rh) + 1;
    }
}
