import java.util.Iterator;

public class CircularLinkedList<E> implements Iterable<E>  {
    private Node<E> front;
    private Node<E> end;
    private int size;

    public CircularLinkedList() {
        front = null;
        end = null;
        size = 0;
    }


    public void add(E data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        Node<E> newNode = new Node<>(data);
        //logic to ensure end is always updated
        if (end == null) {
            end = newNode;
        }
        newNode.next = front;
        front = newNode;
        size++;

    }


    public void addAtEnd(E data) {
        //todo: preconditions data cannot be null
        Node<E> newNode = new Node<>(data);
        if (front == null) {
           front = newNode;
        } else {
            Node<E> current = front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }


    public void remove(E value) {

    }


    public void remove(int pos) {

    }


    public void clear() {
        front = null;
    }


    public int size() {
        return size;
    }


    public Iterator<E> iterator() {
        return new CircularLinkedListIterator();
    }


    private static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data) {
            this.data = data;
            next = null;

        }
    }


    public class CircularLinkedListIterator implements Iterator<E> {
        Node<E> curr;
        Node<E> prev;
        boolean removeOk;

        public CircularLinkedListIterator() {
            removeOk = false;
            curr = front;
        }

        public boolean hasNext() {
            if(curr == null) {
                curr = front;
            }

            return curr != null || size > 1;

        }


        public E next() {
            E result = null;
            result = curr.data;
            if (hasNext()) {
                prev = curr;
                curr = curr.next;
                removeOk = true;
            }
            return result;
        }

        public void remove() {
            //Removes from the underlying collection the last element returned by this iterator
            if (removeOk) {
                prev.next = curr.next;
                size--;
            }
        }

    }

}
