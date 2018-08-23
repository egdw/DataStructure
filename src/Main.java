import com.hongdeyan.queue.EgdwPriorityQueue;

public class Main {

    public static void main(String[] args) {
        EgdwPriorityQueue<Integer> queue = new EgdwPriorityQueue<>();
        queue.add(1010);
        queue.add(1024);
        queue.add(768);
        queue.add(897);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

}
