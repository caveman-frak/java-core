/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.exceptions;


import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.LocaleData;


public class TestExceptionOne extends BaseException
{

	private static final long serialVersionUID = -8696671605965223582L;

	@BaseName( "uk.co.bluegecko.core.exceptions.TestExceptionOne$Message" )
	@LocaleData(
		{ @ch.qos.cal10n.Locale( "en" ) } )
	public enum Message
	{
		ONE, TWO, THREE
	}

	public TestExceptionOne( final Throwable cause, final Message messageKey, final Object... args )
	{
		super( cause, messageKey, args );
	}

	public TestExceptionOne( final Message messageKey, final Object... args )
	{
		super( messageKey, args );
	}

}
