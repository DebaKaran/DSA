package binarytree;

import java.util.ArrayList;
import java.util.List;

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
}
