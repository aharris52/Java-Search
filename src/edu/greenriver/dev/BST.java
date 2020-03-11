package edu.greenriver.dev;
public class BST<Key extends Comparable<Key>, Value> {
    //helper class
    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int count; //default is 0 if not initialized
        //constructor
        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }//end node class
    //fields for BST
    private Node root; //saves the root of the tree
    //constructor method
    public BST() {
        root = null; //initialize root variable to null for starting
    }
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
    //call this to get the count at the root
    public int size() {
        return size(root);
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
