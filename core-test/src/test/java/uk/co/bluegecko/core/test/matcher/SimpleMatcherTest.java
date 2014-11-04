/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.matcher;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class SimpleMatcherTest
{

	private Matcher< String > matcher;

	private Description description;

	@Before
	public final void setUp()
	{
		matcher = SimpleMatcher.does( s -> s.startsWith( "Hello" ), "string starting with \"Hello\"" );

		description = new StringDescription();
	}

	@Test
	public final void testSuccess()
	{
		final String string = "Hello World!";
		assertThat( matcher.matches( string ), is( true ) );

		matcher.describeMismatch( string, description );
		assertThat( description.toString(), is( "was \"Hello World!\" expected string starting with \"Hello\"" ) );
	}

	@Test
	public final void testFailure()
	{
		final String string = "Good Bye World!";
		assertThat( matcher.matches( string ), is( false ) );

		matcher.describeMismatch( string, description );
		assertThat( description.toString(), is( "was \"Good Bye World!\" expected string starting with \"Hello\"" ) );
	}

}
