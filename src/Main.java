import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        CircularLinkedList<Integer> myInts = new CircularLinkedList<>();
        myInts.add(1);
        myInts.add(5);
        myInts.add(4);
        myInts.add(3);
        myInts.add(2);

        Iterator<Integer> iter = myInts.iterator();

        for(Integer i : myInts) {
            System.out.println(iter.next());
        }

    }
}
