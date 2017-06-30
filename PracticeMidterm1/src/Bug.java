/**
 * Created by Bruce on 9/30/16.
 */
public class Bug {
    private int position;
    private String direction;

    public Bug(int initialPosition) {
        this.position = initialPosition;
        this.direction = "right";
    }

    public int getPosition() {
        return position;
    }

    public String getDirection() {
        return direction;
    }

    public void turn() {
        if (direction.equals("right")) {
            direction = "left";
        } else if (direction.equals("left")) {
            direction = "right";
        }
    }

    public void move() {
        if (direction.equals("right")) {
            position = position + 1;
        } else if (direction.equals("left")) {
            position = position - 1;
        }
    }

    public static void main(String[] args) {
        Bug b = new Bug(10);
        b.move();
        b.turn();
        b.move();
        System.out.println("Position: " + b.getPosition() + "\nDirection: " + b.getDirection());
    }
}
