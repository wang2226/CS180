import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Mon Oct 24 20:37:31 EDT 2016
 */



/**
 * @author bruce wang
 */
public class FormsTutorial extends JPanel {
    public FormsTutorial() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - bruce wang
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        goodiesFormsSeparator2 = compFactory.createSeparator("General");
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        goodiesFormsSeparator1 = compFactory.createSeparator("Propeller");
        label3 = new JLabel();
        textField3 = new JTextField();
        label6 = new JLabel();
        textField6 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        label5 = new JLabel();
        textField5 = new JTextField();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new FormLayout(
            "right:default, $lcgap, default, $ugap, right:default, $lcgap, default",
            "2*(default, $lgap), default, $pgap, 2*(default, $lgap), default"));
        ((FormLayout)getLayout()).setColumnGroups(new int[][] {{1, 5}, {3, 7}});
        add(goodiesFormsSeparator2, CC.xywh(1, 1, 7, 1));

        //---- label1 ----
        label1.setText("Company");
        add(label1, CC.xy(1, 3));
        add(textField1, CC.xywh(3, 3, 5, 1));

        //---- label2 ----
        label2.setText("Contact");
        add(label2, CC.xy(1, 5));
        add(textField2, CC.xywh(3, 5, 5, 1));
        add(goodiesFormsSeparator1, CC.xywh(1, 7, 7, 1));

        //---- label3 ----
        label3.setText("PTI [kW]");
        add(label3, CC.xy(1, 9));

        //---- textField3 ----
        textField3.setColumns(8);
        add(textField3, CC.xy(3, 9));

        //---- label6 ----
        label6.setText("Power [kW]");
        add(label6, CC.xy(5, 9));

        //---- textField6 ----
        textField6.setColumns(8);
        add(textField6, CC.xy(7, 9));

        //---- label4 ----
        label4.setText("R [mm]");
        add(label4, CC.xy(1, 11));
        add(textField4, CC.xy(3, 11));

        //---- label5 ----
        label5.setText("D [mm]");
        add(label5, CC.xy(5, 11));
        add(textField5, CC.xy(7, 11));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - bruce wang
    private JComponent goodiesFormsSeparator2;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JComponent goodiesFormsSeparator1;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label6;
    private JTextField textField6;
    private JLabel label4;
    private JTextField textField4;
    private JLabel label5;
    private JTextField textField5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
