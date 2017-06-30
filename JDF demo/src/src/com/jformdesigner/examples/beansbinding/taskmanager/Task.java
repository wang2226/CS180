/*
 * Copyright (C) 2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.beansbinding.taskmanager;

import com.jformdesigner.examples.beansbinding.util.AbstractModelObject;

/**
 * Task data model class.
 * <p>
 * It has getter and setter methods to access property values and supports
 * property change listeners thru {@link AbstractModelObject}.
 */
public class Task
	extends AbstractModelObject
{
	public enum Priority { HIGH, NORMAL, LOW };

	private String title;
	private String description;
	private String category = "None";
	private Priority priority = Priority.NORMAL;
	private boolean completed;

	public Task() {
	}

	public Task(String title, String description, String category, Priority priority, boolean completed) {
		this.title = title;
		this.description = description;
		this.category = category;
		this.priority = priority;
		this.completed = completed;
	}

	public Task(Task task) {
		this.title = task.title;
		this.description = task.description;
		this.category = task.category;
		this.priority = task.priority;
		this.completed = task.completed;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		String oldTitle = this.title;
		this.title = title;
		changeSupport.firePropertyChange("title", oldTitle, title);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		String oldDescription = this.description;
		this.description = description;
		changeSupport.firePropertyChange("description", oldDescription, description);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		String oldCategory = this.category;
		this.category = category;
		changeSupport.firePropertyChange("category", oldCategory, category);
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		Priority oldPriority = this.priority;
		this.priority = priority;
		changeSupport.firePropertyChange("priority", oldPriority, priority);
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		boolean oldCompleted = this.completed;
		this.completed = completed;
		changeSupport.firePropertyChange("completed", oldCompleted, completed);
	}
}
