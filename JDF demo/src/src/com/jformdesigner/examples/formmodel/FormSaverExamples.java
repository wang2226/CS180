/*
 * Copyright (C) 2004-2010 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.formmodel;

import info.clearthought.layout.TableLayout;
import java.awt.*;
import java.io.FileOutputStream;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import com.jformdesigner.model.*;
import com.jformdesigner.runtime.*;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints.Alignment;

/**
 * Demonstrates how to use the JFormDesigner form model API and form saver API
 * to create form files using various layout managers.
 * It creates in-memory form models and saves them to .jfd files.
 * <p>
 * Use the {@link #main} method to test this example.
 */
public class FormSaverExamples
{
	public static void main( String[] args ) {
		new FormSaverExamples();
	}

	FormSaverExamples() {
		FormModel model;

		try {
			model = createBorderLayoutExample();
			FormSaver.save( model, new FileOutputStream( "BorderLayoutExample.jfd" ) );

			model = createBoxLayoutExample();
			FormSaver.save( model, new FileOutputStream( "BoxLayoutExample.jfd" ) );

			model = createFlowLayoutExample();
			FormSaver.save( model, new FileOutputStream( "FlowLayoutExample.jfd" ) );

			model = createFormLayoutExample();
			FormSaver.save( model, new FileOutputStream( "FormLayoutExample.jfd" ) );

			model = createGridBagLayoutExample();
			FormSaver.save( model, new FileOutputStream( "GridBagLayoutExample.jfd" ) );

			model = createTableLayoutExample();
			FormSaver.save( model, new FileOutputStream( "TableLayoutExample.jfd" ) );

			model = createMiscExample();
			FormSaver.save( model, new FileOutputStream( "MiscExample.jfd" ) );

		} catch( Exception ex ) {
			ex.printStackTrace();
		}
	}

	//---- BorderLayout example -----------------------------------------------

	/**
	 * Demonstrates how to create a panel using BorderLayout.
	 */
	private FormModel createBorderLayoutExample() {
		FormContainer panel = new FormContainer( "javax.swing.JPanel",
			new FormLayoutManager( BorderLayout.class ) );
		panel.setName( "this" );

		FormComponent northButton = new FormComponent( "javax.swing.JButton" );
		northButton.setName( "northButton" );
		northButton.setProperty( "text", "North" );
		panel.add( northButton, new FormLayoutConstraints( String.class, BorderLayout.NORTH ) );

		FormComponent centerButton = new FormComponent( "javax.swing.JButton" );
		centerButton.setName( "centerButton" );
		centerButton.setProperty( "text", "Center" );
		panel.add( centerButton, new FormLayoutConstraints( String.class, BorderLayout.CENTER ) );

		FormRoot root = new FormRoot();
		root.add( panel, createDesignViewCons( 0, 0, 300, 200 ) );

		FormModel model = new FormModel();
		model.setContentType( "form/swing" );
		model.setRoot( root );
		return model;
	}

	//---- BoxLayout example --------------------------------------------------

	/**
	 * Demonstrates how to create a panel using BoxLayout.
	 */
	private FormModel createBoxLayoutExample() {
		FormContainer panel = new FormContainer( "javax.swing.JPanel",
			new FormLayoutManager( BoxLayout.class ) );
		panel.setName( "this" );

		FormComponent button1 = new FormComponent( "javax.swing.JButton" );
		button1.setName( "button1" );
		button1.setProperty( "text", "Button 1" );
		panel.add( button1 );

		FormComponent button2 = new FormComponent( "javax.swing.JButton" );
		button2.setName( "button2" );
		button2.setProperty( "text", "Button 2" );
		panel.add( button2 );

		FormRoot root = new FormRoot();
		root.add( panel, createDesignViewCons( 0, 0, 300, 200 ) );

		FormModel model = new FormModel();
		model.setContentType( "form/swing" );
		model.setRoot( root );
		return model;
	}

	//---- FlowLayout example -------------------------------------------------

	/**
	 * Demonstrates how to create a panel using FlowLayout.
	 */
	private FormModel createFlowLayoutExample() {
		FormContainer panel = new FormContainer( "javax.swing.JPanel",
			new FormLayoutManager( FlowLayout.class ) );
		panel.setName( "this" );

		FormComponent button1 = new FormComponent( "javax.swing.JButton" );
		button1.setName( "button1" );
		button1.setProperty( "text", "Button 1" );
		panel.add( button1 );

		FormComponent button2 = new FormComponent( "javax.swing.JButton" );
		button2.setName( "button2" );
		button2.setProperty( "text", "Button 2" );
		panel.add( button2 );

		FormRoot root = new FormRoot();
		root.add( panel, createDesignViewCons( 0, 0, 300, 200 ) );

		FormModel model = new FormModel();
		model.setContentType( "form/swing" );
		model.setRoot( root );
		return model;
	}

