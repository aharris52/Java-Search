package edu.greenriver.dev;

/**
 * ArrayST
 * Exercise 3.1.2
 * Sequential search (in an unordered array)
 *
 * @param <K> Key
 * @param <V> Value
 *
 * @author PUT YOUR NAME HERE
 * @version 1.0
 */
public class ArrayST<K, V> {
    private static final int INIT_CAP = 8;
    private K[] keys;
    private V[] vals;
    private int n;

    /**
     * Initializes an empty symbol table with the specified initial capacity.
     */
    public ArrayST(){
        keys = (K[]) new Object[INIT_CAP];
        vals = (V[]) new Object[INIT_CAP];
    }

    /**
     * Inserts the specified key-value pair at the end of the symbol table
     *
     * @param  key the key
     * @param  val the value
     */
    public void put(K key, V val){
        delete(key);
        //resize if needed
        if(n >= vals.length){
            resize(2*n);
        }
        //append key : value pair
        keys[n] = key;
        vals[n] = val;
        n++;
    }

    /**
     * Returns the value associated with the given key in this symbol table.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     */
    public V get(K key){
        for(int i = 0; i < n; i++){
            if(keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    /**
     * Removes the specified key and associated value from this symbol table
     * (if the key is in the symbol table).
     *
     * @param  key the key
     */
    public void delete(K key){
        for(int i = 0; i< n; i++){
            if(key.equals(keys[i])){
                keys[i] = keys[i-1];
                vals[i] = vals[i-1];
                keys[n-1] = null;
                vals[n-1] = null;
                n--;
                if(n > 0 && n == keys.length/4){
                    resize(keys.length/2);
                }
                return;
            }
        }
    }

    /**
     * Does this symbol table contain the given key?
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size(){
        return n;
    }

    public Iterable<K> keys(){
        Queue<K> queue = new Queue<K>();
        for(int i = 0; i < n; i++){
            queue.enqueue(keys[i]);
        }
        return queue;
    }

    /**
     * Resizes the key and value arrays
     *
     * @param capacity
     */
    //Resizes the the key and val arrays
    private void resize(int capacity){
        K[] tempk = (K[]) new Comparable[capacity];
        V[] tempv = (V[]) new Object[capacity];
        for(int i = 0; i < n; i++){
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        keys = tempk;
        vals = tempv;
    }
}
