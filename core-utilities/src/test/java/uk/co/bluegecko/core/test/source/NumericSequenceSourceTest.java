package uk.co.bluegecko.core.test.source;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;


public class NumericSequenceSourceTest
{

	private Source< Integer > source;

	@Before
	public final void setUp()
	{
		source = new NumericSequenceSource( 0 );
	}

	@Test
	public void testSequence()
	{
		assertThat( source.next(), is( 0 ) );
		assertThat( source.next(), is( 1 ) );
		assertThat( source.next(), is( 2 ) );
		assertThat( source.next(), is( 3 ) );
		assertThat( source.next(), is( 4 ) );
	}

}
