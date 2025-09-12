package binarytree;

//LeetCode
//https://leetcode.com/problems/symmetric-tree/

public class SymmetricBinaryTree {

    // Time Complexity: O(N) where N is the number of nodes in the tree
    // Space Complexity: O(h) where h is the height of the tree (recursion stack)
    
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
         return isSymmetric(root.left, root.right); 
    }

    private boolean isSymmetric(TreeNode leftNode, TreeNode rightNode) {
        if(leftNode == null && rightNode == null) return true;
        if(leftNode == null) return false;
        if(rightNode == null) return false;

        if(leftNode.val != rightNode.val) {
            return false;
        }

        return isSymmetric(leftNode.left, rightNode.right) 
        && isSymmetric(leftNode.right, rightNode.left);
    }
}
