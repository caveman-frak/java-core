/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.security.exception;


import java.util.Locale;

import uk.co.bluegecko.core.exceptions.BaseRuntimeException;


/**
 * Exception to indicate a security problem.
 */
public class SecurityException extends BaseRuntimeException implements SecurityError
{

	private static final long serialVersionUID = -8696671605965223582L;

	/**
	 * Create a new generic Security Exception.
	 *
	 * @param cause
	 *            the underlying cause of the security issue
	 * @param locale
	 *            the locale for the message
	 * @param messageKey
	 *            informational message about the security issue
	 * @param args
	 *            any additional message arguments
	 */
	public SecurityException( final Throwable cause, final Locale locale, final SecurityMessage messageKey,
			final Object... args )
	{
		super( cause, locale, messageKey, args );
	}

	/**
	 * Create a new generic Security Exception.
	 * 
	 * @param locale
	 *            the locale for the message
	 * @param messageKey
	 *            informational message about the security issue
	 * @param args
	 *            any additional message arguments
	 */
	public SecurityException( final Locale locale, final SecurityMessage messageKey, final Object... args )
	{
		super( locale, messageKey, args );
	}

}
