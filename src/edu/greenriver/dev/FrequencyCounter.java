package edu.greenriver.dev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
*
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

    private static final int MINLEN = 1;
    /*private static final String FILENAME = "tale.txt";*/
    private static final String FILENAME = "tinytale.txt";

/*
*
     * Main method for FrequencyCounter (symbol table client)
     * @param args not used

*/

    public static void main(String[] args) {

        //will try to create a new scanner obj, reader, and file
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(FILENAME)))) {
            //create an empty symbol table that holds string integer table as key-value pairs

            /*SequentialSearchST<String, Integer> symbolTable = new SequentialSearchST<>();*/


            /*BinarySearchST<String, Integer> symbolTable = new BinarySearchST<>(1);*/


             //BST<String, Integer> symbolTable = new BST<>();

            RedBlackBST<String, Integer> symbolTable = new RedBlackBST<>();

            while (scanner.hasNext()) {
                String word = scanner.next();
                if(word.length() < MINLEN) {
                    continue; //go back to top of loop
                }
                if(!symbolTable.contains(word)) {
                    //add a new entry into symbolTable
                    symbolTable.put(word, 1);
                }
                else {
                    //update the value of the key (word)
                    int count = symbolTable.get(word);
                    symbolTable.put(word, count + 1);
                    //compressed version
                    //symbolTable.put(word, symbolTable.get(word, count + 1);
                }
            }//end of while loop

            //find the key with the highest freq count
            String max = "";
            symbolTable.put(max, 0);
            for(String word : symbolTable.keys()) {
                if(symbolTable.get(word) > symbolTable.get(max)) {
                    max = word;
                }
            }
            System.out.println(max + " " + symbolTable.get(max));
        }
        catch (IOException x) {
            System.err.println("Unable to open or process file.");
        }

        // When done, check your output to make sure it matches
        // all three output cases at the bottom of p. 372
    }

}

