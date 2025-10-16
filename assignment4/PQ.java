
public class PQ {
    private int cap;
    private edge[] h;
    private static int size;

    public PQ(int cap) {
        this.cap = cap + 1;
        this.h = new edge[this.cap];
        this.size = 0;
    }

    public void insert(edge key) {
        size++;
        h[size] = key;
        swim(size);
    }

    public edge delMin() {
        if (size > 0) {
            edge min = h[1];
            h[1] = h[size];
            h[size] = null;
            size--;
            sink(1);
            return min;
        }
        return null; // Return null if the priority queue is empty.
    }

    private void swim(int k) {
        while (k > 1 && h[k / 2].weight > h[k].weight) {
            edge temp = h[k];
            h[k] = h[k / 2];
            h[k / 2] = temp;
            k = k / 2;
        }
    }

    public void heapify() {
        int n = size;
        for (int i = n / 2; i >= 1; i--) {
            sink(i);
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && h[j].weight > h[j + 1].weight) {
                j++;
            }

            if (h[k].weight <= h[j].weight) {
                break;
            }

            edge temp = h[k];
            h[k] = h[j];
            h[j] = temp;

            k = j;
        }

    }

    public void printQ() {
        System.out.println("Elements in the priority queue:");
        for (int i = 1; i <= size; i++) {
            System.out.println("Edge: (" + h[i].v + ", " + h[i].w + "), Weight: " + h[i].weight);
        }
    }
    public boolean isEmpty() {
        return PQ.size==0;
    }

    public boolean contains(edge key) {
        for (int i = 1; i <= size; i++) {
            if (h[i].equals(key)) {
                return true;
            }
        }
        return false;
    }


    /*public static void main(String[] args) {
        PQ minPriorityQueue = new PQ(10); // Create a min priority queue with a capacity of 10.

        // Insert elements into the priority queue.
        minPriorityQueue.insert(new edge(1, 2, 3.5f));
        minPriorityQueue.insert(new edge(2, 3, 1.2f));
        minPriorityQueue.insert(new edge(4, 5, 0.8f));
        minPriorityQueue.insert(new edge(3, 6, 2.7f));
        minPriorityQueue.delMin();

        // Delete and print the minimum elements.
       minPriorityQueue.printQ();
    }*/
}

