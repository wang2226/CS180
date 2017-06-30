/*
 * Copyright (C) 2006-2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.propertyeditors;

/**
 * This is a (mutable) custom type used as property values.
 * Mutable means that it has setter methods to modify its value.
 * <p>
 * Note that JFormDesigner uses java.beans.XMLEncoder to save forms in .jfd files.
 * XMLEncoder saves only those variables of this class that have public
 * getter and setter methods.
 */
public class MyPropertyData {
	private String value1;
	private String value2;

	public MyPropertyData() {
	}

	public MyPropertyData( String value1, String value2 ) {
		this.value1 = value1;
		this.value2 = value2;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1( String value1 ) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2( String value2 ) {
		this.value2 = value2;
	}
}
