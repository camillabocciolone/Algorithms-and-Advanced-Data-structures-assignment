import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Kruskal {
    private List<List<edge>> forests; // List of minimum spanning tree forests
    private PQ pq;
    private unionFind uf;

    public Kruskal(graph G) {
        forests = new ArrayList<>();
        pq = new PQ(G.E());
        uf = new unionFind(G.V());

        // Use CC to find connected components
        CC cc = new CC(G);

        // Initialize the forests
        for (int i = 0; i < cc.cnt; i++) {
            forests.add(new ArrayList<>());
        }

        // Iterate over the edges and add them to the appropriate connected component's forest
        Iterator<edge> edgeIterator = G.iterator();
        while (edgeIterator.hasNext()) {
            edge e = edgeIterator.next();
            if (!pq.contains(e))
                pq.insert(e);
        }

        while (!pq.isEmpty()) {
            edge e = pq.delMin();
            int v = e.src();
            int w = e.dst(v);
            int componentIdV = cc.ids[v];

            if (!uf.connected(v, w)) {
                uf.union(v, w);
                forests.get(componentIdV).add(e);
            }
        }
    }

    public List<List<edge>> forests() {
        return forests;
    }

    public double totalWeight() {
        double weightSum = 0;
        for (int i = 0; i < forests.size(); i++) {
            List<edge> forest = forests.get(i);
            for (int j = 0; j < forest.size(); j++) {
                edge e = forest.get(j);
                weightSum += e.weight;
            }
        }
        return weightSum;
    }


    public static void main(String[] args) {
        // Create an undirected graph
        undirected G = new undirected(8);

        // Add edges to the graph
        G.add_edge(0, 1, 2);
        G.add_edge(0, 2, 4);
        G.add_edge(1, 2, 3);
        G.add_edge(1, 3, 5);
        G.add_edge(2, 3, 1);
        G.add_edge(4, 5, 0);
        G.add_edge(5, 7, 4);// A self-loop

        // Create and run Kruskal's algorithm
        Kruskal kruskal = new Kruskal(G);

        // Get the minimum spanning tree forest
        List<List<edge>> forests = kruskal.forests();

        // Print the minimum spanning tree for each component
        int componentNum = 1;
        for (List<edge> tree : forests) {
            if(!tree.isEmpty()) {
                System.out.println("Minimum Spanning Tree for Component " + componentNum);
                for (edge e : tree) {
                    System.out.println(e.src() + " - " + e.dst(e.src()) + " (Weight: " + e.weight + ")");
                }
            }
            else {
                System.out.println("Minimum Spanning Tree for Component " + componentNum);
                System.out.println( " no edge");
            }
            componentNum++;
        }

        // Print the total weight of the forest
        double totalWeight = kruskal.totalWeight();
        System.out.println("Total Weight of the Minimum Spanning Tree Forest: " + totalWeight);
    }
}
