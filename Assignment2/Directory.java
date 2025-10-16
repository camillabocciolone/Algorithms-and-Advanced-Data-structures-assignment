import java.io.File;
import java.util.Scanner;

public class Directory {

    File f;
    LCRS node;
    static LCRS root;

    public Directory(String path){
        f = new File(path);
    }                           

    // list all the files and add them to a LCRS tree
    private void listAll( int depth ){
        print(depth);
        if(f.isDirectory()){
            for (int i = 0; i<f.listFiles().length; i++ ){ //each file in this directory is a child 
                Directory child = new Directory(f.listFiles()[i].getAbsolutePath());
                LCRS added = node.add_child(child.f.getAbsolutePath());
                child.node = added;
                child.listAll( depth + 1);
            }
            
        }
    }

    public void list(){
        node = new LCRS(f.getAbsolutePath());
        root = node;
        listAll(0);
    }

    private void print(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  "); // Print spaces to see the structure depending on the depth
        }
        System.out.println(f.getName());
    }

    public LCRS find_node(String path){
        LCRS found = node.find_node(node, path);
        if(found != null){
            System.out.print("\nNode found\n");
        }
        else{
            System.out.print("\nNode not found\n");
        }
        return found;
    }

    public void add_child_to_node(LCRS n, String path_child){
        n.add_child(path_child);
    }

    public static void main(String[] args) {
        //String path = "/Users/albaballara/uni/assignment2";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Write the path for the starting directory: ");
        String path = scanner.nextLine();

        Directory files = new Directory(path);
        System.out.print("\nList of files:\n"); 
        files.list(); // list all the files and create the tree
        System.out.print("\nWalking the tree: \n"); //walk the tree
        files.node.walk(); 

        //String path2 = "/Users/albaballara/uni/assignment2/1/2";
        System.out.print("\nEnter the path of the node you want to find: ");
        String path2 = scanner.nextLine();


        //find a node by its path and then add a new child to the found node
        LCRS found_node = files.find_node(path2);
        if (found_node != null){
            System.out.print("\nEnter the name of the child you want to add to the node you just found: ");
            String path3 = scanner.nextLine();
            scanner.close();
            files.add_child_to_node(found_node, path3);
            // we walk the tree again to check if the file has been added correctly
            System.out.print("\nWalking the tree with the new file added: \n");
            files.node.walk(); 
        }
        else{
            System.out.print("\nYou can't add a child because the node doesn't exist");
        }
        
    }
}
