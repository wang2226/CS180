/*
 * Copyright (C) 2006-2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.propertyeditors;

/**
 * Test form for property editors.
 */
public class PropertyEditorTestForm {
	public PropertyEditorTestForm() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		propertyEditorTestBean1 = new PropertyEditorTestBean();

		//---- propertyEditorTestBean1 ----
		propertyEditorTestBean1.setMyImmutablePropertyData(new MyImmutablePropertyData("a", "ba"));
		propertyEditorTestBean1.setMyPropertyData(new MyPropertyData("ca", "d"));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private PropertyEditorTestBean propertyEditorTestBean1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
