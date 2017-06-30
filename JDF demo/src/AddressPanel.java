import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Mon Oct 24 20:37:31 EDT 2016
 */



/**
 * @author bruce wang
 */
public class AddressPanel extends JPanel {
    public AddressPanel() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - bruce wang
        JLabel nameLabel = new JLabel();
        nametField = new JTextField();
        JLabel phoneLabel = new JLabel();
        phoneField = new JTextField();
        JLabel zipCityLabel = new JLabel();
        zipField = new JTextField();
        cityField = new JTextField();
        JLabel countryLabel = new JLabel();
        countryField = new JTextField();
        textPane1 = new JTextPane();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new FormLayout(
            "[50dlu,pref], $lcgap, 50dlu, $lcgap, 100dlu:grow",
            "7*(pref, $lgap), default"));

        //---- nameLabel ----
        nameLabel.setText("Name:");
        add(nameLabel, CC.xy(1, 1));
        add(nametField, CC.xywh(3, 1, 3, 1));

        //---- phoneLabel ----
        phoneLabel.setText("Phone:");
        add(phoneLabel, CC.xy(1, 3));
        add(phoneField, CC.xywh(3, 3, 3, 1));

        //---- zipCityLabel ----
        zipCityLabel.setText("ZIP / City:");
        add(zipCityLabel, CC.xy(1, 5));
        add(zipField, CC.xy(3, 5));
        add(cityField, CC.xy(5, 5));

        //---- countryLabel ----
        countryLabel.setText("Country:");
        add(countryLabel, CC.xy(1, 7));
        add(countryField, CC.xywh(3, 7, 3, 1));
        add(textPane1, CC.xy(1, 15));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - bruce wang
    private JTextField nametField;
    private JTextField phoneField;
    private JTextField zipField;
    private JTextField cityField;
    private JTextField countryField;
    private JTextPane textPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
