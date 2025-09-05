package binarytree;

//Leetcode: 100
//https://leetcode.com/problems/same-tree/description/

public class SameBinaryTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return isSameBinaryTree(p, q);
    }

    //Time Complexity: O(min(m,n)) where m and n are number of nodes in two trees
    //Space Complexity: O(h) where h is the height of the tree
    
    private boolean isSameBinaryTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        }

        if(p == null || q == null) return false;

        if(p.val != q.val) return false;

        return isSameBinaryTree(p.left, q.left) && isSameBinaryTree(p.right, q.right);
    }

}
