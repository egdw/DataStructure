import com.hongdeyan.list.EgdwArrayList;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        EgdwArrayList<String> list = new EgdwArrayList<>(3);
        list.add("12312313");
        list.add("21312313");
        list.add("21312313");

        list.add("21312313");

        list.add("21312313");

        list.add("21312313");

        list.add("21312313");

        System.out.println(list);

        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            if(next.equals("12312313") || next.equals("21312313")){
                iterator.remove();
            }
        }
        System.out.println(list);
//        ArrayList<String> arrayList = new ArrayList<>();
//        Iterator<String> iterator = arrayList.iterator();
//        while (iterator.hasNext()){
//
//        }
    }
}
