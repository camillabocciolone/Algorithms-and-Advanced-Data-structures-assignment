import java.util.ArrayList;



public class dfs {

    graph g;
    int s;
    ArrayList<Boolean> marked; 
    ArrayList<Integer> edge_to;

    public dfs(graph graph, int s){
        this.s = s;
        g = graph;
        this.marked = new ArrayList<>();
        this.edge_to = new ArrayList<>();
        for (int i = 0; i < g.V(); ++i){
            marked.add(false);
            edge_to.add(0);
        }

        dfs_impl(g, s);

    }

    public void dfs_impl(graph g, int v){

        marked.set(v, true);
        
        for ( int i = 0; i < g.adj_list.get(v).size(); i++){
            edge w = g.adj_list.get(v).get(i);
            if(marked.get(w.dst(v)) == false){
                dfs_impl(g, w.dst(v));
                edge_to.set(w.dst(v), v);
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

        dfs d = new dfs(g, 3);
        System.out.println(d.has_path_to(5));
        System.out.println(d.has_path_to(11));
        System.out.println(d.path_to(8));
    }
}
