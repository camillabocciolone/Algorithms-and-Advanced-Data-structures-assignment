#include <iostream>
#include <list>
#include <vector>
#include <tuple>
#include <stdio.h>
#include <chrono>

using namespace std;
list<tuple<int, int, int>> sum3(vector<int> v);


list<tuple<int, int, int>> sum3(vector<int> v){
    list<tuple<int, int, int>> res;
    tuple<int, int, int> tup;
    for(int i=0;i!=v.size();i++)
        for(int j=0;j!=v.size();j++)
            for(int k=0;k!=v.size();k++){
                if (i==j or i==k or j==k)
                    continue;
                if (v[i]+v[j]+v[k]==0){
                    tup=make_tuple(v[i],v[j],v[k]);
                    res.push_back(tup);
                }
            }
    return res;
}

class Timer {
public:
    void startTime() {
            start= chrono::high_resolution_clock::now();
        }
    void endTime() {
            end= chrono::high_resolution_clock::now();
            chrono::duration<double> difference = end - start;
            cout <<  difference.count() << "," << endl;
        }

    private:
    chrono::time_point<std::chrono::high_resolution_clock> start;
    chrono::time_point<std::chrono::high_resolution_clock> end;
};


int main(int argc, const char * argv[]) {
    list<tuple<int, int, int>> res;
    vector<int> sizes={1, 2, 3, 10, 20, 30,40,50,60,70,80,90,100,200, 300, 400};
    for(int i=0;i<sizes.size();i++){
        Timer t;
        vector<int> d;
        for(int j=0;j<sizes[i];j++){
            srand(static_cast<unsigned int>(time(nullptr)+i));
            d.push_back(rand());
        }
        t.startTime();
        res=sum3(d);
        t.endTime();
    }
    return 0;
}

