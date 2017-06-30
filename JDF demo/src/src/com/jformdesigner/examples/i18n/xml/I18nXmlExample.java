/*
 * Copyright (c) 2012 Karl Tauber <karl at jformdesigner dot com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.jformdesigner.examples.i18n.xml;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * Demonstrates how to use XML resource bundles.
 *
 * Requires Java 6 or later.
 */
public class I18nXmlExample extends JPanel {
	public static void main( String[] args ) {
		JOptionPane.showMessageDialog( null, new I18nXmlExample() );
	}

	public I18nXmlExample() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		ResourceBundle bundle = ResourceBundle.getBundle("com.jformdesigner.examples.i18n.xml.I18nXmlExample", new XMLResourceBundle.Control());
		label1 = new JLabel();
		button1 = new JButton();

		//======== this ========
		setLayout(new FlowLayout());

		//---- label1 ----
		label1.setText(bundle.getString("I18nXmlTest.label1.text"));
		add(label1);

		//---- button1 ----
		button1.setText(bundle.getString("I18nXmlTest.button1.text"));
		add(button1);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	protected JLabel label1;
	protected JButton button1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
