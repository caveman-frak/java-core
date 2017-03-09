package uk.co.bluegecko.core.test.exception;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.test.exception.ThrowableCaptor.capture;
import static uk.co.bluegecko.core.test.exception.ThrowableCaptor.caught;

import org.junit.Test;


public class ThrowableCaptorTest
{

	@Test
	public void testCatchFooThrow()
	{
		final Foo foo = new Foo( false );
		final Throwable caught = capture( foo::foo );

		assertThat( caught, is( not( nullValue() ) ) );
	}

	@Test
	public void testCatchConstructorThrow()
	{
		final Throwable caught = capture( Foo::new );

		assertThat( caught, is( not( nullValue() ) ) );
	}

	@Test
	public void testCatchLastFooThrow()
	{
		final Foo foo = new Foo( false );
		final Throwable caught = capture( foo::foo );

		assertThat( caught(), is( sameInstance( caught ) ) );
	}

	@Test
	public void testCatchFooNoThrow()
	{
		final Foo foo = new Foo( false );
		try
		{
			capture( () -> foo.foo( false ) );

			throw new IllegalStateException( "should not be here" );
		}
		catch ( final AssertionError ex )
		{
			assertThat( ex.getMessage(), is( "failed-to-catch-exception" ) );
		}
	}

	@Test
	public void testCatchBarNoThrow()
	{
		final Foo foo = new Foo( false );
		try
		{
			capture( foo::bar );

			throw new IllegalStateException( "should not be here" );
		}
		catch ( final AssertionError ex )
		{
			assertThat( ex.getMessage(), is( "failed-to-catch-exception" ) );
		}
	}

}