import com.hongdeyan.tree.EgdwSegmentTree;
import com.hongdeyan.tree.EgdwSegmentTreeMerge;

public class Main {

    public static void main(String[] args) {
        EgdwSegmentTree<Integer> tree = new EgdwSegmentTree<>(new Integer[]{-5,10, 2, 6, 5, 23, 8,54}, new EgdwSegmentTreeMerge<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a + b;
            }
        });

        tree.update(7,10);

        Integer query = tree.query(6, 7);
        System.out.println(query);
    }

}
