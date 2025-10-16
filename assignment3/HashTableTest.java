public class HashTableTest {

    public String key;

    public HashTableTest(String value){
        key = value;
    }

    public int hashCode(){
        int hashVal = 0;
        for( int i = 0; i < key.length( ); i++ )
            hashVal += key.charAt( i );

        return hashVal; 
    }
}
