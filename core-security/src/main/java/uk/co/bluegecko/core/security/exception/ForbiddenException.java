/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.security.exception;


import java.util.Locale;


/**
 * Exception to indicate the action requires the user to have additional permissions.
 */
public class ForbiddenException extends SecurityException implements AuthorisationError
{

	private static final long serialVersionUID = -8696671605965223582L;

	/**
	 * Create a new Forbidden Security Exception.
	 *
	 * @param cause
	 *            the underlying cause of the security issue
	 * @param locale
	 *            the locale for the message
	 * @param args
	 *            any additional message arguments
	 */
	public ForbiddenException( final Throwable cause, final Locale locale, final Object... args )
	{
		super( cause, locale, SecurityMessage.UNAUTHORISED, args );
	}

	/**
	 * Create a new Forbidden Security Exception.
	 *
	 * @param locale
	 *            the locale for the message
	 * @param args
	 *            any additional message arguments
	 */
	public ForbiddenException( final Locale locale, final Object... args )
	{
		super( locale, SecurityMessage.UNAUTHORISED, args );
	}

}
