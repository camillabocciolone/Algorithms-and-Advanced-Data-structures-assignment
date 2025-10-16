#include <stdio.h>
#include <iostream>
#include <vector>
#include <chrono>
#include <random>


using namespace std;


class Timer {
public:
    void startTime() {
            start= chrono::high_resolution_clock::now();
        }
    void endTime() {
            end= chrono::high_resolution_clock::now();
            chrono::duration<double> difference = end - start;
            cout << ""<< difference.count() << "," << endl;
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
    Timer timer;
    timer.startTime();
    int N = 10000; // Fixed size N
    int maxUnions = 9000; // Maximum number of unions to perform
    int stepSize = 1000; // Increment unions by this amount in each step
    unionfind uf(N);
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<int> dis(0, N - 1);

    for (int unions = stepSize; unions <= maxUnions; unions += stepSize) {
        unionfind uf(N);
        timer.startTime();
        for (int i = 0; i < unions; i++) {
            int a = dis(gen);
            int b = dis(gen);
            if(a!=b) //ignore duplicates
                uf.unify(a, b);
        }
        timer.endTime();
    }

    return 0;
}


