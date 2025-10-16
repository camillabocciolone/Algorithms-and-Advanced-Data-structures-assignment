
#include <iostream>
#include <stdio.h>
#include <vector>
#include <chrono>
#include <set>
#include <cstdlib>

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


class percolation{
public:
    percolation (int N) : uf(N * N), n(N){
        int idx=0;
        for(int i=0;i<n;i++){
            grid.push_back(vector<int>());
            for(int j=0;j<n;j++){
                grid.back().push_back(idx);
                idx++;
            }
        }
    }
    
 /*   void print() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    cout << grid[i][j] << " ";
                }
                cout << endl; // Start a new line after each row
            }
        }*/

    void open_site(int site){
        if(opened.find(site+1)!=opened.end()&&site%(n)!=n-1) //check if it is in the right border
            uf.unify(site,site+1);
        if(opened.find(site-1)!=opened.end()&&site%(n)!=0) //check if it is in the left border
            uf.unify(site,site-1);
        if(opened.find(site-n)!=opened.end()&&site>=n) //check if it is in the first row
            uf.unify(site,site-n);
        if(opened.find(site+n)!=opened.end()&&site<((n*n)-n)) //check if it is in the last row
            uf.unify(site,site+n);
        opened.insert(site);
    }
    
    bool percolates(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(uf.connected(grid[0][i],grid[n-1][j]))
                    return true;
            }
        }
        return false;
    }
    
public:
    set<int> opened;
private:
    int n;
    vector<vector<int> > grid;
    unionfind uf;
};



int main(int argc, const char * argv[]) {
    int n=10;
    vector<int> results;
    int sum=0;

    for(int i=0;i<50;i++){
        percolation p(n);
        bool percolates=false;
        int sites_opened=0; //counts the number of sites opened
        p.opened.clear(); //stores the opened sites
        srand(static_cast<unsigned int>(time(nullptr)+i)); //seed the random number generator with current time to use different random sequences each time
        while(percolates==false){
            int site=rand()%(n*n);
            if(p.opened.find(site)==p.opened.end()){ //check if the site is already opened
                p.open_site(site);
                sites_opened++;
            }
            percolates=p.percolates();
        }
        results.push_back(sites_opened);
    }
    
    for(int j=0;j<results.size();j++)
        sum=sum+results[j];
    float avg=sum/results.size();
    float percentuage=avg/(n*n);
    cout <<"the probability is "<<percentuage<<endl;

    return 0;
}
