/**
 * Created by Bruce on 9/12/16.
 */
import java.lang.Math;
import java.util.Scanner;
public class Player {
    private String name;
    private double positionX;
    private double positionY;
    public Player (String name, double positionX, double positionY) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
    }
    public Player (String name) {
        this(name,0.0,0.0);
    }
    public String getName() {
        return name;
    }
    public double getPositionX() {
        return positionX;
    }
    public double getPositionY() {
        return positionY;
    }
    public void setName(String name) {
        this.name =  name;
    }
    public void moveX (double offsetX) {
        positionX = positionX + offsetX;
    }
    public void moveY (double offsetY) {
        positionY = positionY + offsetY;
    }
    public void moveInDirection (double theta, double distance) {
        positionX = positionX + distance * Math.cos(2 * Math.PI * theta / 360);
        positionY = positionY + distance * Math.sin(2 * Math.PI * theta / 360);
    }
    public boolean hasSamePositionAs (Player player) {
        return this.distanceFrom(player) <= 0.001;
    }
    public double distanceFrom (Player player) {
        return Math.sqrt(Math.pow(this.positionX-player.positionX,2) + Math.pow(this.positionY-player.positionY,2));
    }

    public static void main (String [] args) {
        Scanner s = new Scanner(System.in);
        String name;
        double x;
        double y;

        System.out.println("What is the name of player 1:");
        name = s.next();
        System.out.println("Enter the starting xPosition of " + name);
        x = s.nextDouble();
        System.out.println("Enter the starting yPosition of " + name);
        y = s.nextDouble();
        Player p1 = new Player(name, x, y);

        System.out.println("What is the name of player 2:");
        name = s.next();
        System.out.println("Enter the starting xPosition of " + name);
        x = s.nextDouble();
        System.out.println("Enter the starting yPosition of " + name);
        y = s.nextDouble();
        Player p2 = new Player(name, x, y);

        System.out.println("Enter " + p1.name + "'s horizontal move offset");
        double offsetX1 = s.nextDouble();
        p1.moveX(offsetX1);
        System.out.println("Enter " + p1.name + "'s vertical move offset");
        double offsetY1 = s.nextDouble();
        p1.moveY(offsetY1);
        System.out.println("Enter " + p1.name + "'s diagonal move angle degrees");
        double theta1 = s.nextDouble();
        System.out.println("Enter " + p1.name + "'s diagonal move distance");
        double distance1 = s.nextDouble();
        p1.moveInDirection(theta1,distance1);

        System.out.println("Enter " + p2.name + "'s horizontal move offset");
        double offsetX2 = s.nextDouble();
        p2.moveX(offsetX2);
        System.out.println("Enter " + p2.name + "'s vertical move offset");
        double offsetY2 = s.nextDouble();
        p2.moveY(offsetY2);
        System.out.println("Enter" + p2.name + "'s diagonal move angle degrees");
        double theta2 = s.nextDouble();
        System.out.println("Enter" + p2.name + "'s diagonal move distance");
        double distance2 = s.nextDouble();
        p2.moveInDirection(theta2,distance2);

        System.out.println(p1.name + "'s position: " + "(" + String.format("%.5f",p1.positionX) + "," + String.format("%.5f",p1.positionY) + ")");
        System.out.println(p2.name + "'s position: " + "(" + String.format("%.5f",p2.positionX) + "," + String.format("%.5f",p2.positionY) + ")");
        System.out.println("Distance between players: " + String.format("%.5f", p1.distanceFrom(p2)));
        System.out.println("Same position: " + p1.hasSamePositionAs(p2));
    }
}