/*
 * Copyright (C) 2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.beans;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Demonstrates the use of {@link JBaseDialog}.
 * <p>
 * Use the {@link #main} method to test this example.
 */
public class MyDialog
	extends JBaseDialog
{
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {
					// ignore
				}

				// show dialog
				MyDialog dialog = new MyDialog();
				dialog.open();
			}
		});
	}

	public MyDialog() {
		super( (Frame) null );
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label1 = new JLabel();
		textField1 = new JTextField();
		label2 = new JLabel();
		comboBox1 = new JComboBox();

		//======== this ========
		setTitle("My Dialog");
		setHelpVisible(true);
		Container contentPanel = getContentPanel();
		contentPanel.setLayout(new GridBagLayout());
		((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 0, 0};
		((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 0, 0};
		((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
		((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

		//---- label1 ----
		label1.setText("text");
		contentPanel.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//---- textField1 ----
		textField1.setColumns(30);
		contentPanel.add(textField1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 0), 0, 0));

		//---- label2 ----
		label2.setText("text");
		contentPanel.add(label2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 0, 5), 0, 0));
		contentPanel.add(comboBox1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 0, 0), 0, 0));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label1;
	private JTextField textField1;
	private JLabel label2;
	private JComboBox comboBox1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
