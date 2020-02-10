package edu.greenriver.dev;

public class BST <Key extends Comparable<Key>, Value> {
    //Helper Class

    //fields
    private class Node{
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int count;

        public Node(Key key, Value value){
            this.key = key;
            this.value = value;
        }
    }// end node class

    //fields
    private Node root;      //references root of tree

    public BST(){
        root = null;
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

    public boolean contains(Key key){
        return get(key) != null;
    }


    public void put(Key key, Value value){
        root = put(root, key, value);       //Starts the recursion
    }


    //helper recursive method
    private Node put(Node current, Key key, Value value){
        if (current == null){
            return new Node(key, value);
        }
        int result = key.compareTo(current.key);
        if(result < 0){
            current.left = put(current.right, key, value);
        }
        else if(result > 0){
            current.right = put(current.right, key, value);
        }
        else{
            current.value = value;
        }
        current.count = 1 + size(current.left) + size(current.right);
        return current;
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

    //returns a queue witha lla of the keys in order
    public Iterable<Key> keys(){
        Queue<Key> q = new Queue<>();
        inorder(root, q);   //call helper, start at root
        return q;
    }

    //helper method for recursion-inorder traversal
    private void inorder(Node current, Queue<Key> q){
        //base case
        if(current == null){
            return;
        }
        //recursive case; Left, Me, Right
        inorder(current.left, q);
        q.enqueue(current.key);
        inorder(current.right, q);
    }
}
