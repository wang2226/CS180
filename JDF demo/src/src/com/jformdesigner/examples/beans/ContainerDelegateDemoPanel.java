/*
 * Copyright (C) 2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.beans;

import java.awt.*;
import javax.swing.*;

// requires redist/jfd-annotations.jar
import com.jformdesigner.annotations.BeanInfo;

/**
 * A panel that demonstrates the use of container delegates.
 * It has own components and a {@link #contentPane}, where you can add more
 * components in JFormDesigner.
 * <p>
 * To test this panel, create a form and use "Choose Bean" to add this panel
 * to the form. You'll see the components of this panel and can add additional
 * components (in JFormDesigner) to the {@link #contentPane}.
 */
@BeanInfo(isContainer=true, containerDelegate="getContentPane")
public class ContainerDelegateDemoPanel
	extends JPanel
{
	// JFormDesigner adds children to this panel
	private final JPanel contentPane = new JPanel();

	public ContainerDelegateDemoPanel() {
	    super(new BorderLayout());
	    add(contentPane);
	    add(new JLabel("north"), BorderLayout.NORTH);
	    add(new JLabel("west"), BorderLayout.WEST);
	}

	/**
	 * Used by JFormDesigner. See @BeanInfo annotation of this class.
	 */
	public Container getContentPane() {
	    return contentPane;
	}
}
