import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();
        boolean exit = false;

        System.out.println("Welcome to the Interactive Binary Search Tree Program");

        while (!exit) {
            // ---- MENU OPTIONS ----
            System.out.println("\n====== MENU ======");
            System.out.println("1. Insert a node");
            System.out.println("2. Delete a node");
            System.out.println("3. Search a value");
            System.out.println("4. Find minimum value");
            System.out.println("5. Find maximum value");
            System.out.println("6. Display all traversals");
            System.out.println("7. Show visual tree structure");
            System.out.println("8. Find Kth largest element");
            System.out.println("9. Find Lowest Common Ancestor (LCA)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = getValidInteger(sc);

            switch (choice) {
                // INSERT NODE
                case 1 -> {
                    System.out.print("Enter value to insert: ");
                    int val = getValidInteger(sc);
                    bst.insert(val);
                    System.out.println(" Inserted " + val);
                    bst.printTreeStructure();
                }

                // DELETE NODE
                case 2 -> {
                    System.out.print("Enter value to delete: ");
                    int del = getValidInteger(sc);
                    bst.delete(del);
                    System.out.println("🗑 Deleted " + del + " (if it existed)");
                    bst.printTreeStructure();
                }

                // SEARCH VALUE
                case 3 -> {
                    System.out.print("Enter value to search: ");
                    int searchVal = getValidInteger(sc);
                    boolean found = bst.search(searchVal);
                    System.out.println(found ? " Value FOUND!" : " Value NOT FOUND!");
                }

                // MIN VALUE
                case 4 -> {
                    try {
                        System.out.println(" Minimum value: " + bst.getMin());
                    } catch (IllegalStateException e) {
                        System.out.println(" Tree is empty!");
                    }
                }

                // MAX VALUE
                case 5 -> {
                    try {
                        System.out.println(" Maximum value: " + bst.getMax());
                    } catch (IllegalStateException e) {
                        System.out.println(" Tree is empty!");
                    }
                }

                // PRINT TRAVERSALS
                case 6 -> {
                    System.out.println(" Displaying all traversals:");
                    bst.printTree();
                }

                // PRINT STRUCTURE
                case 7 -> bst.printTreeStructure();

                // Kth LARGEST ELEMENT
                case 8 -> {
                    System.out.print("Enter K (e.g. 1 for largest, 2 for 2nd largest): ");
                    int k = getValidInteger(sc);

                    // Create a KthLargestElementBST object and call logic
                    KthLargestElementBST helper = new KthLargestElementBST();
                    KthLargestElementBST.Node root = buildSimpleBST(bst.getRoot());

                    if (root == null) {
                        System.out.println("Tree is empty!");
                    } else {
                        int result = helper.kthLargest(root, k);
                        System.out.println(" " + k + "th largest element: " + result);
                    }
                }

                // LOWEST COMMON ANCESTOR
                case 9 -> {
                    System.out.print("Enter first node value: ");
                    int n1 = getValidInteger(sc);
                    System.out.print("Enter second node value: ");
                    int n2 = getValidInteger(sc);

                    LowestCommonAncestor.BST lcaHelper = new LowestCommonAncestor.BST();
                    LowestCommonAncestor.Node root = buildLCATree(bst.getRoot());
                    if (root == null) {
                        System.out.println("Tree is empty!");
                    } else {
                        LowestCommonAncestor.Node ans = lcaHelper.LCA(root, n1, n2);
                        if (ans != null)
                            System.out.println(" Lowest Common Ancestor: " + ans.data);
                        else
                            System.out.println(" One or both values not found.");
                    }
                }

                // EXIT
                case 0 -> {
                    exit = true;
                    System.out.println(" Exiting program... Goodbye!");
                }

                // INVALID INPUT
                default -> System.out.println(" Invalid choice. Please try again.");
            }
        }

        sc.close();
    }

    /** Safely reads an integer input from user */
    private static int getValidInteger(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println(" Please enter a valid integer:");
            sc.next();
        }
        return sc.nextInt();
    }

    /** Helper: Convert BinarySearchTree.TreeNode to KthLargestElementBST.Node */
    private static KthLargestElementBST.Node buildSimpleBST(BinarySearchTree.TreeNode root) {
        if (root == null) return null;
        KthLargestElementBST.Node node = new KthLargestElementBST().new Node(root.val);
        node.left = buildSimpleBST(root.left);
        node.right = buildSimpleBST(root.right);
        return node;
    }

    /** Helper: Convert BinarySearchTree.TreeNode to LowestCommonAncestor.Node */
    private static LowestCommonAncestor.Node buildLCATree(BinarySearchTree.TreeNode root) {
        if (root == null) return null;
        LowestCommonAncestor.Node node = new LowestCommonAncestor.Node(root.val);
        node.left = buildLCATree(root.left);
        node.right = buildLCATree(root.right);
        return node;
    }
}
