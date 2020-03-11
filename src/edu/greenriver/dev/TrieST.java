package edu.greenriver.dev;

public class TrieST<Value> {
    // fields
    private static final int R = 256; //number of child nodes
    private Node root = new Node();

    // private constructor class
    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key){
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    //recursive helper method
    //returns the node associated with key in the subtrie at x
    private Node get(Node x, String key, int d){
        if(x == null) return null;
        if(d == key.length()) return x;
        char c = key.charAt(d); //we use the dth subtrie to locate the subtrie we want
        return get(x.next[c], key, d+1);
    }

    public void put(String key, Value val){
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d){
        // change value associated with key if in subtrie rooted at x
        if(x == null) x = new Node();
        if(d == key.length()){
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new Queue<String>();
        collect(get(root, prefix, 0), prefix, queue);
        return queue;
    }
    private void collect(Node x, String prefix, Queue<String> queue) {
        if(x == null) {
            return;
        }
        if(x.val != null) {
            queue.enqueue(prefix);
        }
        for(char c = 0; c < R; c++) {
            collect(x.next[c], prefix + c, queue);
        }
    }
}
