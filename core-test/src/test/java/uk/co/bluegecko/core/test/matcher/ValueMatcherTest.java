/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.matcher;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Test;


public class ValueMatcherTest
{

	private Matcher< Long > matcher;

	private Description description;

	@Before
	public final void setUp()
	{
		matcher = ValueMatcher.value( "number", "intValue", 10, i -> i.intValue() );

		description = new StringDescription();
	}

	@Test
	public final void testSuccess()
	{
		final Long value = 10L;
		assertThat( matcher.matches( value ), is( true ) );

		matcher.describeMismatch( value, description );
		assertThat( description.toString(), is( "was a number with intValue <10> expected <10>" ) );
	}

	@Test
	public final void testFailure()
	{
		final Long value = 11L;
		assertThat( matcher.matches( value ), is( false ) );

		matcher.describeMismatch( value, description );
		assertThat( description.toString(), is( "was a number with intValue <11> expected <10>" ) );
	}

}
