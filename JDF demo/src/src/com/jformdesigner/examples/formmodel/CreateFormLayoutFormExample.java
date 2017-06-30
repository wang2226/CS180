/*
 * Copyright (C) 2004-2010 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.formmodel;

import java.awt.*;
import java.io.FileOutputStream;
import com.jformdesigner.model.*;
import com.jformdesigner.runtime.FormSaver;
import com.jformdesigner.runtime.FormSpecCoder;
import com.jgoodies.forms.layout.*;
import com.jgoodies.forms.layout.CellConstraints.Alignment;

/**
 * Demonstrates how to use the JFormDesigner form model API and form saver API
 * to dynamically create a form file from a list of properties using FormLayout.
 * <p>
 * Use the {@link #main} method to test this example.
 */
public class CreateFormLayoutFormExample
{
	public static void main( String[] args ) {
		new CreateFormLayoutFormExample();
	}

	static class PropertyInfo {
		String name;
		String type;

		PropertyInfo( String name, String type ) {
			this.name = name;
			this.type = type;
		}
	}

	private CreateFormLayoutFormExample() {
		PropertyInfo[] propertyInfos = new PropertyInfo[] {
			new PropertyInfo( "firstName", "javax.swing.JTextField" ),
			new PropertyInfo( "lastName", "javax.swing.JTextField" ),
			new PropertyInfo( "company", "javax.swing.JTextField" ),
		};

		try {
			FormModel model = createFormLayoutFormExample( propertyInfos );
			FormSaver.save( model, new FileOutputStream( "FormLayoutFormExample.jfd" ) );
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
	}

	private FormModel createFormLayoutFormExample( PropertyInfo[] propertyInfos ) {
		ColumnSpec[] columnSpecs = new ColumnSpec[] {
			FormSpecs.DEFAULT_COLSPEC,
			FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
			ColumnSpec.decode( "default:grow" ),
		};

		RowSpec[] rowSpecs = new RowSpec[propertyInfos.length * 2 - 1];
		for( int i = 0, j = 0; i < propertyInfos.length; i++ ) {
			if( i > 0 )
				rowSpecs[j++] = FormSpecs.LINE_GAP_ROWSPEC;
			rowSpecs[j++] = FormSpecs.DEFAULT_ROWSPEC;
		}

		FormLayoutManager layout = new FormLayoutManager( FormLayout.class );
		// $columnSpecs specifies the columns and $rowSpecs the rows of the layout
		// in comma separated lists.
		// The syntax is the same as described in the JGoodies Forms 1.1 documentation
		// but extended with some keywords for predefined column/row specifications
		// as defined in the class com.jgoodies.forms.layout.FormSpecs.
		// Predefined column specs:
		//   "glue", "relgap", "unrelgap", "labelcompgap", "button" and "growbutton"
		// Predefined row specs:
		//   "glue", "relgap", "unrelgap", "narrowlinegap", "linegap" and "pargap"
		//
		// To encode ColumnSpec[] and RowSpec[] use the methods encodeColumnSpecs()
		// and encodeRowSpecs() from the class com.jformdesigner.runtime.FormSpecCoder.
		layout.setProperty( "$columnSpecs", FormSpecCoder.encodeColumnSpecs( columnSpecs ) ); // mandatory
		layout.setProperty( "$rowSpecs", FormSpecCoder.encodeRowSpecs( rowSpecs ) );  // mandatory

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
			panel.add( label, createCellConstraints( 1, i * 2 + 1 ) );

			FormComponent field = new FormComponent( propertyInfo.type );
			field.setName( fieldName );
			panel.add( field, createCellConstraints( 3, i * 2 + 1 ) );
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

	private FormLayoutConstraints createCellConstraints( int gridX, int gridY ) {
		return createCellConstraints( gridX, gridY, 1, 1 );
	}

	private FormLayoutConstraints createCellConstraints( int gridX, int gridY,
		int gridWidth, int gridHeight )
	{
		return createCellConstraints( gridX, gridY, gridWidth, gridHeight,
			CellConstraints.DEFAULT, CellConstraints.DEFAULT, null );
	}

	private FormLayoutConstraints createCellConstraints( int gridX, int gridY,
		int gridWidth, int gridHeight, Alignment hAlign, Alignment vAlign, Insets insets )
	{
		FormLayoutConstraints cons = new FormLayoutConstraints( CellConstraints.class );
		cons.setPropertyInt( "gridX", gridX );
		cons.setPropertyInt( "gridY", gridY );
		cons.setPropertyInt( "gridWidth", gridWidth, 1 );
		cons.setPropertyInt( "gridHeight", gridHeight, 1 );
		cons.setProperty( "hAlign", hAlign, CellConstraints.DEFAULT );
		cons.setProperty( "vAlign", vAlign, CellConstraints.DEFAULT );
		cons.setProperty( "insets", insets );
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
