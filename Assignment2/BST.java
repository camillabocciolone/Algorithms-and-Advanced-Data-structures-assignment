import java.nio.BufferUnderflowException;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Iterator;


public class BST implements Iterable<Integer>{

    private static class Node{

        public Node( int d, Node n1, Node n2){
            data = d;
            left = n1;
            right = n2;
        }
        public int data;
        public Node left;
        public Node right;
    }

    private Node root;

    public BST(){
        root=null;
    }

    public void add(int key){
        root=add(root, key);
    }
    private Node add(Node n, int key){
        if (n==null)
            return new Node(key, null, null);
        if (n.data>key)
            n.left=add(n.left,key);
        else if (n.data<key)
            n.right=add(n.right,key);
        return n;
    }

    public int size(){
        return size(root);
    }

    private int size(Node n){
        int l=0;
        int r=0;
        if(n==null)
            return 0;
        if(n.left!=null)
            l=size(n.left);
        if(n.right!=null)
            r=size(n.right);
        return 1+r+l;
    }


    public boolean contains(int key){
        return contains(root, key);
    }
    private boolean contains(Node n, int key){
        if (n==null)
            return false;
        if (n.data>key)
            return contains(n.left,key);
        else if (n.data < key)
            return contains(n.right,key);
        else
            return true;
    }
    public void remove( int key){
        root=remove(key,root);
    }

    private Node remove(int key, Node n){
        if (n==null)
            return n;
        if (n.data>key)
            n.left=remove(key,n.left);
        else if (n.data<key)
            n.right=remove(key,n.right);
        else {
            if (n.right == null)
                return n.left;
            if (n.left == null)
                return n.right;
            n.data = min(n.right);
            n.right = remove(n.data, n.right);
        }

        return n;
    }

    public int min(){
        if (size()==0)
            throw new BufferUnderflowException();
        return min(root);
    }

    private int min(Node n){
        if (n==null)
            throw new NoSuchElementException();
        if (n.left==null)
            return n.data;
        else
            return min(n.left);
    }

    public int height(){
        return height(root);
    }

    private int height(Node n){
        if(n==null)
            return -1;
        else
            return 1+Math.max(height(n.left),height(n.right));
    }

    private class inorderIterator implements Iterator<Integer> {

        private Stack<Node> stack;

        public inorderIterator() {
            stack = new Stack<>();
            Node cur=root;
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        }


        public boolean hasNext() {
            return stack.isEmpty()==false;
        }


        public Integer next() {
            if (hasNext()==false) {
                throw new NoSuchElementException("No more elements in the BSTIterator");
            }
            Node current = stack.pop();
            Node moveright=current.right;
            while (moveright != null) {
                stack.push(moveright);
                moveright = moveright.left;
            }
            return current.data;
        }
    }

    public Iterator<Integer> iterator(){
        return new inorderIterator();
    }

    // private class PreorderIterator implements Iterator<Integer> {
    //     private Stack<Node> stack;

    //     public PreorderIterator() {
    //         stack = new Stack<>();
    //         if (root != null) {
    //             stack.push(root);
    //         }
    //     }

    //     public boolean hasNext() {

    //         return !stack.isEmpty();
    //     }

    //     public Integer next() {
    //         if (!hasNext()) {
    //             throw new NoSuchElementException("No more elements in the PreorderIterator");
    //         }
    //         Node current = stack.pop();
    //         if (current.right != null) {
    //             stack.push(current.right);
    //         }
    //         if (current.left != null) {
    //             stack.push(current.left);
    //         }
    //         return current.data;
    //     }
    // }

    // public Iterator<Integer> iterator(){
    //     return new PreorderIterator();
    // }

    // private class PostorderIterator implements Iterator<Integer> {
    //     private Stack<Node> stack;

    //     public PostorderIterator() {
    //         stack = new Stack<>();
    //         pushPostorder(root);
    //     }

    //     public boolean hasNext() {
    //         return !stack.isEmpty();
    //     }

    //     public Integer next() {
    //         if (!hasNext()) {
    //             throw new NoSuchElementException("No more elements in the PostorderIterator");
    //         }

    //         Node current = stack.pop();
    //         return current.data;
    //     }

    //     private void pushPostorder(Node node) {
    //         if (node == null) {
    //             return;
    //         }

    //         Stack<Node> tempStack = new Stack<>();
    //         tempStack.push(node);

    //         while (!tempStack.isEmpty()) {
    //             Node temp = tempStack.pop();
    //             stack.push(temp);

    //             if (temp.left != null) {
    //                 tempStack.push(temp.left);
    //             }
    //             if (temp.right != null) {
    //                 tempStack.push(temp.right);
    //             }
    //         }
    //     }
    // }

    // public Iterator<Integer> iterator(){
    //     return new PostorderIterator();
    // }

    public void removek(int k){
        removek(root, k);
    }
    private void removek(Node n, int k) {
        int count = 0;
        int search=size()-k+1;
        int elem=0;
        if(search<1)
            throw new RuntimeException("invalid k");

        for (int element: this ){
            if (count >= search){
                break;
            }
            else{
                elem = element;
                count++;
            }
        }

        if (count == search)
            remove(elem);
    }

    public static void main(String[] args) {
        BST ll = new BST();
        System.out.println("size:");
        System.out.println(ll.size());
        ll.add(1);
        ll.add(6);
        ll.add(3);
        ll.add(17);
        ll.add(4);
        ll.add(16);
        ll.add(7);

        System.out.println("tree:");
        for (int element: ll){
            System.out.println(element);
        }
        System.out.println("size:");
        System.out.println(ll.size());
        System.out.println("size after removing ");
        ll.removek(7);

        System.out.println(ll.size());
        System.out.println("tree after removing ");

        for (int element: ll){
            System.out.println(element);
        }

    }
}





