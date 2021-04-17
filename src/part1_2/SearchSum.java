package part1_2;

import java.util.Arrays;

public class SearchSum {
    static int[] search(int[] array, int num) {
        int[] result = new int[2];

        outer:
        for (int i = 0; i < array.length; i++) {
            if(array[i] < num) {
                for (int j = i+1; j < array.length; j++) {
                   if (array[i] + array[j] == num) {
                       result[0] = i;
                       result[1] = j;
                       break outer;
                   }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {3, 43, 8, 15, 17};
        System.out.println(Arrays.toString(search(array, 23)));
    }
}
