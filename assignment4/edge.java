public class edge {

    int v,w;
    float weight;
     
    public edge(int va, int wa, float we){
        v = va;
        w = wa;
        weight = we;
    }

    public int src(){
        return v;
    }

    int dst(int v) { 
        if (v == this.v) {
            return this.w;
        } else {
            return this.v;
        }
    }

    public int dst(){
        return w;
    }

    public float weight_ret(){
        return weight;
    }

    public boolean compare(edge other){
        return weight < other.weight;
    }
}
