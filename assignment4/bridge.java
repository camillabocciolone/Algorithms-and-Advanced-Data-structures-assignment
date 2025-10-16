public class bridge {
    
    public boolean is_bridge(graph g, int v, int w) {
        boolean is_bridge = false;
        g.remove_edge(v, w);

        CC cc = new CC(g);
        if (cc.cnt > 1) //if the edge is a bridge, it will have two components once it is removed
            is_bridge = true;

        g.add_edge(v, w); 

        return is_bridge;
    }

    public static void main(String[] args) {
        
        undirected g = new undirected(6);
        g.add_edge(0, 1);
        g.add_edge(0, 2);
        g.add_edge(1, 2);
        g.add_edge(2, 3);
        g.add_edge(3, 4);
        g.add_edge(3, 5);
        g.add_edge(4, 5);
        //the edge between 2 and 3 is a bridge because it desconnects the graph

        bridge b = new bridge();
        System.out.println(b.is_bridge(g,2,3)); //should be true
        System.out.println(b.is_bridge(g,1,2)); //should be false
        System.out.println(b.is_bridge(g,4,5)); //should be false
    }
}
