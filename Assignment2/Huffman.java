import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Huffman {

    private static class Node{

        public Node( Character l, int f){ 
            letter = l;
            weight = f; 
        }
    
        public Character letter;
        public int weight;
        public Node left;
        public Node right;
    }

    char_freq f;
    List<Character> letters; 
    List<Integer> freq;

    public Huffman(String file) throws Exception{
        f = new char_freq(file);
        f.frequency();
        f.sort();
        letters = f.get_letters();
        freq = f.get_freq();

    }

    public Node Tree(){

        List<Node> nodes = new ArrayList<>();
        for (int j= 0; j< letters.size(); j++){
            Node n = new Node(letters.get(j), freq.get(j));
            nodes.add(n);
        }

        int i = 0;

        while (nodes.size() != 1){

            
            Node new_n = new Node(null, nodes.get(i).weight + nodes.get(i+1).weight );
            new_n.left = nodes.get(i);
            new_n.right = nodes.get(i+1);

            nodes.remove(i);
            nodes.remove(i);
            int index = 0;
            for (int j = 0; j< nodes.size(); j++){
                if(nodes.get(j).weight >= new_n.weight){
                    index = j;
                    break;
                }
                else{
                    index++;
                }
            }
            nodes.add(index, new_n);
        }

        return nodes.get(0);
    }


    public String get_code_by_char(Node curr, String code, Character c){

        if (curr == null) {
            return "";
        }
        if (curr.letter == c) {
            return code;
        }

        String left = get_code_by_char(curr.left, code + "0", c);
        String right = get_code_by_char(curr.right,code + "1", c);

        if (left.isEmpty()){
            return right;
        }
        else{
            return left;
        }
    }

    public static void main(String[] args) throws Exception{
        //String file = "/Users/albaballara/uni/lnu/algorithms and advanced data structures/assignment2/huffman.txt";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write the path for the text file: ");
        String file = scanner.nextLine();
        Huffman tree = new Huffman(file);
        Node root = tree.Tree();
        //Character c = 'o';
        System.out.print("Write a character to get its Huffman code: ");
        String s = scanner.nextLine();
        Character c = s.charAt(0);
        String p = tree.get_code_by_char(root, "", c);
        if (p != ""){
            System.out.println("Code for "+ c + " is " + p);
        }
        else{
            System.out.println("This letter is not in the tree");
        }
        scanner.close();

    }
}
