import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListTest {

    @Test
    void add() {
        CircularLinkedList<Integer> myInts = new CircularLinkedList<>();
        myInts.add(5);
        myInts.add(4);
        myInts.add(3);
        myInts.add(2);
        myInts.add(1);
        assertEquals(5,myInts.size());
    }

    @Test
    void addAtEnd() {
    }

    @Test
    void remove() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void clear() {
    }

    @Test
    void size() {
    }

    @Test
    void iterator() {
        CircularLinkedList<Integer> myInts = new CircularLinkedList<>();
        myInts.add(5);
        myInts.add(4);
        myInts.add(3);
        myInts.add(2);
        myInts.add(1);
        Iterator<Integer> iter = myInts.iterator();

        for (int i = 0; i < 10; i++) {
            System.out.println(iter.next());
            iter.remove();
        }
    }
}