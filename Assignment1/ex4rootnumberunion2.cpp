#include <stdio.h>
#include <vector>
#include <iostream>
#include<array>
#include <random>
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
        cout << difference.count() << "," << endl;
        }

    private:
    chrono::time_point<std::chrono::high_resolution_clock> start;
    chrono::time_point<std::chrono::high_resolution_clock> end;
};


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
        cout <<"{"<<endl;
        for(int i=0;i!=d.size();i++)
            cout <<d[i]<<endl;
        cout <<"}\n"<<endl;
    }; //no
private:
    std::vector<int> d;
};


int main(int argc, const char * argv[]) {
    Timer timer;
    timer.startTime();
    int N = 10000; // Fixed size N
    int maxUnions = 9000; // Maximum number of unions to perform
    int stepSize = 1000; // Increment unions by this amount in each step
    QunionFind uf(N);
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<int> dis(0, N - 1);

    for (int unions = stepSize; unions <= maxUnions; unions += stepSize) {
        QunionFind uf(N);
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

