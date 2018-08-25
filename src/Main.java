import com.hongdeyan.set.EgdwBinarySearchSet;
import com.hongdeyan.tree.EgdwTrie;
import com.hongdeyan.union.EgdwUnionFind;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws Exception {
        EgdwUnionFind find = new EgdwUnionFind(5);
        System.out.println(find);
        find.unionElement(3,2);
        System.out.println(find);
        System.out.println(find.isConnected(1,2));
        System.out.println(find);
        find.unionElement(1,2);
        System.out.println(find);
        System.out.println(find.isConnected(1,2));
        System.out.println(find.isConnected(1,3));
        System.out.println(find.isConnected(2,3));

    }

}
