import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class graph implements Iterable<edge>{
   // public class graph implements Iterable<Integer>{
    int v; //number of vertices
    ArrayList<ArrayList<edge>> adj_list;

    public graph(int v){
        this.v = v;
        adj_list = new ArrayList<>();
        for (int i = 0; i < v; ++i)
            adj_list.add(new ArrayList<>());
    }

    public int V() {
        return this.v;
    }

    public int E() {
        return 0; //default number
    }

    public int degree(int a) {
        return this.adj_list.get(a).size();
    }

    public void add_edge(int v, int w, float weight) {}

    public void add_edge(int v, int w) {}

    public void remove_edge(int v, int u) {}


    public class VertexIterator implements Iterator<Integer> {
        private int currentIndex = 0;

        public boolean hasNext() {

            return currentIndex < V();
        }

        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int vertex = currentIndex;
            currentIndex++;
            return vertex;
        }
    }


    public class EdgeIterator implements Iterator<edge> {
        private int vertexIndex = 0;
        private int edgeIndex = 0;


        public boolean hasNext() {
            if (vertexIndex >= V()){
                return false;
            }
            else{
                while (adj_list.get(vertexIndex).isEmpty()){
                    if (vertexIndex < V()-1){
                        vertexIndex++;
                    }
                    else{
                        return false;
                    }
                }
            }
            return edgeIndex < adj_list.get(vertexIndex).size();
        }


        public edge next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            edge e = adj_list.get(vertexIndex).get(edgeIndex);
            edgeIndex++;
            if (edgeIndex >= adj_list.get(vertexIndex).size()) {
                vertexIndex++;
                edgeIndex = 0;
            }
            return e;
        }
    }


    public class AdjacencyListIterator implements Iterator<edge> {
        private int currentIndex = 0;
        int v;

        public AdjacencyListIterator(int v) {
            this.v = v;
        }

        public boolean hasNext() {
            return currentIndex < adj_list.get(v).size();
        }

        public edge next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            edge e = adj_list.get(v).get(currentIndex);
            currentIndex++;
            return e;
        }


    }

     public Iterator<edge> adjacencyiterator(int input) {
        return new AdjacencyListIterator(input);
    }

   public Iterator<edge> iterator() {
        return new EdgeIterator();
    }

 /* public Iterator<Integer> iterator() {
    return new VertexIterator();
 }*/


}
