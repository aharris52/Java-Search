package edu.greenriver.dev;

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
    private int n;

    public BinarySearchST(int capacity){
        keys = (K[]) new Comparable[capacity];
        vals = (V[]) new Object[capacity];
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public V get(K key){
        //checking for valid arguments
        if(key == null){
            throw new IllegalArgumentException("Argument for get() is null.\n");
        }
        if(isEmpty()){
            return null;
        }
        int i = rank(key);
        if(i < n && keys[i].compareTo(key) == 0){
            return vals[i];
        } else {
            return null;
        }

    }

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

    public void put(K key, V val){
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
    }


    public void delete(K key){
        if(key == null) throw new IllegalArgumentException("Cannot perform deletion on a null key");
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

}
