

import java.util.NoSuchElementException;


public class AVL {

    private static class AVLNode{

        public AVLNode(int k){
            key = k;
        }

        public int key;
        public AVLNode left;
        public AVLNode right;
        public int height = 0;
    }

    public AVLNode root;

    private AVLNode add_(AVLNode n, int key){
        if (n == null){
            return new AVLNode(key);
        }

        if (n.key > key){
            n.left = add_(n.left, key);
        }
        else{
            if (n.key < key){
                n.right = add_(n.right, key);
            }
        }

        return balance(n);
    }

    public void add(int key){
        root = add_(root, key);
    }

    public AVLNode balance(AVLNode n){
        if (n==null){
            return n;
        }

        if ((height(n.left) - height(n.right)) > 1){
            if (height(n.left.left) >= height(n.left.right)){ //if the left is the biggest one, we do a left rotate
                n = rotate_left(n);
            }
            else{
                n = double_left(n);
            }
        }
        else{
            if((height(n.right) - height(n.left)) >1){
                if(height(n.right.right) >= height(n.right.left)){
                    n = rotate_right(n);
                }
                else{
                    n = double_right(n);
                }
            }
        }

        n.height = Math.max(height(n.left), height(n.right)) + 1;
        return n;
    }

    public int height(AVLNode n){
        if(n == null){
            return -1;
        }
        return n.height;
    }

    public int height_tree(){
        return height_tree(root);
    }

    private int height_tree(AVLNode n){
        if(n==null)
            return -1;
        else
            return 1+Math.max(height_tree(n.left),height_tree(n.right));
    }

    public AVLNode rotate_left(AVLNode r2){
        AVLNode r1 = r2.left;
        r2.left = r1.right;
        r1.right = r2;
        r2.height = Math.max(height(r2.left), height(r2.right)) + 1;
        r1.height = Math.max(height(r1.left), r2.height) + 1;
        return r1;
    }

    public AVLNode rotate_right(AVLNode r2){
        AVLNode r1 = r2.right;
        r2.right = r1.left;
        r1.left = r2;
        r2.height = Math.max(height(r2.left), height(r2.right)) + 1;
        r1.height = Math.max(height(r1.left), r2.height) + 1;
        return r1;
    }

    public AVLNode double_right(AVLNode n){
        n.right = rotate_left(n.right);
        return rotate_right(n);
    }

    public AVLNode double_left(AVLNode n){
        n.left = rotate_right(n.left);
        return rotate_left(n);
    }

    public void print_preorder(){
        preorder(root);
    }

    private void preorder(AVLNode n){
        if (n != null){
            System.out.print(n.key);
            preorder(n.left);
            preorder(n.right);
        }
    }

    private int min(AVLNode n){
        if (n==null)
            throw new NoSuchElementException();
        if (n.left==null)
            return n.key;
        else
            return min(n.left);
    }

    private AVLNode delete_(AVLNode n, int key){
        if (n == null) {
            return n;
        }

        if (n.key > key) {
            n.left = delete_(n.left, key);
        }
        else{
            if (n.key < key) {
                n.right = delete_(n.right, key);
            }
            else{
                if (n.right == null){
                    return n.left;
                }
                if (n.left == null){
                    return n.right;
                }
                n.key = min(n.right);
                n.right = delete_(n.right, n.key);
            }
        }
        return balance(n);
    }

    public void delete(int key){
        root = delete_(root, key);
    }

    public boolean contains(int key){
        return contains(root, key);
    }
    private boolean contains(AVLNode n, int key){
        if (n==null)
            return false;
        if (n.key>key)
            return contains(n.left,key);
        else if (n.key < key)
            return contains(n.right,key);
        else
            return true;
    }
}
