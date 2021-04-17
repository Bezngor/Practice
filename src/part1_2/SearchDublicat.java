package part1_2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SearchDublicat {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 9, 5, 6, 1, 6, 8);
        System.out.println(searchDub(list));
    }

    static boolean searchDub(List<Integer> list) {
        Collections.sort(list);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals(list.get(i + 1))) {
                return true;
            }
        }
        return false;
    }
}
