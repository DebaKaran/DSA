package binarytree;

import java.util.ArrayList;
import java.util.Stack;

//GFG Problem: https://practice.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1
//LeetCode Problem: https://leetcode.com/problems/boundary-of-binary-tree/
public class BoundaryTraversalBT {

    //Time: O(N) Space: O(H) H = height of tree

    public ArrayList<Integer> boundaryTraversal(TreeNode node) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        if(node == null) {
            return result;
        }
        
        // 1. Root
        if(!isLeaf(node)) {
             result.add(node.val);
        }
       
        // 2. Left boundary
        TreeNode curr = node.left;
        
        while(curr != null) {
            if(!isLeaf(curr)){
                 result.add(curr.val);
            }
            
            if(curr.left != null) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        
        //leaf nodes
        addLeaves(result, node);
        
         // Right Boundary
        Stack<Integer> stack = new Stack<>();
        curr = node.right;
        
        while(curr != null) {
            if(!isLeaf(curr)) {
                stack.push(curr.val);
            }
            
            if(curr.right != null) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        
        while(!stack.isEmpty()) {
            result.add(stack.pop());
        }
        
        return result;
        
    }
    
    //Level Order is giving wrong output
    
    private void addLeaves(ArrayList<Integer> result, TreeNode node) {
        if(node == null) return;
        if(isLeaf(node)) {
            result.add(node.val);
            return;
        }
        
        addLeaves(result, node.left);
        addLeaves(result, node.right);
    }
    
    
    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null ;
    }
}
