#include <iostream>
#include <list>
#include <vector>
#include <tuple>
#include <string>

using namespace std;
vector<tuple<int, int, int> > sum3(vector<int> v);

vector<tuple<int, int, int>> sum3(vector<int> v){
    vector<tuple<int, int, int>> res;
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
 
int main(int argc, const char * argv[]) {
    vector<tuple<int, int, int> > res;
    vector<int>  d={-1,8,-7,7,9,2,3,5,-1,0};
    res=sum3(d);
    for (auto v: res)
            cout<<"("<<get<0>(v)<<","<<get<1>(v)<<","<<get<2>(v)<< ")\n";
    return 0;
}
