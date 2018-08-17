import com.hongdeyan.queue.EgdwArrayQueue;
import com.hongdeyan.queue.EgdwCirculationQueue;
import com.hongdeyan.queue.EgdwQueue;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        testQueue(new EgdwArrayQueue());

        testQueue(new EgdwCirculationQueue());

    }

    public static void testQueue(EgdwQueue queue) {
        long startTime = System.currentTimeMillis();
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            queue.add(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < queue.size(); i++) {
            queue.poll();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总时长为:" + (double)(endTime - startTime) / 1000);
    }
}
