import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class order {

    directed_order graph;
    ArrayList<Boolean> marked; 
    ArrayList<Integer> postorder;
    ArrayList<String> different_courses;

    public void order_const(){

        marked = new ArrayList<>();
        postorder = new ArrayList<>();
        for (int i = 0; i < graph.V(); ++i)
            marked.add(false);

        for (int v = 0; v < graph.V(); ++v) {
            if (marked.get(v) == false) {
                DFO(v);
            }
        }
    }

    
    public void read_file(String file){ //read the file, create the graph and add edges
        different_courses = new ArrayList<>();
        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();

			while (line != null) {
                String[] parts = line.split(";");
                if (different_courses.contains(parts[0]) == false){
                    different_courses.add(parts[0]);
                }
                if (different_courses.contains(parts[1]) == false){
                    different_courses.add(parts[1]);
                }
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

        graph = new directed_order(different_courses.size());

        try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();

			while (line != null) {
                String[] parts = line.split(";");
                int pos = different_courses.indexOf(parts[0]);
                graph.add_edge(parts[0], parts[1], pos);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }


    public void DFO(int v) {
        marked.set(v, true);
        directed_order.AdjacencyListIterator iter = graph.new AdjacencyListIterator(v);
        while (iter.hasNext()){
            edge_order e = iter.next();
            if (marked.get( different_courses.indexOf(e.dst()) ) == false)
                DFO(different_courses.indexOf(e.dst()));
        }

        postorder.add(v);
    }

    public ArrayList<Integer> reversePost() {
        ArrayList<Integer> rev = postorder;
        Collections.reverse(rev);
        return rev;
    }

    public static void main(String[] args) {
        order o = new order();
        o.read_file("data.txt");

        o.order_const();

        //ArrayList<Integer> ord = o.reversePost();
        ArrayList<String> courses = new ArrayList<>();

        for(int i = 0; i < o.different_courses.size(); i++){
            courses.add(o.different_courses.get(o.postorder.get(i)));
            System.out.println( o.different_courses.get(o.postorder.get(i))); 
        }
    }
}
