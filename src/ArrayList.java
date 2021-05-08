import java.util.*;

/**
 * represents a general list of data
 * @author "Building Java Programs 5th Edition"; Gary Larson some edits
 * @version 5/1/2021
 * @param <E> type reference; no primitives allowed
 */
public class ArrayList<E> implements Iterable<E> {
    /** list of values */
    private E[] elementData;
    /** current number of elements in the list */
    private int size;
    /** default length if not supplied */
    public static final int DEFAULT_CAPACITY = 100;

    /**
     * constructs an empty list
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }


    /**
     *  constructs a list with given capacity
     * @param capacity desired capacity
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        elementData = (E[]) new Object[capacity];
        size = 0;
    }


    /**
     * the size of the list
     * @return number of items in list
     */
    public int size() {
        return size;
    }


    /**
     * retrieve item at given index
     * @param index given index
     * @return item
     */
    public E get(int index) {
        checkIndex(index);
        return elementData[index];
    }


    /**
     * string representation of the list
     * @return list data
     */
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            StringBuilder result = new StringBuilder("[" + elementData[0]);
            for (int i = 1; i < size; i++) {
                result.append(", ").append(elementData[i]);
            }
            result.append("]");
            return result.toString();
        }
    }


    /**
     * returns the index of given value
     * @param value given value
     * @return idx; if not found returns -1
     */
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }


    /**
     *  determine if list is empty
     * @return true if empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * check if list contains a value
     * @param value sought value
     * @return true if found
     */
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }


    /**
     * add an item to end of list
     * @param value given value
     */
    public void add(E value) {
        ensureCapacity(size + 1);
        elementData[size] = value;
        size++;
    }


    /**
     * inserts the given the value at the given index
     * @param index given index
     * @param value given value
     */
    public void add(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        ensureCapacity(size + 1);
        if (size - index >= 0) {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
        }
        elementData[index] = value;
        size++;
    }

    /**
     * remove an item from the list
     * @param index given index
     */
    public void remove(int index) {
        checkIndex(index);
        if (size - index >= 0) {
            System.arraycopy(elementData, index + 1, elementData, index, size - 1);
        }
        elementData[size - 1] = null;
        size--;
    }


    /**
     * insert given value at given index
     * @param index given index
     * @param value given value
     */
    public void set(int index, E value) {
        checkIndex(index);
        elementData[index] = value;
    }


    /**
     * sets all the values in list to null
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }


    /**
     * appends a list of values from other list
     * @param other given list
     */
    public void addAll(ArrayList<E> other) {
        ensureCapacity(size + other.size);
        for (int i = 0; i < other.size; i++) {
            add(other.elementData[i]);
        }
    }


    /**
     * to implement looping
     * @return iterator for given type
     */
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }


    /**
     * ensures there is always enough room for next value
     * @param capacity capacity needed
     */
    public void ensureCapacity(int capacity) {
        if (capacity > elementData.length) {
            int newCapacity = elementData.length * 2 + 1;
            if (capacity > newCapacity) {
                newCapacity = capacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    /**
     * an array of given type
     * @param template empty array of given type
     * @return items from list
     */
    @SuppressWarnings("unchecked")
    public  E[] toArray(E[] template) {
        if (template.length < size) {
            return (E[]) Arrays.copyOf(elementData, size, template.getClass());
        }
        System.arraycopy(elementData, 0, template, 0, size);
        if (template.length > size) {
            template[size] = null;
        }
        return template;

    }


    /**
     * whether index is in bounds of list
     * @param index given index
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }


    /**
     *  represents an iterator for the ArrayList
     */
    private class ArrayListIterator implements Iterator<E> {
        /** current position within the list */
        private int position;
        /** whether its ok to remove now */
        private boolean removeOK;

        /**
         * constructor for Iterator
         */
        public ArrayListIterator() {
            position = 0;
            removeOK = false;
        }

        /**
         * determines if more elements are left
         * @return true if more elements
         */
        public boolean hasNext() {
            return position < size();
        }


        /**
         *  throws exception if there isn't another element
         * @return the next element
         */
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = elementData[position];
            position++;
            removeOK = true;
            return result;
        }

        /**
         * remove an element from list; if next has not been called will throw IllegalStateException
         */
        public void remove() {
            if (!removeOK) {
                throw new IllegalStateException();
            }
            ArrayList.this.remove(position - 1);
            position--;
            removeOK = false;
        }
    }
}