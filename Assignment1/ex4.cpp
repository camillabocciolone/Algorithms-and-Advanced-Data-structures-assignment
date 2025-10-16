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
    int a,b;
    vector<int> sizes={10,100,1000,10000,100000,1250000, 1500000,1750000, 2250000,2500000,2750000, 3000000,3250000,3500000, 3750000, 4000000,4250000,4500000, 4750000, 5000000,5250000, 5500000,5750000, 6000000,6250000,6500000,6750000, 7000000,7250000, 7500000,7750000,8000000,8250000, 8500000, 8750000,9000000, 9250000,9500000, 9750000, 10000000};
    for(int i=0;i<sizes.size();i++){
        Timer t;
        unionfind l(sizes[i]);
        t.startTime();
        for(int j=0;j<100;j++){
            srand(static_cast<unsigned int>(time(nullptr)+i));
            a=rand()%(sizes[i]);
            b=rand()%(sizes[i]);
            l.unify(a, b);
        }
        for(int j=0;j<5;j++){
            a=0;
            b=sizes[i]-1;
            l.connected(a, b);
            b=sizes[i]/2;
            l.connected(a, b);
        }
        cout << "with the array of "<<sizes[i]<<" elements ";
        t.endTime();
    }
    return 0;
}
