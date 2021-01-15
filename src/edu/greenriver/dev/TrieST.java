package edu.greenriver.dev;

/*
* @author Andrew Harris
* @version 1.0
*/

public class TrieST<Value> {
    private final static int R = 256;
    private Node root = new Node();
    private int n;

    // helper class
    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if(x == null) {
            return null;
        }
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        // Return node associated with key in the subtrie rooted at x.
        if(x == null) {
            return null;
        }
        if(d == key.length()) {
            return x;
        }
        char c = key.charAt(d); // use dth key char to identify subtrie.
        return get(x.next[c], key, d +1);
    }
    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }
    private Node put(Node x, String key, Value val, int d) {
        // Change value associated with key if in subtrie rooted at x.
        if(x == null) {
            x = new Node();
        }
        if(d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d); // use dth key char to identify subtrie.
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    public Iterable<String> keys() {

        return keysWithPrefix("");
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
