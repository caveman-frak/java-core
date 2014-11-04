/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
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
public class MapHasEntriesTest
{

	private Map< Long, String > map;
	private StringDescription description;

	@Before
	public final void setUp()
	{
		map = new HashMap<>();
		map.put( 1L, "One" );
		map.put( 2L, "Two" );
		map.put( 10L, "Ten" );

		description = new StringDescription();
	}

	@Test
	public final void testSuccessOneEntry()
	{
		final Matcher< Map< Long, String >> matcher = MapHasEntries.hasEntries( EntryMatcher.entry( 1L, "One" ) );
		assertThat( matcher.matches( map ), is( true ) );

		matcher.describeMismatch( map, description );
		assertThat( description.toString(),
				is( "map was [<1=One>, <2=Two>, <10=Ten>] expected (a collection containing <1L>=\"One\")" ) );
	}

	@Test
	public final void testSuccessTwoEntry()
	{
		final Matcher< Map< Long, String >> matcher = MapHasEntries.hasEntries( EntryMatcher.entry( 1L, "One" ),
				EntryMatcher.entry( 10L, "Ten" ) );
		assertThat( matcher.matches( map ), is( true ) );

		matcher.describeMismatch( map, description );
		assertThat(
				description.toString(),
				is( "map was [<1=One>, <2=Two>, <10=Ten>] expected (a collection containing <1L>=\"One\" and a collection containing <10L>=\"Ten\")" ) );
	}

	@Test
	public final void testSuccessAllEntries()
	{
		final Matcher< Map< Long, String >> matcher = MapHasEntries.hasEntries( EntryMatcher.entry( 1L, "One" ),
				EntryMatcher.entry( 2L, "Two" ), EntryMatcher.entry( 10L, "Ten" ) );
		assertThat( matcher.matches( map ), is( true ) );

		matcher.describeMismatch( map, description );
		assertThat(
				description.toString(),
				is( "map was [<1=One>, <2=Two>, <10=Ten>] expected (a collection containing <1L>=\"One\" and a collection containing <2L>=\"Two\" and a collection containing <10L>=\"Ten\")" ) );
	}

	@Test
	public final void testFailureWrongEntry()
	{
		final Matcher< Map< Long, String >> matcher = MapHasEntries.hasEntries( EntryMatcher.entry( 6L, "Six" ) );
		assertThat( matcher.matches( map ), is( false ) );

		matcher.describeMismatch( map, description );
		assertThat( description.toString(),
				is( "map was [<1=One>, <2=Two>, <10=Ten>] expected (a collection containing <6L>=\"Six\")" ) );
	}

	@Test
	public final void testFailureExtraEntry()
	{
		final Matcher< Map< Long, String >> matcher = MapHasEntries.hasEntries( EntryMatcher.entry( 1L, "One" ),
				EntryMatcher.entry( 6L, "Six" ) );
		assertThat( matcher.matches( map ), is( false ) );

		matcher.describeMismatch( map, description );
		assertThat(
				description.toString(),
				is( "map was [<1=One>, <2=Two>, <10=Ten>] expected (a collection containing <1L>=\"One\" and a collection containing <6L>=\"Six\")" ) );
	}

}
