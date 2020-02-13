package edu.greenriver.dev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * FrequencyCounter
 * A symbol-table client
 *
 * Counts the frequency of occurrences of strings in a file.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Andrew Harris
 * @version 2.0
 */
public class FrequencyCounter {

    private static final int MINLEN = 8;
    private static final String FILENAME = "tale.txt";
    //private static final String FILENAME = "tinytale.txt";

    /**
     * Main method for FrequencyCounter (symbol table client)
     * @param args not used
     */
    public static void main(String[] args) {

        //will try to create a new scanner obj, reader, and file
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(FILENAME)))) {
            //creates an empty symbol table that holds string-integer pairs
            SequentialSearchST<String, Integer> symbolTable =
                    new SequentialSearchST<>();

            //BinarySearchST<String, Integer> symbolTable = new BinarySearchST<>(1);


            for (int i = 0; scanner.hasNext(); i++) {
                String word = scanner.next();

                //check for 1-letter words
                if(word.length()<MINLEN){
                    continue;   //go to the top of the loop
                }

                //check to see if the value is in the table
                if (!symbolTable.contains(word)){
                    symbolTable.put(word, 1);
                } else {
                    int count = symbolTable.get(word);
                    symbolTable.put(word, count+1);
                }
            }//end for loop
            String max = "";
            symbolTable.put(max, 0);
            for (String word : symbolTable.keys()){
                if(symbolTable.get(word) > symbolTable.get(max)){
                    max = word;
                }
            }
            System.out.println(max + " " + symbolTable.get(max));
        }
        //if it can' it will throw an exception
        catch (IOException x) {
            System.err.println("Unable to open or process file.");
        }

        // When done, check your output to make sure it matches
        // all three output cases at the bottom of p. 372
    }

}
