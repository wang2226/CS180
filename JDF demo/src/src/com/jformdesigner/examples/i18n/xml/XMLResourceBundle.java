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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * A resource bundle that reads XML properties files.
 *
 * Use the nested Control class when invoking
 *   ResourceBundle.getBundle(String baseName, ResourceBundle.Control control)
 * to load XML resource bundles.
 *
 * In JFormDesigner change the "'getBundle' template" preference option to
 *    ResourceBundle.getBundle(${bundleName}, new XMLResourceBundle.Control())
 * See http://www.formdev.com/jformdesigner/doc/ui/preferences/#code_gen_localization
 *
 * Requires Java 6 or later.
 */
public class XMLResourceBundle
	extends ResourceBundle
{
	private final Properties props;

	public XMLResourceBundle( InputStream stream ) throws IOException {
		props = new Properties();
		props.loadFromXML( stream );
	}

	@Override
	protected Object handleGetObject( String key ) {
		if( key == null )
			throw new NullPointerException();
		return props.get( key );
	}

	@Override
	public Enumeration<String> getKeys() {
		throw new IllegalArgumentException();
	}

	//---- class Control ------------------------------------------------------

	public static class Control
		extends ResourceBundle.Control
	{
		@Override
		public List<String> getFormats( String baseName ) {
			if( baseName == null )
				throw new NullPointerException();
			return Arrays.asList( "xml" );
		}

		@Override
		public ResourceBundle newBundle( String baseName, Locale locale,
			String format, ClassLoader loader, boolean reload )
			throws IllegalAccessException, InstantiationException, IOException
		{
			if( !format.equals( "xml" ) )
				throw new IllegalArgumentException( "unknown format: " + format );

			String bundleName = toBundleName( baseName, locale );
			String resourceName = toResourceName( bundleName, "xml" );
			InputStream stream = null;
			if( reload ) {
				URL url = loader.getResource( resourceName );
				if( url != null ) {
					URLConnection connection = url.openConnection();
					if( connection != null ) {
						connection.setUseCaches( false );
						stream = connection.getInputStream();
					}
				}
			} else
				stream = loader.getResourceAsStream( resourceName );

			if( stream == null )
				return null;

			try {
				return new XMLResourceBundle( stream );
			} finally {
				stream.close();
			}
		}
	}
}
