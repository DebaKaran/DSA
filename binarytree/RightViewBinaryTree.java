package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//LeetCode
//https://leetcode.com/problems/binary-tree-right-side-view/
public class RightViewBinaryTree {
    // Function to return a list of nodes visible from the right view
    // from top to bottom in Binary Tree.
    // Time Complexity: O(N) where N is the number of nodes in the tree
    // Space Complexity: O(N) for the storage of nodes in the queue
    
    ArrayList<Integer> rightView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while(!queue.isEmpty()) {
            int n = queue.size();
            
            for(int i = 0; i < n; i++) {
                TreeNode top = queue.remove();
                if(i == 0) {
                    result.add(top.val);
                }
                
                if(top.right != null) {
                    queue.add(top.right);
                }
                
                if(top.left != null) {
                    queue.add(top.left);
                }
            }
        }
        return result;
    }
}
