/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.security.exception;


import java.util.Locale;


/**
 * Exception to indicate the action requires the user to be authorised.
 */
public class UnauthorisedException extends SecurityException implements AuthenticationError
{

	private static final long serialVersionUID = -8696671605965223582L;

	/**
	 * Create a new Unauthorised Security Exception.
	 *
	 * @param cause
	 *            the underlying cause of the security issue
	 * @param locale
	 *            the locale for the message
	 * @param args
	 *            any additional message arguments
	 */
	public UnauthorisedException( final Throwable cause, final Locale locale, final Object... args )
	{
		super( cause, locale, SecurityMessage.UNAUTHORISED, args );
	}

	/**
	 * Create a new Unauthorised Security Exception.
	 * 
	 * @param locale
	 *            the locale for the message
	 * @param args
	 *            any additional message arguments
	 */
	public UnauthorisedException( final Locale locale, final Object... args )
	{
		super( locale, SecurityMessage.UNAUTHORISED, args );
	}

}
