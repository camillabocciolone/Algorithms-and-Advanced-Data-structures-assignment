import java.util.ArrayList;
import java.util.Iterator;

public class Dijkstra {
    float inf = Float.POSITIVE_INFINITY;
    ArrayList<edge> edgeTo;
    ArrayList<Float> distTo;
    duplicatePQ pq;

    int iteration=0;

    public Dijkstra(graph G, int s) {
        edgeTo = new ArrayList<>();
        distTo = new ArrayList<Float>();
        for (int i = 0; i < G.V(); ++i) {
            edgeTo.add(null);
            distTo.add(inf);
        }
        pq = new duplicatePQ(G.E());
        distTo.set(s, 0.0f);
        pq.insert(new duplicate(s, 0.0f));
        while (!pq.isEmpty()) {
            int v = pq.delMin().v; //su slide preleva la coppia dst e v ma non credo usi dst
            Iterator<edge> adjacencyIter = G.adjacencyiterator(v);
            while (adjacencyIter.hasNext()) {
                edge e = adjacencyIter.next();
                relax(e);
                iteration++;
            }
        }
    }

    void relax(edge e) {
        if (this.distTo.get(e.dst()) > this.distTo.get(e.src()) + e.weight) {
            this.distTo.set(e.dst(), this.distTo.get(e.src()) + e.weight);
            this.edgeTo.set(e.dst(), e);
            for (int i = 1; i <= duplicatePQ.size; ++i) {
                duplicate p = pq.h[i];
                if (p.v == e.dst()) {
                    this.pq.h[i]=new duplicate(e.dst(), this.distTo.get(e.dst()));
                    pq.heapify();
                    return;
                }
            }

            this.pq.insert(new duplicate(e.dst(), this.distTo.get(e.dst())));
        }
    }

    int iteration() {
        return iteration;
    }
    boolean hasPathTo(int v) {
        return this.distTo.get(v) < this.inf;
    }

    ArrayList<edge> pathTo(int v) {
        if (!hasPathTo(v))
            return null;
        ArrayList<edge> path = new ArrayList<>();
        edge x = this.edgeTo.get(v);
        while (x != null) {
            path.add(0, x);
            x = edgeTo.get(x.src());
        }
        return path;
    }
}