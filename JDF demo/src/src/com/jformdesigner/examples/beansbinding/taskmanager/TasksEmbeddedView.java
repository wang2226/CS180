/*
 * Copyright (C) 2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.beansbinding.taskmanager;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.swingbinding.*;

/**
 * This view shows a list of tasks in a JTable, the selected task in an embedded
 * {@link TaskView} and provides New and Delete buttons to edit the tasks.
 * <p>
 * It uses Beans Binding to bind the list of {@link #tasks} to the JTable
 * and the selected task to the embedded {@link TaskView}.
 * Beans Binding is also used to enable/disable the Delete button based on selection.
 * <p>
 * The New button uses {@link TaskView} to edit a new task in a sub-dialog.
 * <p>
 * Use the {@link #main} method to test this view.
 */
public class TasksEmbeddedView extends JPanel {

	private List<Task> tasks = ObservableCollections.observableList(new ArrayList<Task>());
	private List<String> categories;

	public TasksEmbeddedView() {
		initComponents();
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		List<Task> oldTasks = this.tasks;
		this.tasks = tasks;
		firePropertyChange("tasks", oldTasks, tasks);
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		List<String> oldCategories = this.categories;
		this.categories = categories;
		firePropertyChange("categories", oldCategories, categories);
	}

	private void newTask() {
		// create new task and add it to tasks list
		tasks.add(new Task());

		// select new task in table and scroll row to visible area
		int row = tasks.size() - 1;
		tasksTable.setRowSelectionInterval(row, row);
		tasksTable.scrollRectToVisible(tasksTable.getCellRect(row, 0, true));

		taskView.requestFocusInWindow();
	}

	private void deleteTask() {
		int[] selectedRows = tasksTable.getSelectedRows();
		if (selectedRows.length == 0)
			return;

		// remove items
		for (int i = selectedRows.length - 1; i >= 0; i--)
			tasks.remove(selectedRows[i]);

		// select row
		if (tasksTable.getRowCount() > 0) {
			int newSel = Math.min(selectedRows[0], tasksTable.getRowCount() - 1);
			tasksTable.setRowSelectionInterval(newSel, newSel);
			tasksTable.scrollRectToVisible(tasksTable.getCellRect(newSel, 0, true));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		tasksScrollPane = new JScrollPane();
		tasksTable = new JTable();
		taskView = new TaskView();
		buttonPanel = new JPanel();
		newTaskButton = new JButton();
		deleteTaskButton = new JButton();

		//======== this ========
		setLayout(new GridBagLayout());
		((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0};
		((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0};
		((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
		((GridBagLayout)getLayout()).rowWeights = new double[] {1.0, 0.0, 0.0, 1.0E-4};

		//======== tasksScrollPane ========
		{

			//---- tasksTable ----
			tasksTable.setPreferredScrollableViewportSize(new Dimension(450, 200));
			tasksScrollPane.setViewportView(tasksTable);
		}
		add(tasksScrollPane, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 0), 0, 0));
		add(taskView, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 0), 0, 0));

		//======== buttonPanel ========
		{
			buttonPanel.setLayout(new GridBagLayout());
			((GridBagLayout)buttonPanel.getLayout()).columnWidths = new int[] {0, 85, 80, 0};
			((GridBagLayout)buttonPanel.getLayout()).rowHeights = new int[] {0, 0};
			((GridBagLayout)buttonPanel.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 1.0E-4};
			((GridBagLayout)buttonPanel.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

			//---- newTaskButton ----
			newTaskButton.setText("New");
			newTaskButton.setMnemonic('N');
			newTaskButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					newTask();
				}
			});
			buttonPanel.add(newTaskButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- deleteTaskButton ----
			deleteTaskButton.setText("Delete");
			deleteTaskButton.setMnemonic('D');
			deleteTaskButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteTask();
				}
			});
			buttonPanel.add(deleteTaskButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		add(buttonPanel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 0, 0), 0, 0));

		//---- bindings ----
		bindingGroup = new BindingGroup();
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			this, BeanProperty.create("categories"),
			taskView, BeanProperty.create("categories")));
		{
			JTableBinding binding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE,
				this, (BeanProperty) BeanProperty.create("tasks"), tasksTable);
			binding.addColumnBinding(BeanProperty.create("title"))
				.setColumnName("Title")
				.setColumnClass(String.class);
			binding.addColumnBinding(BeanProperty.create("category"))
				.setColumnName("Category")
				.setColumnClass(String.class);
			binding.addColumnBinding(BeanProperty.create("priority"))
				.setColumnName("Priority")
				.setColumnClass(Task.Priority.class);
			binding.addColumnBinding(BeanProperty.create("completed"))
				.setColumnName("Completed")
				.setColumnClass(Boolean.class);
			bindingGroup.addBinding(binding);
		}
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			tasksTable, BeanProperty.create("selectedElement"),
			taskView, BeanProperty.create("task")));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			tasksTable, ELProperty.create("${selectedElement != null}"),
			deleteTaskButton, BeanProperty.create("enabled")));
		bindingGroup.bind();
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane tasksScrollPane;
	private JTable tasksTable;
	private TaskView taskView;
	private JPanel buttonPanel;
	private JButton newTaskButton;
	private JButton deleteTaskButton;
	private BindingGroup bindingGroup;
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

				// create tasks view
				TasksEmbeddedView tasksView = new TasksEmbeddedView();
				tasksView.setBorder(new EmptyBorder(10, 10, 10, 10));

				// init demo data
				tasksView.setCategories(Arrays.asList(new String[] { "None", "Development", "Business", "Personal" }));
				List<Task> tasks = new ArrayList<Task>();
				tasks.add(new Task("Wash Car", "Winter is over. Time to clean the car.", "Personal", Task.Priority.NORMAL, false));
				tasks.add(new Task("Visit Client XYZ", null, "Business", Task.Priority.HIGH, false));
				tasks.add(new Task("Redesign web site", "Our web site looks great, but there is room for improvements.", "Development", Task.Priority.NORMAL, false));
				tasks.add(new Task("Implement new layout manager", "We need support for this new exciting layout manager.", "Development", Task.Priority.HIGH, false));
				tasksView.setTasks(ObservableCollections.observableList(tasks));

				// select first task
				tasksView.tasksTable.setRowSelectionInterval(0, 0);

				// create frame
				JFrame frame = new JFrame("Tasks Embedded Demo");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(tasksView);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
