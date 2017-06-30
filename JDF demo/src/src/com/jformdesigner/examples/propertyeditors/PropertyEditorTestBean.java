/*
 * Copyright (C) 2006-2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.propertyeditors;

/**
 * This is a (non-visual) bean to test the property editors.
 * It uses {@link MyPropertyData} and {@link MyImmutablePropertyData}.
 * Use "Choose Bean" in JFormDesigner to add it to a form.
 */
public class PropertyEditorTestBean
{
	private MyPropertyData myPropertyData;
	private MyImmutablePropertyData myImmutablePropertyData;

	public PropertyEditorTestBean() {
	}

	public MyPropertyData getMyPropertyData() {
		return myPropertyData;
	}

	public void setMyPropertyData( MyPropertyData myPropertyData ) {
		this.myPropertyData = myPropertyData;
	}

	public MyImmutablePropertyData getMyImmutablePropertyData() {
		return myImmutablePropertyData;
	}

	public void setMyImmutablePropertyData( MyImmutablePropertyData myImmutablePropertyData ) {
		this.myImmutablePropertyData = myImmutablePropertyData;
	}
}
