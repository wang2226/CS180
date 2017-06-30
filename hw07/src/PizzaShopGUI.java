import java.awt.*;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.border.*;

import java.util.ArrayList;
/*
 * Created by JFormDesigner on Wed Oct 26 10:33:04 EDT 2016
 */



/**
 * @author bruce wang
 */
public class PizzaShopGUI {
    public PizzaShopGUI() {
        initComponents();
        mainFrame.setSize(800, 500);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    private void smallradioButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        sizeString = smallradioButton.getText();
    }

    private void mediumradioButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        sizeString = mediumradioButton.getText();
    }

    private void largeradioButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        sizeString = largeradioButton.getText();
    }

    private void stylecomboBoxActionPerformed(ActionEvent e) {
        // TODO add your code here
        styleString = stylecomboBox.getSelectedItem().toString();
    }

    private void garliccheckBoxActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (garliccheckBox.isSelected()) {
            toppings.add("Garlic");
        }
    }

    private void jalapenoscheckBoxActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (jalapenoscheckBox.isSelected()) {
            toppings.add("Jalapenos");
        }
    }

    private void extracheesekBoxActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (extracheesekBox.isSelected()) {
            toppings.add("Extra cheese");
        }
    }

    private void baconcheckBoxActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (baconcheckBox.isSelected()) {
            toppings.add("Baconc");
        }
    }

    private void submitButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        String temp = "Your custom pizza\n";
        outputtextArea.append(temp);
        temp = String.format("Size: %s\n", sizeString);
        outputtextArea.append(temp);
        temp = String.format("Style: %s\n", styleString);
        outputtextArea.append(temp);
        temp = "Toppings: ";
        outputtextArea.append(temp);
        for (int i = 0; i < toppings.size(); i++) {
            if (i == 0) {
                temp = String.format("%s\n", toppings.get(i));
                outputtextArea.append(temp);
            } else {
                temp = String.format("                  %s\n", toppings.get(i));
                outputtextArea.append(temp);
            }
        }

    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - bruce wang
        mainFrame = new JFrame();
        outputPanel = new JPanel();
        outputtextArea = new JTextArea();
        optionPanel = new JPanel();
        sizePanel = new JPanel();
        smallradioButton = new JRadioButton();
        mediumradioButton = new JRadioButton();
        largeradioButton = new JRadioButton();
        stylePanel = new JPanel();
        stylecomboBox = new JComboBox<>();
        toppingsPanel = new JPanel();
        garliccheckBox = new JCheckBox();
        jalapenoscheckBox = new JCheckBox();
        extracheesekBox = new JCheckBox();
        baconcheckBox = new JCheckBox();
        submitButton = new JButton();

        //======== mainFrame ========
        {
            mainFrame.setBackground(Color.lightGray);
            mainFrame.setTitle("Pizza Shop");
            Container mainFrameContentPane = mainFrame.getContentPane();

            //======== outputPanel ========
            {
                outputPanel.setBorder(new TitledBorder("Output"));


                GroupLayout outputPanelLayout = new GroupLayout(outputPanel);
                outputPanel.setLayout(outputPanelLayout);
                outputPanelLayout.setHorizontalGroup(
                    outputPanelLayout.createParallelGroup()
                        .addGroup(outputPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(outputtextArea, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(14, Short.MAX_VALUE))
                );
                outputPanelLayout.setVerticalGroup(
                    outputPanelLayout.createParallelGroup()
                        .addGroup(outputPanelLayout.createSequentialGroup()
                            .addComponent(outputtextArea, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                );
            }

            //======== optionPanel ========
            {
                optionPanel.setBorder(new TitledBorder("Options"));

                //======== sizePanel ========
                {
                    sizePanel.setBorder(new TitledBorder("Select your pizza size"));

                    //---- smallradioButton ----
                    smallradioButton.setText("Small");
                    smallradioButton.addActionListener(e -> smallradioButtonActionPerformed(e));

                    //---- mediumradioButton ----
                    mediumradioButton.setText("Medium");
                    mediumradioButton.addActionListener(e -> mediumradioButtonActionPerformed(e));

                    //---- largeradioButton ----
                    largeradioButton.setText("Large");
                    largeradioButton.addActionListener(e -> largeradioButtonActionPerformed(e));

                    GroupLayout sizePanelLayout = new GroupLayout(sizePanel);
                    sizePanel.setLayout(sizePanelLayout);
                    sizePanelLayout.setHorizontalGroup(
                        sizePanelLayout.createParallelGroup()
                            .addGroup(sizePanelLayout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(smallradioButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mediumradioButton)
                                .addGap(26, 26, 26)
                                .addComponent(largeradioButton, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                    );
                    sizePanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {largeradioButton, mediumradioButton, smallradioButton});
                    sizePanelLayout.setVerticalGroup(
                        sizePanelLayout.createParallelGroup()
                            .addGroup(sizePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(mediumradioButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                                .addComponent(largeradioButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                                .addComponent(smallradioButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
                    );
                    sizePanelLayout.linkSize(SwingConstants.VERTICAL, new Component[] {largeradioButton, mediumradioButton, smallradioButton});
                }

                //======== stylePanel ========
                {
                    stylePanel.setBorder(new TitledBorder("Select your pizza style"));

                    //---- stylecomboBox ----
                    stylecomboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                        "Margherita",
                        "Prosciutoo",
                        "Diavola",
                        "Verdure"
                    }));
                    stylecomboBox.addActionListener(e -> stylecomboBoxActionPerformed(e));

                    GroupLayout stylePanelLayout = new GroupLayout(stylePanel);
                    stylePanel.setLayout(stylePanelLayout);
                    stylePanelLayout.setHorizontalGroup(
                        stylePanelLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, stylePanelLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(stylecomboBox, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90))
                    );
                    stylePanelLayout.setVerticalGroup(
                        stylePanelLayout.createParallelGroup()
                            .addGroup(stylePanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(stylecomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(28, Short.MAX_VALUE))
                    );
                }

                //======== toppingsPanel ========
                {
                    toppingsPanel.setBorder(new TitledBorder("Choose your toppings"));

                    //---- garliccheckBox ----
                    garliccheckBox.setText("Garlic");
                    garliccheckBox.addActionListener(e -> garliccheckBoxActionPerformed(e));

                    //---- jalapenoscheckBox ----
                    jalapenoscheckBox.setText("Jalapenos");
                    jalapenoscheckBox.addActionListener(e -> jalapenoscheckBoxActionPerformed(e));

                    //---- extracheesekBox ----
                    extracheesekBox.setText("Extra Cheese");
                    extracheesekBox.addActionListener(e -> extracheesekBoxActionPerformed(e));

                    //---- baconcheckBox ----
                    baconcheckBox.setText("Bacon");
                    baconcheckBox.addActionListener(e -> baconcheckBoxActionPerformed(e));

                    GroupLayout toppingsPanelLayout = new GroupLayout(toppingsPanel);
                    toppingsPanel.setLayout(toppingsPanelLayout);
                    toppingsPanelLayout.setHorizontalGroup(
                        toppingsPanelLayout.createParallelGroup()
                            .addGroup(toppingsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(garliccheckBox)
                                .addGap(18, 18, 18)
                                .addComponent(jalapenoscheckBox)
                                .addGap(18, 18, 18)
                                .addComponent(extracheesekBox)
                                .addGap(18, 18, 18)
                                .addComponent(baconcheckBox)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    toppingsPanelLayout.setVerticalGroup(
                        toppingsPanelLayout.createParallelGroup()
                            .addGroup(toppingsPanelLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(toppingsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(garliccheckBox)
                                    .addComponent(jalapenoscheckBox)
                                    .addComponent(extracheesekBox)
                                    .addComponent(baconcheckBox))
                                .addContainerGap(28, Short.MAX_VALUE))
                    );
                }

                GroupLayout optionPanelLayout = new GroupLayout(optionPanel);
                optionPanel.setLayout(optionPanelLayout);
                optionPanelLayout.setHorizontalGroup(
                    optionPanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, optionPanelLayout.createSequentialGroup()
                            .addGroup(optionPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(sizePanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(toppingsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(stylePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
                );
                optionPanelLayout.setVerticalGroup(
                    optionPanelLayout.createParallelGroup()
                        .addGroup(optionPanelLayout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sizePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(stylePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(toppingsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                );
            }

            //---- submitButton ----
            submitButton.setText("Submit");
            submitButton.setActionCommand("Submit");
            submitButton.addActionListener(e -> submitButtonActionPerformed(e));

            GroupLayout mainFrameContentPaneLayout = new GroupLayout(mainFrameContentPane);
            mainFrameContentPane.setLayout(mainFrameContentPaneLayout);
            mainFrameContentPaneLayout.setHorizontalGroup(
                mainFrameContentPaneLayout.createParallelGroup()
                    .addGroup(mainFrameContentPaneLayout.createSequentialGroup()
                        .addComponent(optionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(outputPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(mainFrameContentPaneLayout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(submitButton, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            mainFrameContentPaneLayout.setVerticalGroup(
                mainFrameContentPaneLayout.createParallelGroup()
                    .addGroup(mainFrameContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainFrameContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(outputPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(optionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(submitButton)
                        .addContainerGap(38, Short.MAX_VALUE))
            );
            mainFrame.pack();
            mainFrame.setLocationRelativeTo(mainFrame.getOwner());
        }

        //---- sizebuttonGroup ----
        ButtonGroup sizebuttonGroup = new ButtonGroup();
        sizebuttonGroup.add(smallradioButton);
        sizebuttonGroup.add(mediumradioButton);
        sizebuttonGroup.add(largeradioButton);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - bruce wang
    private JFrame mainFrame;
    private JPanel outputPanel;
    private JTextArea outputtextArea;
    private JPanel optionPanel;
    private JPanel sizePanel;
    private JRadioButton smallradioButton;
    private JRadioButton mediumradioButton;
    private JRadioButton largeradioButton;
    private JPanel stylePanel;
    private JComboBox<String> stylecomboBox;
    private JPanel toppingsPanel;
    private JCheckBox garliccheckBox;
    private JCheckBox jalapenoscheckBox;
    private JCheckBox extracheesekBox;
    private JCheckBox baconcheckBox;
    private JButton submitButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private String sizeString;
    private String styleString;
    private ArrayList<String> toppings = new ArrayList<>();

    public static void main(String[] args) {
        new PizzaShopGUI();
    }

}
