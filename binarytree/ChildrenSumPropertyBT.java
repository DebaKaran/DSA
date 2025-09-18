package binarytree;

//gfg problem
// https://www.geeksforgeeks.org/problems/children-sum-parent/1

public class ChildrenSumPropertyBT {
    public boolean isSumProperty(TreeNode root) {
        return isSumPropertyBT(root);
        
    }
    
    //O(n) time and O(h) space
    
    private boolean isSumPropertyBT(TreeNode curr) {
        // Base case: null or leaf node
        if(curr == null || (curr.left == null && curr.right == null)) {
            return true;
        }
        
        int leftVal = curr.left != null ? curr.left.val : 0;
        int rightVal = curr.right != null ? curr.right.val : 0;
        
         // Current node check + recursive checks
        return (curr.val == leftVal + rightVal) 
                && isSumPropertyBT(curr.left) 
                && isSumPropertyBT(curr.right);
    }
}
