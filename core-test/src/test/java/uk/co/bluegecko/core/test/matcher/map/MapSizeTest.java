/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.matcher.map;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class MapSizeTest
{

	private Map< Long, String > map;
	private StringDescription description;

	@Before
	public final void setUp()
	{
		map = new HashMap<>();
		map.put( 1L, "One" );

		description = new StringDescription();
	}

	@Test
	public final void testSuccess()
	{
		final Matcher< Map< ?, ? > > matcher = MapSize.hasMapSize( 1 );
		assertThat( matcher.matches( map ), is( true ) );

		matcher.describeMismatch( 1, description );
		assertThat( description.toString(), is( "was a java.lang.Integer (<1>)" ) );
	}

	@Test
	public final void testFailure()
	{
		final Matcher< Map< ?, ? > > matcher = MapSize.hasMapSize( 2 );
		assertThat( matcher.matches( map ), is( false ) );

		matcher.describeMismatch( 2, description );
		assertThat( description.toString(), is( "was a java.lang.Integer (<2>)" ) );
	}

}
