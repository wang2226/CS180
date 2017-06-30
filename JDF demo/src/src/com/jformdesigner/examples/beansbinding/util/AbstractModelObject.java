/*
 * Copyright (C) 2011 Karl Tauber <karl at jformdesigner dot com>
 * All Rights Reserved
 */

package com.jformdesigner.examples.beansbinding.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Abstract base class for model objects with support for bound properties.
 * Bound properties fire property change events when changed.
 * The Beans Binding library invokes {@link #addPropertyChangeListener} and
 * {@link #removePropertyChangeListener} and listens to property changes.
 */
public class AbstractModelObject {

	protected final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}
}
