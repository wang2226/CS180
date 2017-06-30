/**
 * Created by Bruce on 11/9/16.
 */
public class SelectionSort {

    public static void sort(int[] example) {
        int temp = 0;
        for (int i = 0; i < example.length - 1; i++) {
            int smallest = i;
            for (int j = i + 1; j < example.length; j++) {
                if (example[j] < example[smallest]) {
                    smallest = j;
                }
            }
            temp = example[i];
            example[i] = example[smallest];
            example[smallest] = temp;
        }
    }

    public static void main(String[] args) {
        int[] example = {56, 12, 80, 91, 20};
        sort(example);
        for (int i = 0; i < example.length; i++) {
            System.out.print(example[i] + " ");
        }
    }
}