import java.util.Iterator;

/**
 * represents a circular linked list
 * @author Gary Larson
 * @version 5/9/2021
 */
public class CircularLinkedList<E> implements Iterable<E>  {
    /** reference pointer to the beginning of the list */
    private Node<E> front;
    /** lazy pointer used in remove */
    private Node<E> prev;
    /** reference pointer to the end of the list */
    private Node<E> end;
    /** number of elements in the list */
    private int size;


    /**
     * constructs CircularLinkedList
     */
    public CircularLinkedList() {
        front = null;
        end = null;
        size = 0;
    }


    /**
     * add an element to the list
     * @param data input
     */
    public void add(E data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        Node<E> newNode = new Node<>(data);
        //logic to ensure end is always updated
        if (end == null) {
            front = newNode;
            end = newNode;
            front.next = end;
        }
        else {
            end.next = newNode;
            end = newNode;
        }
        end.next = front;
        size++;

    }


    /**
     * return an element from given position
     * @param pos desired position
     * @return element from given position
     */
    public E get(int pos) {
        checkBounds(pos);
        Node<E> curr = front;
        for (int counter = 1; counter < pos; counter++) {
            curr = curr.next;
        }
        return curr.data;
    }


    /**
     * remove an element at the given position
     * @param pos position of the elmement to remove
     */
    public void remove(int pos) {
        checkBounds(pos);
        Node<E> curr = front;
        for (int i = 1; i < pos; i++) {
            prev = curr;
            curr = curr.next;
        }
        if (pos == 1) {
            front = curr.next;
            end.next = front;
        }
        else if (pos == size) {
           curr.next = front;
           end = curr;
        }
        else {
            prev.next = curr.next;
        }
        size--;
    }


    /**
     * removes the first occurrence of the given value
     * @param value given value
     * @return true if successful
     */
    public boolean remove(E value) {
        boolean containsValue = true;
        Node<E> curr = front;
        int counter = 0;
        //empty list
        if (size == 0) {
            return false;
        }
        //only one item
        if (size == 1 && front.data == value ) {
            front = null;
            end = null;
            size--;
            return true;
        }
        //traverse for value
        while (curr.next.data != value && containsValue) {
            prev = curr;
            curr = curr.next;
            counter++;
            if (counter > size) {
                containsValue = false;
            }
        }
        if (containsValue) {
            //is it the front?
            if (curr == front) {
                front = curr.next.next;
                end.next = front;
            }
            //is it the end?
            else if (curr.next == end) {
                curr.next = front;
                end = curr;
            }
            //is it the middle?
            else {
                curr.next = curr.next.next;
            }
            size--;
        }
        return containsValue;
    }

    /**
     * clears the list of all elements
     */
    public void clear() {
        front = null;
        size = 0;
    }

    /**
     * size of the list
     * @return the size of the list
     */
    public int size() {
        return size;
    }


    /**
     * helper function to check bounds of position input
     * @param pos desired position
     */
    private void checkBounds(int pos) {
        if( pos < 1 || pos > size()) {
            String msg = String.format("The index must be between 1 and the size: %d, inclusive.", size());
            if (size < 1) {
                msg = "Can't access items in a size 0 list.";
            }
            throw new IndexOutOfBoundsException(msg);
        }
    }


    /**
     * circular iterator; moves from end to front continuously
     * @return iterator
     */
    public Iterator<E> iterator() {
        return new CircularLinkedListIterator();
    }

    /**
     * represents a node to store data
     * @param <T> given Type
     */
    private static class Node<T> {
        public T data;
        public Node<T> next;

        /**
         * constructs a Node
         * @param data value the node will contain
         */
        public Node(T data) {
            this.data = data;
            next = null;

        }
    }


    /**
     * represents an Iterator for CircularLinkedList
     */
    public class CircularLinkedListIterator implements Iterator<E> {
        Node<E> curr;
        Node<E> prev;
        Node<E> trail;
        boolean removeOk;
        int pos = 1;


        /**
         * constructs the iterator
         */
        public CircularLinkedListIterator() {
            removeOk = false;
            curr = front;
            prev = front;
            trail = null;
        }


        /**
         *  verifies there is another element
         * @return true if theres another element in the list
         */
        public boolean hasNext() {
           return size > 1;
        }

        /**
         * moves to the next element
         * @return data in current element
         */
        public E next() {
            E result = null;
            if (hasNext()) {
                result = curr.data;
                if (curr == end) {
                    curr = front;
                }
                else {
                    curr = curr.next;
                }
                removeOk = true;
                if ( pos >= 3) {
                    trail = prev;
                    prev = prev.next;
                }
                pos %= size + 1;
                pos++;
            }
            return result;
        }


        /**
<<<<<<< HEAD
<<<<<<< HEAD
         * removes from the underlying collection the last element returned by this iterator; throws IllegalStateException
         * if next() hasn't been called
=======
         * removes from the underlying collection the last element returned by this iterator
>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
=======
         * removes from the underlying collection the last element returned by this iterator
>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
         */
        public void remove() {
            if (!removeOk) {
                throw new IllegalStateException();
            }
<<<<<<< HEAD
<<<<<<< HEAD
            if (pos == 2) {
                front = front.next;
                end.next = front;
            }
            else if (pos == size + 1) {
                end = trail.next;
                end.next = front;
            }
            else {
                prev.next = prev.next.next;
            }
            size--;
=======
=======
>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
            int prevPos = pos - 1;
            if ( prevPos > 4) {
                prev = prev.next;
            }
            if (prevPos == 4) {
                front.next.next.next = front.next.next.next.next.next;
            }
            if (prevPos < 4) {
                switch(prevPos) {
                    case 3:
                        front.next.next = front.next.next.next;
                        break;
                    case 2:
                        front.next = front.next.next;
                        break;
                    case 1:
                        end.next = front.next;
                        front = end.next;
                }
            }
            else {
                prev.next = curr.next;
            }

            size--;

<<<<<<< HEAD
>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
=======
>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
        }
    }

}
