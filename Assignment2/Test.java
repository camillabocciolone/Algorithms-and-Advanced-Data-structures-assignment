import java.util.ArrayList;
import java.util.Random;
import java.util.List;


public class Test {
    public static void main(String[] args) {

        // We want to create an array with 10100 unique random values and add them to the tree
        AVL avl = new AVL();
        BST bst = new BST();
        List<Integer> random_arr = new ArrayList<Integer>();

        Random random = new Random();

        while (random_arr.size() < 10100) {
            int el = random.nextInt();
            if (!random_arr.contains(el)) {
                random_arr.add(el);
            }
        }
        time t1 = new time();
        t1.StartTime();
        for(int i=0;i<10100;i++){
            int el = random_arr.get(i);
            avl.add(el);

        }
        long end = t1.EndTime();
        System.out.println("Time that it takes to add 10100 values in an AVL tree randomly created: " + (end));

        t1.StartTime();
        for(int i=0;i<10100;i++){
            int el = random_arr.get(i);
            bst.add(el);
        }
        end = t1.EndTime();
        System.out.println("Time that it takes to add 10100 values in a BST tree randomly created: " + (end));
        // We remove 4500 of the values in the BST and AVL


        while (random_arr.size() > 1000) {
            int index=0;
                avl.delete(random_arr.get(index));
                bst.remove(random_arr.get(index));
                random_arr.remove(index);}


        // We search for the 100 values in the BST and AVL and measure how much the contains operation take

        t1.StartTime();
        for (int i = 0; i < 1000; i++)
            bst.contains(random_arr.get(i));
        end = t1.EndTime();
        System.out.println("Time that it takes to find 1000 values in a BST tree randomly created: " + (end));

        t1.StartTime();
        for (int i = 0; i < 1000; i++)
            avl.contains(random_arr.get(i));
        end = t1.EndTime();
        System.out.println("Time that it takes to find 1000 values in an AVL tree randomly created: " + (end));



        //check the heights
        System.out.println("Height AVL tree: " + avl.height_tree());
        System.out.println("Height BST: " + bst.height()); //higher

        //we create a tree with consecutive numbers from 1 to 5000

        AVL a=new AVL();
        BST b= new BST();
        for (int i = 1; i < 5000; i++) {
            a.add(i);
            b.add(i);
        }
        t1=new time();
        t1.StartTime();
        for (int i = 1; i < 5000; i++)
            a.contains(i);
        end = t1.EndTime();
        System.out.println("Time that it takes to find 5000 values in an AVL tree (tree created with sequantial numbers) : " + (end));

        t1.StartTime();
        for (int i = 1; i < 5000; i++)
            b.contains(i);
        end = t1.EndTime();
        System.out.println("Time that it takes to find 5000 values in a BST tree(tree created with sequantial numbers) : " + (end));

        //check the heights
        System.out.println("Height AVL tree: " + a.height_tree());
        System.out.println("Height BST: " + b.height()); //higher


    }
}