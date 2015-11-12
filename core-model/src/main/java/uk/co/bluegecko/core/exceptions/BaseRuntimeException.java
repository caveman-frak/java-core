/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.exceptions;


import java.util.Locale;

import ch.qos.cal10n.MessageConveyor;


/**
 * RuntimeException that uses Enums to created localised message details.
 */
public abstract class BaseRuntimeException extends RuntimeException implements ExceptionLocale
{

	private static final long serialVersionUID = -2796448640823556346L;

	/**
	 * Create a new runtime exception, build the message string from the message key and args for the passed locale.
	 *
	 * @param cause
	 *            the original cause of the exception
	 * @param locale
	 *            the locale for the message
	 * @param messageKey
	 *            the key for the localised message
	 * @param args
	 *            any message arguments
	 */
	protected BaseRuntimeException( final Throwable cause, final Locale locale, final Enum< ? > messageKey,
			final Object... args )
	{
		super( new MessageConveyor( locale ).getMessage( messageKey, args ), cause );
	}

	/**
	 * Create a new runtime exception, the message will be derived from the cause.
	 *
	 * @param cause
	 *            the original cause of the exception
	 */
	protected BaseRuntimeException( final Throwable cause )
	{
		super( cause );
	}

	/**
	 * Create a new runtime exception, build the message string from the message key and args for the passed locale.
	 *
	 * @param locale
	 *            the locale for the message
	 * @param messageKey
	 *            the key for the localised message
	 * @param args
	 *            any message arguments
	 */
	protected BaseRuntimeException( final Locale locale, final Enum< ? > messageKey, final Object... args )
	{
		super( new MessageConveyor( locale ).getMessage( messageKey, args ) );
	}

}
