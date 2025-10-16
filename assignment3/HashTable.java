import java.util.Arrays;

public class HashTable<E> {

    public E[] table;
    public boolean[] table_boolean;
    public int size;

    public HashTable(int s) {
        size = s;
        table = (E[]) new Object[s];
        table_boolean = new boolean[s];
        Arrays.fill(table_boolean, false); //initialize everything to false because the table initially is empty

    }

    public int[] insert(E obj) {
        boolean found = false;
        int hash_v = obj.hashCode();

        int i = 0; //collisions
        int offset = 0;
        while (found == false) {
            offset = i * i;
            if (table_boolean[(hash_v + i * i) % size] == false) { //when I find an empty spot I insert the value and set the boolean array to true
                found = true;
                table[(hash_v + i * i) % size] = obj;
                table_boolean[(hash_v + i * i) % size] = true;
            } else {
                i++;
            }
        }

        return new int[]{i, offset}; // (collisions, offset)
    }

    public void delete(E obj) {

        int pos = find(obj);
        if (pos != -1) {
            table_boolean[pos] = false;
            System.out.print("Item deleted");
        } else {
            System.out.print("The item was not found so it can't be deleted");
        }

    }

    public int find(E obj) {
        int hash_v = obj.hashCode();
        boolean found = false;
        int i = 0;
        int pos;
        while (found == false && i < size) {

            if (table[(hash_v + i * i) % size] == obj && table_boolean[(hash_v + i * i) % size] == true) {
                found = true;

            } else {
                if (table[(hash_v + i * i) % size] == null) { //if there isn't anything in the hash table it
                    // means we never reached that position when adding the object
                    break;
                } else {
                    i++;
                }

            }

        }

        if (found ==true){
            pos = (hash_v + i*i ) % size;
        }
        else{
            pos = -1;
        }

        return pos;
    }

    public static void main(String[] args) {
        // the closest prime number to the elements we want to insert
        // insert 10 val, 10*2, closest prime is 23
        HashTable<HashTableTest> table = new HashTable<HashTableTest> (23);

        HashTableTest obj1 = new HashTableTest("camilla");
        HashTableTest obj2 = new HashTableTest("alba");
        HashTableTest obj3 = new HashTableTest("hola");
        HashTableTest obj4 = new HashTableTest("oahl");
        HashTableTest obj5 = new HashTableTest("ciao");
        HashTableTest obj6 = new HashTableTest("oica");
        HashTableTest obj7 = new HashTableTest("caio");
        HashTableTest obj8 = new HashTableTest("hello");
        HashTableTest obj9 = new HashTableTest("bye");
        HashTableTest obj10 = new HashTableTest("bey");
        int[] arr = table.insert(obj1);
        System.out.print("Collisions: " + arr[0] + " offset: " + arr[1] + "\n ");
        arr =table.insert(obj2);
        System.out.print("Collisions: " + arr[0] + " offset: " + arr[1] + "\n ");
        arr =table.insert(obj3);
        System.out.print("Collisions: " + arr[0] + " offset: " + arr[1] + "\n ");
        arr =table.insert(obj4);
        System.out.print("Collisions: " + arr[0] + " offset: " + arr[1] + "\n ");
        arr =table.insert(obj5);
        System.out.print("Collisions: " + arr[0] + " offset: " + arr[1] + "\n ");
        arr =table.insert(obj6);
        System.out.print("Collisions: " + arr[0] + " offset: " + arr[1] + "\n ");
        arr =table.insert(obj7);
        System.out.print("Collisions: " + arr[0] + " offset: " + arr[1] + "\n ");
        arr =table.insert(obj8);
        System.out.print("Collisions: " + arr[0] + " offset: " + arr[1] + "\n ");
        arr =table.insert(obj9);
        System.out.print("Collisions:: " + arr[0] + " offset: " + arr[1] + "\n ");
        arr =table.insert(obj10);
        System.out.print("Collisions:: " + arr[0] + " offset: " + arr[1] + "\n ");


        // for (int i = 0; i < 23; i++) {
        //     System.out.print(table.table[i] + "\n ");
        // }


        System.out.print(table.find(obj3) + "\n");
        System.out.print(table.find(obj4) + "\n");
        System.out.print(table.find(obj1) + "\n");

        System.out.print("\nWe delete the first object we added\n");

        table.delete(obj10);
        arr =table.insert(obj10);

        // for (int i = 0; i < 23; i++) {
        //     System.out.print(table.table[i] + "\n ");
        // }

    }
}

