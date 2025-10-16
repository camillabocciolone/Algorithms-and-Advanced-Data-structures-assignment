import java.util.Random;
import java.util.HashSet;
import java.util.Set;

public class ShortestPathComparison {
    public static void main(String[] args) {
        int numVertices = 1000; //  number of vertices
        int sourceVertex = 2; //  source vertex
        int numberofgraphs = 10;
        int[] BellmanFord = new int[numberofgraphs];
        int[] Dijkstra = new int[numberofgraphs];

        for (int i = 0; i < numberofgraphs; i++) {
            directed graph = generateRandomGraph2(numVertices);
            // Run Dijkstra's algorithm
            Dijkstra dijkstra = new Dijkstra(graph, sourceVertex);
            Dijkstra[i] = dijkstra.iteration();
            System.out.println(" To find the shortest path using Dijkstra in graph " + i + " I had to relax " + dijkstra.iteration() + " on a total of " + graph.E() + " edges");
            // Run Bellman-Ford algorithm
            BellmanFord bellmanFord = new BellmanFord(graph, sourceVertex);
            BellmanFord[i] = bellmanFord.iteration();
            System.out.println(" To find the shortest path using Bellman in graph " + i + " I had to relax " + bellmanFord.iteration() + " on a total of " + graph.E() + " edges");
        }

    }

    public static directed generateRandomGraph2(int numVertices) {
            directed graph = new directed(numVertices);
            Random rand = new Random();
            Set<String> usedEdges = new HashSet<>();

            for (int i = 0; i < numVertices; i++) {
                int numEdges = rand.nextInt((numVertices)/100); // Random number of edges for each vertex that is at least numVertices/100
                for (int j = 0; j < numEdges; j++) {
                    int w;
                    String edgeKey;
                    do {
                        w = rand.nextInt(numVertices); // Random destination vertex
                        while (w == i)
                            w = rand.nextInt(numVertices); // Not an edge from a vertex to the same vertex
                        edgeKey = i + "-" + w;
                    } while (usedEdges.contains(edgeKey));

                    float weight = rand.nextFloat() * 10; // Random non-negative weight
                    graph.add_edge(i, w, weight);
                    usedEdges.add(edgeKey);
                }
            }

            return graph;
        }




}
