import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RandomArrayDemo {
    public static void main(String[] args) {
        Set<Integer> treeSet = new TreeSet<Integer>();
        for (Integer i = 0; i < 100; i++) {
            treeSet.add(i);
        }
        treeSet.remove(0);
        treeSet.remove(99);
        Integer[] arr = new Integer[treeSet.size()];
        treeSet.toArray(arr);

        List list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            boolean found = false;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == i) {
                    found = true;
                    break;
                }
            }

            if (!found)
                list.add(i);
        }

        System.out.println(list);
    }
}
