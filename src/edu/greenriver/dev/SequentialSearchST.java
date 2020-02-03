package edu.greenriver.dev;

import java.util.StringJoiner;

/**
 * SequentialSearchST
 * Algorithm 3.1
 * Sequential search (in an unordered linked list)
 *
 * @param <K> Key
 * @param <V> Value
 *
 * @author PUT YOUR NAME HERE
 * @version 1.0
 */
public class SequentialSearchST<K, V> {

    private Node first;         // first node in the linked list

    private class Node {
        private K key;
        private V val;
        private Node next;

        public Node(K key, V val, Node next) {
            // replace this exception with your code
            throw new UnsupportedOperationException("Remove this exception");

            // see Algorithm 3.1 on page 375
        }

        @Override
        public String toString() {
            return "[" + key + "|" + val + "|" + hashCode() + "]";
        }
    }

    /**
     * Create a sequential search symbol table
     */
    public SequentialSearchST() {
        first = null;
    }

    /**
     * Get the value associated with a key
     * @param key Key to search for
     * @return Value associated with the key (null if key is absent)
     */
    @SuppressWarnings("NewMethodNamingConvention")
    public V get(K key) {
        // replace this exception with your code
        throw new UnsupportedOperationException("Remove this exception");

        // see Algorithm 3.1 on page 375
    }

    /**
     * Put key-value pair into the table
     * @param key Key to insert
     * @param val Value associated with the key to insert
     */
    @SuppressWarnings("NewMethodNamingConvention")
    public void put(K key, V val) {
        // replace this exception with your code
        throw new UnsupportedOperationException("Remove this exception");

        // see Algorithm 3.1 on page 375
    }

    /**
     * Remove key (and its value) from table
     * @param key Key to search for
     */
    public void delete(K key) {
        // replace this exception with your code
        throw new UnsupportedOperationException("Remove this exception");

        // see default implementation provided on p. 364
    }

    /**
     * Is there a value paired with key?
     * @param key Key to search
     * @return true if the key-value pair exists, false otherwise
     */
    public boolean contains(K key) {
        // replace this exception with your code
        throw new UnsupportedOperationException("Remove this exception");

        // see default implementation provided on p. 364
    }

    /**
     * Is the table empty?
     * @return true if table is empty, false otherwise
     */
    public boolean isEmpty() {
        // replace this exception with your code
        throw new UnsupportedOperationException("Remove this exception");

        // see default implementation provided on p. 364
    }

    /**
     * Number of key-value pairs in the table
     * @return number of key-value pairs in the table
     */
    public int size() {
        // replace this exception with your code
        throw new UnsupportedOperationException("Remove this exception");

        // Suggested solution:
        // 1. Add a new private field to the class to track the size
        // 2. Add code to increment the field when a new node
        //    is created in the put() method.
        // 3. Simply return the value of the field in this size() method.
    }

    /**
     * All the keys in the table
     * @return a collection of all the keys in the table
     */
    public Iterable<K> keys() {
        // replace this exception with your code
        throw new UnsupportedOperationException("Remove this exception");

        // Suggested solution:
        // 1. Create a new queue (which implements Iterable)
        // 2. Loop through the linked list, starting at front, and enqueue all the keys
        // 3. Return the reference to the queue
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "{", "}");

        for (K key : keys()) {
            // key=value
            stringJoiner.add(key + "=" + get(key));
        }

        return stringJoiner.toString();
    }
}
