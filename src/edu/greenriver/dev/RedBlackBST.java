/*
 *
 * LLRedBlackBST
 * A symbol-table client
 *
 * Counts the frequency of occurrences of strings in a file.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Andrew Harris
 * @version 2.0

 */

package edu.greenriver.dev;
import javax.swing.plaf.basic.BasicLookAndFeel;
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    // constants
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    // helper class
    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int count;      // default is 0
        private boolean color;
        // true/ red if link from parent is red
        // false/ black if link from parent is black
        public Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
        }
    } // end of Node class
    // field
    private Node root;
    // constructor
    public RedBlackBST() {
        root = null;
    }
    // helper methods
    public Node put(Key key, Value val) {
        return root = put(root, key, val);
    }
    //helper method for recursion
    private Node put(Node current, Key key, Value val) {
        if(current == null) {
            return new Node(key, val, 1, RED);
        }
        int result = key.compareTo(current.key);
        if(result < 0) {
            current.left = put(current.left, key, val);
        }
        else if(result > 0) {
            current.right = put(current.right, key, val);
        }
        else { //result == 0
            current.val = val;
        }
        // if we get here insertion is complete and we are
        // working our way back up the recursion
        if(isRed(current.right) && !isRed(current.left)) {
            current = rotateLeft(current);
        }
        if(isRed(current.left) && isRed(current.left.left)) {
            current = rotateRight(current);
        }
        if(isRed(current.left) && isRed(current.right)) {
            flipColors(current);
        }
        current.count = 1 + size(current.left) + size(current.right);
        return current;
    }
    private boolean isRed(Node x) {
        if(x == null) {
            return false;
        }
        return x.color == RED;
    }
    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.count = h.count;
        h.count = 1 + size(h.left) + size(h.right);
        return x;
    }
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.count = h.count;
        h.count = 1 + size(h.left) + size(x.right);
        return x;
    }
    // methods
    public Value get(Key key) {
        Node current = root;
        while(current != null) {
            int result = key.compareTo(current.key);
            if(result < 0) {
                current = current.left;
            }
            else if(result > 0) {
                current = current.right;
            }
            else {
                return current.val;
            }
        }//end of while loop
        return null; // if we get here then the key does not exist in tree
    }
    private int size(Node current) {
        if(current == null) {
            return 0;
        }
        return current.count;
    }
    //returns a queue with all the keys in order
    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        inorder(root, q); //call helper, start at root
        return q;
    }
    //helper method for recursion
    private void inorder(Node current, Queue<Key> q) {
        if(current == null) {
            return;
        }
        //recursive case: go left, then myself, then the right
        inorder(current.left,q);
        q.enqueue(current.key);
        inorder(current.right,q);
    }
    public boolean contains(Key key) {
        return get(key) != null;
    }
}