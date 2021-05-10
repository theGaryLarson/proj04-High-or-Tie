import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CircularLinkedListTest {

    ////////////////////////////////////////////////////////////////////
    //                    HAPPY PATH TESTS
    ////////////////////////////////////////////////////////////////////

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
    void get() {
        CircularLinkedList<String> myInts = new CircularLinkedList<>();
        myInts.add("a");
        myInts.add("b");
        myInts.add("c");
        assertEquals("a",myInts.get(1));
        assertEquals("b",myInts.get(2));
        assertEquals("c",myInts.get(3));
    }


    @Test
    void removeByPosFirst() {
        CircularLinkedList<String> myInts = new CircularLinkedList<>();
        myInts.add("a");
        myInts.add("b");
        myInts.add("c");
        myInts.remove(1);
        assertEquals("b", myInts.get(1));
        myInts.remove(1);
        assertEquals("c", myInts.get(1));
        myInts.remove(1);
        assertEquals(0, myInts.size());
    }


    @Test
    void removeByPosLast() {
        CircularLinkedList<String> myInts = new CircularLinkedList<>();
        myInts.add("a");
        myInts.add("b");
        myInts.add("c");
        myInts.remove(myInts.size());
        assertEquals("b", myInts.get(myInts.size()));
        myInts.remove(myInts.size());
        assertEquals("a", myInts.get(myInts.size()));
        myInts.remove(myInts.size());
        assertEquals(0, myInts.size());


    }
    @Test
    void removeByPosMiddle() {
        CircularLinkedList<String> myInts = new CircularLinkedList<>();
        myInts.add("a");
        myInts.add("b");
        myInts.add("c");
        myInts.remove(2);
        assertEquals("c", myInts.get(myInts.size()));
        myInts.add("d");
        myInts.remove(2);
        assertEquals("d", myInts.get(myInts.size()));




    }

    @Test
    void removeFromNullByValue() {
        CircularLinkedList<String> myInts = new CircularLinkedList<>();
        assertEquals(false,myInts.remove("1"));
    }


    @Test
    void removeByValueSizeOne() {
        CircularLinkedList<String> myInts = new CircularLinkedList<>();
        myInts.add("1");
        myInts.remove("1");
        assertTrue(myInts.size() == 0);
    }


    @Test
    void removeByValueNotPresent() {
        CircularLinkedList<String> myInts = new CircularLinkedList<>();
        myInts.add("1");
        myInts.add("2");
        myInts.add("3");
        assertEquals(false,myInts.remove("4"));
    }

    @Test
    void removeFirstValue() {
        CircularLinkedList<String> myInts = new CircularLinkedList<>();
        myInts.add("1");
        myInts.add("2");
        myInts.add("3");
        myInts.remove("1");
        assertEquals("2", myInts.get(1));
        myInts.remove("2");
        assertEquals("3", myInts.get(1));
    }


    @Test
    void removeMiddleValue() {
        CircularLinkedList<String> myInts = new CircularLinkedList<>();
        myInts.add("1");
        myInts.add("2");
        myInts.add("3");
        myInts.remove("2");
        assertEquals("3", myInts.get(2));
    }


    @Test
    void removeLastValue() {
        CircularLinkedList<String> myInts = new CircularLinkedList<>();
        myInts.add("1");
        myInts.add("2");
        myInts.add("3");
        myInts.remove("3");
        assertEquals(2, myInts.size());
    }


    @Test
    void clearAndSize() {
        CircularLinkedList<String> myInts = new CircularLinkedList<>();
        myInts.add("1");
        myInts.add("2");
        myInts.add("3");
        myInts.clear();
        assertTrue(myInts.size() == 0);

    }


    @Test
    void iteratorNext() {
        CircularLinkedList<Integer> myInts = new CircularLinkedList<>();
        myInts.add(5);
        myInts.add(4);
        myInts.add(3);
        Iterator<Integer> iter = myInts.iterator();

        assertTrue(iter.hasNext());
        assertEquals(5,iter.next());
        assertEquals(4,iter.next());
        assertEquals(3,iter.next());
        assertEquals(5,iter.next());
        assertEquals(4,iter.next());
        assertEquals(3,iter.next());
    }


    @Test
    void iteratorRemove() {
        CircularLinkedList<Integer> myInts = new CircularLinkedList<>();
        myInts.add(5);
        myInts.add(4);
        myInts.add(3);
        Iterator<Integer> iter = myInts.iterator();

       iter.next();
       iter.remove();
        iter.next();
        iter.remove();
        iter.next();
        iter.remove();
        iter.next();
        iter.remove();
        System.out.println();

    }


    ////////////////////////////////////////////////////////////////////
    //                    EXCEPTION TESTS
    ////////////////////////////////////////////////////////////////////


    @Test
    void removeFromNullByPos() {
        CircularLinkedList<String> myInts = new CircularLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class,() -> {
            myInts.remove(1);
        });
    }

    @Test
    void inputNullData() {
        CircularLinkedList<String> myInts = new CircularLinkedList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            myInts.add(null);
        });

    }
}