import java.util.ArrayList;

public class bfs {

    graph g;
    int s;
    ArrayList<Boolean> marked; 
    ArrayList<Integer> edge_to;

    public bfs(graph graph, int s){
        this.s = s;
        g = graph;
        this.marked = new ArrayList<>();
        this.edge_to = new ArrayList<>();
        for (int i = 0; i < g.V(); ++i){
            marked.add(false);
            edge_to.add(0);
        }

        bfs_impl(g, s);

    }

    public void bfs_impl(graph g, int v){

        ArrayList<Integer> q = new ArrayList<>();
        q.add(0, v);
        marked.set(v, true);

        while (q.isEmpty()==false) {
            int vv = q.remove(q.size() - 1);
            for ( int i = 0; i < g.adj_list.get(vv).size(); i++){
                edge e = g.adj_list.get(vv).get(i);
                if (marked.get(e.dst(vv)) == false) {
                    q.add(0, e.dst(vv));
                    marked.set(e.dst(vv), true);
                    edge_to.set(e.dst(vv), vv);
                }
            } 
        }
        

    }

    public boolean has_path_to(int v){
        return marked.get(v);
    }

    public ArrayList<Integer> path_to(int v){

        ArrayList<Integer> path = new ArrayList<>();
        if (has_path_to(v) == false){
            return path;
        }

        int x = v;
        while (x != s){
            path.add(0, x);
            x = edge_to.get(x);
        }
        path.add(0, s);
        return path;
    }

    public static void main(String[] args) {
        undirected g = new undirected(15);
        g.add_edge(0, 2);
        g.add_edge(2, 3);
        g.add_edge(1, 3);
        g.add_edge(3, 4);
        g.add_edge(4, 5);
        g.add_edge(4, 6);
        g.add_edge(5, 6);
        g.add_edge(6, 7);
        g.add_edge(7, 8);
        g.add_edge(8, 9);
        g.add_edge(9, 10);
        g.add_edge(10, 6);

        g.add_edge(11, 12);
        g.add_edge(12, 13);
        g.add_edge(12, 14);
        g.add_edge(13, 14);

        bfs b = new bfs(g, 3);
        System.out.println(b.has_path_to(5));
        System.out.println(b.has_path_to(11));
        System.out.println(b.path_to(8));
        System.out.println(b.edge_to);
    }
}
