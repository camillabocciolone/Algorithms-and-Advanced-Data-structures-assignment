import java.util.Arrays;

public class InsertSort {

     static void sort(int[] array, int start, int end) {
        for (int i = start+1; i <= end; i++) {
            for (int j = i; j > start; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                } else
                    break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,5,9,4,1,2,5,9,1};
        int start = 0;
        int end=4;
        sort(arr,start, end);
        System.out.print("Sorted until pos "+ end + ": "+ Arrays.toString(arr));
    }
}

