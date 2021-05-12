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
        Iterator<String> iter = myInts.iterator();
        assertEquals("1", iter.next());
        assertEquals("2", iter.next());
        assertEquals("1", iter.next());

        myInts.add("3");
        myInts.add("5");
        myInts.add("6");
        myInts.add("7");
        myInts.remove("7");
        assertEquals("1", iter.next());

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
    void iteratorRemoveFirst() {
        CircularLinkedList<Integer> myInts = new CircularLinkedList<>();
        myInts.add(5);
        myInts.add(4);
        myInts.add(3);
        Iterator<Integer> iter = myInts.iterator();
        //first position
        iter.next();
        iter.remove();
        assertEquals(4,iter.next());
        assertEquals(3,iter.next());
        assertEquals(4,iter.next());
    }


    @Test
    void iteratorRemoveSecond() {
        CircularLinkedList<Integer> myInts = new CircularLinkedList<>();
        myInts.add(5);
        myInts.add(4);
        myInts.add(3);
        Iterator<Integer> iter = myInts.iterator();
        //second position
        int x = iter.next();
        int y = iter.next();
        iter.remove();
        assertEquals(3, iter.next());
        assertEquals(5, iter.next());
        assertEquals(3, iter.next());
    }


    @Test
    void iteratorRemoveThird() {
        CircularLinkedList<Integer> myInts = new CircularLinkedList<>();
        myInts.add(6);
        myInts.add(5);
        myInts.add(4);
        myInts.add(3);
        myInts.add(2);
        myInts.add(1);

        Iterator<Integer> iter = myInts.iterator();
        //third position
        iter.next();
        iter.next();
        iter.next();
        iter.remove();
        assertEquals(3, iter.next());
        assertEquals(2, iter.next());
        assertEquals(1, iter.next());
        assertEquals(6, iter.next());
        assertEquals(5, iter.next());
        assertEquals(3, iter.next());
<<<<<<< HEAD
<<<<<<< HEAD
=======


>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
=======


>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
    }


    @Test
    void iteratorRemoveFourth() {
        CircularLinkedList<Integer> myInts = new CircularLinkedList<>();
        myInts.add(6);
        myInts.add(5);
        myInts.add(4);
        myInts.add(3);
        myInts.add(2);
        myInts.add(1);
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
=======

>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
        Iterator<Integer> iter = myInts.iterator();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
        iter.remove();
        assertEquals(2, iter.next());
        assertEquals(1, iter.next());
        assertEquals(6, iter.next());
        assertEquals(5, iter.next());
        assertEquals(4, iter.next());
        assertEquals(2, iter.next());
    }
<<<<<<< HEAD


=======


>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
    @Test
    void iteratorRemoveLastElementSize3() {
        CircularLinkedList<Integer> myInts = new CircularLinkedList<>();
        myInts.add(6);
        myInts.add(2);
        myInts.add(1);
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
=======

>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
        Iterator<Integer> iter = myInts.iterator();
        iter.next();
        iter.next();
        iter.next();
        iter.remove();
        assertEquals(6, iter.next());
        assertEquals(2, iter.next());
        assertEquals(6, iter.next());
    }


    @Test
    void iteratorRemoveLastElementSize4() {
        CircularLinkedList<Integer> myInts = new CircularLinkedList<>();
<<<<<<< HEAD
<<<<<<< HEAD
        myInts.add(1);
        myInts.add(2);
        myInts.add(3);
        myInts.add(4);
=======
=======
>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
        myInts.add(6);
        myInts.add(5);
        myInts.add(2);
        myInts.add(1);

<<<<<<< HEAD
>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
=======
>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
        Iterator<Integer> iter = myInts.iterator();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
        iter.remove();
<<<<<<< HEAD
<<<<<<< HEAD
        assertEquals(1, iter.next());
        assertEquals(2, iter.next());
        assertEquals(3, iter.next());
        assertEquals(1, iter.next());
=======
=======
>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
        assertEquals(6, iter.next());
        assertEquals(5, iter.next());
        assertEquals(2, iter.next());
        assertEquals(6, iter.next());
<<<<<<< HEAD
>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
=======
>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
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


    @Test
    void iteratorRemoveWithoutNext() {
        CircularLinkedList<Integer> myInts = new CircularLinkedList<>();
        Iterator<Integer> iter = myInts.iterator();
<<<<<<< HEAD
<<<<<<< HEAD
        assertThrows(IllegalStateException.class, () -> {
                iter.remove();
        });
=======
=======
>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616

        assertThrows(IllegalStateException.class, () -> {
                iter.remove();
        });

<<<<<<< HEAD
>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
=======
>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
    }
}