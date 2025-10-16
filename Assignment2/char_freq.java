import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class char_freq {


    private class LetterFrequency {
        private char letter;
        private int frequency;
    
        public LetterFrequency(char letter, int frequency) {
            this.letter = letter;
            this.frequency = frequency;
        }
    
        public char getLetter() {
            return letter;
        }
    
        public int getFrequency() {
            return frequency;
        }
    }
    
    String sentence;
    List<Character> letter; 
    List<Integer> freq;
    FileReader file;
    List<LetterFrequency> combined;

    public char_freq(String f) throws Exception{
        file = new FileReader(f);
        letter = new ArrayList<>();
        freq = new ArrayList<>();
        combined = new ArrayList<>();
    }

    public void frequency() throws Exception{

        int i;
        
        while ((i = file.read()) != -1){ //true until there is nothing else to read
       
            if (letter.contains((char)i) == true){
                int index = letter.indexOf((char)i);
                int value = freq.get(index);
                freq.set(index, value + 1);
            }
            else{
                letter.add((char)i);
                freq.add(1);
            }
        }

        for (int j = 0; j < letter.size(); j++) {
            char l = letter.get(j);
            int frequency = freq.get(j);
            combined.add(new LetterFrequency(l, frequency));
        }

    }

    public void sort(){

        combined.sort((lf1, lf2) -> Integer.compare(lf1.getFrequency(), lf2.getFrequency())); //ascending order

        letter.clear();
        freq.clear();
        for (LetterFrequency lf : combined) {
            letter.add(lf.getLetter());
            freq.add(lf.getFrequency());
        }
        System.out.println("Sorted Letters: " + letter +"\n");
        System.out.println("Sorted Frequencies: " + freq);
    }

    public List<Integer> get_freq(){
        return freq;
    }
    public List<Character> get_letters(){
        return letter;
    }
}
