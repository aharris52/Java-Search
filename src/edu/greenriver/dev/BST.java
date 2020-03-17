/**
 *  The {@code BST} class represents an ordered symbol table of generic
 *  key-value pairs.
 *  It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 *  <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 *  This symbol table implements the <em>associative array</em> abstraction:
 *  when associating a value with a key that is already in the symbol table,
 *  the convention is to replace the old value/overwrite with the new value.
 *  Unlike {@link java.util.Map}, this class uses the convention that
 *  values cannot be {@code null}—setting the
 *  value associated with a key to {@code null} is equivalent to deleting the key
 *  from the symbol table.
 *  <p>
 *  It requires that
 *  the key type implements the {@code Comparable} interface and calls the
 *  {@code compareTo()} and method to compare two keys. It does not call either
 *  {@code equals()} or {@code hashCode()}.
 *  <p>
 *  This implementation uses an (unbalanced) <em>binary search tree</em>.
 *  The <em>put</em> and <em>contains</em> operations each take &Theta;
 *  (<em>n</em>) time in the worst case, where <em>n</em> is the number
 *  of key-value pairs. The <em>size</em> and <em>is-empty</em> operations
 *  take &Theta;(1) time. The keys method takes &Theta;(<em>n</em>) time
 *  in the worst case. Construction takes &Theta;(1) time.
 *  <p>
 *  For alternative implementations of the symbol table API, see {@link ST},
 *  {@link BinarySearchST}, {@link SequentialSearchST}, {@link RedBlackBST},
 *  and {@link SeparateChainingHashST}
 *  For additional documentation, see
 *  <a href="https://algs4.cs.princeton.edu/32bst">Section 3.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Andrew Harris
 */

package edu.greenriver.dev;
public class BST<Key extends Comparable<Key>, Value> {

    //helper class
    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int count; // number of nodes in subtree

        //constructor
        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }//end node class

    //fields for BST
    private Node root; //saves the root of the tree

    /**
     * Initializes an empty symbol table.
     */
    public BST() {
        root = null; //initialize root variable to null for starting
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
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
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }
    //helper method for recursion
    private Node put(Node current, Key key, Value val) {
        if(current == null) {
            return new Node(key, val);
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
        current.count = 1 + size(current.left) + size(current.right);
        return current;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    //call this to get the count at the root
    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node current) {
        if(current == null) {
            return 0;
        }
        return current.count;
    }

    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEpmpty(){
        return size() == 0;
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

    //determines whether the value exists in the symbol table
    public boolean contains(Key key) {
        return get(key) != null;
    }
}
