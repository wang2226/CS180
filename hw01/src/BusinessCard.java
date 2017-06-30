import javax.swing.*;
/**
 * BusinessCard
 *
 * Display a business card
 *
 * @author Haoran Wang, BLK13
 *
 * @version Aug 29, 2016
 *
 */
public class BusinessCard {
    /**
     *This is the main method to run the program
     *
     * The main method
     *
     * @param args is needed for the main method
     */
    public static void main(String [] args) {
        String name = JOptionPane.showInputDialog("Enter your name: "); //input user's name
        String major = JOptionPane.showInputDialog("Enter your major: "); //input user's major
        String email = JOptionPane.showInputDialog("Enter your e-mail: "); //input user's email
        JOptionPane.showMessageDialog(null, "Name: " + name + "\nMajor: " + major + "\nE-mail: " + email);
        //display user's information
    }
}
