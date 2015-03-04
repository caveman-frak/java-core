/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.helper;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.helper.Utility.first;
import static uk.co.bluegecko.core.helper.Utility.item;
import static uk.co.bluegecko.core.helper.Utility.last;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class UtilityTest
{

	private List< Integer > list;

	@Before
	public final void setUp()
	{
		list = Arrays.asList( new Integer[]
			{ 1, 2, 3, 4, 5 } );
	}

	@Test
	public final void testFirst()
	{
		assertThat( first( list ), is( 1 ) );
	}

	@Test
	public final void testLast()
	{
		assertThat( last( list ), is( 5 ) );
	}

	@Test
	public final void testSecond()
	{
		assertThat( item( list, 1 ), is( 2 ) );
	}

	@Test
	public final void testSixth()
	{
		assertThat( item( list, 5 ), is( ( Integer ) null ) );
	}

}
