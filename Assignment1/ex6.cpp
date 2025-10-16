#include <iostream>
#include <list>
#include <vector>
#include <tuple>
#include <set>
#include <string>

using namespace std;

vector<tuple<int, int> > sum2(vector<int> v, int target);
vector<tuple<int, int, int> > sum3(vector<int> v);


int main(int argc, const char * argv[]) {
    vector<tuple<int, int, int> > res;
    vector<int> d={-1,7,1,0,8,9,-9};
    res=sum3(d);
    for (auto v: res) 
            cout<<"("<<get<0>(v)<<","<<get<1>(v)<<","<<get<2>(v)<< ")\n";
    return 0;
}

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
