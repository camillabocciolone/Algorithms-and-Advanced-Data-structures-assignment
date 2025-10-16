
class BinaryTree {
    private static class Node {
        int d;
        Node left, right;

        Node(int item) {
            d = item;
            left = null;
            right = null;
        }
    }
    public boolean isomorphic(Node n1, Node n2) {
        if (n1 == null && n2 != null)
            return false;
        if (n1 != null && n2 == null)
            return false;
        if (n1 == null)
            return true;

        if (n1.d != n2.d)
            return false;

        if ((isomorphic(n1.left, n2.left)) &&
                isomorphic(n1.right, n2.right))
            return true;
        if ((isomorphic(n1.left, n2.right)) &&
                isomorphic(n1.right, n2.left))
            return true;
        return false;
    }

    public static void main(String args[]) {
        BinaryTree tree = new BinaryTree();
        //check if the given trees are isomorphic
        Node tree1 = new Node(1);
        tree1.left = new Node(2);
        tree1.left.left = new Node(4);
        tree1.left.right = new Node(5);
        tree1.left.right.left = new Node(6);
        tree1.right = new Node(3);
        tree1.right.left = new Node(7);
        tree1.right.left.left = new Node(8);

        Node tree2 = new Node(1);
        tree2.left = new Node(3);
        tree2.left.left = new Node(7);
        tree2.left.left.right = new Node(8);
        tree2.right = new Node(2);
        tree2.right.left = new Node(5);
        tree2.right.right = new Node(4);
        tree2.right.left.left = new Node(6);

        if (tree.isomorphic(tree1, tree2))
            System.out.println("It is isomorphic");
        else
            System.out.println("It is not isomorphic");



    }
}


