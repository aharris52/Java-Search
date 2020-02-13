package edu.greenriver.dev;

import java.util.NoSuchElementException;

/**
 * BinarySearchST
 * Algorithm 3.2
 * Binary search (in an ordered array)
 *
 * @param <K> Key
 * @param <V> Value
 *
 * @author Andrew Harris
 * @version 1.0
 */
public class BinarySearchST <K extends Comparable<K>, V> {
    private K[] keys;
    private V[] vals;
    private int n = 0;

    /**
     * Initializes an empty symbol table with the specified initial capacity.
     * @param capacity the maximum capacity
     */
    public BinarySearchST(int capacity){
        keys = (K[]) new Comparable[capacity];
        vals = (V[]) new Object[capacity];
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size(){
        return n;
    }

    public int size(K lo, K hi){
        if(lo == null){
            throw new IllegalArgumentException("first argument to size() is null");
        }
        if(hi == null){
            throw new IllegalArgumentException("second argument to size() is null");
        }
        if(lo.compareTo(hi) > 0){
            return 0;
        }
        if(contains(hi)){
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty(){
        return size() == 0;
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
     * Returns the value associated with the given key in this symbol table.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public V get(K key){
        //checking for valid arguments
        if(key == null){
            throw new IllegalArgumentException("Argument for get() is null.\n");
        }
        //make sure it's not an empty array
        if(isEmpty()) {return null;}
        int i = rank(key);
        if(i < n && keys[i].compareTo(key) == 0){
            return vals[i];
        } else {
            return null;
        }

    }

    /**
     * Returns the number of keys in this symbol table strictly less than {@code key}.
     *
     * @param  key the key
     * @return the number of keys in the symbol table strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(K key){
        int lo = 0;
        int hi = n-1;
        while (lo <= hi){
            int mid = lo + (hi -lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0){
                hi = mid-1;
            } else if(cmp > 0){
                lo = mid+1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(K key, V val){

        if(n == keys.length){
            resize(n*2);
        }
        int i = rank(key);
        if(i < n && keys[i].compareTo(key) == 0){
            vals[i] = val;
            return;
        }
        for(int j = n; j > i; j--){
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    /**
     * Removes the specified key and associated value from this symbol table
     * (if the key is in the symbol table).
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(K key){
        if(key == null){throw new IllegalArgumentException("Cannot perform deletion on a null key");}
        if(isEmpty()){
            return;
        }
        //check the rank
        int i  = rank(key);
        //check if the key is actually in the table
        if(i == n || keys[i].compareTo(key) != 0){
            return;
        }
        for(int j = i; j < n-1; j++){
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        n--;
        keys[n] = null;     //prevent loitering
        vals[n] = null;
        //resize if needed, @ 1/4 capacity
        if(n> 0 && n == keys.length/4){
            resize(keys.length/2);
        }
    }

    /**
     * Removes the smallest key and associated value from this symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin(){
        if(isEmpty()) {throw new NoSuchElementException();}
        delete(min());
    }

    /**
     * Removes the largest key and associated value from this symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax(){
        if(isEmpty()) {throw new NoSuchElementException();}
        delete(max());
    }

    /**
     * Returns the smallest key in this symbol table.
     *
     * @return the smallest key in this symbol table
     * @throws NoSuchElementException if this symbol table is empty
     */
    public K min(){
        /*if(isEmpty()) {
            throw new NoSuchElementException("Can't call method min() on an empty table.");
        }*/
        return keys[0];
    }

    /**
     * Returns the largest key in this symbol table.
     *
     * @return the largest key in this symbol table
     * @throws NoSuchElementException if this symbol table is empty
     */
    public K max(){
        //if(isEmpty()) {throw new NoSuchElementException("Can't call method max() on an empty table.");}
        return keys[n-1];
    }

    /**
     * Return the kth element from the table[]n .
     *
     * @param  k the order statistic
     * @return the {@code k}th smallest key in this symbol table
     * @throws IllegalArgumentException unless {@code k} is between 0 and
     *        <em>n</em>–1
     */
    public K select(int k){
        if(k < 0 || k >= size()){
            throw new IllegalArgumentException(k + " is an invalid argument for this operation");
        }
        return keys[k];
    }

    /**
     * Resizes the key and value arrays
     *
     * @param capacity
     */
    //Resizes the the key and val arrays
    public void resize(int capacity){
        K[] tempk = (K[]) new Comparable[capacity];
        V[] tempv = (V[]) new Object[capacity];
        for(int i = 0; i < n; i++){
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        keys = tempk;
        vals = tempv;
    }

    /**
     * Returns all keys in this symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     *
     * @return all keys in this symbol table
     */
    public Iterable<K> keys(){
        return keys(min(), max());
    }

    /**
     * Returns all keys in this symbol table in the given range,
     * as an {@code Iterable}.
     *
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return all keys in this symbol table between {@code lo}
     *         (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *         is {@code null}
     */
     public Iterable<K> keys(K lo, K hi){
         if(lo == null) throw new IllegalArgumentException("cannot pass a null argument");
         if(hi == null) throw new IllegalArgumentException("cannot pass a null argument");

         Queue<K> queue = new Queue<K>();
         if(lo.compareTo(hi) > 0) return queue;
         for (int i = rank(lo); i < rank(hi); i++){
             queue.enqueue(keys[i]);
         }
         if(contains(hi)){
             queue.enqueue(keys[rank(hi)]);
         }
         return queue;
     }

}
