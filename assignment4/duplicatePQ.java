public class duplicatePQ {
    private int cap;
    public duplicate[] h;
    public static int size;

    public duplicatePQ(int cap) {
        this.cap = cap + 1;
        this.h = new duplicate[this.cap];
        this.size = 0;
    }

    public duplicatePQ() {
        this.cap = 0;
        this.h = new duplicate[this.cap];
        this.size = 0;
    }

    public void insert(duplicate key) {
        size++;
        h[size] = key;
        swim(size);
    }

    public duplicate delMin() {
        if (size > 0) {
            duplicate min = h[1];
            h[1] = h[size];
            h[size] = null;
            size--;
            sink(1);
            return min;
        }
        return null; // Return null if the priority queue is empty.
    }

    private void swim(int k) {
        while (k > 1 && h[k / 2].dist > h[k].dist) {
            duplicate temp = h[k];
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
            if (j < size && h[j].dist > h[j + 1].dist) {
                j++;
            }

            if (h[k].dist <= h[j].dist) {
                break;
            }

            duplicate temp = h[k];
            h[k] = h[j];
            h[j] = temp;

            k = j;
        }

    }
    public boolean isEmpty() {
        return size==0;
    }

    public boolean contains(duplicate key) {
        for (int i = 1; i <= size; i++) {
            if (h[i].equals(key)) {
                return true;
            }
        }
        return false;
    }
}

