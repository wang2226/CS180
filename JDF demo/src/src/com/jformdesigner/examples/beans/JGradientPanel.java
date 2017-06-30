/*
 * Copyright (C) 2004-2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.beans;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

// requires redist/jfd-annotations.jar
import com.jformdesigner.annotations.BeanInfo;
import com.jformdesigner.annotations.BeanInfo.Category;
import com.jformdesigner.annotations.PropertyDesc;
import com.jformdesigner.annotations.PropertyDesc.Enum;

/**
 * A panel that paints a gradient background from "background" color to
 * "background2" color to the specified direction using the specified magnitude.
 * <p>
 * Demonstrates the use of JFormDesigner annotations.
 * http://www.formdev.com/jformdesigner/doc/annotations/
 */
@BeanInfo(isContainer=true, categories={
	@Category(name="Gradient Properties",
		properties={"background", "background2", "direction", "magnitude"})
})
public class JGradientPanel
	extends JPanel
	implements SwingConstants
{
	private Color background2;
	private int direction = SOUTH;
	private int magnitude = 100;

	public JGradientPanel() {
	}

	@PropertyDesc(preferred=true)
	public Color getBackground2() {
		return background2;
	}

	public void setBackground2( Color background2 ) {
		this.background2 = background2;
	}

	@PropertyDesc(displayName="direction (to)", preferred=true, enumValues={
		@Enum(name="NORTH",			intValue=SwingConstants.NORTH,		code="SwingConstants.NORTH"),
		@Enum(name="NORTH_EAST",	intValue=SwingConstants.NORTH_EAST,	code="SwingConstants.NORTH_EAST"),
		@Enum(name="EAST",			intValue=SwingConstants.EAST,		code="SwingConstants.EAST"),
		@Enum(name="SOUTH_EAST",	intValue=SwingConstants.SOUTH_EAST,	code="SwingConstants.SOUTH_EAST"),
		@Enum(name="SOUTH",			intValue=SwingConstants.SOUTH,		code="SwingConstants.SOUTH"),
		@Enum(name="SOUTH_WEST",	intValue=SwingConstants.SOUTH_WEST,	code="SwingConstants.SOUTH_WEST"),
		@Enum(name="WEST",			intValue=SwingConstants.WEST,		code="SwingConstants.WEST"),
		@Enum(name="NORTH_WEST",	intValue=SwingConstants.NORTH_WEST,	code="SwingConstants.NORTH_WEST")
	}, imports=SwingConstants.class)
	public int getDirection() {
		return direction;
	}

	public void setDirection( int direction ) {
		this.direction = direction;
	}

	@PropertyDesc(displayName="magnitude (in %)", preferred=true)
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
