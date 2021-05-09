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
            //todo: exception test
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
        for (int i = 1; i < pos; i++) {
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
        Node<E> prev = null;

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
            if (curr.next == front) {
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
            //todo: exception test
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
        boolean removeOk;


        /**
         * constructs the iterator
         */
        public CircularLinkedListIterator() {
            removeOk = false;
            curr = front;
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
                prev = curr;
                if (curr == end) {
                    curr = front;
                }
                else {
                    curr = curr.next;
                }
                removeOk = true;
            }
            return result;
        }
    }

}
