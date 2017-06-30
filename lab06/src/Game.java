/**
 * Created by Bruce on 9/28/16.
 */
import java.util.Random;
import java.util.Scanner;
public class Game {
    /**
     * declare static variable Rock, Paper and Scissors
     */
    private final static int Rock = 1;
    private final static int Paper = 2;
    private final static int Scissors = 3;

    Scanner s = new Scanner(System.in);
    Random r = new Random();

    /**
     * this is the method to run the game
     */
    public void runGame() {
        /**
         * while loop for playing the game infinite times
         */
        while (true) {
            System.out.println("Welcome" + "\nPlease enter an option: " + "\n1.Rock" + "\n2.Paper" + "\n3.Scissors" + "\n4.Exit");
            int userInput = s.nextInt();
            int computerInput = simulateComputerMove();
            int winner = checkWinner(userInput, computerInput);
            if (userInput == 1) {
                System.out.println("You played Rock!");
                if (winner == 1) {
                    System.out.println("The computer played scissors!" + "\nYou win!" + "\n");
                } else if (winner == 2) {
                    System.out.println("The computer played paper!" + "\nYou lose!" + "\n");
                } else if (winner == 0) {
                    System.out.println("The computer played rock!" + "\nDraw!" + "\n");
                }
            } else if (userInput == 2) {
                System.out.println("You played Paper!");
                if (winner == 1) {
                    System.out.println("The computer played rock!" + "\nYou win!" + "\n");
                } else if (winner == 2) {
                    System.out.println("The computer played scissors!" + "\nYou lose!" + "\n");
                } else if (winner == 0) {
                    System.out.println("The computer played paper!" + "\nDraw!" + "\n");
                }
            } else if (userInput == 3) {
                System.out.println("You played Scissors!");
                if (winner == 1) {
                    System.out.println("The computer played paper!" + "\nYou win!" + "\n");
                } else if (winner == 2) {
                    System.out.println("The computer played rock!" + "\nYou lose!" + "\n");
                } else if (winner == 0) {
                    System.out.println("The computer played scissors!" + "\nDraw!" + "\n");
                }
            }
            if (userInput == 4) {
                System.out.println("Thanks for playing!");
                break;
            }
        }
    }

    /**
     * method to check the winner
     * @param move1
     * @param move2
     * @return winner
     */
    private int checkWinner(int move1, int move2) {
        if (move1 == move2) {
            return 0;
        } else if (move1 == 1 && move2 == 2) {
            return 2;
        } else if (move1 == 1 && move2 == 3) {
            return 1;
        } else if (move1 == 2 && move2 == 1) {
            return 1;
        } else if (move1 == 2 && move2 == 3) {
            return 2;
        } else if (move1 == 3 && move2 == 1) {
            return 2;
        } else if (move1 == 3 && move2 == 2) {
            return 1;
        }
        return -1;
    }

    /**
     * method to return a random number
     * @return random number between 1 and 3
     */
    private int simulateComputerMove() {
        return r.nextInt(3) + 1;
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.runGame();
    }
}
