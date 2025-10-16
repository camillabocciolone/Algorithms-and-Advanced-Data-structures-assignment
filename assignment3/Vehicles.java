import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Vehicles {
    
    public int plate_num;
    public int year;
    public String color;
    public String make;


    public Vehicles(int num, int y, String col, String m){
        plate_num = num;
        year = y;
        color = col;
        make = m;
    }

    public int hashCode(){
        int hashVal = plate_num * year + 1;
        for( int i = 0; i < color.length( ); i++ )
            hashVal = 37 * hashVal + color.charAt( i );

        for( int i = 0; i < make.length( ); i++ )
            hashVal = 37 * hashVal + make.charAt( i );

        if(hashVal<0){
            hashVal = hashVal * (-1);
        }

        return hashVal; 
    }


    public static void main(String[] args) {

        System.out.print("EXPERIMENT 1: Only changing plate number\n");
        //we add 5000 elements so the size is 10007, 5000*2 = 10000 and 10007 is the closest prime number to 10000
        HashTable<Vehicles> table = new HashTable<Vehicles>( 10007 ); 
        int[] arr;
        int sum_coll = 0;
        Random random = new Random();
        for (int i=0; i<5000; i++){
            int random_n = 1000 + random.nextInt(9000);
            Vehicles obj = new Vehicles(random_n, 2002, "blue", "volvo");
            arr = table.insert(obj);
            //System.out.print("Collisions: " + arr[0] + " offset: " + arr[1] + "\n ");
            sum_coll += arr[0];
        }

        int avg = sum_coll/5000;
        System.out.print("\nAverage number of collisions: " + avg +"\n");

        System.out.print("\nEXPERIMENT 2: Different attributes\n");
        //we add 5000 elements so the size is 10007, 5000*2 = 10000 and 10007 is the closest prime number to 10000
        HashTable<Vehicles> table2 = new HashTable<Vehicles>( 10007 ); 
        int[] arr2;
        int sum_coll2 = 0;
        for (int i=0; i<5000; i++){
            int random_n2 = 1000 + random.nextInt(9000);
            int random_year = 2000 + random.nextInt(24);

            ArrayList<String> colors = new ArrayList<>(Arrays.asList("blue", "green", "red", "black", "gray", "white"));
            ArrayList<String> brands = new ArrayList<>(Arrays.asList("volvo", "audi", "mercedes", "tesla"));
            
            int randomIndex_colors = random.nextInt(colors.size());
            int randomIndex_brands = random.nextInt(brands.size());
            String random_color = colors.get(randomIndex_colors);
            String random_brand = brands.get(randomIndex_brands);
            Vehicles obj = new Vehicles(random_n2, random_year, random_color, random_brand);
            arr2 = table2.insert(obj);
            //System.out.print("Collisions: " + arr[0] + " offset: " + arr[1] + "\n ");
            sum_coll2 += arr2[0];
        }

        int avg2 = sum_coll2/5000;
        System.out.print("\nAverage number of collisions: " + avg2);


    }
    
}
