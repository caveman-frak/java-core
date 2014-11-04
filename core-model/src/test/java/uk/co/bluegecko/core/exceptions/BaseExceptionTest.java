/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.exceptions;


import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.Locale;

import org.junit.Test;

import uk.co.bluegecko.core.exceptions.TestExceptionOne.Message;
import uk.co.bluegecko.core.test.harness.TestHarness;


@SuppressWarnings( "javadoc" )
public class BaseExceptionTest extends TestHarness
{

	@Test
	public final void testMessages()
	{
		verifyLocalisation( TestExceptionOne.Message.class );
	}

	@Test
	public final void testCtorMessage()
	{
		try
		{
			throw new TestExceptionOne( Locale.US, Message.ONE );
		}
		catch ( final Exception ex )
		{
			assertThat( ex, is( instanceOf( TestExceptionOne.class ) ) );
			assertThat( ex.getCause(), is( nullValue() ) );
			assertThat( ex.getMessage(), is( "One Exception" ) );
			assertThat( ex.getLocalizedMessage(), is( "One Exception" ) );
		}
	}

	@Test
	public final void testCtorCause()
	{
		try
		{
			throw new TestExceptionOne( new NullPointerException( "Null Pointer" ) );
		}
		catch ( final Exception ex )
		{
			assertThat( ex, is( instanceOf( TestExceptionOne.class ) ) );
			assertThat( ex.getCause(), is( instanceOf( NullPointerException.class ) ) );
			assertThat( ex.getCause().getMessage(), is( "Null Pointer" ) );
			assertThat( ex.getMessage(), is( "java.lang.NullPointerException: Null Pointer" ) );
			assertThat( ex.getLocalizedMessage(), is( "java.lang.NullPointerException: Null Pointer" ) );
		}
	}

	@SuppressWarnings( "deprecation" )
	@Test
	public final void testCtorCauseAndMessage()
	{
		try
		{
			throw new TestExceptionOne( new NullPointerException( "Null Pointer" ), Locale.UK, Message.THREE, new Date(
					2000, 0, 1, 15, 30 ) );
		}
		catch ( final Exception ex )
		{
			assertThat( ex, is( instanceOf( TestExceptionOne.class ) ) );
			assertThat( ex.getCause(), is( instanceOf( NullPointerException.class ) ) );
			assertThat( ex.getCause().getMessage(), is( "Null Pointer" ) );
			assertThat( ex.getMessage(), is( "Three Exception at 15:30" ) );
			assertThat( ex.getLocalizedMessage(), is( "Three Exception at 15:30" ) );
		}
	}

}
