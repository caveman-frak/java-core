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

	private final Enum< ? > messageKey;
	private final Object[] args;

	/**
	 * Create a new runtime exception, build the message string from the message key and args for the passed locale.
	 *
	 * @param cause
	 *            the original cause of the exception
	 * @param messageKey
	 *            the key for the localised message
	 * @param args
	 *            any message arguments
	 */
	protected BaseRuntimeException( final Throwable cause, final Enum< ? > messageKey, final Object... args )
	{
		super( cause );

		this.messageKey = messageKey;
		this.args = args;
	}

	/**
	 * Create a new runtime exception, build the message string from the message key and args for the passed locale.
	 *
	 * @param messageKey
	 *            the key for the localised message
	 * @param args
	 *            any message arguments
	 */
	protected BaseRuntimeException( final Enum< ? > messageKey, final Object... args )
	{
		super( messageKey.name() );

		this.messageKey = messageKey;
		this.args = args;
	}

	/**
	 * Get the raw enumeration for the message key.
	 *
	 * @return the message key
	 */
	public Enum< ? > getMessageKey()
	{
		return messageKey;
	}

	/**
	 * get the raw message arguments.
	 *
	 * @return message arguments
	 */
	public Object[] arguments()
	{
		return args;
	}

	/**
	 * Get the message as a localised string using the passed locale.
	 *
	 * @param locale
	 *            locale to use
	 * @return localised string
	 */
	public String getLocalizedMessage( final Locale locale )
	{
		// uses MessageFormat which does not understand java date time objects, so convert to dates
		final Object[] arguments = ExceptionLocale.updateDateTimeToDate( args );
		return new MessageConveyor( locale ).getMessage( messageKey, arguments );
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	@Override
	public String getLocalizedMessage()
	{
		return getLocalizedMessage( LOCALE );
	}

}
