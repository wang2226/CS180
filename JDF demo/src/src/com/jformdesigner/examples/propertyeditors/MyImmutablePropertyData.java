/*
 * Copyright (C) 2006-2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.propertyeditors;

// requires redist/jfd-annotations.jar
import com.jformdesigner.annotations.BeanInfo;

/**
 * This is a immutable custom type used as property values.
 * Immutable means that it has no methods to modify its value.
 * <p>
 * Note that JFormDesigner uses java.beans.XMLEncoder to save forms in .jfd files.
 * XMLEncoder saves only those variables of a class that have public
 * getter and setter methods. Because this class has no setter methods,
 * it is necessary to specify a persistence delegate in the BeanInfo.
 */
@BeanInfo(persistenceConstructorProperties={"value1", "value2"})
public class MyImmutablePropertyData {
	private final String value1;
	private final String value2;

	public MyImmutablePropertyData( String value1, String value2 ) {
		this.value1 = value1;
		this.value2 = value2;
	}

	public String getValue1() {
		return value1;
	}

	public String getValue2() {
		return value2;
	}
}
