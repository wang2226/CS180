/**
 * Created by Bruce on 9/26/16.
 */
import java.util.Scanner;
public class Pokemon {
    private String name;
    private int ID;
    private String type;
    private int healthPower;
    private double baseAttackPower;
    public static int NUM_POKEMONS = 0;

    public Pokemon(String name, String type, int healthPower, double baseAttackPower) {
        this.name = name;
        String formatType = MyUtils.formatStr(type);
        if (formatType.equals("Fire") || formatType.equals("Water") || formatType.equals("Grass") || formatType.equals("Electric")) {
            this.type = formatType;
        }else{
            this.type = "Fire";
        }
        if (healthPower >= 0) {
            this.healthPower = healthPower;
        }else{
            this.healthPower =0;
        }
        if (baseAttackPower > 0) {
            this.baseAttackPower = baseAttackPower;
        }else{
            this.baseAttackPower = 1;
        }
        this.ID = NUM_POKEMONS;
        NUM_POKEMONS = NUM_POKEMONS + 1;

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public int getHealthPower() {
        return healthPower;
    }

    public double getBaseAttackPower() {
        return baseAttackPower;
    }

    public boolean setType(String type) {
        if (type.equals("Fire") || type.equals("Water") || type.equals("Grass") || type.equals("Electric")) {
            this.type = type;
            return true;
        }
        return false;
    }

    public boolean setHealthPower(int healthPower) {
        if (healthPower >= 0) {
            this.healthPower = healthPower;
            return true;
        }
        return false;
    }

    public boolean setBaseAttackPower(double baseAttackPower) {
        if (baseAttackPower > 0) {
            this.baseAttackPower = baseAttackPower;
            return true;
        }
        return false;
    }

    public String toString() {
        return ("Name: " + getName()
                + "\nID: " + getId()
                + "\nType: " + getType()
                + "\nHealth Power (HP): " + getHealthPower()
                + "\nBase Attack Power: " + String.format("%.1f", getBaseAttackPower()));
    }

    public boolean isDead() {
        return (healthPower == 0) ;
    }

    public void boostHealthPower(int healthPower) {
        this.healthPower = this.healthPower + healthPower;
    }

    public void reduceHealthPower(int healthPower) {
        this.healthPower = this.healthPower - healthPower;
        if (this.healthPower < 0) {
            this.healthPower = 0;
        }
    }

    public static double getAttackMultiplier(Pokemon attacker, Pokemon defender) {
        double factor = 0.0;
        if (attacker.type.equals("Grass") && defender.type.equals("Grass")) {
            factor = 0.5;
        } else if (attacker.type.equals("Grass") && defender.type.equals("Electric")) {
            factor = 1;
        } else if (attacker.type.equals("Grass") && defender.type.equals("Water")) {
            factor = 2;
        } else if (attacker.type.equals("Grass") && defender.type.equals("Fire")) {
            factor = 0.5;
        } else if (attacker.type.equals("Electric") && defender.type.equals("Electric")) {
            factor = 0.5;
        } else if (attacker.type.equals("Electric") && defender.type.equals("Water")) {
            factor = 2;
        } else if (attacker.type.equals("Electric") && defender.type.equals("Fire")) {
            factor = 1;
        } else if (attacker.type.equals("Electric") && defender.type.equals("Grass")) {
            factor = 0.5;
        } else if (attacker.type.equals("Water") && defender.type.equals("Water")) {
            factor = 0.5;
        } else if (attacker.type.equals("Water") && defender.type.equals("Electric")) {
            factor = 1;
        } else if (attacker.type.equals("Water") && defender.type.equals("Fire")) {
            factor = 2;
        } else if (attacker.type.equals("Water") && defender.type.equals("Grass")) {
            factor = 0.5;
        } else if (attacker.type.equals("Fire") && defender.type.equals("Fire")) {
            factor = 0.5;
        } else if (attacker.type.equals("Fire") && defender.type.equals("Electric")) {
            factor = 1;
        } else if (attacker.type.equals("Fire") && defender.type.equals("Water")) {
            factor = 0.5;
        } else if (attacker.type.equals("Fire") && defender.type.equals("Grass")) {
            factor = 2;
        }
        return factor;
    }

    public static int battle(Pokemon p1, Pokemon p2) {
        while (p1.getHealthPower() != 0 && p2.getHealthPower() != 0) {
            p1.reduceHealthPower((int) ( p2.getBaseAttackPower() * getAttackMultiplier(p2, p1)));
            p2.reduceHealthPower((int) ( p1.getBaseAttackPower() * getAttackMultiplier(p1, p2)));
        }
        if (p1.isDead()) {
            return 2;
        } else  {
            return 1;
        }
    }

    public static int battleOracle(Pokemon p1, Pokemon p2) {
        int p1_healthPower = p1.getHealthPower();
        int p2_healthPower = p2.getHealthPower();

        while (p1_healthPower > 0 && p2_healthPower > 0) {
            p1_healthPower = (int) (p1_healthPower - p2.getBaseAttackPower() * getAttackMultiplier(p2, p1));
            p2_healthPower = (int) (p2_healthPower - p1.getBaseAttackPower() * getAttackMultiplier(p1, p2));
        }
        if (p1_healthPower <= 0) {
            return 2;
        } else  {
            return 1;
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String name, type, inputString;
        int healthPower;
        double baseAttackPower;
        Pokemon winner;

        System.out.println("Enter the first Pokemon's name: ");
        name = MyUtils.formatStr(s.next());
        System.out.println("Enter the first Pokemon's type: ");
        type = MyUtils.formatStr(s.next());

        System.out.println("Enter the first Pokemon's Health Power (HP): ");
        inputString = s.next();
        while(!MyUtils.isNumeric(inputString)) {
            System.out.println("Invalid Health Power(HP) entered. Please re-enter.");
            System.out.println("Enter the first Pokemon's Health Power (HP): ");
            inputString = s.next();
        }
        healthPower = Integer.parseInt(inputString);

        System.out.println("Enter the first Pokemon's Base Attack's Power: ");
        inputString = s.next();
        while(!MyUtils.isNumeric(inputString)) {
            System.out.println("Invalid Base Attack Power entered. Please re-enter.");
            System.out.println("Enter the first Pokemon's Base Attack's Power: ");
            inputString = s.next();
        }
        baseAttackPower = Integer.parseInt(inputString);
        Pokemon p1 = new Pokemon(name, type, healthPower, baseAttackPower);

        System.out.println("Enter the second Pokemon's name: ");
        name = MyUtils.formatStr(s.next());
        System.out.println("Enter the first Pokemon's type: ");
        type = MyUtils.formatStr(s.next());

        System.out.println("Enter the second Pokemon's Health Power (HP): ");
        inputString = s.next();
        while(!MyUtils.isNumeric(inputString)) {
            System.out.println("Invalid Health Power(HP) entered. Please re-enter.");
            System.out.println("Enter the second Pokemon's Health Power (HP): ");
            inputString = s.next();
        }
        healthPower = Integer.parseInt(inputString);

        System.out.println("Enter the second Pokemon's Base Attack's Power: ");
        inputString = s.next();
        while(!MyUtils.isNumeric(inputString)) {
            System.out.println("Invalid Base Attack Power entered. Please re-enter.");
            System.out.println("Enter the second Pokemon's Base Attack's Power: ");
            inputString = s.next();
        }
        baseAttackPower = Integer.parseInt(inputString);
        Pokemon p2 = new Pokemon(name, type, healthPower, baseAttackPower);

        if (battle(p1,p2) == 1) {
            winner = p1;
        } else  {
            winner = p2;
        }

        System.out.println("Reducing by " + (int)(p2.getBaseAttackPower()*getAttackMultiplier(p2,p1)));
        System.out.println(p1.ID);
        System.out.println("Reducing by " + (int)(p1.getBaseAttackPower()*getAttackMultiplier(p1,p2)));
        System.out.println(p2.ID);

        System.out.println("First Pokemon's Stats after the battle: ");
        System.out.println("");
        System.out.println(p1.toString());
        System.out.println("-------------------------------");
        System.out.println("");

        System.out.println("Second Pokemon's Stats after the battle: ");
        System.out.println("");
        System.out.println(p2.toString());
        System.out.println("===============================");

        System.out.println("The winner of the battle is " + winner.getName());
    }
}
