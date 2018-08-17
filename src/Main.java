import com.hongdeyan.list.EgdwDoubleLinkedList;
import com.hongdeyan.list.EgdwSingleLinkedList;
import com.hongdeyan.queue.EgdwArrayQueue;
import com.hongdeyan.queue.EgdwCirculationQueue;
import com.hongdeyan.queue.EgdwQueue;
import com.hongdeyan.stack.EgdwLinkedStack;

import java.util.LinkedList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        EgdwDoubleLinkedList<Integer> list = new EgdwDoubleLinkedList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list);
    }

    public static void testQueue(EgdwQueue queue) {

    }
}
