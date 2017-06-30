/*
 * Copyright (C) 2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.beans;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// requires redist/jfd-annotations.jar
import com.jformdesigner.annotations.BeanInfo;

/**
 * A base dialog that can be used as superclass for own dialogs.
 * It includes a button bar (OK, Cancel and Help),
 * adds a listener for the ESC key that closes the dialog and
 * handles closing from window manager (e.g. 'x' in window title).
 */
@BeanInfo(containerDelegate="getContentPanel")
public class JBaseDialog
	extends JDialog
{
	public static final int OK_ID = 0;
	public static final int CANCEL_ID = 1;

	private int returnCode = OK_ID;

	public JBaseDialog(Frame owner) {
		super(owner);
		initComponents();
		init();
	}

	public JBaseDialog(Dialog owner) {
		super(owner);
		initComponents();
		init();
	}

	private void init() {
		helpButton.setVisible( false );

		getRootPane().setDefaultButton( okButton );

		ActionListener listener = new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				Object source = e.getSource();
				if( source == okButton )
					close( OK_ID );
				else if( source == cancelButton || source == getContentPane() )
					close( CANCEL_ID );
				else if( source == helpButton )
					showHelp();
			}
		};

		okButton.addActionListener( listener );
		cancelButton.addActionListener( listener );
		helpButton.addActionListener( listener );

		// register Help and F1 keys
		ActionListener helpAction = new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				showHelp();
			}
		};
		((JComponent)getContentPane()).registerKeyboardAction( helpAction,
				KeyStroke.getKeyStroke( KeyEvent.VK_HELP, 0 ),
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT );
		((JComponent)getContentPane()).registerKeyboardAction( helpAction,
				KeyStroke.getKeyStroke( KeyEvent.VK_F1, 0 ),
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT );

		// register ESC to cancel dialog
		((JComponent)getContentPane()).registerKeyboardAction(
				listener,
				KeyStroke.getKeyStroke( KeyEvent.VK_ESCAPE, 0, false ),
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT );

		// handle closing from window manager (e.g. 'x' in window title)
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing( WindowEvent e ) {
				close( CANCEL_ID );
			}
		} );
	}

	public boolean isHelpVisible() {
		return (helpButton != null) ? helpButton.isVisible() : false;
	}

	public void setHelpVisible( boolean visible ) {
		helpButton.setVisible( visible );
	}

	/**
	 * Opens the dialog, waits, and returns the clicked button ID.
	 *
	 * @return {@link #OK_ID} or {@link #CANCEL_ID}
	 */
	public int open() {
		setVisible( true );
		return getReturnCode();
	}

	/**
	 * Closes the dialog and sets the return code.
	 */
	public void close( int returnCode ) {
		this.returnCode = returnCode;
		setVisible( false );
		dispose();
	}

	/**
	 * Returns the return code of the dialog.
	 *
	 * @return {@link #OK_ID} or {@link #CANCEL_ID}
	 */
	public int getReturnCode() {
		return returnCode;
	}

	public void showHelp() {
		System.out.println("help"); // todo
	}

	/**
	 * Used by JFormDesigner. See @BeanInfo annotation of this class.
	 */
	public JPanel getContentPanel() {
		return contentPanel;
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		buttonBar = new JPanel();
		helpButton = new JButton();
		okButton = new JButton();
		cancelButton = new JButton();

		//======== this ========
		setModal(true);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new BorderLayout());
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {85, 0, 85, 80};
				((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 0.0};

				//---- helpButton ----
				helpButton.setText("Help");
				buttonBar.add(helpButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

				//---- okButton ----
				okButton.setText("OK");
				buttonBar.add(okButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

				//---- cancelButton ----
				cancelButton.setText("Cancel");
				buttonBar.add(cancelButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JPanel buttonBar;
	private JButton helpButton;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
