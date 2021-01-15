package edu.greenriver.dev;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * The {@code LinkedQueue} class represents a first-in-first-out (FIFO)
 * queue of generic items.
 * It supports the usual <em>enqueue</em> and <em>dequeue</em>
 * operations, along with methods for peeking at the first item,
 * testing if the queue is empty, and iterating through
 * the items in FIFO order.
 * <p>
 * This implementation uses a singly linked list with an inner class
 * for linked-list nodes.  The <em>enqueue</em>, <em>dequeue</em>,
 * <em>peek</em>, <em>size</em>, and <em>is-empty</em> operations all
 * take constant time in the worst case.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @param <E> Item (element) type
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Kendrick Hang
 * @author Andrew Harris
 * @version 2.0
 */
public class Queue<E> implements Iterable<E> {
    private int size;      // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    // helper linked list class
    private class Node {
        private E item;
        private Node next;

        @Override
        public String toString() {
            return "[" + item + "|" + hashCode() + "]";
        }
    }

    /**
     * Initializes an empty queue.
     */
    public Queue() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Is this queue empty?
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this queue.
     * @return the number of items in this queue
     */
    public int size() {
        return size;
    }

    /**
     * Returns the item least recently added to this queue.
     * @return the item least recently added to this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }

        return first.item;
    }

    /**
     * Adds the item to this queue.
     * @param item the item to add
     */
    public void enqueue(E item) {
        Node oldlast = last;

        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
        }

        size++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     * @return the item on this queue that was least recently added
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }

        E item = first.item;
        first = first.next;
        size--;

        if (isEmpty()) {
            last = null;   // to avoid loitering
        }

        return item;
    }

    /**
     * Returns a string representation of this queue.
     * @return the sequence of items in FIFO order, separated by commas
     */
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");

        for (E item : this) {
            stringJoiner.add(item.toString());
        }

        return stringJoiner.toString();
    }


    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    @Override
    public Iterator<E> iterator()  {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<E> {
        private Node current;

        public ListIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next items");
            }

            E item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public String toString() {
            return current.toString();
        }
    }
}

/*
 *  Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 */
