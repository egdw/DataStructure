import com.hongdeyan.queue.EgdwLinkedQueue;
import com.hongdeyan.queue.EgdwQueue;

public class Main {

    public static void main(String[] args) {
        EgdwLinkedQueue<Integer> queue = new EgdwLinkedQueue<>();
        queue.add(100);
        queue.add(99);
        queue.add(98);
        queue.add(97);
        queue.add(96);
        queue.add(95);
        System.out.println(queue.poll());
        System.out.println(queue.poll());System.out.println(queue.poll());System.out.println(queue.poll());

    }

    public static void testQueue(EgdwQueue queue) {

    }
}
