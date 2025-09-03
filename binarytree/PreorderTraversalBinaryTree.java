package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreorderTraversalBinaryTree {

    public List<Integer> preorderTraversal(TreeNode root) {
        /**List<Integer> result = new ArrayList<>();
        preorderTraversal(root, result);
        return result; */

        return preorderTraversalIterative(root);
    }

    //Time Complexity O(n)
    //Space Complexity O(h) h is height of tree
    
    private List<Integer> preorderTraversalIterative(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        if(node == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);

        while(!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            result.add(temp.val);

            if(temp.right != null) {
                stack.push(temp.right);
            }

            if(temp.left != null) {
                stack.push(temp.left);
            }
        }

        return result;
    }

    //Time Complexity O(n)
    //Space Complexity O(h) h is height of tree
    
    private void preorderTraversal(TreeNode node, List<Integer> result) {
        if(node == null) {
            return;
        }

        result.add(node.val);
        preorderTraversal(node.left, result);
        preorderTraversal(node.right, result);
    }
}
