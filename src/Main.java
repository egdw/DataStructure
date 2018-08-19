import com.hongdeyan.queue.EgdwLinkedQueue;
import com.hongdeyan.queue.EgdwQueue;
import com.hongdeyan.tree.EgdwBinarySearchTree;

import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        EgdwBinarySearchTree<Integer> tree = new EgdwBinarySearchTree<>();
        tree.add(10);
        tree.add(20);
        tree.add(5);
        tree.add(22);
        tree.add(18);
        tree.remove(18);
        tree.foreachBehind();
    }

    public static void testQueue(EgdwQueue queue) {

    }
}
