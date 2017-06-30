/**
 * Created by Bruce on 10/12/16.
 */
import java.util.Scanner;
public class Connect4 {
    private char[][] board;
    public static final char red = 'O';
    public static final char yellow = 'X';
    public static final char emptySpace = ' ';

    public Connect4() {
        board = new char[6][7];
        for(int i=0; i<6; i++){
            for(int j=0; j<7; j++){
                board[i][j] = emptySpace;
            }
        }
    }

    public char[][] getBoard() {
        char[][] tempBoard = new char[6][7];
        for(int i=0; i<6; i++){
            for(int j=0; j<7; j++){
                tempBoard[i][j] = board[i][j];
            }
        }
        return tempBoard;
    }

    public int putPiece(int column, char color) {
        //the column is filled
        if (board[0][column] != emptySpace) {
            return -1;
        }

        for (int row = 0; row < 6; row++) {
            if (board[row][column] != emptySpace) {
                board[row-1][column] = color;
                return row - 1;
            }
        }
        //at the bottom
        board[5][column] = color;
        return 5;
    }

    public char checkAlignment(int row, int column) {

        //check rows
        int count = 1;
        //to left
        for(int i = 0; i < 3; i++) {
            if ((column - i < 1) ||  (board[row][column - i] != board[row][column - (i + 1)])) {
                break;
            }else {
                count++;
            }
        }
        //to right
        for(int i = 0; i < 3; i++) {
            if ((column + i > 5) ||  (board[row][column + i] != board[row][column + (i + 1)])) {
                break;
            }else {
                count++;
            }
        }
        if (count >= 4) {
            return board[row][column];
        }

        //check columns
        count = 1;
        //only to down
        for(int i = 0; i < 3; i++) {
            if ((row + i > 4) ||  (board[row + i][column] != board[row + (i + 1)][column])) {
                break;
            }else {
                count++;
            }
        }

        if (count >= 4) {
            return board[row][column];
        }

        //diagonals from top-left to bottom-right
        count = 1;
        //to top-left
        for(int i = 0; i < 3; i++) {
            if ((row - i < 1) || (column - i < 1) ||
                    (board[row - i][column - i] != board[row - (i + 1)][column - (i + 1)])) {
                break;
            }else {
                count++;
            }
        }

        //to bottom-right
        for(int i = 0; i < 3; i++) {
            if ((row + i > 4) || (column + i > 5) ||
                    (board[row + i][column + i] != board[row + (i + 1)][column + (i + 1)])) {
                break;
            }else {
                count++;
            }
        }

        if (count >= 4) {
            return board[row][column];
        }

        //diagonal to top-right to bottom-left
        count = 1;
        //diagonal to top-right
        for(int i = 0; i < 3; i++) {
            if ((row - i < 1) || (column + i > 5) ||
                    (board[row - i][column + i] != board[row - (i + 1)][column + (i + 1)])) {
                break;
            }else {
                count++;
            }
        }
        //to bottom-left
        for(int i = 0; i < 3; i++) {
            if ((row + i > 4) || (column - i < 1) ||
                    (board[row + i][column - i] != board[row + (i + 1)][column - (i + 1)])) {
                break;
            }else {
                count++;
            }
        }

        if (count >= 4) {
            return board[row][column];
        }

        return emptySpace;
    }

    public void printScreen() {
        System.out.println(" 0 1 2 3 4 5 6 ");
        System.out.println(" ---------------");

        for (int row = 0; row < 6; ++row) {
            System.out.print((char)(65 + row) +"|");
            for (int col = 0; col < 7; ++col)
                System.out.print(board[row][col] + "|");
            System.out.println();
            System.out.println(" ---------------");
        }
    }


    public void play() {
        Scanner s = new Scanner(System.in);
        boolean isRed = true;
        int row;
        int column;

        while (true) {
            printScreen();

            if(isRed){
                System.out.println("Current player: " + red);
            }else {
                System.out.println("Current player: " + yellow);
            }
            System.out.print("Choose a column: ");

            column = s.nextInt();
            System.out.println("");

            while (column < 0 || column > 6){
                System.out.println("Column is invalid, pls reenter: ");
                column = s.nextInt();
            }

            if(isRed){
                row = putPiece(column, red);
            }else {
                row = putPiece(column, yellow);
            }

            char result = checkAlignment(row, column);

            if (result == red || result == yellow) {
                printScreen();
                System.out.println("!!! Winner is Player " + result +"!!!");
                break;
            }

            // to alternate turns.
            isRed = !isRed;
        }
    }
}
