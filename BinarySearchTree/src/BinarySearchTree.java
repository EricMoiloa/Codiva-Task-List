import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

    /** -------------------------------
     *  Internal Tree Node Definition
     * ------------------------------- */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.val = data;
            left = right = null;
        }
    }

    /** Root of the BST */
    private TreeNode root = null;

    // Getter for root
    public TreeNode getRoot() {
        return root;
    }

    /** -------------------------------
     *  Core BST Operations
     * ------------------------------- */

    /** Inserts a value into the BST */
    public void insert(int data) {
        root = insertUtil(root, data);
    }

    /** Recursive insertion logic */
    private TreeNode insertUtil(TreeNode node, int data) {
        if (node == null) return new TreeNode(data);

        if (data < node.val) {
            node.left = insertUtil(node.left, data);
        } else if (data > node.val) {
            node.right = insertUtil(node.right, data);
        }
        // Duplicate values are ignored (standard BST)
        return node;
    }

    /** Deletes a value from the BST */
    public void delete(int valueToDelete) {
        root = deleteNode(root, valueToDelete);
    }

    /** Recursive delete helper */
    private TreeNode deleteNode(TreeNode node, int valueToDelete) {
        if (node == null) return null;

        if (valueToDelete < node.val) {
            node.left = deleteNode(node.left, valueToDelete);
        } else if (valueToDelete > node.val) {
            node.right = deleteNode(node.right, valueToDelete);
        } else {
            // Node to delete found
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Find inorder successor
            TreeNode successor = findMin(node.right);
            node.val = successor.val;
            node.right = deleteNode(node.right, successor.val);
        }
        return node;
    }

    /** Finds the minimum node in a subtree */
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    /** Searches a value in BST */
    public boolean search(int value) {
        return searchUtil(root, value);
    }

    private boolean searchUtil(TreeNode node, int value) {
        if (node == null) return false;
        if (node.val == value) return true;
        if (value < node.val) return searchUtil(node.left, value);
        return searchUtil(node.right, value);
    }

    /** -------------------------------
     *  Traversal Print Methods
     * ------------------------------- */

    /** Print all traversal orders */
    public void printTree() {
        System.out.println("\n--- Preorder Traversal ---");
        preorderTraversal(root);
        System.out.println();

        System.out.println("\n--- Inorder Traversal ---");
        inorderTraversal(root);
        System.out.println();

        System.out.println("\n--- Postorder Traversal ---");
        postorderTraversal(root);
        System.out.println();

        System.out.println("\n--- Level Order Traversal ---");
        levelOrderTraversal();
        System.out.println();
    }

    /** Preorder: Root → Left → Right */
    private void preorderTraversal(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    /** Inorder: Left → Root → Right */
    private void inorderTraversal(TreeNode node) {
        if (node == null) return;
        inorderTraversal(node.left);
        System.out.print(node.val + " ");
        inorderTraversal(node.right);
    }

    /** Postorder: Left → Right → Root */
    private void postorderTraversal(TreeNode node) {
        if (node == null) return;
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.val + " ");
    }

    /** Level Order (Breadth-First Traversal) */
    public void levelOrderTraversal() {
        if (root == null) return;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            System.out.print(curr.val + " ");
            if (curr.left != null) q.add(curr.left);
            if (curr.right != null) q.add(curr.right);
        }
    }

    /** -------------------------------
     *  Visualization Helper
     * ------------------------------- */

    /** Pretty-print tree structure */
    public void printTreeStructure() {
        System.out.println("\n--- Visual Structure ---");
        printTreeStructure(root, "", false);
    }

    private void printTreeStructure(TreeNode node, String indent, boolean isLeft) {
        if (node == null) return;
        System.out.println(indent + (isLeft ? "├── " : "└── ") + node.val);
        printTreeStructure(node.left, indent + (isLeft ? "│   " : "    "), true);
        printTreeStructure(node.right, indent + (isLeft ? "│   " : "    "), false);
    }

    /** -------------------------------
     *  Utility Methods
     * ------------------------------- */

    /** Returns minimum value in BST */
    public int getMin() {
        if (root == null) throw new IllegalStateException("Tree is empty");
        return findMin(root).val;
    }

    /** Returns maximum value in BST */
    public int getMax() {
        if (root == null) throw new IllegalStateException("Tree is empty");
        TreeNode curr = root;
        while (curr.right != null) curr = curr.right;
        return curr.val;
    }
}
