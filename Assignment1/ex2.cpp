#include <iostream>
#include <vector>


using namespace std;


class QunionFind
{
public:
    QunionFind (int N){
        d.resize(N);
        for(int i=0;i!=N;i++)
            d[i]=i;
    };
    bool connected (int a, int b){
        return root(a)==root(b);
    };
    void unify (int a, int b){
        int ra=root(a);
        int rb=root(b);
        d[ra]=rb;
        
    };
    int root (int a){
        while(a!=d[a])
            a=d[a];
        return a;
    };
    void read (){
        cout <<"{";
        for(int i=0;i!=d.size();i++)
            cout <<d[i]<<" , ";
        cout <<"}\n"<<endl;
    }; 
private:
    std::vector<int> d;
};



int main(int argc, const char * argv[]) {
    int n=8;
    QunionFind l(n);
    l.read();
    l.unify(0,1);
    l.read();
    l.unify(1,2);
    l.read();
    l.unify(3,4);
    l.read();
    return 0;
}