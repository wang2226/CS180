/**
 * Created by Bruce on 9/7/16.
 */
import java.lang.Math;
import java.util.Scanner;
public class Pythagoras {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a");
        double a = s.nextInt();
        System.out.println("Enter b");
        double b = s.nextInt();
        double hypotenuse = Math.sqrt(a*a + b*b);
        System.out.println("Hypotenuse = " + hypotenuse);
    }
}
