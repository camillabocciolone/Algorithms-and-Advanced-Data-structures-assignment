#include <cassert>
#include <iostream>
#include <stdio.h>
#include <vector>
#include <chrono>

using namespace std;

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
    int n=100;
    return 0;
}