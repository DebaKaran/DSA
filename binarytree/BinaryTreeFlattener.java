package binarytree;


public class BinaryTreeFlattener {
    public void flatten(TreeNode root) {
        flattenRecursive(root);
    }

    

    // 1: Time Complexity:

    // Each node is visited once by recursion → O(n).

    // But finding the tail of the left list can take up to O(n) in the worst case.

    // On a skewed tree, this results in repeated scans → O(n²) worst case.

    // 2: Space Complexity:

    // No extra data structures are used.

    // Only recursion stack: O(h), where h = tree height.

    // Worst case (skewed tree): O(n).

    // Best/average (balanced tree): O(log n).

    private void flattenRecursive(TreeNode node) {
        if(node == null) {
            return;
        }

        // Step 1: flatten left and right subtrees
        flattenRecursive(node.left);
        flattenRecursive(node.right);

        // Step 2: store references
        TreeNode left = node.left;
        TreeNode right = node.right;

        // Step 3: attach left list to root.right
        if(left != null) {
            node.right = left;
            node.left = null;

            // Step 4: find tail of left list
            TreeNode tail = left;
            while(tail.right != null) {
                tail = tail.right;
            }

            // Step 5: attach right list after tail
            tail.right = right;
        }
    }
}
