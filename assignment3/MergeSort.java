import java.util.Random;
class MergeSort {

    public void merge(int[] a, int[] tmp, int lo, int mid, int hi){

        for(int k = lo; k < hi +1; k++){
            tmp[k] = a[k];
        }

        int i = lo;
        int j = mid+1;

        for(int k = lo; k < hi +1; k++){
            if (i>mid){
                a[k] = tmp[j];
                j = j +1;
            }
            else{
                if (j>hi){
                    a[k] = tmp[i];
                    i = i+1;
                }
                else{
                    if(tmp[j]<tmp[i]){
                        a[k] = tmp[j];
                        j = j +1;
                    }
                    else{
                        a[k] = tmp[i];
                        i =  i + 1;
                    }
                }
            }
        }


    }


    private void sort_(int[] a,int[] tmp,int lo, int hi){
        int mid;
        if (hi<=lo)
            return;
        mid=lo+(hi-lo)/2;
        sort_(a,tmp,lo,mid);
        sort_(a,tmp,mid+1, hi);
        merge(a, tmp,lo,mid, hi);
    }

    public void sort(int[] a){
        int[] tmp = new int[a.length];
        sort_(a,tmp,0, a.length - 1);
    }


    public void iter_sort(int[] arr) {
        int[] tmp = new int[arr.length];

        for (int j = 2; j/2 <= arr.length; j = j * 2) {
            for (int i = 0; i <= arr.length; i += j) {
                int lo = i;
                int mid = i + j / 2-1;
                int hi = Math.min(i + j - 1, arr.length - 1);
                merge(arr, tmp, lo, mid, hi);
            }
        }
    }




    static void printArray(int A[], int size)
    {
        int i;
        for (i=0; i < size; i++)
            System.out.printf("%d ", A[i]);
        System.out.printf("\n");
    }

    public static void main(String[] args) {
        int numArrays = 100000;
        int ArraySize = 10000;
        Random random = new Random();
        MergeSort mg = new MergeSort();
        time timer = new time();
        long iter = 0;
        long totrec = 0;
        long meaniter = 0;
        long meanrec = 0;
        long[] itertime = new long[numArrays];
        long[] rectime = new long[numArrays];
        for (int i = 0; i < numArrays; i++) {
            int[] Array1 = new int[ArraySize];
            int[] Array2 = new int[ArraySize];
            for (int j = 0; j < ArraySize; j++) {
                Array1[j] = random.nextInt(1000);
                Array2[j]=Array1[j];// Fill the sub-array with random numbers
            }
            timer.StartTime();
            mg.sort(Array1);
            rectime[i]=timer.EndTime();
            meanrec+=rectime[i];
            timer.StartTime();
            mg.iter_sort(Array2);
            itertime[i]=timer.EndTime();
            meaniter+=itertime[i];
        }



        System.out.println("To sort using the iterative mergesort on " +numArrays +" arrays it took in mean " +meaniter/numArrays+ " nanoseconds");

        System.out.println("To sort using the recursive mergesort on " +numArrays +" arrays it took in mean " +meanrec/numArrays+" nanoseconds" );
    }
}
