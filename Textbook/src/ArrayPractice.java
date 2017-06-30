import java.util.Random;

/**
 * Created by Bruce on 11/2/16.
 */
public class ArrayPractice {
    public static void main(String[] args) {
        int[] values =new int[10];
        Random random = new Random();
        int sum = 0;

        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt(10);
            sum = sum + values[i];
            System.out.print("\n" + "Value at index " + i + " is:" + values[i]);
        }
        System.out.print("\n-----------------------------");
        System.out.println("\nSum is: " + sum);

        int largest = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > largest) {
                largest = values[i];
            }
        }
        System.out.print("\n-----------------------------");
        System.out.println("\nLargest number is: " + largest);

        for (int i = 0; i < values.length - 1; i++) {
            int biggest = i;
            for (int j = i + 1; j < values.length; j++) {
                if (values[j] > values[biggest]) {
                    biggest = j;
                }
                int temp = values[biggest];
                values[biggest] = values[i];
                values[i] = temp;
            }
        }
        System.out.print("\n-----------------------------");
        System.out.print("\nThe sorted list is: ");
        for (int i = 0; i < values.length; i++) {
            System.out.print("\n" + values[i]);
        }
    }
}
