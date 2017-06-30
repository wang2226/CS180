/*
 * Copyright (C) 2004-2010 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.basic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * Demonstrates the use of the JFormDesigner generated code.
 * <p>
 * Use the {@link #main} method to test this example.
 */
public class CodeGenExample
	extends JDialog
{
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {
					// ignore
				}

				CodeGenExample dialog = new CodeGenExample();

				// show dialog
				dialog.setVisible(true);

				System.out.println(dialog.nameField.getText());
				System.out.println(dialog.checkBox.isSelected());
			}
		});
	}

	CodeGenExample() {
		initComponents();

		// set values
		nameField.setText("enter name here");
		checkBox.setSelected(true);
	}

	// event handler for checkBox
	private void checkBoxActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "check box clicked");
	}

	// event handler for okButton
	private void okButtonActionPerformed() {
		dispose();
	}

	@SuppressWarnings("deprecation")
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		dialogPane = new JPanel();
		contentPane = new JPanel();
		label2 = new JLabel();
		nameField = new JTextField();
		checkBox = new JCheckBox();
		buttonBar = new JPanel();
		okButton = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Code Generation Example");
		setModal(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane2 = getContentPane();
		contentPane2.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setLayout(new BorderLayout());

			//======== contentPane ========
			{
				contentPane.setLayout(new FormLayout(
					"default, $lcgap, default",
					"default, $lgap, default"));

				//---- label2 ----
				label2.setText("Name:");
				contentPane.add(label2, cc.xy(1, 1));

				//---- nameField ----
				nameField.setColumns(20);
				contentPane.add(nameField, cc.xy(3, 1));

				//---- checkBox ----
				checkBox.setText("check me");
				checkBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						checkBoxActionPerformed(e);
					}
				});
				contentPane.add(checkBox, cc.xy(3, 3));
			}
			dialogPane.add(contentPane, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
				buttonBar.setLayout(new FormLayout(
					"$glue, $button",
					"pref"));

				//---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okButtonActionPerformed();
					}
				});
				buttonBar.add(okButton, cc.xy(2, 1));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane2.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel dialogPane;
	private JPanel contentPane;
	private JLabel label2;
	private JTextField nameField;
	private JCheckBox checkBox;
	private JPanel buttonBar;
	private JButton okButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
