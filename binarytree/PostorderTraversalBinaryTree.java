package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostorderTraversalBinaryTree {
    public List<Integer> postorderTraversal(TreeNode root) {
        /**List<Integer> result = new ArrayList<>();
        postorderTraversal(root, result);
        return result; */
        //return postOrderUsingTwoStack(root);
        return postOrderUsingOneStack(root);
	}
	
    private static List<Integer> postOrderUsingOneStack(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        if (node == null) return result;
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = node;
        TreeNode lastVisited = null;

        while(current != null || !stack.isEmpty()) {
            // Step 1: Push all left nodes
            while(current != null) {
                stack.push(current);
                current = current.left;
            }

            // Step 2: Peek the top node
            TreeNode topNode = stack.peek();

            // Step 3: Check if the right child exists and is unvisited
            if(topNode.right != null && topNode.right != lastVisited) {
                current = topNode.right;
            } else {
                // Step 4: Process the node
                result.add(topNode.val);
                lastVisited = stack.pop();
            }
        }
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
