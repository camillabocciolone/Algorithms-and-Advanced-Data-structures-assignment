#include <stdio.h>
#include <iostream>
#include <vector>
#include <chrono>

using namespace std;


class Timer {
public:
    void startTime() {
            start= chrono::high_resolution_clock::now();
        }
    void endTime() {
            end= chrono::high_resolution_clock::now();
            chrono::duration<double> difference = end - start;
            cout << "it took you:"<< difference.count() << " seconds" << endl;
        }

    private:
    chrono::time_point<std::chrono::high_resolution_clock> start;
    chrono::time_point<std::chrono::high_resolution_clock> end;
};

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
        int id_a=d[a];
        int id_b=d[b];
        for(int i=0;i!=d.size();i++){
            if(d[i]==id_a)
                d[i]=id_b;
        }
    }
    void read (){
        for(int i=0;i!=d.size();i++)
            cout <<d[i]<<endl;
        cout <<"\n"<<endl;
    };
private:
    vector<int> d;
};
int main(int argc, const char * argv[]) {
    int N = 10000;
    int maxUnions = N - 1;
    int stepSize = 1000;
    unionfind uf(N);
    Timer timer;
    timer.startTime();
    for (int i = 0; i < 1; i++) {
            uf.unify(i, i + 1);
        }
    timer.endTime();
    for (int unions = 1000; unions <= maxUnions; unions += stepSize) {
        unionfind uf(N);
        timer.startTime();
        cout << "with "<<unions<<" unions ";
        for (int i = 0; i < unions; i++) {
                uf.unify(0, i + 1);
            }
        timer.endTime();
        }
    return 0;
}

