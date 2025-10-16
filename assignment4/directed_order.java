import java.util.ArrayList;
import java.util.NoSuchElementException;

public class directed_order{
    //implements Iterable<edge_order>

    int v; //number of vertices
    ArrayList<ArrayList<edge_order>> adj_list;

    public directed_order(int v) {
        this.v = v;
        this.adj_list = new ArrayList<>();
        for (int i = 0; i < v; ++i)
            adj_list.add(new ArrayList<>());
    }

    public int V() {
        return this.v;
    }

    public int E() {
        int sum = 0;
        for (ArrayList<edge_order> adj : adj_list)
            sum += adj.size();
        return sum ;
    }

    public void add_edge(String v, String w, int pos) {
        edge_order e = new edge_order(v,w);
        
        this.adj_list.get(pos).add(e);

    }


    public class AdjacencyListIterator  { //implements Iterator<edge_order>
        private int currentIndex = 0;
        int v;

        public AdjacencyListIterator(int v) {
            this.v = v;
        }

        public boolean hasNext() {
            return currentIndex < adj_list.get(v).size();
        }

        public edge_order next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            edge_order e = adj_list.get(v).get(currentIndex);
            currentIndex++;
            return e;
        }


    }

   /* public Iterator<edge> iterator() {

        return new EdgeIterator();
    }*/

    // public Iterator<edge_order> iterator() {
    //     return new AdjacencyListIterator();
    // }

    //  public Iterator<Integer> iterator() {
    //     return new VertexIterator();
    // }
}
