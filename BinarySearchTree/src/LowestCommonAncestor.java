
public class LowestCommonAncestor {

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

// } Driver Code Ends


//User function Template for Java

    static class BST {

        /**
         * TODO: Lowest Ancestor of Node is:
         * To find the LCA of two nodes in a BST, you can start at the root of the tree and compare
         * 'the values of the nodes to the value of the root. If the values of both nodes are less than the value of the root,
         * <p>
         * you can search for the LCA in the left subtree. If the values of both nodes are greater than the value of the root,
         * you can search for the LCA in the right subtree. If one value is less than the root and the other is greater,
         * then the root is the LCA. You can continue this process until you find the LCA or you reach a leaf node without finding the LCA,
         * in which case the LCA does not exist in the tree.
         */
        //Function to find the lowest common ancestor in a BST.

            // Function to find the lowest common ancestor in a BST.
            public Node LCA(Node root, int n1, int n2) {
                if (root == null) return null;

                // If both nodes are smaller than root, LCA lies in left subtree
                if (n1 < root.data && n2 < root.data) {
                    return LCA(root.left, n1, n2);
                }

                // If both nodes are greater than root, LCA lies in right subtree
                if (n1 > root.data && n2 > root.data) {
                    return LCA(root.right, n1, n2);
                }

                // Otherwise, this root is the LCA
                return root;
            }
        }
    }
