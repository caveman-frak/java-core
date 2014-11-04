/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.matcher.map;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Test;

import uk.co.bluegecko.core.helper.CollectionHelper;


@SuppressWarnings( "javadoc" )
public class EntryMatcherTest
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
	public final void testSuccessWithValues()
	{
		final Matcher< Map.Entry< Long, String > > matcher = EntryMatcher.entry( 1L, "One" );
		final Map.Entry< Long, String > entry = CollectionHelper.first( map.entrySet() );
		assertThat( matcher.matches( entry ), is( true ) );

		matcher.describeMismatch( entry, description );
		assertThat( description.toString(), is( "was <1L>=\"One\" expected <1L>=\"One\"" ) );
	}

	@Test
	public final void testFailureWithFirstValue()
	{
		final Matcher< Map.Entry< Long, String > > matcher = EntryMatcher.entry( 2L, "One" );
		final Map.Entry< Long, String > entry = CollectionHelper.first( map.entrySet() );
		assertThat( matcher.matches( entry ), is( false ) );

		matcher.describeMismatch( entry, description );
		assertThat( description.toString(), is( "was <1L>=\"One\" expected <2L>=\"One\"" ) );
	}

	@Test
	public final void testFailureWithSecondValue()
	{
		final Matcher< Map.Entry< Long, String > > matcher = EntryMatcher.entry( 1L, "Two" );
		final Map.Entry< Long, String > entry = CollectionHelper.first( map.entrySet() );
		assertThat( matcher.matches( entry ), is( false ) );

		matcher.describeMismatch( entry, description );
		assertThat( description.toString(), is( "was <1L>=\"One\" expected <1L>=\"Two\"" ) );
	}

	@Test
	public final void testFailureWithBothValues()
	{
		final Matcher< Map.Entry< Long, String > > matcher = EntryMatcher.entry( 2L, "Two" );
		final Map.Entry< Long, String > entry = CollectionHelper.first( map.entrySet() );
		assertThat( matcher.matches( entry ), is( false ) );

		matcher.describeMismatch( entry, description );
		assertThat( description.toString(), is( "was <1L>=\"One\" expected <2L>=\"Two\"" ) );
	}

	@Test
	public final void testSuccessWithMatchers()
	{
		final Matcher< Map.Entry< Long, String > > matcher = EntryMatcher.entry( lessThan( 2L ), startsWith( "O" ) );
		final Map.Entry< Long, String > entry = CollectionHelper.first( map.entrySet() );
		assertThat( matcher.matches( entry ), is( true ) );

		matcher.describeMismatch( entry, description );
		assertThat( description.toString(),
				is( "was <1L>=\"One\" expected a value less than <2L>=a string starting with \"O\"" ) );
	}

	@Test
	public final void testFailureWithFirstMatcher()
	{
		final Matcher< Map.Entry< Long, String > > matcher = EntryMatcher.entry( lessThan( 1L ), startsWith( "O" ) );
		final Map.Entry< Long, String > entry = CollectionHelper.first( map.entrySet() );
		assertThat( matcher.matches( entry ), is( false ) );

		matcher.describeMismatch( entry, description );
		assertThat( description.toString(),
				is( "was <1L>=\"One\" expected a value less than <1L>=a string starting with \"O\"" ) );
	}

	@Test
	public final void testFailureWithSecondMatcher()
	{
		final Matcher< Map.Entry< Long, String > > matcher = EntryMatcher.entry( lessThan( 2L ), startsWith( "T" ) );
		final Map.Entry< Long, String > entry = CollectionHelper.first( map.entrySet() );
		assertThat( matcher.matches( entry ), is( false ) );

		matcher.describeMismatch( entry, description );
		assertThat( description.toString(),
				is( "was <1L>=\"One\" expected a value less than <2L>=a string starting with \"T\"" ) );
	}

	@Test
	public final void testFailureWithBothMatchers()
	{
		final Matcher< Map.Entry< Long, String > > matcher = EntryMatcher.entry( lessThan( 1L ), startsWith( "T" ) );
		final Map.Entry< Long, String > entry = CollectionHelper.first( map.entrySet() );
		assertThat( matcher.matches( entry ), is( false ) );

		matcher.describeMismatch( entry, description );
		assertThat( description.toString(),
				is( "was <1L>=\"One\" expected a value less than <1L>=a string starting with \"T\"" ) );
	}

}
