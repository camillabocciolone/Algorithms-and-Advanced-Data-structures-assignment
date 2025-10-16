import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Iterator;

public class Queue implements Iterable<Integer>{

    private Integer[] array;
    private int count;

    Queue(){
        array = new Integer[10];
        count = 0;
    }

    public int size(){
        return count; //elements in the array
    }

    public boolean isEmpty(){
        return count==0;
    }

    public void enqueue(int v){
        int i = 0;
        while (i<array.length&&array[i] != null){
            i++;
        }
        if (i<array.length){ //the array is not full
            array[i] = v;
        }
        else{ //we have to make it bigger
            Integer[] tmp = new Integer[2*size()];
            for(i = 0; i<size(); i++){
                tmp[i] = array[i];
            }
            array = tmp;
            array[size()] = v;
        }
        count++;
    }

    public int dequeue(){
        Random random = new Random();
        int randomNum = random.nextInt(array.length-1);
        while (array[randomNum] == null){
            randomNum = random.nextInt(array.length-1);
        }
        array[randomNum] = null;
        count--;
        return randomNum;
    }

    public void ret(){
        for (int i = 0; i < array.length; i++) {
            System.out.println("Element at index " + i + ": " + array[i]);
        }
    }

    private class ArrIterator implements Iterator<Integer>{

        int[] indices;

        int index = 0;

        public ArrIterator() {

            indices = new int[count];

            // Initialize the indices array with the positions of non-null elements
            int index = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    indices[index] = i;
                    index++;
                }
            }

            // rearrange the indices to iterate randomly
            Random rand = new Random();
            for (int i = indices.length - 1; i > 0; i--) {
                int j = rand.nextInt(i + 1);
                int temp = indices[i];
                indices[i] = indices[j];
                indices[j] = temp;
            }
        }

        public boolean hasNext() {
            return index < indices.length ;
        }

        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int curr = indices[index];
            index++;

            return array[curr];
        }
    }

    public Iterator<Integer> iterator(){
        return new ArrIterator();
    }

    public static void main(String[] args) {
        Queue arr = new Queue();
        System.out.println(arr.size());
        arr.enqueue(4);
        arr.enqueue(6);
        arr.enqueue(8);
        arr.enqueue(10);
        arr.enqueue(3);
        arr.enqueue(9);

        arr.dequeue();
        arr.ret();
        for (int element : arr) {
            System.out.println("Random Element: " + element);
        }
    }

}
