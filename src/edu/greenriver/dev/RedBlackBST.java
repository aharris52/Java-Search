package edu.greenriver.dev;

public class RedBlackBST <Key extends Comparable<Key>, Value> {
    //constants
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    //helper class
    private class Node{
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int count;  //default 0
        private boolean color;  //true/red if the link from the parent is red

        public Node(Key key, Value value, int n, boolean color){
            this.key = key;
            this.value = value;
            this.count = n;
            this.color = color;
        }
    }// end node class

    //field
    private Node root;

    //constructor
    public RedBlackBST() { root = null;}

    //helper methods
    private boolean isRed(Node x){
        if(x == null){
            return false;
        }
        return x.color == RED;
    }
    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.count = h.count;
        h.count = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.count = h.count;
        h.count = 1 + size(h.left) + size(h.right);
        return x;
    }

    public int size(){
        return size(root);
    }

    private int size(Node current){
        if(current == null){
            return 0;
        }
        return current.count;
    }

    public Value get(Key key){
        Node current = root;

        //iterate through the loop
        while (current != null){
            int comp = key.compareTo(current.key);
            if(comp < 0){
                current = current.left;
            } else if (comp > 0){
                current = current.right;
            } else {
                return current.value;
            }
        }//end while loop
        return null;    //if we get here, it doesn't exist in the tree
    }
}
