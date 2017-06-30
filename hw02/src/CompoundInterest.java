/**
 * Created by Bruce on 9/7/16.
 */
import java.lang.*;
import java.util.Scanner;

public class CompoundInterest {
    public static void getInterest(double principle,double rate,int years ) {
        for(int x = 1; x <= years; x++) {
            double amount = principle * Math.pow(1+rate/100,x);
            double interest = amount - principle;
            System.out.println("Interest = " + (int)interest);
        }
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter principle: ");
        double principle = s.nextDouble();
        System.out.println("Enter interest rate: ");
        double rate = s.nextDouble();
        System.out.println("Enter years: ");
        int years = s.nextInt();
        getInterest(principle,rate,years);
    }
}
