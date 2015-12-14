/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.exceptions;


import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.time.Month;

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
			throw new TestExceptionOne( Message.ONE );
		}
		catch ( final BaseException ex )
		{
			assertThat( ex, is( instanceOf( TestExceptionOne.class ) ) );
			assertThat( ex.getCause(), is( nullValue() ) );
			assertThat( ex.getMessage(), is( "ONE" ) );
			assertThat( ex.getMessageKey(), is( Message.ONE ) );
			assertThat( ex.getArguments(), emptyArray() );
			assertThat( ex.getLocalizedMessage(), is( "One Exception" ) );
		}
	}

	@Test
	public final void testCtorCauseAndMessage()
	{
		try
		{
			throw new TestExceptionOne( new NullPointerException( "Null Pointer" ), Message.THREE,
					LocalDateTime.of( 2000, Month.JANUARY, 1, 15, 30 ) );
		}
		catch ( final BaseException ex )
		{
			assertThat( ex, is( instanceOf( TestExceptionOne.class ) ) );
			assertThat( ex.getCause(), is( instanceOf( NullPointerException.class ) ) );
			assertThat( ex.getCause().getMessage(), is( "Null Pointer" ) );
			assertThat( ex.getMessage(), is( "java.lang.NullPointerException: Null Pointer" ) );
			assertThat( ex.getMessageKey(), is( Message.THREE ) );
			assertThat( ex.getArguments(), arrayContaining( LocalDateTime.of( 2000, Month.JANUARY, 1, 15, 30 ) ) );
			assertThat( ex.getLocalizedMessage(), is( "Three Exception at 15:30" ) );
		}
	}

}
