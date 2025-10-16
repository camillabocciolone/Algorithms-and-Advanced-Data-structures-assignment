
public class unionFind {

    public int[] d;

    public unionFind(int N){


        d = new int[N];
        for (int i = 0; i < N; i++) {
            d[i] = i;
        }
    }

    public boolean connected(int a, int b){
        return d[a] == d[b];
    }

    public void union(int a, int b){
        for (int i = 0; i < d.length; i++) {
            if (d[i] == d[a]){
                d[i] = d[b];
            }
        }
    }
}





