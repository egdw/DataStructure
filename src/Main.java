import com.hongdeyan.list.EgdwArrayList;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        EgdwArrayList arrayList = new EgdwArrayList();
        arrayList.add("hdy");
        arrayList.add("wyj",0);
        arrayList.add("flb",0);
        arrayList.add("flb2",2);
        arrayList.pop();
        arrayList.removeIndex(1);
        arrayList.remove(arrayList.get(0));
        arrayList.add("adhasdad");
        System.out.println(arrayList);
        System.out.println(arrayList.size());
    }
}
