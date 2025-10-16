
public class CC {
    private boolean[] marked;
    public int[] ids;
    public int cnt;

    public CC(graph G) {
        int V = G.V();
        marked = new boolean[V];
        ids = new int[V];
        cnt = 0;

        for (int v = 0; v < V; v++) {
            if (!marked[v]) {
                dfs(G, v);
                cnt++;
            }
        }
    }

    private void dfs(graph G, int v) {
        marked[v] = true;
        ids[v] = cnt;

        for ( int i = 0; i < G.adj_list.get(v).size(); i++) {
            edge w = G.adj_list.get(v).get(i);
            if(!marked[w.dst(v)]){
                dfs(G, w.dst(v));
            }
        }
    }
}
