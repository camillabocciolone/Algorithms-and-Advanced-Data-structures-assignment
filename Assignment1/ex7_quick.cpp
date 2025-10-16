#include <iostream>
#include <list>
#include <vector>
#include <tuple>
#include <set>
#include <stdio.h>
#include <chrono>


using namespace std;


vector<tuple<int, int>> sum2(vector<int> v, int target);
vector<tuple<int, int, int>> sum3(vector<int> v);

vector<tuple<int, int> > sum2(vector<int> v, int target){
    vector<tuple<int, int> > res;
    tuple<int, int> tup;
    set<int> seen;
    int difference;
    for(int i=0;i!=v.size();i++){
        difference=target-v[i];
        if (seen.find(difference)!=seen.end()){
            tup=make_tuple(v[i],difference);
            res.push_back(tup);
        }
        seen.insert(v[i]);
    }
    return res;
}

vector<tuple<int, int, int> > sum3(vector<int> v){
    vector<tuple<int, int, int> > res;
    tuple<int, int, int> tup;
    vector<tuple<int, int> > intermed;
    for(int i=0;i!=v.size();i++){
        vector<int> subvector(v.begin() + i + 1, v.end());
        if (!sum2(subvector, -v[i]).empty()) {
            intermed=sum2(subvector,-v[i]);
            for (int j=0;j!=intermed.size();j++) {
                int first = get<0>(intermed[j]);
                int second = get<1>(intermed[j]);
                int third = v[i]; 
                tup=make_tuple(first, second, third);
                res.push_back(tup);
            }
        }
    }
    return res;
}

class Timer {
public:
    void startTime() {
            start = chrono::high_resolution_clock::now();
        }
    void endTime() {
            end= chrono::high_resolution_clock::now();
            chrono::duration<double> difference = end - start;
            cout <<  difference.count() << " seconds" << endl;
        }

    private:
    chrono::time_point<std::chrono::high_resolution_clock> start;
    chrono::time_point<std::chrono::high_resolution_clock> end;
};



int main(int argc, const char * argv[]) {
    vector<tuple<int, int, int>> res;
    vector<int> sizes={1, 2, 3, 10, 20, 30,40,50,60,70,80,90,100};
    for(int i=0;i<sizes.size();i++){
        Timer t;
        vector<int> d;
        for(int j=0;j<sizes[i];j++){
            srand(static_cast<unsigned int>(time(nullptr)+i)); //seed the random number generator with current time to use different random sequences each time
            d.push_back(rand());
        }
        t.startTime();
        res=sum3(d);
        t.endTime();
    }
    return 0;
}