	//---- FormLayout example -------------------------------------------------

	/**
	 * Demonstrates how to create a panel using JGoodies FormLayout.
	 */
	private FormModel createFormLayoutExample() {
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
		layout.setProperty( "$columnSpecs", "default, labelcompgap, default:grow" );
		layout.setProperty( "$rowSpecs", "default, linegap, default" );

		FormContainer panel = new FormContainer( "javax.swing.JPanel", layout );
		panel.setName( "this" );

		FormComponent nameLabel = new FormComponent( "javax.swing.JLabel" );
		nameLabel.setName( "nameLabel" );
		nameLabel.setProperty( "text", "Name:" );
		panel.add( nameLabel, createCellConstraints( 1, 1 ) );

		FormComponent nameField = new FormComponent( "javax.swing.JTextField" );
		nameField.setName( "nameField" );
		nameField.setPropertyInt( "columns", 20 );
		panel.add( nameField, createCellConstraints( 3, 1 ) );

		FormComponent companyLabel = new FormComponent( "javax.swing.JLabel" );
		companyLabel.setName( "companyLabel" );
		companyLabel.setProperty( "text", "Company:" );
		panel.add( companyLabel, createCellConstraints( 1, 3 ) );

		FormComponent companyField = new FormComponent( "javax.swing.JTextField" );
		companyField.setName( "companyField" );
		companyField.setPropertyInt( "columns", 20 );
		panel.add( companyField, createCellConstraints( 3, 3 ) );

		FormRoot root = new FormRoot();
		root.add( panel, createDesignViewCons( 0, 0, 300, 200 ) );

		FormModel model = new FormModel();
		model.setContentType( "form/swing" );
		model.setRoot( root );
		return model;
	}

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

	//---- GridBagLayout example ----------------------------------------------

	/**
	 * Demonstrates how to create a panel using GridBagLayout.
	 */
	private FormModel createGridBagLayoutExample() {
		FormLayoutManager layout = new FormLayoutManager( GridBagLayout.class );
		// $columnSpecs specifies the columns and $rowSpecs the rows of the layout
		// in comma separated lists.
		// Syntax: <minimumSize>[:<resizeWeight>], ...
		layout.setProperty( "$columnSpecs", "0, 0:1.0" ); // mandatory
		layout.setProperty( "$rowSpecs", "0, 0" );  // mandatory
		layout.setPropertyInt( "$hGap", 5 ); // optional; default is 0
		layout.setPropertyInt( "$vGap", 5 ); // optional; default is 0
		layout.setPropertyBoolean( "$alignLeft", true ); // optional; default is false
		layout.setPropertyBoolean( "$alignTop", true ); // optional; default is false

		FormContainer panel = new FormContainer( "javax.swing.JPanel", layout );
		panel.setName( "this" );

		FormComponent nameLabel = new FormComponent( "javax.swing.JLabel" );
		nameLabel.setName( "nameLabel" );
		nameLabel.setProperty( "text", "Name:" );
		panel.add( nameLabel, createGridBagConstraints( 0, 0 ) );

		FormComponent nameField = new FormComponent( "javax.swing.JTextField" );
		nameField.setName( "nameField" );
		nameField.setPropertyInt( "columns", 20 );
		panel.add( nameField, createGridBagConstraints( 1, 0 ) );

		FormComponent companyLabel = new FormComponent( "javax.swing.JLabel" );
		companyLabel.setName( "companyLabel" );
		companyLabel.setProperty( "text", "Company:" );
		panel.add( companyLabel, createGridBagConstraints( 0, 1 ) );

		FormComponent companyField = new FormComponent( "javax.swing.JTextField" );
		companyField.setName( "companyField" );
		companyField.setPropertyInt( "columns", 20 );
		panel.add( companyField, createGridBagConstraints( 1, 1 ) );

		FormRoot root = new FormRoot();
		root.add( panel, createDesignViewCons( 0, 0, 300, 200 ) );

		FormModel model = new FormModel();
		model.setContentType( "form/swing" );
		model.setRoot( root );
		return model;
	}

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
	 * Converts GridBagConstraints anchor and fill to JFormDesigner hAlign.
	 */
	@SuppressWarnings("unused")
	private int toHAlign( int anchor, int fill ) {
		if( fill == GridBagConstraints.HORIZONTAL ||
			fill == GridBagConstraints.BOTH )
		  return ALIGN_DEFAULT;
		switch( anchor ) {
			case GridBagConstraints.NORTHWEST:	return ALIGN_LEFT;
			case GridBagConstraints.WEST:		return ALIGN_LEFT;
			case GridBagConstraints.SOUTHWEST:	return ALIGN_LEFT;
			case GridBagConstraints.NORTH:		return ALIGN_CENTER;
			case GridBagConstraints.CENTER:		return ALIGN_CENTER;
			case GridBagConstraints.SOUTH:		return ALIGN_CENTER;
			case GridBagConstraints.NORTHEAST:	return ALIGN_RIGHT;
			case GridBagConstraints.EAST:		return ALIGN_RIGHT;
			case GridBagConstraints.SOUTHEAST:	return ALIGN_RIGHT;
		}
		return ALIGN_DEFAULT;
	}

