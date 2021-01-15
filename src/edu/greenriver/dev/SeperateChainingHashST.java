package edu.greenriver.dev;

/*
* @author Andrew Harris
* @version 1.0
*/

public class SeperateChainingHashST<Key, Value> {

    private int M;  //hash table size
    private SequentialSearchST<Key, Value>[] st;  // this is an array of linked lists
                                            // st == symbol table

    //constructor
    public SeperateChainingHashST(){
        M = 997;

        st = new SequentialSearchST[M];

        // for each array position, make a new SequentialSearchST
        for (int i = 0; i < M; i++){
            st[i] = new SequentialSearchST<>();
        }
    }

    //helper method
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key){
        int index = hash(key);
        return st[index].get(key);
    }

    public void put(Key key, Value value){
        int index = hash(key);      // find the bucket number
        st[index].put(key, value);  // go into the bucket and put it there
    }

    public Iterable<Key> keys(){
        Queue<Key> theBigQueue = new Queue<>();
        for (int i = 0; i < M; i++){
            Iterable<Key> littleQueue = st[i].keys();

            for(Key k : littleQueue){
                theBigQueue.enqueue(k);
            }
        }
        return theBigQueue;
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

}
