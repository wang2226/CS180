/*
 * Copyright (C) 2004-2010 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.formmodel;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Map;
import com.jformdesigner.model.*;
import com.jformdesigner.runtime.FormLoader;

/**
 * Loads JFormDesigner form files (*.jfd) and dumps the content.
 * Demonstrates the use of the JFormDesigner form loader API.
 * <p>
 * Use the {@link #main} method to test this example.
 */
public class DumpFormFile
{
	public static void main( String[] args ) {
		if( args.length == 0 ) {
			System.out.println( "Usage: DumpFormFile <jfd-file> ..." );
			return;
		}

		for( String arg : args ) {
			System.out.println( "---- " + arg + " --------------------------------------------" );
			try {
				FormModel model = FormLoader.load( new File( arg ) );
				dumpModel( model );
			} catch( Exception ex ) {
				ex.printStackTrace();
			}
		}
	}

	public static void dumpModel( FormModel model ) {
		FormRoot root = model.getRoot();
		dumpContainer( root, "" );
	}

	public static void dumpContainer( FormContainer container, String indent ) {
		System.out.println( indent + "{" );
		String newIndent = indent + "    ";

		// dump menubar
		FormContainer menuBar = container.getMenuBar();
		if( menuBar != null ) {
			System.out.println( newIndent + "MenuBar:" );
			dumpContainer( menuBar, newIndent );
		}

		// dump layout manager properties
		FormLayoutManager layout = container.getLayout();
		if( layout != null ) {
			System.out.println( newIndent + "layout: " + layout.getLayoutClass() );
			dumpProperties( layout, newIndent );
		}

		// dump children
		for( FormComponent c : container.getComponents() ) {
			dumpComponent( c, newIndent );

			if( c instanceof FormContainer )
				dumpContainer( (FormContainer) c, newIndent );
		}

		if( container instanceof FormRoot )
			dumpBindings( (FormRoot) container, newIndent );

		System.out.println( indent + "}" );
	}

	private static void dumpComponent( FormComponent c, String indent ) {
		// dump component name and class
		System.out.println( indent + c.getName() + " - " + c.getClassName() );

		// dump layout constraints
		FormLayoutConstraints constraints = c.getConstraints();
		if( constraints != null ) {
			String newIndent = indent + "    ";
			System.out.println( newIndent + "constraints: " + constraints.getConstraintsClass() );
			dumpProperties( constraints, newIndent );
		}

		// dump component properties and events
		dumpProperties( c, indent );
		dumpEvents( c, indent );
	}

	private static void dumpProperties( FormObject obj, String indent ) {
		// get all properties of component
		for( Map.Entry<String, Object> entry : obj.properties() ) {
			String name = entry.getKey();
			Object value = entry.getValue();

			System.out.println( indent + "    " + name + " = " + value );

			// dump array values
			if( value != null && value.getClass().isArray() ) {
				int length = Array.getLength( value );
				for( int i = 0; i < length; i++ )
					System.out.println( indent + "        " + i + ": " + Array.get( value, i ) );
			}
		}
	}

	private static void dumpEvents( FormComponent c, String indent ) {
		// get all events of component
		for( FormEvent e : c.getEvents() ) {
			System.out.println( indent + "    " + e.getListener() + "." + e.getListenerMethod() + " --> " + e.getHandler() );
		}
	}

	private static void dumpBindings( FormRoot root, String indent ) {
		if( root.getBindingGroupCount() == 0 )
			return;

		String newIndent = indent + "    ";

		System.out.println( indent + "Bindings {" );
		for( FormBindingGroup bindingGroup : root.getBindingGroups() )
			dumpBindingGroup( bindingGroup, newIndent );
		System.out.println( indent + "}" );
	}

	private static void dumpBindingGroup( FormBindingGroup bindingGroup, String indent ) {
		String newIndent = indent + "    ";
		System.out.println( indent + "bindingGroupClass: " + bindingGroup.getBindingGroupClass() );

		for( FormBinding binding : bindingGroup.getBindings() )
			dumpBinding( binding, newIndent );
	}

	private static void dumpBinding( FormBinding binding, String indent ) {
		System.out.println( indent + "binding:" );
		dumpProperties( binding, indent );
	}
}
