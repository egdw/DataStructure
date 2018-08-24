import com.hongdeyan.set.EgdwBinarySearchSet;
import com.hongdeyan.tree.EgdwTrie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/hdy/Desktop/book.txt");
        FileInputStream stream = new FileInputStream(file);
        StringBuilder sb = new StringBuilder();
        byte[] bytes = new byte[stream.available()];
        int len = -1;
        while ((len = stream.read(bytes)) != -1) {
            sb.append(new String(bytes), 0, len);
        }
        stream.close();
        testTrie(sb.toString());
        testSet(sb.toString());
    }


    public static void testTrie(String str) {
        EgdwTrie<String> trie = new EgdwTrie<>();
        String[] split = str.split("\\s*");
        for (int i = 0; i < split.length; i++) {
            if (split[i].isEmpty()) {
                continue;
            }
            trie.addWord(split[i]);
        }
        long timeMillis = System.currentTimeMillis();

        for (int i = 0; i < split.length; i++) {
            if (split[i].isEmpty()) {
                continue;
            }
            trie.contains(split[i]);
        }
        long timeMillis2 = System.currentTimeMillis();
        System.out.println("trie花费的时间:");
        System.out.println((double) (timeMillis2 - timeMillis) / 1000);
    }

    public static void testSet(String str) {
        HashSet<String> set = new HashSet<>();
        String[] split = str.split("\\s*");
        for (int i = 0; i < split.length; i++) {
            if (split[i].isEmpty()) {
                continue;
            }
            set.add(split[i]);
        }
        long timeMillis = System.currentTimeMillis();

        for (int i = 0; i < split.length; i++) {
            if (split[i].isEmpty()) {
                continue;
            }
            set.contains(split[i]);
        }
        long timeMillis2 = System.currentTimeMillis();
        System.out.println("set花费的时间:");
        System.out.println((double) (timeMillis2 - timeMillis) / 1000);
    }
}
