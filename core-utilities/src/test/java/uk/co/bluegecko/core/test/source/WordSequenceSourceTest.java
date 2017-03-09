package uk.co.bluegecko.core.test.source;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;


public class WordSequenceSourceTest
{

	private Source< String > source;

	@Before
	public final void setUp()
	{
		source = new WordSequenceSource( 0 );
	}

	@Test
	public void testSequence()
	{
		assertThat( source.next(), is( "Zero" ) );
		assertThat( source.next(), is( "One" ) );
		assertThat( source.next(), is( "Two" ) );
		assertThat( source.next(), is( "Three" ) );
		assertThat( source.next(), is( "Four" ) );
	}

}
