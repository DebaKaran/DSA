package binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigzagBinaryTree {
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        boolean fromLeftToRight = true;
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while(!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> ds = new ArrayList<>(Collections.nCopies(n, 0));

            for(int i = 0; i < n; i++) {
                TreeNode node = queue.remove();
                int index = fromLeftToRight ? i : n - 1 - i;

                ds.set(index, node.val);

                 if(node.left != null) {
                    queue.add(node.left);
                 }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(ds);
            fromLeftToRight = !fromLeftToRight;
        }
        return result;
    }
}
