/*
 * Copyright (C) 2004-2010 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.basic;

import java.awt.event.*;
import javax.swing.*;

// requires redist/jfd-loader.jar
import com.jformdesigner.model.FormModel;
import com.jformdesigner.runtime.FormCreator;
import com.jformdesigner.runtime.FormLoader;

/**
 * Demonstrates the use of the JFormDesigner form loader API.
 * http://www.formdev.com/jformdesigner/doc/runtime-library/
 * <p>
 * Use the {@link #main} method to test this example.
 */
public class LoaderExample
{
	private JDialog dialog;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {
					// ignore
				}

				new LoaderExample();
			}
		});
	}

	LoaderExample() {
		try {
			// load the .jfd file into memory
			FormModel formModel = FormLoader.load("com/jformdesigner/examples/basic/LoaderExample.jfd");

			// create a dialog
			FormCreator formCreator = new FormCreator(formModel);
			formCreator.setTarget(this);
			dialog = formCreator.createDialog(null);

			// get references to components
			JTextField nameField = formCreator.getTextField("nameField");
			JCheckBox checkBox = formCreator.getCheckBox("checkBox");

			// set values
			nameField.setText("enter name here");
			checkBox.setSelected(true);

			// show dialog
			dialog.setModal(true);
			dialog.pack();
			dialog.setVisible(true);

			System.out.println(nameField.getText());
			System.out.println(checkBox.isSelected());
			System.exit(0);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	// event handler for checkBox
	@SuppressWarnings("unused")
	private void checkBoxActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(dialog, "check box clicked");
	}

	// event handler for okButton
	@SuppressWarnings("unused")
	private void okButtonActionPerformed() {
		dialog.dispose();
	}
}
