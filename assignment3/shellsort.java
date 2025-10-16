import java.util.Random;

public class shellsort {
    public static void sortKnuth(int[] arr) {
        int n = arr.length;
        int h = 1;


        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {

            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && arr[j] < arr[j - h]; j -= h) {
                    int temp = arr[j];
                    arr[j] = arr[j - h];
                    arr[j - h] = temp;
                }
            }


            h = h / 3;
        }
    }

    public static void firstshellsort(int[] arr) {
        int n = arr.length;
        int h = 1;

        while (h < n/2) {
            h = 2*h;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && arr[j - h] > arr[j]; j -= h) {
                    int temp = arr[j];
                    arr[j] = arr[j - h];
                    arr[j - h] = temp;
                }
            }

            h = h / 2;
        }
    }


    public static void sortHibbard(int[] arr) {
        int n = arr.length;
        int h = 1;

        // Calculate the largest h value less than or equal to n/2
        while (h < n / 2) {
            h = 2 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && arr[j - h] > arr[i]; j -= h) {
                    int temp = arr[j];
                    arr[j] = arr[j - h];
                    arr[j - h] = temp;
                }
            }

            h = (h - 1) / 2;
        }
    }

    public static void main(String[] args)
    {
        shellsort ms = new shellsort();
        InsertSort is=new InsertSort();
        Random random = new Random();
        time timer = new time();
        long meanshellbasic = 0;
        long meanshellKnuth = 0;
        long meanshellHibbard = 0;
        long meaninsertionsort = 0;
        int numArrays = 100;
        int arraySize = 10000;
        for (int i = 0; i < numArrays; i++) {
            int[] Array1 = new int[arraySize];
            int[] Array2 = new int[arraySize];
            int[] Array3 = new int[arraySize];
            int[] Array4 = new int[arraySize];
            for (int j = 0; j < arraySize; j++) {
                Array1[j] = random.nextInt(10000);
                Array2[j]=Array1[j];
                Array3[j]=Array1[j];
                Array4[j]=Array1[j];
            }
            timer.StartTime();
            shellsort.firstshellsort(Array2);
            meanshellbasic += timer.EndTime();
            timer.StartTime();
            shellsort.sortHibbard(Array3);
            meanshellHibbard += timer.EndTime();
            timer.StartTime();
            is.sort(Array4,0,arraySize-1);
            meaninsertionsort += timer.EndTime();
            timer.StartTime();
            shellsort.sortKnuth(Array1);
            meanshellKnuth += timer.EndTime();
        }
        System.out.println("To sort using the instertion sort on " +numArrays +" arrays it took in mean " +meaninsertionsort/numArrays+ " nanoseconds");
        System.out.println("To sort using the basic shellsort on " +numArrays +" arrays it took in mean " +meanshellbasic/numArrays+ " nanoseconds");
        System.out.println("To sort using the Hibbard sort on " +numArrays +" arrays it took in mean " +meanshellHibbard/numArrays+ " nanoseconds");
        System.out.println("To sort using the Knuth sort on " +numArrays +" arrays it took in mean " +meanshellKnuth/numArrays+ " nanoseconds");
    }
}
