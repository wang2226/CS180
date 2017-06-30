/*
 * Copyright (C) 2004-2010 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.formmodel;

import java.awt.*;
import java.io.FileOutputStream;
import com.jformdesigner.model.*;
import com.jformdesigner.runtime.FormSaver;
import com.jformdesigner.runtime.GridBagConstraintsEx;

/**
 * Demonstrates how to use the JFormDesigner form model API and form saver API
 * to dynamically create a form file from a list of properties using GridBagLayout.
 * <p>
 * Use the {@link #main} method to test this example.
 */
public class CreateGridBagLayoutFormExample
{
	public static void main( String[] args ) {
		new CreateGridBagLayoutFormExample();
	}

	static class PropertyInfo {
		String name;
		String type;

		PropertyInfo( String name, String type ) {
			this.name = name;
			this.type = type;
		}
	}

	private CreateGridBagLayoutFormExample() {
		PropertyInfo[] propertyInfos = new PropertyInfo[] {
			new PropertyInfo( "firstName", "javax.swing.JTextField" ),
			new PropertyInfo( "lastName", "javax.swing.JTextField" ),
			new PropertyInfo( "company", "javax.swing.JTextField" ),
		};

		try {
			FormModel model = createGridBagLayoutFormExample( propertyInfos );
			FormSaver.save( model, new FileOutputStream( "GridBagLayoutFormExample.jfd" ) );
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
	}

	private FormModel createGridBagLayoutFormExample( PropertyInfo[] propertyInfos ) {
		StringBuffer rowSpecs = new StringBuffer();
		for( int i = 0; i < propertyInfos.length; i++ ) {
			if( i > 0 )
				rowSpecs.append( ", " );
			rowSpecs.append( "0" );
		}

		FormLayoutManager layout = new FormLayoutManager( GridBagLayout.class );
		// $columnSpecs specifies the columns and $rowSpecs the rows of the layout
		// in comma separated lists.
		// Syntax: <minimumSize>[:<resizeWeight>], ...
		layout.setProperty( "$columnSpecs", "0, 0:1.0" ); // mandatory
		layout.setProperty( "$rowSpecs", rowSpecs.toString() );  // mandatory
		layout.setPropertyInt( "$hGap", 5 ); // optional; default is 0
		layout.setPropertyInt( "$vGap", 5 ); // optional; default is 0
		layout.setPropertyBoolean( "$alignLeft", true ); // optional; default is false
		layout.setPropertyBoolean( "$alignTop", true ); // optional; default is false

		FormContainer panel = new FormContainer( "javax.swing.JPanel", layout );
		panel.setName( "this" );

		for( int i = 0; i < propertyInfos.length; i++ ) {
			PropertyInfo propertyInfo = propertyInfos[i];
			String fieldName = propertyInfo.name + "Field";

			FormComponent label = new FormComponent( "javax.swing.JLabel" );
			label.setName( propertyInfo.name + "Label" );
			label.setProperty( "text", propertyInfo.name );
			label.setProperty( "labelFor", new FormReference( fieldName ) );
			label.auxiliary().setProperty( AUX_VARIABLE_LOCAL, Boolean.TRUE );
			panel.add( label, createGridBagConstraints( 0, i ) );

			FormComponent field = new FormComponent( propertyInfo.type );
			field.setName( fieldName );
			panel.add( field, createGridBagConstraints( 1, i ) );
		}

		FormRoot root = new FormRoot();
		root.add( panel, createDesignViewCons( 0, 0, 400, 400 ) );

		FormModel model = new FormModel();
		model.setContentType( "form/swing" );
		model.setRoot( root );
		return model;
	}

	//---- utility methods ----------------------------------------------------

	public static final String AUX_VARIABLE_LOCAL     = "JavaCodeGenerator.variableLocal";

	// alignment constants for GridBagLayout and TableLayout
	static final int ALIGN_DEFAULT = -1; // maps to FILL
	static final int ALIGN_CENTER	= 0;
	static final int ALIGN_TOP		= 1;
	static final int ALIGN_LEFT		= 2;
	static final int ALIGN_BOTTOM	= 3;
	static final int ALIGN_RIGHT	= 4;
	static final int ALIGN_FILL		= 5;

	private FormLayoutConstraints createGridBagConstraints( int gridX, int gridY ) {
		return createGridBagConstraints( gridX, gridY, 1, 1 );
	}

	private FormLayoutConstraints createGridBagConstraints( int gridX, int gridY,
		int gridWidth, int gridHeight )
	{
		return createGridBagConstraints( gridX, gridY, gridWidth, gridHeight,
			ALIGN_DEFAULT, ALIGN_DEFAULT, null, 0.0, 0.0, 0, 0 );
	}

	private FormLayoutConstraints createGridBagConstraints( int gridX, int gridY,
		int gridWidth, int gridHeight, int hAlign, int vAlign, Insets insets,
		double weightx, double weighty, int ipadx, int ipady )
	{
		FormLayoutConstraints cons = new FormLayoutConstraints( GridBagConstraintsEx.class );
		cons.setPropertyInt( "gridx", gridX );
		cons.setPropertyInt( "gridy", gridY );
		cons.setPropertyInt( "gridwidth", gridWidth, 1 );
		cons.setPropertyInt( "gridheight", gridHeight, 1 );
		cons.setPropertyInt( "hAlign", hAlign, ALIGN_DEFAULT );
		cons.setPropertyInt( "vAlign", vAlign, ALIGN_DEFAULT );
		cons.setProperty( "insets", insets );
		cons.setPropertyDouble( "weightx", weightx, 0.0 );
		cons.setPropertyDouble( "weighty", weighty, 0.0 );
		cons.setPropertyInt( "ipadx", ipadx, 0 );
		cons.setPropertyInt( "ipady", ipady, 0 );
		return cons;
	}

	/**
	 * Creates constraints for top-level components.
	 * It contains the location and size of a top-level component in the design view.
	 */
	private FormLayoutConstraints createDesignViewCons( int x, int y, int width, int height ) {
		FormLayoutConstraints cons = new FormLayoutConstraints( null );
		cons.setProperty( "location", new Point( x, y ) ); // location in design view
		cons.setProperty( "size", new Dimension( width, height ) ); // size in design view
		return cons;
	}
}
