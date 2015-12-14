/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.security.exception;


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
	 * @param args
	 *            any additional message arguments
	 */
	public ForbiddenException( final Throwable cause, final Object... args )
	{
		super( cause, SecurityMessage.UNAUTHORISED, args );
	}

	/**
	 * Create a new Forbidden Security Exception.
	 *
	 * @param args
	 *            any additional message arguments
	 */
	public ForbiddenException( final Object... args )
	{
		super( SecurityMessage.UNAUTHORISED, args );
	}

}