	/**
	 * Converts GridBagConstraints anchor and fill to JFormDesigner vAlign.
	 */
	@SuppressWarnings("unused")
	private int toVAlign( int anchor, int fill ) {
		if( fill == GridBagConstraints.VERTICAL ||
			fill == GridBagConstraints.BOTH )
		  return ALIGN_DEFAULT;
		switch( anchor ) {
			case GridBagConstraints.NORTHWEST:	return ALIGN_TOP;
			case GridBagConstraints.NORTH:		return ALIGN_TOP;
			case GridBagConstraints.NORTHEAST:	return ALIGN_TOP;
			case GridBagConstraints.WEST:		return ALIGN_CENTER;
			case GridBagConstraints.CENTER:		return ALIGN_CENTER;
			case GridBagConstraints.EAST:		return ALIGN_CENTER;
			case GridBagConstraints.SOUTHWEST:	return ALIGN_BOTTOM;
			case GridBagConstraints.SOUTH:		return ALIGN_BOTTOM;
			case GridBagConstraints.SOUTHEAST:	return ALIGN_BOTTOM;
		}
		return ALIGN_DEFAULT;
	}

	//---- TableLayout example ------------------------------------------------

	/**
	 * Demonstrates how to create a panel using TableLayout.
	 */
	private FormModel createTableLayoutExample() {
		FormLayoutManager layout = new FormLayoutManager( TableLayout.class );
		// $columnSpecs specifies the columns and $rowSpecs the rows of the layout
		// in comma separated lists.
		// Syntax: pref|fill|min|<integer>|<double>, ...
		//
		// Use "pref" for TableLayout.PREFERRED, "fill" for TableLayout.FILL,
		// "min" for TableLayout.MINIMUM, a integer >= 1 for pixel size and
		// a double > 0.0 and < 1.0 for percentage.
		// Take a look at the TableLayout documentation for more details.
		layout.setProperty( "$columnSpecs", "pref, fill" ); // mandatory
		layout.setProperty( "$rowSpecs", "pref, pref" ); // mandatory
		layout.setPropertyInt( "hGap", 5 ); // optional; default is 0
		layout.setPropertyInt( "vGap", 5 ); // optional; default is 0

		FormContainer panel = new FormContainer( "javax.swing.JPanel", layout );
		panel.setName( "this" );

		FormComponent nameLabel = new FormComponent( "javax.swing.JLabel" );
		nameLabel.setName( "nameLabel" );
		nameLabel.setProperty( "text", "Name:" );
		panel.add( nameLabel, createTableConstraints( 0, 0 ) );

		FormComponent nameField = new FormComponent( "javax.swing.JTextField" );
		nameField.setName( "nameField" );
		nameField.setPropertyInt( "columns", 20 );
		panel.add( nameField, createTableConstraints( 1, 0 ) );

		FormComponent companyLabel = new FormComponent( "javax.swing.JLabel" );
		companyLabel.setName( "companyLabel" );
		companyLabel.setProperty( "text", "Company:" );
		panel.add( companyLabel, createTableConstraints( 0, 1 ) );

		FormComponent companyField = new FormComponent( "javax.swing.JTextField" );
		companyField.setName( "companyField" );
		companyField.setPropertyInt( "columns", 20 );
		panel.add( companyField, createTableConstraints( 1, 1 ) );

		FormRoot root = new FormRoot();
		root.add( panel, createDesignViewCons( 0, 0, 300, 200 ) );

		FormModel model = new FormModel();
		model.setContentType( "form/swing" );
		model.setRoot( root );
		return model;
	}

	private FormLayoutConstraints createTableConstraints( int gridX, int gridY ) {
		return createTableConstraints( gridX, gridY, 1, 1 );
	}

	private FormLayoutConstraints createTableConstraints( int gridX, int gridY,
		int gridWidth, int gridHeight )
	{
		return createTableConstraints( gridX, gridY, gridWidth, gridHeight,
			ALIGN_DEFAULT, ALIGN_DEFAULT );
	}

