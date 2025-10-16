#include <cassert>
#include <iostream>
#include <stdio.h>
#include <vector>
#include <chrono>

using namespace std;

class unionfind
{
public:
    unionfind (int N){
        d.resize(N); 
        for(int i=0;i!=N;i++)
            d[i]=i;
    }
    bool connected (int a, int b){
        return d[a]==d[b];
        
    }
    void unify (int a, int b){
        //Timer timer;
        //timer.startTime();
        int id_a=d[a];
        int id_b=d[b];
        for(int i=0;i!=d.size();i++){
            if(d[i]==id_a)
                d[i]=id_b;
        }
        //cout <<  " unify " << a<<  " and "<< b<<  " took ";
        //timer.endTime();
        
    }
    void read (){
        for(int i=0;i!=d.size();i++)
            cout <<d[i]<<" ";
        cout <<"\n"<<endl;
    };
private:
    vector<int> d;
};


int main(int argc, const char * argv[]) {
    int n=10;
    unionfind l(n);
    cout <<"Initial list: ";
    l.read();
    l.unify(0,1);
    l.connected(0, 1);
    l.unify(2,3);
    l.unify(3,4);
    l.unify(7,8);
    cout <<"List after the unions: ";
    l.read();
    assert(l.connected(7, 8)==true);
    assert(l.connected(0, 3)==false);
    return 0;
}
