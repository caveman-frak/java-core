/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.source;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.test.matcher.SimpleMatcher.test;

import org.junit.Before;
import org.junit.Test;


/**
 * Test for {@link uk.co.bluegecko.core.test.source.RandomAlphaNumericSource}
 */
public class RandomAlphaNumericSourceTest
{

	private Source< Character > source;

	/**
	 * Create the source for testing
	 */
	@Before
	public final void setUp()
	{
		source = new RandomAlphaNumericSource();
	}

	/**
	 * Test method for {@link uk.co.bluegecko.core.test.source.RandomAlphaNumericSource#next()}.
	 */
	@Test
	public final void testNext()
	{
		for ( int i = 0; i < 1000; i++ )
		{
			assertThat( source.next(),
					is( test( ( final Character ch ) -> Character.isLetterOrDigit( ch ), "letter or digit" ) ) );
		}
	}
}
