package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderTraversalBinaryTree {
    public List<Integer> inorderTraversal(TreeNode root) {
        /**List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;*/
        return inorderTraversalIterative(root);
    }

    private List<Integer> inorderTraversalIterative(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = node;
        while(curr != null || !stack.isEmpty()) {
            // go left as far as possible
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // backtrack
            curr = stack.pop();
            result.add(curr.val);

            // go right
            curr = curr.right;
        }
        return result;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(h) where h is the height of the tree
    
    private void inorderTraversal(TreeNode node, List<Integer> result) {
        if(node == null) {
            return;
        }

        
        inorderTraversal(node.left, result);
        result.add(node.val);
        inorderTraversal(node.right, result);
    }
}
