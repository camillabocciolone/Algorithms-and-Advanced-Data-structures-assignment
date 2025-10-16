import java.util.ArrayList;
import java.util.Iterator;

public class BellmanFord {
    float inf = Float.POSITIVE_INFINITY;
    ArrayList<edge> edgeTo;
    ArrayList<Float> distTo;
    ArrayList<Boolean> onQueue;
    ArrayList<Integer> queue;
    int iteration=0;

    BellmanFord(graph G, int s) {
        edgeTo = new ArrayList<>();
        distTo = new ArrayList<>();
        onQueue = new ArrayList<>();
        queue = new ArrayList<>();
        for (int i = 0; i < G.V(); ++i) {
            this.edgeTo.add(null);
            this.distTo.add(inf);
            this.onQueue.add(false);
        }
        this.distTo.set(s, 0.0f);
        this.onQueue.set(s, true);
        this.queue.add(s);

        while (!queue.isEmpty()) {
            int v = this.queue.remove(this.queue.size() - 1);
            this.onQueue.set(v, false);
            this.relax(G, v);
        }
    }

    void relax(graph G, int v) {
        Iterator<edge> adjacencyIter = G.adjacencyiterator(v);
        while (adjacencyIter.hasNext()) {
            edge e=adjacencyIter.next();
            int w = e.dst();
            iteration++;
            if (this.distTo.get(w) > this.distTo.get(v) + e.weight) {
                this.distTo.set(w, this.distTo.get(v) + e.weight);
                this.edgeTo.set(w, e);
                if (!this.onQueue.get(w)) {
                    this.queue.add(0, w);
                    this.onQueue.set(w, true);
                }
            }
        }
    }

    boolean hasPathTo(int v) {
        return this.distTo.get(v) < this.inf;
    }

    int iteration() {
        return iteration;
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

