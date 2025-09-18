package binarytree;

//gfg problem
// https://www.geeksforgeeks.org/problems/children-sum-parent/1

public class ChildrenSumPropertyBT {
    public boolean isSumProperty(TreeNode root) {
        return isSumPropertyBT(root);
        
    }
    //O(n) time and O(h) space
    
    private boolean isSumPropertyBT(TreeNode curr) {
        if(curr == null) {
            return true;
        }
        
        if(curr.left == null && curr.right == null) {
            return true;
        }
        
        int leftVal = curr.left != null ? curr.left.val : 0;
        int rightVal = curr.right != null ? curr.right.val : 0;
        
        if(curr.val != (leftVal + rightVal)) {
            return false;
        }
        
        if(!isSumPropertyBT(curr.left) || !isSumPropertyBT(curr.right)) {
            return false;
        }
        
        return true;
    }
}
