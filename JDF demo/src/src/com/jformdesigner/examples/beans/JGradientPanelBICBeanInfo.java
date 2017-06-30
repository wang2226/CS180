/*
 * Copyright (C) 2004-2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.beans;

import java.beans.*;
import javax.swing.SwingConstants;

/**
 * BeanInfo for JGradientPanelBIC.
 */
public class JGradientPanelBICBeanInfo
	extends SimpleBeanInfo
{
	@Override
	public BeanDescriptor getBeanDescriptor() {
		BeanDescriptor beanDesc = new BeanDescriptor( JGradientPanelBIC.class );
		beanDesc.setValue( "isContainer", Boolean.TRUE );
		return beanDesc;
	}

	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		try {
			PropertyDescriptor background2 = new PropertyDescriptor( "background2", JGradientPanelBIC.class );
			background2.setPreferred( true );
			background2.setValue( "category", "Gradient Properties" );

			PropertyDescriptor direction = new PropertyDescriptor( "direction", JGradientPanelBIC.class );
			direction.setDisplayName( "direction (to)" );
			direction.setPreferred( true );
			direction.setValue( "category", "Gradient Properties" );
			direction.setValue( "enumerationValues", new Object[] {
				"NORTH",		SwingConstants.NORTH,		"SwingConstants.NORTH",
				"NORTH_EAST",	SwingConstants.NORTH_EAST,	"SwingConstants.NORTH_EAST",
				"EAST",			SwingConstants.EAST,		"SwingConstants.EAST",
				"SOUTH_EAST",	SwingConstants.SOUTH_EAST,	"SwingConstants.SOUTH_EAST",
				"SOUTH",		SwingConstants.SOUTH,		"SwingConstants.SOUTH",
				"SOUTH_WEST",	SwingConstants.SOUTH_WEST,	"SwingConstants.SOUTH_WEST",
				"WEST",			SwingConstants.WEST,		"SwingConstants.WEST",
				"NORTH_WEST",	SwingConstants.NORTH_WEST,	"SwingConstants.NORTH_WEST",
			} );
			direction.setValue( "imports", SwingConstants.class.getName() );

			PropertyDescriptor magnitude = new PropertyDescriptor( "magnitude", JGradientPanelBIC.class );
			magnitude.setDisplayName( "magnitude (in %)" );
			magnitude.setPreferred( true );
			magnitude.setValue( "category", "Gradient Properties" );

			return new PropertyDescriptor[] {
				background2,
				direction,
				magnitude,
			};
		} catch( IntrospectionException ex ) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public BeanInfo[] getAdditionalBeanInfo() {
		try {
			return new BeanInfo[] {
				Introspector.getBeanInfo( JGradientPanelBIC.class.getSuperclass() )
			};
		} catch( IntrospectionException e ) {
			return null;
		}
	}
}
