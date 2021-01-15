package edu.greenriver.dev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main
 *
 * Contains a small test client to test a symbol table.
 *
 * Input is a sequence of single-character strings (see basic.txt)
 *
 * Creates a symbol table with String keys and Integer values.
 *
 * Builds the symbol table, associating the value i with the ith
 * string in the input and then prints the table.
 *
 * Note that the order of the output may not match the order of the input
 * file, because the symbol table may re-order things to make it more
 * efficient to search. It depends on which implementation of SymbolTable
 * you use (unordered linked list, ordered array, binary search tree, etc.)
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Kendrick Hang
 * @author Andrew Harris
 * @version 2.0
 */
public class BasicTestClient {

    private final static String FILENAME = "basic.txt";

    /**
     * Main method for BasicTestClient
     * @param args not used
     */
    public static void main(String[] args) {
        // ported code using example on p. 370
        // using Java file I/O instead of authors' StdIn

        //SequentialSearchST<String, Integer> symbolTable = new SequentialSearchST<>();

        //BinarySearchST<String, Integer> symbolTable = new BinarySearchST<>(1);

        ArrayST<String, Integer> symbolTable = new ArrayST<String, Integer>();

        /*RedBlackBST<String, Integer> symbolTable = new RedBlackBST<>();*/

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(FILENAME)))) {

            for (int i = 0; scanner.hasNext(); i++) {
                String key = scanner.next();
                symbolTable.put(key, i);
            }

        }
        catch (IOException x) {
            System.err.println("Unable to open or process file.");
        }

        for (String s : symbolTable.keys()) {
            System.out.println(s + " " + symbolTable.get(s));
        }

    }

}
