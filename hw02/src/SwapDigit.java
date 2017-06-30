/**
 * Created by Bruce on 9/7/16.
 */
import java.util.Scanner;
import java.lang.Math;
public class SwapDigit {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter number");
        int number = s.nextInt();
        int swapped = Math.floorMod(number,10)*10 + number/10;
        System.out.println("Swapped:" + swapped);
    }

}
