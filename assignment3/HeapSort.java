import java.util.Arrays;

public class HeapSort {

    public static void sink(int[] arr, int start, int end, int k) {
        while (2 * k + 1 <= end - start) {
            int j = 2 * k + 1;
            if (j + 1 <= end - start && arr[start + j] < arr[start + j + 1]) {
                j++;
            }

            if (arr[start + k] >= arr[start + j]) {
                break;
            }

            int temp = arr[start + k];
            arr[start + k] = arr[start + j];
            arr[start + j] = temp;

            k = j;
        }
    }

    public static void sort(int[] arr, int start, int end) {
        int size = end - start + 1;

        for (int i = size / 2 - 1; i >= 0; i--) {
            sink(arr, start, end, i);
        }

        for (int i = size - 1; i > 0; i--) {
            int temp = arr[start];
            arr[start] = arr[start + i];
            arr[start + i] = temp;

            sink(arr, start, start + i - 1, 0);
        }
    }

    public static void main(String[] args) {
        int[] list = {10,5,9,4,1,2,5,9,1};
        int start = 1;
        int end = 4;

        System.out.print("Original: "+ Arrays.toString(list));
        sort(list, start, end);
        System.out.print("\nSorted from pos "+start+" until pos "+ end +": "+Arrays.toString( list));
    }
}




