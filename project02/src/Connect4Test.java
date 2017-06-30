import org.junit.*;
import java.lang.reflect.*;

import static org.junit.Assert.*;

/**
 *
 * @author Matthew Ess
 * @author Maria Pacheco (reflection)
 * @version 10-04-16
 *
 */
public class Connect4Test {

    Connect4 c;

    @org.junit.Before
    public void setUp() throws Exception {
        c = new Connect4();
    }

    @Test(timeout=100)
    public void testConstructor()  {
        try {
            char[][] expected = new char[6][7];
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    expected[i][j] = ' ';
                }
            }

            // reflection to access private field
            Field field = c.getClass().getDeclaredField("board");
            field.setAccessible(true);
            char[][] actual = (char[][]) field.get(c);

            String message = "Check your constructor";
            for (int i = 0; i < 6; i++) {
                assertArrayEquals(message, expected[i], actual[i]);
            }
        } catch(IllegalAccessException|NoSuchFieldException e) {
            assertFalse("Make the board variable a char array named 'board'", true);
        } catch(Exception e) {
            assertFalse("An exception occurred", true);
            e.printStackTrace();
        }

    }

    @Test(timeout=100)
    public void testGetBoardNew()  {
        try {
            char[][] expected = new char[6][7];
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    expected[i][j] = ' ';
                }
            }
            char[][] actualCopy = c.getBoard();

            // reflection to access private field
            Field field = c.getClass().getDeclaredField("board");
            field.setAccessible(true);
            char[][] actualBoard = (char[][])field.get(c);

            String message = "Check getBoard() when the game is new";
            for (int i = 0; i < 6; i++) {
                assertArrayEquals(message, expected[i], actualCopy[i]);
            }
            message = "Ensure that getBoard() returns a copy of the board";
            assertNotSame(message, actualBoard, actualCopy);
        } catch(IllegalAccessException|NoSuchFieldException e) {
            assertFalse("Make the board variable a char array named 'board'", true);
        } catch(Exception e) {
            assertFalse("An exception occurred", true);
            e.printStackTrace();
        }
    }

    @Test(timeout=100)
    public void testGetBoardInProgress()  {
        try {
            char[][] expected = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', 'X', ' ', ' ', ' ', ' '},
                    {'X', 'X', 'O', 'O', 'O', 'X', ' '}};

            // reflection to access private field
            Field field = c.getClass().getDeclaredField("board");
            field.setAccessible(true);
            char[][] actualBoard = (char[][]) field.get(c);

            actualBoard[4] = expected[4].clone();
            actualBoard[5] = expected[5].clone();
            char[][] actualCopy = c.getBoard();
            String message = "Check getBoard() when game is in progress";
            for (int i = 0; i < 6; i++) {
                assertArrayEquals(message, expected[i], actualCopy[i]);
            }
            message = "Ensure that getBoard() returns a copy of the board";
            assertNotSame(message, actualBoard, actualCopy);
        }  catch(IllegalAccessException|NoSuchFieldException e) {
            assertFalse("Make the board variable a char array named 'board'", true);
        } catch(Exception e) {
            assertFalse("An exception occurred", true);
            e.printStackTrace();
        }
    }

    @Test(timeout=100)
    public void testPutPieceX()  {
        try {
            String message = "Check putPiece() when putting red/X pieces";
            int expectedRow;
            int actualRow;
            for (int i = 0; i < 6; i++) {
                expectedRow = 5 - i;
                actualRow = c.putPiece(0, 'X');
                assertEquals(message, expectedRow, actualRow);
            }
            char[][] expectedBoard = new char[6][];
            expectedBoard[0] = new char[]{'X', ' ', ' ', ' ', ' ', ' ', ' '};
            for (int i = 1; i < 6; i++) {
                expectedBoard[i] = expectedBoard[0].clone();
            }

            // reflection to access private field
            Field field = c.getClass().getDeclaredField("board");
            field.setAccessible(true);
            char[][] actualBoard = (char[][])field.get(c);

            for (int i = 0; i < 6; i++) {
                assertArrayEquals(message, expectedBoard[i], actualBoard[i]);
            }
        }  catch(IllegalAccessException|NoSuchFieldException e) {
            assertFalse("Make the board variable a char array named 'board'", true);
        } catch(Exception e) {
            assertFalse("An exception occurred", true);
            e.printStackTrace();
        }
    }

    @Test(timeout=100)
    public void testPutPieceO()  {
        try {
            String message = "Check putPiece() when putting yellow/O pieces";
            int expectedRow;
            int actualRow;
            for (int i = 0; i < 6; i++) {
                expectedRow = 5 - i;
                actualRow = c.putPiece(0, 'O');
                assertEquals(message, expectedRow, actualRow);
            }
            char[][] expectedBoard = new char[6][];
            expectedBoard[0] = new char[]{'O', ' ', ' ', ' ', ' ', ' ', ' '};
            for (int i = 1; i < 6; i++) {
                expectedBoard[i] = expectedBoard[0].clone();
            }

            // reflection to access private field
            Field field = c.getClass().getDeclaredField("board");
            field.setAccessible(true);
            char[][] actualBoard = (char[][])field.get(c);

            for (int i = 0; i < 6; i++) {
                assertArrayEquals(message, expectedBoard[i], actualBoard[i]);
            }
        }  catch(IllegalAccessException|NoSuchFieldException e) {
            assertFalse("Make the board variable a char array named 'board'", true);
        } catch(Exception e) {
            assertFalse("An exception occurred", true);
            e.printStackTrace();
        }
    }

    @Test(timeout=100)
    public void testCheckAlignmentNone() {
        try {
            char expected = ' ';
            char actual;
            String message = "Check checkAlignment() when there are no 4 in a row's";
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    actual = (char) c.checkAlignment(i, j);
                    assertEquals(message, expected, actual);
                }
            }
        } catch(Exception e) {
            assertFalse("An exception occurred", true);
            e.printStackTrace();
        }
    }

    @Test(timeout=100)
    public void testCheckAlignmentVertX()  {
        try {
            // reflection to access private field
            Field field = c.getClass().getDeclaredField("board");
            field.setAccessible(true);
            char[][] board = (char[][])field.get(c);

            for (int i = 2; i < 6; i++) {
                board[i][0] = 'X';
            }
            char expected = 'X';
            char actual;
            String message = "Check checkAlignment() when there are 4 vertical X's";
            actual = (char) c.checkAlignment(2, 0);
            assertEquals(message, expected, actual);

        }  catch(IllegalAccessException|NoSuchFieldException e) {
            assertFalse("Make the board variable a char array named 'board'", true);
        } catch(Exception e) {
            assertFalse("An exception occurred", true);
            e.printStackTrace();
        }
    }

    @Test(timeout=100)
    public void testCheckAlignmentHorzX()  {
        try {
            // reflection to access private field
            Field field = c.getClass().getDeclaredField("board");
            field.setAccessible(true);
            char[][] board = (char[][])field.get(c);

            for (int i = 0; i < 4; i++) {
                board[5][i] = 'X';
            }
            char expected = 'X';
            char actual;
            String message = "Check checkAlignment() when there are 4 horizontal X's";
            for (int i = 0; i < 4; i++) {
                actual = (char) c.checkAlignment(5, i);
                assertEquals(message, expected, actual);
            }
        }  catch(IllegalAccessException|NoSuchFieldException e) {
            assertFalse("Make the board variable a char array named 'board'", true);
        } catch(Exception e) {
            assertFalse("An exception occurred", true);
            e.printStackTrace();
        }
    }

    @Test(timeout=100)
    public void testCheckAlignmentDiag1X()  {
        try {
            // reflection to access private field
            Field field = c.getClass().getDeclaredField("board");
            field.setAccessible(true);
            char[][] board = (char[][])field.get(c);

            for (int i = 2; i < 6; i++) {
                board[i][5 - i] = 'X';
            }
            char expected = 'X';
            char actual;
            String message = "Check checkAlignment() when there are 4 diagonal X's";
            for (int i = 0; i < 4; i++) {
                actual = (char) c.checkAlignment(5 - i, i);
                assertEquals(message, expected, actual);
            }
        }  catch(IllegalAccessException|NoSuchFieldException e) {
            assertFalse("Make the board variable a char array named 'board'", true);
        } catch(Exception e) {
            assertFalse("An exception occurred", true);
            e.printStackTrace();
        }
    }

    @Test(timeout=100)
    public void testCheckAlignmentDiag2X()  {
        try {
            // reflection to access private field
            Field field = c.getClass().getDeclaredField("board");
            field.setAccessible(true);
            char[][] board = (char[][])field.get(c);

            for (int i = 2; i < 6; i++) {
                board[i][i - 2] = 'X';
            }
            char expected = 'X';
            char actual;
            String message = "Check checkAlignment() when there are 4 diagonal X's";
            for (int i = 0; i < 4; i++) {
                actual = (char) c.checkAlignment(i + 2, i);
                assertEquals(message, expected, actual);
            }
        }  catch(IllegalAccessException|NoSuchFieldException e) {
            assertFalse("Make the board variable a char array named 'board'", true);
        } catch(Exception e) {
            assertFalse("An exception occurred", true);
            e.printStackTrace();
        }
    }

    @Test(timeout=100)
    public void testCheckAligmentVertO()  {
        try {
            // reflection to access private field
            Field field = c.getClass().getDeclaredField("board");
            field.setAccessible(true);
            char[][] board = (char[][])field.get(c);

            for (int i = 2; i < 6; i++) {
                board[i][0] = 'O';
            }
            char expected = 'O';
            char actual;
            String message = "Check checkAlignment() when there are 4 vertical O's";

            actual = (char) c.checkAlignment(2, 0);
            assertEquals(message, expected, actual);
        }  catch(IllegalAccessException|NoSuchFieldException e) {
            assertFalse("Make the board variable a char array named 'board'", true);
        } catch(Exception e) {
            assertFalse("An exception occurred", true);
            e.printStackTrace();
        }
    }

    @Test(timeout=100)
    public void testCheckAlignmentHorzO()  {
        try {
            // reflection to access private field
            Field field = c.getClass().getDeclaredField("board");
            field.setAccessible(true);
            char[][] board = (char[][])field.get(c);

            for (int i = 0; i < 4; i++) {
                board[5][i] = 'O';
            }
            char expected = 'O';
            char actual;
            String message = "Check checkAlignment() when there are 4 horizontal O's";
            for (int i = 0; i < 4; i++) {
                actual = (char) c.checkAlignment(5, i);
                assertEquals(message, expected, actual);
            }
        }  catch(IllegalAccessException|NoSuchFieldException e) {
            assertFalse("Make the board variable a char array named 'board'", true);
        } catch(Exception e) {
            assertFalse("An exception occurred", true);
            e.printStackTrace();
        }
    }

    @Test(timeout=100)
    public void testCheckAlignmentDiag1O()  {
        try {
            // reflection to access private field
            Field field = c.getClass().getDeclaredField("board");
            field.setAccessible(true);
            char[][] board = (char[][])field.get(c);

            for (int i = 2; i < 6; i++) {
                board[i][5 - i] = 'O';
            }
            char expected = 'O';
            char actual;
            String message = "Check checkAlignment() when there are 4 diagonal O's";
            for (int i = 0; i < 4; i++) {
                actual = (char) c.checkAlignment(5 - i, i);
                assertEquals(message, expected, actual);
            }
        }  catch(IllegalAccessException|NoSuchFieldException e) {
            assertFalse("Make the board variable a char array named 'board'", true);
        } catch(Exception e) {
            assertFalse("An exception occurred", true);
            e.printStackTrace();
        }
    }

    @Test(timeout=100)
    public void testCheckAlignmentDiag2O()  {
        try {
            // reflection to access private field
            Field field = c.getClass().getDeclaredField("board");
            field.setAccessible(true);
            char[][] board = (char[][])field.get(c);

            for (int i = 2; i < 6; i++) {
                board[i][i - 2] = 'O';
            }
            char expected = 'O';
            char actual;
            String message = "Check checkAlignment() when there are 4 diagonal O's";
            for (int i = 0; i < 4; i++) {
                actual = (char) c.checkAlignment(i + 2, i);
                assertEquals(message, expected, actual);
            }
        }  catch(IllegalAccessException|NoSuchFieldException e) {
            assertFalse("Make the board variable a char array named 'board'", true);
        } catch(Exception e) {
            assertFalse("An exception occurred", true);
            e.printStackTrace();
        }
    }

}