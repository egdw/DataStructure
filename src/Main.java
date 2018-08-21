import com.hongdeyan.heap.EgdwMaxHeap;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        EgdwMaxHeap<Integer> heap = new EgdwMaxHeap<>();
        heap.add(99);
        heap.add(99);
        heap.add(99);
        heap.add(99);
        heap.add(99);
        heap.add(99);
        System.out.println(heap.size());
//        System.out.print("插入的数为:");
//        for (int i = 0; i < 30; i++) {
//            int rand = new Random().nextInt(100) + 1;
//            System.out.print(rand + " ");
//            heap.add(rand);
//        }
//
//        System.out.println();
//        for (int i = 0; i < 30; i++) {
//            System.out.println("执行");
//            System.out.println(heap.getFirst());
//            System.out.println("执行2");
//        }
    }

}
