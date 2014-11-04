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
public class MapContainsTest
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
	public final void testContainsSuccess()
	{
		final Matcher< Map< Long, String >> matcher = MapContains.contains( EntryMatcher.entry( 1L, "One" ),
				EntryMatcher.entry( 2L, "Two" ), EntryMatcher.entry( 10L, "Ten" ) );
		assertThat( matcher.matches( map ), is( true ) );

		matcher.describeMismatch( map, description );
		assertThat(
				description.toString(),
				is( "map was [<1=One>, <2=Two>, <10=Ten>] expected iterable containing [<1L>=\"One\", <2L>=\"Two\", <10L>=\"Ten\"]" ) );
	}

	@Test
	public final void testContainsFailWrongOrder()
	{
		final Matcher< Map< Long, String >> matcher = MapContains.contains( EntryMatcher.entry( 2L, "Two" ),
				EntryMatcher.entry( 1L, "One" ), EntryMatcher.entry( 10L, "Ten" ) );
		assertThat( matcher.matches( map ), is( false ) );

		matcher.describeMismatch( map, description );
		assertThat(
				description.toString(),
				is( "map was [<1=One>, <2=Two>, <10=Ten>] expected iterable containing [<2L>=\"Two\", <1L>=\"One\", <10L>=\"Ten\"]" ) );
	}

	@Test
	public final void testContainsFailMissingEntry()
	{
		final Matcher< Map< Long, String >> matcher = MapContains.contains( EntryMatcher.entry( 1L, "One" ),
				EntryMatcher.entry( 10L, "Ten" ) );
		assertThat( matcher.matches( map ), is( false ) );

		matcher.describeMismatch( map, description );
		assertThat( description.toString(),
				is( "map was [<1=One>, <2=Two>, <10=Ten>] expected iterable containing [<1L>=\"One\", <10L>=\"Ten\"]" ) );
	}

	@Test
	public final void testContainsFailExtraEntry()
	{
		final Matcher< Map< Long, String >> matcher = MapContains.contains( EntryMatcher.entry( 1L, "One" ),
				EntryMatcher.entry( 2L, "Two" ), EntryMatcher.entry( 6L, "Six" ), EntryMatcher.entry( 10L, "Ten" ) );
		assertThat( matcher.matches( map ), is( false ) );

		matcher.describeMismatch( map, description );
		assertThat(
				description.toString(),
				is( "map was [<1=One>, <2=Two>, <10=Ten>] expected iterable containing [<1L>=\"One\", <2L>=\"Two\", <6L>=\"Six\", <10L>=\"Ten\"]" ) );
	}

	@Test
	public final void testContainsFailWrongEntry()
	{
		final Matcher< Map< Long, String >> matcher = MapContains.contains( EntryMatcher.entry( 1L, "One" ),
				EntryMatcher.entry( 2L, "Two" ), EntryMatcher.entry( 6L, "Six" ) );
		assertThat( matcher.matches( map ), is( false ) );

		matcher.describeMismatch( map, description );
		assertThat(
				description.toString(),
				is( "map was [<1=One>, <2=Two>, <10=Ten>] expected iterable containing [<1L>=\"One\", <2L>=\"Two\", <6L>=\"Six\"]" ) );
	}

	@Test
	public final void testContainsInAnyOrderSuccess()
	{
		final Matcher< Map< Long, String >> matcher = MapContains.containsInAnyOrder( EntryMatcher.entry( 1L, "One" ),
				EntryMatcher.entry( 2L, "Two" ), EntryMatcher.entry( 10L, "Ten" ) );
		assertThat( matcher.matches( map ), is( true ) );

		matcher.describeMismatch( map, description );
		assertThat(
				description.toString(),
				is( "map was [<1=One>, <2=Two>, <10=Ten>] expected iterable over [<1L>=\"One\", <2L>=\"Two\", <10L>=\"Ten\"] in any order" ) );
	}

	@Test
	public final void testContainsInAnyOrderSuccessWrongOrder()
	{
		final Matcher< Map< Long, String >> matcher = MapContains.containsInAnyOrder( EntryMatcher.entry( 2L, "Two" ),
				EntryMatcher.entry( 1L, "One" ), EntryMatcher.entry( 10L, "Ten" ) );
		assertThat( matcher.matches( map ), is( true ) );

		matcher.describeMismatch( map, description );
		assertThat(
				description.toString(),
				is( "map was [<1=One>, <2=Two>, <10=Ten>] expected iterable over [<2L>=\"Two\", <1L>=\"One\", <10L>=\"Ten\"] in any order" ) );
	}

	@Test
	public final void testContainsInAnyOrderFailMissingEntry()
	{
		final Matcher< Map< Long, String >> matcher = MapContains.containsInAnyOrder( EntryMatcher.entry( 1L, "One" ),
				EntryMatcher.entry( 10L, "Ten" ) );
		assertThat( matcher.matches( map ), is( false ) );

		matcher.describeMismatch( map, description );
		assertThat(
				description.toString(),
				is( "map was [<1=One>, <2=Two>, <10=Ten>] expected iterable over [<1L>=\"One\", <10L>=\"Ten\"] in any order" ) );
	}

	@Test
	public final void testContainsInAnyOrderFailExtraEntry()
	{
		final Matcher< Map< Long, String >> matcher = MapContains.containsInAnyOrder( EntryMatcher.entry( 1L, "One" ),
				EntryMatcher.entry( 2L, "Two" ), EntryMatcher.entry( 6L, "Six" ), EntryMatcher.entry( 10L, "Ten" ) );
		assertThat( matcher.matches( map ), is( false ) );

		matcher.describeMismatch( map, description );
		assertThat(
				description.toString(),
				is( "map was [<1=One>, <2=Two>, <10=Ten>] expected iterable over [<1L>=\"One\", <2L>=\"Two\", <6L>=\"Six\", <10L>=\"Ten\"] in any order" ) );
	}

	@Test
	public final void testContainsInAnyOrderFailWrongEntry()
	{
		final Matcher< Map< Long, String >> matcher = MapContains.containsInAnyOrder( EntryMatcher.entry( 1L, "One" ),
				EntryMatcher.entry( 2L, "Two" ), EntryMatcher.entry( 6L, "Six" ) );
		assertThat( matcher.matches( map ), is( false ) );

		matcher.describeMismatch( map, description );
		assertThat(
				description.toString(),
				is( "map was [<1=One>, <2=Two>, <10=Ten>] expected iterable over [<1L>=\"One\", <2L>=\"Two\", <6L>=\"Six\"] in any order" ) );
	}
}
