/*
 * Copyright (C) 2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.beansbinding.simple;

import com.jformdesigner.examples.beansbinding.util.AbstractModelObject;

/**
 * Demo data model class.
 * <p>
 * It has getter and setter methods to access property values and supports
 * property change listeners thru {@link AbstractModelObject}.
 */
public class SimpleData
	extends AbstractModelObject
{
	private String value1;
	private String value2;

	public String getValue1() {
		return value1;
	}

	public void setValue1( String value1 ) {
		String oldValue1 = this.value1;
		this.value1 = value1;
		changeSupport.firePropertyChange( "value1", oldValue1, value1 );
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2( String value2 ) {
		String oldValue2 = this.value2;
		this.value2 = value2;
		changeSupport.firePropertyChange( "value2", oldValue2, value2 );
	}
}
