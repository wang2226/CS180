/**
 * Created by Bruce on 9/6/16.
 */
import javax.swing.*;
public class CitationMachine {
    public static void main (String [] args) {
        JOptionPane.showMessageDialog(null,"Enter book details to generate APA format citation.");
        String name = JOptionPane.showInputDialog("Author name: ");
        String year = JOptionPane.showInputDialog("Year of publication: ");
        String title = JOptionPane.showInputDialog("Title of work: ");
        String publisher = JOptionPane.showInputDialog("Publisher: ");
        String location =  JOptionPane.showInputDialog("Location: ");
        String citation = name.substring(name.indexOf(" ")+1,name.length()) + ", " + name.charAt(0) + "." + "(" + year + ")." + title + "." + location + ":" + publisher + ".";
        JOptionPane.showMessageDialog(null,citation);
    }
}
