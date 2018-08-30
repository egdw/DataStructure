import com.hongdeyan.avl.EgdwAvlTree;
import com.hongdeyan.tree.EgdwRedBlackTree;

import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws Exception {
        EgdwRedBlackTree<Integer, String> tree = new EgdwRedBlackTree<>();
        tree.put(100, "100");
        tree.put(50, "50");
        tree.put(40, "40");
        tree.put(30, "30");
        tree.put(20, "20");
        tree.put(70, "70");
        tree.put(120, "120");
        tree.put(130, "130");
        tree.remove(100);
        System.out.println("213123");


    }

}
