/*
 * Copyright (C) 2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.beansbinding.taskmanager;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.*;

/**
 * This view shows/edits a single task.
 * <p>
 * It uses Beans Binding to bind the properties of {@link #task} to input fields.
 * Beans Binding is also used to enable/disable the input fields.
 * <p>
 * Use the {@link #main} method to test this view.
 */
public class TaskView extends JPanel {

	private Task task;
	private List<String> categories;

	public TaskView() {
		initComponents();
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		Task oldTask = this.task;
		this.task = task;
		firePropertyChange("task", oldTask, task);
	}

	/**
	 * Returns a list of available categories.
	 * Used to fill list of {@link #categoryField} combobox.
	 * See binding: this.categories --> categoryField.elements
	 */
	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		List<String> oldCategories = this.categories;
		this.categories = categories;
		firePropertyChange("categories", oldCategories, categories);
	}

	/**
	 * Returns a list of available priorities.
	 * Used to fill list of {@link #priorityField} combobox.
	 * See binding: this.priorities --> priorityField.elements
	 */
	public List<Task.Priority> getPriorities() {
		return Arrays.asList(Task.Priority.values());
	}

	@Override
	public boolean requestFocusInWindow() {
		return titleField.requestFocusInWindow();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		titleLabel = new JLabel();
		titleField = new JTextField();
		descriptionLabel = new JLabel();
		descriptionScrollPane = new JScrollPane();
		descriptionField = new JTextArea();
		categoryLabel = new JLabel();
		categoryField = new JComboBox();
		priorityLabel = new JLabel();
		priorityField = new JComboBox();
		statusLabel = new JLabel();
		completedCheckBox = new JCheckBox();

		//======== this ========
		setLayout(new GridBagLayout());
		((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0};
		((GridBagLayout)getLayout()).rowHeights = new int[] {0, 85, 0, 0, 0, 0};
		((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
		((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

		//---- titleLabel ----
		titleLabel.setText("Title:");
		titleLabel.setLabelFor(titleField);
		titleLabel.setDisplayedMnemonic('T');
		add(titleLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//---- titleField ----
		titleField.setColumns(20);
		add(titleField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 0), 0, 0));

		//---- descriptionLabel ----
		descriptionLabel.setText("Description:");
		descriptionLabel.setLabelFor(descriptionField);
		descriptionLabel.setDisplayedMnemonic('D');
		add(descriptionLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
			new Insets(3, 0, 5, 5), 0, 0));

		//======== descriptionScrollPane ========
		{

			//---- descriptionField ----
			descriptionField.setColumns(30);
			descriptionField.setFont(UIManager.getFont("TextField.font"));
			descriptionField.setLineWrap(true);
			descriptionField.setWrapStyleWord(true);
			descriptionScrollPane.setViewportView(descriptionField);
		}
		add(descriptionScrollPane, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 0), 0, 0));

		//---- categoryLabel ----
		categoryLabel.setText("Category:");
		categoryLabel.setDisplayedMnemonic('C');
		categoryLabel.setLabelFor(categoryField);
		add(categoryLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));
		add(categoryField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 0), 0, 0));

		//---- priorityLabel ----
		priorityLabel.setText("Priority:");
		priorityLabel.setLabelFor(priorityField);
		priorityLabel.setDisplayedMnemonic('P');
		add(priorityLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));
		add(priorityField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 0), 0, 0));

		//---- statusLabel ----
		statusLabel.setText("Status:");
		add(statusLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 0, 5), 0, 0));

		//---- completedCheckBox ----
		completedCheckBox.setText("Completed");
		completedCheckBox.setMnemonic('O');
		add(completedCheckBox, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 0, 0), 0, 0));

		//---- bindings ----
		bindingGroup = new BindingGroup();
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			this, BeanProperty.create("task.title"),
			titleField, BeanProperty.create("text")));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			this, BeanProperty.create("task.description"),
			descriptionField, BeanProperty.create("text")));
		bindingGroup.addBinding(SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
			this, (BeanProperty) BeanProperty.create("categories"), categoryField));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			this, BeanProperty.create("task.category"),
			categoryField, BeanProperty.create("selectedItem")));
		bindingGroup.addBinding(SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
			this, (BeanProperty) BeanProperty.create("priorities"), priorityField));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			this, BeanProperty.create("task.priority"),
			priorityField, BeanProperty.create("selectedItem")));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			this, BeanProperty.create("task.completed"),
			completedCheckBox, BeanProperty.create("selected")));
		bindingGroup.bind();
		enablementBindingGroup = new BindingGroup();
		enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			this, ELProperty.create("${task != null}"),
			titleField, BeanProperty.create("editable")));
		enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			this, ELProperty.create("${task != null}"),
			descriptionField, BeanProperty.create("editable")));
		enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			this, ELProperty.create("${task != null}"),
			categoryField, BeanProperty.create("enabled")));
		enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			this, ELProperty.create("${task != null}"),
			priorityField, BeanProperty.create("enabled")));
		enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			this, ELProperty.create("${task != null}"),
			completedCheckBox, BeanProperty.create("enabled")));
		enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			titleField, BeanProperty.create("editable"),
			titleLabel, BeanProperty.create("enabled")));
		enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			descriptionField, BeanProperty.create("editable"),
			descriptionLabel, BeanProperty.create("enabled")));
		enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			categoryField, BeanProperty.create("enabled"),
			categoryLabel, BeanProperty.create("enabled")));
		enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			priorityField, BeanProperty.create("enabled"),
			priorityLabel, BeanProperty.create("enabled")));
		enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			completedCheckBox, BeanProperty.create("enabled"),
			statusLabel, BeanProperty.create("enabled")));
		enablementBindingGroup.bind();
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel titleLabel;
	private JTextField titleField;
	private JLabel descriptionLabel;
	private JScrollPane descriptionScrollPane;
	private JTextArea descriptionField;
	private JLabel categoryLabel;
	private JComboBox categoryField;
	private JLabel priorityLabel;
	private JComboBox priorityField;
	private JLabel statusLabel;
	private JCheckBox completedCheckBox;
	private BindingGroup bindingGroup;
	private BindingGroup enablementBindingGroup;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	//---- for testing --------------------------------------------------------

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {
					// ignore
				}

				// create task view
				TaskView taskView = new TaskView();
				taskView.setBorder(new EmptyBorder(10, 10, 10, 10));

				// init demo data
				taskView.setCategories(Arrays.asList(new String[] { "None", "Business", "Personal" }));
				taskView.setTask(new Task("Wash Car", "Winter is over. Time to clean the car.", "Personal", Task.Priority.NORMAL, false));

				// create frame
				JFrame frame = new JFrame("Task Demo");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(taskView);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
