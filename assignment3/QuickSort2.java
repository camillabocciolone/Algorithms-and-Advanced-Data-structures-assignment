import java.util.Random;


public class QuickSort2 {

        public static int partition_median(int[] a, int lo, int hi ){
            int mid = (lo + hi) / 2;
            int median = median(a, lo, mid, hi);

            int tmp = a[lo];
            a[lo] = a[median];
            a[median] = tmp;

            int pivot = a[lo];
            int i = lo + 1;
            int j = hi;

            while (true) {
                while (i <= j && a[i] <= pivot) {
                    i++;
                }
                while (a[j] > pivot) {
                    j--;
                }
                if (i >= j) {
                    break;
                }
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }

            a[lo] = a[j];
            a[j] = pivot;

            return j;

        }

        private static void sort_is(int[] a, int lo, int hi, int depth){
            if (hi<=lo){
                return;
            }

            if(depth==0){
                InsertSort.sort(a, lo, hi);
            }
            else{
                int j = partition_median(a, lo, hi);
                sort_is(a, lo, j-1, depth-1);
                sort_is(a, j+1, hi, depth-1);
            }
        }

        public static int[] sortIS(int[] a, int depth){
            sort_is(a, 0, a.length-1, depth);
            return a;
        }

        private static void sort_hs(int[] a, int lo, int hi, int depth){
            if (hi<=lo){
                return;
            }

            if(depth==0){
                HeapSort.sort(a,lo,hi);
            }
            else{
                int j = partition_median(a, lo, hi);
                sort_hs(a, lo, j-1, depth-1);
                sort_hs(a, j+1, hi, depth-1);
            }
        }

        public static int[] sortHS(int[] a, int depth){
            HeapSort hs = new HeapSort();
            sort_hs(a, 0, a.length-1, depth);
            return a;
        }

        private static int median(int[] a, int lo, int mid, int hi) {
            int lo_v = a[lo];
            int mid_v = a[mid];
            int hi_v = a[hi];

            if ((lo_v <= mid_v && mid_v <= hi_v) || (hi_v <= mid_v && mid_v <= lo_v)) {
                return mid;
            } else if ((mid_v <= lo_v && lo_v <= hi_v) || (hi_v <= lo_v && lo_v <= mid_v)) {
                return lo;
            } else {
                return hi;
            }
        }

        public static void main(String[] args) {


            int[] sizes = {100000, 200000, 400000, 600000, 800000, 1000000};
            int k = 0;
            int[] depths = {10, 12, 15, 17, 20, 22, 25, 27, 30, 32};
            long[] insert = new long[depths.length];
            long[] heap = new long[depths.length];

            Random random = new Random();

            for (int size : sizes) {
                int[] arr = new int[size];
                int[] arr2 = new int[size];
                int[] arr3 = new int[size];
                for (int j = 0; j < 10; j++) {
                    for (int i = 0; i < size; i++) {
                        arr[i] = random.nextInt(1000000);
                    }

                    time timer = new time();
                    k = 0;
                    //EXPERIMENT


                    for (int depth : depths) {
                        for (int i = 0; i < arr.length; i++) {
                            arr2[i] = arr[i];
                            arr3[i] = arr[i];
                        }
                        timer.StartTime();
                        QuickSort.sortIS(arr2, depth);
                        insert[k] = timer.EndTime();

                        timer.StartTime();
                        QuickSort.sortHS(arr3, depth);
                        heap[k] = timer.EndTime();
                        k++;
                    }

                }
                System.out.println("swapping to insert sort with size" + size+ "it took in average");
                for (int i = 0; i < insert.length; i++) {
                    System.out.println(insert[i] / 10 + " nanoseconds at depth " + depths[i]);
                }
                System.out.println("swapping to heap sort" + size+ "it took in average");
                for (int i = 0; i < heap.length; i++) {
                    System.out.println(heap[i] / 10 + " nanoseconds at depth " + depths[i]);
                }
            }
        }
    }

