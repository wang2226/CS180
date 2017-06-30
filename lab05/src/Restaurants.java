import java.util.Scanner;

/**
 * CS180 - Lab 07
 *
 * This class contains a list of some of the on-campus and off-campus restaurants and cafes.
 * You should complete it to fulfill the requirements of lab07
 *
 */
public class Restaurants {
    // On campus
    public static final String ON_CAMPUS_VEGAN = "Purdue Dining Courts\nFlatbreads";
    public static final String ON_CAMPUS_VEGETARIAN = ON_CAMPUS_VEGAN + "\nOasis Cafe\nAh*Z\nFreshens";
    public static final String ON_CAMPUS_GLUTEN_FREE = "Purdue Dining Courts\nFlatbreads\nOasis Cafe\nPappy's " +
            "Sweet Shop";
    public static final String ON_CAMPUS_BURGERS = "Pappy's Sweet Shop\nCary Knight Spot";
    public static final String ON_CAMPUS_SANDWICHES = "Flatbreads\nOasis Cafe\nErbert & Gerbert's";
    public static final String ON_CAMPUS_OTHERS = "Purdue Dining Courts\nAh*Z\nFreshens\nLemongrass";
    public static final String ON_CAMPUS_ALL = ON_CAMPUS_BURGERS + "\n" + ON_CAMPUS_SANDWICHES + "\n" +
            ON_CAMPUS_OTHERS;

    // Off campus
    public static final String OFF_CAMPUS_VEGAN = "Chipotle\nQdoba\nNine Irish Brothers\nFive Guys\n Puccini's " +
            "Smiling Teeth\nPanera Bread";
    public static final String OFF_CAMPUS_VEGETARIAN = OFF_CAMPUS_VEGAN + "\nWendy's\nBruno's Pizza\nJimmy " +
            "John's\nPotbelly Sandwich Shop\nBasil Thai\nIndia Mahal";
    public static final String OFF_CAMPUS_GLUTEN_FREE = "Chipotle\nQdoba\nNine Irish Brothers\nPuccini's Smiling " +
            "Teeth\nWendy's\nScotty's Brewhouse\nPanera Bread\nBasil Thai";
    public static final String OFF_CAMPUS_BURGERS = "Five Guys\nWendy's\nTriple XXX\nScotty's Brewhouse";
    public static final String OFF_CAMPUS_SANDWICHES ="Panera Bread\nJimmy John's\nPotbelly Sandwich Shop";
    public static final String OFF_CAMPUS_PIZZAS = "Puccini's Smiling Teeth\nMad Mushroom Pizza\nBruno's Pizza\n";
    public static final String OFF_CAMPUS_OTHERS = "Chipotle\nQdoba\nNine Irish Brothers\nFamous Frank's\n Von's " +
            "Dough Shack\nBuffalo Wild Wings\nBasil Thai\nMaru Sushi\nIndia Mahal\nHappy China\nYori";
    public static final String offCampusAll = OFF_CAMPUS_BURGERS + "\n" + OFF_CAMPUS_SANDWICHES + "\n" +
            OFF_CAMPUS_PIZZAS + OFF_CAMPUS_OTHERS;

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int location = 0;
        String dietaryPreference = "";
        int dietary = 0;
        String foodPreference = "";
        int food = 0;
        System.out.println("Enter 1 for on-campus and 2 for off-campus: ");
        location = s.nextInt();
        if (location == 1) {
            System.out.println("Do you have dietary restriction? Y/N");
            dietaryPreference = s.next();
            dietaryPreference = dietaryPreference.toUpperCase();
            if (dietaryPreference.equals("Y")) {
                System.out.println("Enter your dietary: 1.Vegan 2.Vegetarian 3.Gluten Free");
                dietary = s.nextInt();
                if (dietary == 1) {
                    System.out.println(ON_CAMPUS_VEGAN);
                } else if (dietary == 2) {
                    System.out.println(ON_CAMPUS_VEGETARIAN);
                } else if (dietary == 3) {
                    System.out.println(ON_CAMPUS_GLUTEN_FREE);
                }
            } else if (dietaryPreference.equals("N")) {
                System.out.println("Do you have food preference? Y/N");
                foodPreference = s.next();
                foodPreference = foodPreference.toUpperCase();
                if (foodPreference.equals("Y")) {
                    System.out.println("Enter your food preference: 1.Burger 2.Sandwiches 3.Others ");
                    food = s.nextInt();
                    if (food == 1) {
                        System.out.println(ON_CAMPUS_BURGERS);
                    } else if (food == 2) {
                        System.out.println(ON_CAMPUS_SANDWICHES);
                    } else if (food == 3) {
                        System.out.println(ON_CAMPUS_OTHERS);
                    }
                } else if (foodPreference.equals("N")) {
                    System.out.println(ON_CAMPUS_ALL);
                }
            }
        } else if (location == 2) {
            System.out.println("Do you have dietary restriction? Y/N");
            dietaryPreference = s.next();
            dietaryPreference = dietaryPreference.toUpperCase();
            if (dietaryPreference.equals("Y")) {
                System.out.println("Enter your dietary: 1.Vegan 2.Vegetarian 3.Gluten Free");
                dietary = s.nextInt();
                if (dietary == 1) {
                    System.out.println(OFF_CAMPUS_VEGAN);
                } else if (dietary == 2) {
                    System.out.println(OFF_CAMPUS_VEGETARIAN);
                } else if (dietary == 3) {
                    System.out.println(OFF_CAMPUS_GLUTEN_FREE);
                }
            } else if (dietaryPreference.equals("N")) {
                System.out.println("Do you have food preference? Y/N");
                foodPreference = s.next();
                foodPreference = foodPreference.toUpperCase();
                if (foodPreference.equals("Y")) {
                    System.out.println("Enter your food preference: 1.Burgers 2.Sandwiches 3.Pizzas 4.Others");
                    food = s.nextInt();
                    if (food == 1) {
                        System.out.println(OFF_CAMPUS_BURGERS);
                    } else if (food == 2) {
                        System.out.println(OFF_CAMPUS_SANDWICHES);
                    } else if (food == 3) {
                        System.out.println(OFF_CAMPUS_PIZZAS);
                    } else if (food == 4) {
                        System.out.println(OFF_CAMPUS_OTHERS);
                    }
                } else if (foodPreference.equals("N")) {
                    System.out.println(ON_CAMPUS_ALL);
                }
            }
        }
    }
}
