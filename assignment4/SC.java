import java.util.ArrayList;



public class SC {
    public static void main(String[] args) {
        directed graph = new directed(8); // Assuming 8 vertices
        graph.add_edge(0, 1, 5);
        graph.add_edge(0, 3, 8);
        graph.add_edge(0, 6, 9);
        graph.add_edge(1, 3, 4);
        graph.add_edge(1, 4, 12);
        graph.add_edge(1, 2, 15);
        graph.add_edge(2, 7, 9);
        graph.add_edge(3, 4, 7);
        graph.add_edge(3, 5, 6);
        graph.add_edge(4, 2, 3);
        graph.add_edge(4, 7, 11);
        graph.add_edge(5, 4, 1);
        graph.add_edge(5, 7, 13);
        graph.add_edge(6, 3, 5);
        graph.add_edge(6, 5, 4);
        graph.add_edge(6, 7, 20);

        int sourceVertex = 0; // Choose the source vertex

        BellmanFord bellman = new BellmanFord(graph, sourceVertex);

        System.out.println("Bellman:");
        for (int v = 0; v < graph.V(); v++) {
            if (bellman.hasPathTo(v)) {
                System.out.println("Shortest Path to vertex " + v + ": " + bellman.pathTo(v));
            } else {
                System.out.println("No path to vertex " + v);
            }
        }

        Dijkstra dijkstra = new Dijkstra(graph, sourceVertex);
        System.out.println("Dijkstra:");
        for (int v = 0; v < graph.V(); v++) {
            if (dijkstra.hasPathTo(v)) {
                System.out.println("Shortest Path to vertex " + v + ": " + dijkstra.pathTo(v));
            } else {
                System.out.println("No path to vertex " + v);
            }
        }

    }
}
