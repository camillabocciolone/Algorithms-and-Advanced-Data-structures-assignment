import java.util.ArrayList;
import java.util.Iterator;


public class directed extends graph {
    public directed(int v) {
        super(v);
    }


    public int E() {
        int sum = 0;
        for (ArrayList<edge> adj : adj_list)
            sum += adj.size();
        return sum;
    }

    public void add_edge(int v, int w, float weight) {
        edge e = new edge(v, w, weight);
        if (v < adj_list.size() && w < adj_list.size()) {
            this.adj_list.get(v).add(e);
        }
    }

    public void add_edge(int v, int w) {
        edge e = new edge(v, w, 1);
        if (v < adj_list.size() && w < adj_list.size()) {
            this.adj_list.get(v).add(e);
        }
    }


    public void remove_edge(int v, int u) {
        AdjacencyListIterator iter = new AdjacencyListIterator(v);
        while (iter.hasNext()) {
            edge e = iter.next();
            if (e.w == u) {
                adj_list.get(v).remove(e);
                break;
            }
        }
    }


    public static void main(String[] args) {
        directed graph = new directed(6);
        graph.add_edge(1, 2);
        graph.add_edge(3, 5);
        graph.add_edge(2, 5, 6);
        System.out.println(graph.adj_list);
        graph.remove_edge(1, 2);
        System.out.println(graph.adj_list);
        System.out.println("\nEdges of the graph:");

        for (edge e : graph) {
            System.out.println("\nEdge from " + e.v + " to " + e.w);
        }

        /* for (Integer v: graph){
              System.out.println("Vertice " + v);
         }*/

        System.out.println("\nEdges of 3");
        Iterator<edge> adjacencyIter = graph.adjacencyiterator(3);
        while (adjacencyIter.hasNext()) {
            edge e = adjacencyIter.next();
            System.out.println("\nEdge from " + e.v + " to " + e.w);
        }


    }
}

