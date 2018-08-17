import com.hongdeyan.list.EgdwSingleLinkedList;
import com.hongdeyan.queue.EgdwArrayQueue;
import com.hongdeyan.queue.EgdwCirculationQueue;
import com.hongdeyan.queue.EgdwQueue;

import java.util.LinkedList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        EgdwSingleLinkedList<Integer> list = new EgdwSingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.println(list.getLast());
        System.out.println(list.delete(2));
        System.out.println(list);
    }

    public static void testQueue(EgdwQueue queue) {

    }
}
