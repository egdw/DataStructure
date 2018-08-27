import com.hongdeyan.avl.EgdwAvlTree;

public class Main {

    public static void main(String[] args) throws Exception {
        EgdwAvlTree<Integer, String> tree = new EgdwAvlTree<>();
        tree.add(100,"100");
        tree.add(50,"50");
        tree.add(40,"40");
        tree.add(30,"30");
        tree.add(20,"20");
        tree.add(70,"70");
        tree.add(120,"120");
        tree.add(130,"130");
        tree.remove(100);
        System.out.println("213123");

    }

}
