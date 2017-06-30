/*
 * Copyright (C) 2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.beansbinding.simple;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

/**
 * This is a very basic example of using Beans Binding (JSR 295) to bind
 * GUI components to a data object. The components 'Value 1' and 'Value 2'
 * are bound to 'simpleData' object fields. The components 'Copy of Value 1'
 * and 'Copy of Value 2' are also bound to 'simpleData'.
 * <p>
 * Initially, the values of 'simpleData' are copied to the GUI components.
 * Changes in the GUI components are copied back to 'simpleData', which fires
 * events to inform others. So if you change 'Value 1', the value is copied
 * to 'simpleData.value1' and then copied to 'Copy of Value 1'.
 */
public class SimpleBindingDemo
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

				// show dialog
				SimpleBindingDemo dialog = new SimpleBindingDemo();
				dialog.setVisible(true);
			}
		});
	}

	public SimpleBindingDemo() {
		initComponents();
	}

	private void okButtonActionPerformed() {
		dispose();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		infoLabel = new JLabel();
		value1Label = new JLabel();
		value1Field = new JTextField();
		value2Label = new JLabel();
		value2Field = new JTextField();
		copyOfValue1Label = new JLabel();
		copyOfValue1Field = new JTextField();
		copyOfValue2Label = new JLabel();
		copyOfValue2Field = new JTextField();
		buttonBar = new JPanel();
		okButton = new JButton();
		simpleData = new SimpleData();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("SimpleBindingDemo");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new GridBagLayout());
				((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 0, 0};
				((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {165, 0, 0, 35, 0, 0, 30, 0};
				((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
				((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

				//---- infoLabel ----
				infoLabel.setText("<html>This is a very basic example of using Beans Binding (JSR 295) to bind GUI components to a data object. The components 'Value 1' and 'Value 2' are bound to 'simpleData' object fields. The components 'Copy of Value 1' and 'Copy of Value 2' are also bound to 'simpleData'.<br><br>Initially, the values of 'simpleData' are copied to the GUI components. Changes in the GUI components are copied back to 'simpleData', which fires events to inform others. So if you change 'Value 1', the value is copied to 'simpleData.value1' and then copied to 'Copy of Value 1'.</html>");
				infoLabel.setPreferredSize(new Dimension(450, 160));
				infoLabel.setVerticalAlignment(SwingConstants.TOP);
				contentPanel.add(infoLabel, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

				//---- value1Label ----
				value1Label.setText("Value 1");
				contentPanel.add(value1Label, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- value1Field ----
				value1Field.setColumns(20);
				contentPanel.add(value1Field, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

				//---- value2Label ----
				value2Label.setText("Value 2:");
				contentPanel.add(value2Label, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- value2Field ----
				value2Field.setColumns(20);
				contentPanel.add(value2Field, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

				//---- copyOfValue1Label ----
				copyOfValue1Label.setText("Copy of Value 1");
				contentPanel.add(copyOfValue1Label, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- copyOfValue1Field ----
				copyOfValue1Field.setColumns(20);
				contentPanel.add(copyOfValue1Field, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

				//---- copyOfValue2Label ----
				copyOfValue2Label.setText("Copy of Value 2:");
				contentPanel.add(copyOfValue2Label, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- copyOfValue2Field ----
				copyOfValue2Field.setColumns(20);
				contentPanel.add(copyOfValue2Field, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
				((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

				//---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okButtonActionPerformed();
					}
				});
				buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());

		//---- simpleData ----
		simpleData.setValue1("test value 1");

		//---- bindings ----
		bindingGroup = new BindingGroup();
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			simpleData, BeanProperty.create("value1"),
			value1Field, BeanProperty.create("text")));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			simpleData, BeanProperty.create("value2"),
			value2Field, BeanProperty.create("text")));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			simpleData, BeanProperty.create("value1"),
			copyOfValue1Field, BeanProperty.create("text")));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			simpleData, BeanProperty.create("value2"),
			copyOfValue2Field, BeanProperty.create("text")));
		bindingGroup.bind();
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel infoLabel;
	private JLabel value1Label;
	private JTextField value1Field;
	private JLabel value2Label;
	private JTextField value2Field;
	private JLabel copyOfValue1Label;
	private JTextField copyOfValue1Field;
	private JLabel copyOfValue2Label;
	private JTextField copyOfValue2Field;
	private JPanel buttonBar;
	private JButton okButton;
	private SimpleData simpleData;
	private BindingGroup bindingGroup;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
