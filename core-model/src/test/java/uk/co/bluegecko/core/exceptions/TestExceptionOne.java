/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.exceptions;


import java.util.Locale;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.LocaleData;


/**
 * Test Exception
 */
public class TestExceptionOne extends BaseException
{

	private static final long serialVersionUID = -8696671605965223582L;

	/**
	 *
	 */
	@BaseName( "uk.co.bluegecko.core.exceptions.TestExceptionOne$Message" )
	@LocaleData(
		{ @ch.qos.cal10n.Locale( "en" ) } )
	@SuppressWarnings( "javadoc" )
	public enum Message
	{
		ONE, TWO, THREE
	}

	/**
	 * @param cause
	 * @param locale
	 * @param messageKey
	 * @param args
	 */
	public TestExceptionOne( final Throwable cause, final Locale locale, final Message messageKey, final Object... args )
	{
		super( cause, locale, messageKey, args );
	}

	/**
	 * @param cause
	 */
	public TestExceptionOne( final Throwable cause )
	{
		super( cause );
	}

	/**
	 * @param locale
	 * @param messageKey
	 * @param args
	 */
	public TestExceptionOne( final Locale locale, final Message messageKey, final Object... args )
	{
		super( locale, messageKey, args );
	}

}
