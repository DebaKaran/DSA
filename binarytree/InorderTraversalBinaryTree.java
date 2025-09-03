package binarytree;

import java.util.ArrayList;
import java.util.List;

public class InorderTraversalBinaryTree {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
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
