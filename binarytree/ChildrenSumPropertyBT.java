package binarytree;

import java.util.LinkedList;
import java.util.Queue;


//gfg problem
// https://www.geeksforgeeks.org/problems/children-sum-parent/1

public class ChildrenSumPropertyBT {
    public boolean isSumProperty(TreeNode root) {
        //return isSumPropertyBT(root);
        return isSumPropertyUsingLevelOrder(root);
        
    }
    
    //O(n) time and O(w) space
    private boolean isSumPropertyUsingLevelOrder(TreeNode curr) {
        if(curr == null) {
            return true;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(curr);
        
        while(!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if(node.left == null && node.right == null) {
                continue;
            }
            
            int leftVal = 0;
            int rightVal = 0;
            
            if(node.left != null) {
                leftVal = node.left.val;
                queue.add(node.left);
            }
            
            if(node.right != null) {
                rightVal = node.right.val;
                queue.add(node.right);
            }
            
            if(node.val != (leftVal + rightVal)) {
                return false;
            }
        }
        
        return true;
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
