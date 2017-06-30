/*
 * Copyright (C) 2006-2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.propertyeditors;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyEditorSupport;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Property editor for {@link MyPropertyData}.
 * This property editor demonstrates how to build a custom editor and how
 * to update the property values on changes.
 */
public class MyPropertyDataEditor
	extends PropertyEditorSupport
{
	@Override
	public String getAsText() {
		// avoid editing in properties table cell
		return null;
	}

	@Override
	public boolean isPaintable() {
		return true;
	}

	@Override
	public void paintValue( Graphics g, Rectangle rect ) {
		MyPropertyData myData = (MyPropertyData) getValue();
		if( myData == null )
			return;

		String text = myData.getValue1() + ", " + myData.getValue2();
		g.drawString( text, rect.x + 1, rect.y + rect.height - 3 );
	}

	@Override
	public boolean supportsCustomEditor() {
		return true;
	}

	@Override
	public Component getCustomEditor() {
		// create the custom editor panel
		JPanel panel = new JPanel();
		final JTextField value1Field = new JTextField( 20 );
		final JTextField value2Field = new JTextField( 20 );
		panel.add( value1Field );
		panel.add( value2Field );

		// copy the property value into the editor fields
		MyPropertyData myData = (MyPropertyData) getValue();
		if( myData != null ) {
			value1Field.setText( myData.getValue1() );
			value2Field.setText( myData.getValue2() );
		}

		// add focus lost listeners to the text fields to update the
		// property value on changes
		FocusListener focustListener = new FocusAdapter() {
			@Override
			public void focusLost( FocusEvent e ) {
				// always create a new instance of the property value (for undo/redo)
				MyPropertyData myData = new MyPropertyData();
				myData.setValue1( value1Field.getText() );
				myData.setValue2( value2Field.getText() );
				setValue( myData );
			}
		};
		value1Field.addFocusListener( focustListener );
		value2Field.addFocusListener( focustListener );
		return panel;
	}

	@Override
	public String getJavaInitializationString() {
		MyPropertyData myData = (MyPropertyData) getValue();

		return "new MyPropertyData(" + toJavaString( myData.getValue1() )
			+ ", " + toJavaString( myData.getValue2() ) + ")";
	}

	private String toJavaString( String str ) {
		if( str == null )
			return "null";

		return "\"" + str + "\"";
	}
}
