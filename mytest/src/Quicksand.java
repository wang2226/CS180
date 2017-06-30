/**
 * Created by Bruce on 21/11/2016.
 */
public class Quicksand extends Danger {
    public static void main(String args[]) {
        Quicksand quicksand = new Quicksand();
        if (quicksand instanceof Danger) {
            System.out.printf(" Run for your lives !");

            if (quicksand.getClass().equals(Danger.class))
                System.out.printf(" Run even faster !");

            if (quicksand instanceof Quicksand) {
                System.out.printf(" The more you struggle ," +
                        " the faster you'll sink !");
                if (quicksand.getClass() == Quicksand.class)
                    System.out.printf(" You'll need to find a vine to escape !");
            }
        }
    }
}
