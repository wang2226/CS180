/**
 * Created by Bruce on 9/7/16.
 */
import java.lang.*;
import java.util.Scanner;

public class EuroConverter {
    public static void main(String [] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter amount in USD");
        double usd = s.nextDouble();
        System.out.println("Enter number of $ in 100 Eur");
        double rate = s.nextDouble();
        double eur = usd * (100/rate);
        System.out.println("Number of euros = " + eur);
    }
}
