/*
 * Copyright (C) 2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.beans;

import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JPanel;

// requires redist/jfd-annotations.jar
import com.jformdesigner.annotations.BeanInfo;
import com.jformdesigner.annotations.PropertyDesc;

/**
 * A panel that paints an image on the background.
 * <p>
 * Demonstrates the use of JFormDesigner annotations.
 * http://www.formdev.com/jformdesigner/doc/annotations/
 */
@BeanInfo(isContainer=true)
public class JBackgroundImagePanel
	extends JPanel
{
	private Icon backgroundImage;

	@PropertyDesc(preferred=true)
	public Icon getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage( Icon backgroundImage ) {
		this.backgroundImage = backgroundImage;
		repaint();
	}

	@Override
	protected void paintComponent( Graphics g ) {
		super.paintComponent(g);

		if( backgroundImage != null )
			backgroundImage.paintIcon( this, g, 0, 0 );
	}
}
