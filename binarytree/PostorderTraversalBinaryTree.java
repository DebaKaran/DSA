package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostorderTraversalBinaryTree {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderTraversal(root, result);
        return result;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(h) where h is the height of the tree
    
    private void postorderTraversal(TreeNode node, List<Integer> result) {
        if(node == null) {
            return;
        }

        
        postorderTraversal(node.left, result);
        postorderTraversal(node.right, result);
        result.add(node.val);
        
    }


    //postorder is: left ---> right ----> root
	//preOrder is: root ---> left ----> right
	//customise preOrder is: root ----> right  ----> left
	//reversal of customise preOrder is: left ---> right ----> root which is same as postOrder
	
	private static List<Integer> postOrderUsingTwoStack(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        if(node == null) {
            return result;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(node);
        while (!stack1.isEmpty()) {
            TreeNode current = stack1.pop();
            stack2.push(current);
            if(current.left != null) {
                stack1.push(current.left);
            }
            if(current.right != null) {
                stack1.push(current.right);
            }
            
        }

        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }
        return result;
    }   
}