	private FormLayoutConstraints createTableConstraints( int gridX, int gridY,
		int gridWidth, int gridHeight, int hAlign, int vAlign )
	{
		FormLayoutConstraints cons = new FormLayoutConstraints( TableConstraints.class );
		cons.setPropertyInt( "gridX", gridX );
		cons.setPropertyInt( "gridY", gridY );
		cons.setPropertyInt( "gridWidth", gridWidth, 1 );
		cons.setPropertyInt( "gridHeight", gridHeight, 1 );
		cons.setPropertyInt( "hAlign", hAlign, ALIGN_DEFAULT );
		cons.setPropertyInt( "vAlign", vAlign, ALIGN_DEFAULT );
		return cons;
	}

	//---- Misc example -------------------------------------------------------

	private FormModel createMiscExample() {
		FormContainer panel = new FormContainer( "javax.swing.JPanel",
			new FormLayoutManager( FlowLayout.class ) );
		panel.setName( "this" );

		//---- This demonstrates how to set mnemonics ----

		FormComponent nameLabel = new FormComponent( "javax.swing.JLabel" );
		nameLabel.setName( "nameLabel" );
		nameLabel.setProperty( "text", "Name:" );
		nameLabel.setPropertyInt( "displayedMnemonic", 'N' );
		nameLabel.setProperty( "labelFor", new FormReference( "nameField" ) );
		panel.add( nameLabel );

		FormComponent nameField = new FormComponent( "javax.swing.JTextField" );
		nameField.setName( "nameField" );
		nameField.setPropertyInt( "columns", 20 );
		panel.add( nameField );


		//---- This demonstrates how to create radio button groups ----

		FormComponent radioButton1 = new FormComponent( "javax.swing.JRadioButton" );
		radioButton1.setName( "radioButton1" );
		radioButton1.setProperty( "text", "Radio Button 1" );
		radioButton1.setProperty( "$buttonGroup", new FormReference( "buttonGroup1" ) );
		panel.add( radioButton1 );

		FormComponent radioButton2 = new FormComponent( "javax.swing.JRadioButton" );
		radioButton2.setName( "radioButton2" );
		radioButton2.setProperty( "text", "Radio Button 2" );
		radioButton2.setProperty( "$buttonGroup", new FormReference( "buttonGroup1" ) );
		panel.add( radioButton2 );

		FormRoot root = new FormRoot();
		root.add( panel, createDesignViewCons( 0, 0, 300, 200 ) );

		// Create button group object and add it to FormModel root. The name of
		// this object must match to the name used in the FormReference above.
		FormNonVisual buttonGroup1 = new FormNonVisual( "javax.swing.ButtonGroup" );
		buttonGroup1.setName( "buttonGroup1" );
		root.add( buttonGroup1 );


		//---- This demonstrates how to create a JTabbedPane ----

		FormContainer tabbedPane = new FormContainer( "javax.swing.JTabbedPane",
			new FormLayoutManager( javax.swing.JTabbedPane.class ) );
		tabbedPane.setName( "tabbedPane" );
		panel.add( tabbedPane );

		FormContainer tab1 = new FormContainer( "javax.swing.JPanel",
			new FormLayoutManager( FlowLayout.class ) );
		tab1.setName( "tab1" );
		FormLayoutConstraints tab1cons = new FormLayoutConstraints( null );
		tab1cons.setProperty( "title", "Tab1 Title" );
		tab1cons.setProperty( "toolTipText", "Tab1 Tool Tip Text" );
		tabbedPane.add( tab1, tab1cons );

		FormContainer tab2 = new FormContainer( "javax.swing.JPanel",
			new FormLayoutManager( FlowLayout.class ) );
		tab2.setName( "tab2" );
		FormLayoutConstraints tab2cons = new FormLayoutConstraints( null );
		tab2cons.setProperty( "title", "Tab2 Title" );
		tab2cons.setProperty( "toolTipText", "Tab2 Tool Tip Text" );
		tabbedPane.add( tab2, tab2cons );


		//---- This demonstrates how to create a JScrollPane ----

		FormContainer scrollPane = new FormContainer( "javax.swing.JScrollPane",
			new FormLayoutManager( JScrollPane.class ) );
		scrollPane.setName( "scroolPane" );
		panel.add( scrollPane );

		FormComponent list = new FormComponent( "javax.swing.JList" );
		list.setName( "list" );
		scrollPane.add( list );


		FormModel model = new FormModel();
		model.setContentType( "form/swing" );
		model.setRoot( root );
		return model;
	}

	// alignment constants for GridBagLayout and TableLayout
	static final int ALIGN_DEFAULT = -1; // maps to FILL
	static final int ALIGN_CENTER	= 0;
	static final int ALIGN_TOP		= 1;
	static final int ALIGN_LEFT		= 2;
	static final int ALIGN_BOTTOM	= 3;
	static final int ALIGN_RIGHT	= 4;
	static final int ALIGN_FILL		= 5;

	//---- utility methods ----------------------------------------------------

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
