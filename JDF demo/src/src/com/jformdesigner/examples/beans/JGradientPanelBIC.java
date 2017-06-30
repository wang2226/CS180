/*
 * Copyright (C) 2004-2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.beans;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * A panel that paints a gradient background from "background" color to
 * "background2" color to the specified direction using the specified magnitude.
 * <p>
 * This class is equal to {@link JGradientPanel} but uses a traditional
 * BeanInfo class (BIC) {@link JGradientPanelBICBeanInfo} instead of annotations.
 * <p>
 * Demonstrates the implementation of a BeanInfo class that uses
 * JFormDesigner BeanDescriptor and PropertyDescriptor Attributes.
 * http://www.formdev.com/jformdesigner/doc/java-beans/#beandesc_attrs
 * http://www.formdev.com/jformdesigner/doc/java-beans/#propdesc_attrs
 */
public class JGradientPanelBIC
	extends JPanel
	implements SwingConstants
{
	private Color background2;
	private int direction = SOUTH;
	private int magnitude = 100;

	public JGradientPanelBIC() {
	}

	public Color getBackground2() {
		return background2;
	}

	public void setBackground2( Color background2 ) {
		this.background2 = background2;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection( int direction ) {
		this.direction = direction;
	}

	public int getMagnitude() {
		return magnitude;
	}

	public void setMagnitude( int magnitude ) {
		this.magnitude = magnitude;
	}

	@Override
	protected void paintComponent( Graphics g ) {
		Color background1 = getBackground();

		if( !isOpaque() || background2 == null || background2.equals( background1 ) ) {
			super.paintComponent( g );
			return;
		}

		int width = getWidth();
		int height = getHeight();
		int magWidth = width * magnitude / 100;;
		int magHeight = height * magnitude / 100;
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;

		switch( direction ) {
			case NORTH: // south --> north
				y1 = height;
				y2 = y1 - magHeight;
				break;

			case NORTH_EAST: // south-west --> north-east
				y1 = height;
				y2 = y1 - magHeight;
				x2 = magWidth;
				break;

			case EAST: // west --> east
				x2 = magWidth;
				break;

			case SOUTH_EAST: // north-west --> south-east
				x2 = magWidth;
				y2 = magHeight;
				break;

			case SOUTH: // north --> south
			default:
				y2 = magHeight;
				break;

			case SOUTH_WEST: // north-east --> south-west
				x1 = width;
				x2 = x1 - magWidth;
				y2 = magHeight;
				break;

			case WEST: // east --> west
				x1 = width;
				x2 = x1 - magWidth;
				break;

			case NORTH_WEST: // south-east --> north-west
				x1 = width;
				y1 = height;
				x2 = x1 - magWidth;
				y2 = y1 - magHeight;
				break;
		}

		GradientPaint gp = new GradientPaint( x1, y1, background1, x2, y2, background2 );
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint( gp );
		g2.fillRect( 0, 0, width, height );
	}
}
