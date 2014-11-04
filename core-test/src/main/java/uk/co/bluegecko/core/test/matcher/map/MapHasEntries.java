/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.matcher.map;


import java.util.Map;
import java.util.Map.Entry;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;


/**
 * Check if the table contains matching entries
 *
 * @param <K>
 *            the type of the map key
 * @param <V>
 *            the type of map value
 */
public class MapHasEntries< K, V > extends TypeSafeMatcher< Map< K, V >>
{

	private final Matcher< Iterable< Entry< K, V >>> matcher;

	/**
	 * Construct a new matcher, checking for the supplied test.
	 *
	 * @param test
	 *            the test to apply to the value
	 * @param entries
	 *            a list of entry matchers
	 */
	protected MapHasEntries( final Matcher< Iterable< Entry< K, V >>> matcher )
	{
		this.matcher = matcher;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo( final Description description )
	{
		matcher.describeTo( description );
	}

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#describeMismatchSafely(java.lang.Object, org.hamcrest.Description)
	 */
	@Override
	public void describeMismatchSafely( final Map< K, V > map, final Description mismatchDescription )
	{
		mismatchDescription.appendText( "map was " ).appendValueList( "[", ", ", "]", map.entrySet() )
		.appendText( " expected " ).appendDescriptionOf( matcher );
	}

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
	 */
	@Override
	protected boolean matchesSafely( final Map< K, V > map )
	{
		return matcher.matches( map.entrySet() );
	}

	/**
	 * Creates a matcher for {@link Map} that matches when consecutive passes over the
	 * examined {@link Map} yield at least one {@link java.util.Map.Entry} that is matched by the corresponding
	 * matcher from the specified <code>itemMatchers</code>. Whilst matching, each traversal of
	 * the examined {@link Map} will stop as soon as a matching item is found.
	 *
	 * @param entryMatchers
	 *            the matchers to apply to items provided by the examined entry set
	 * @return the Matcher
	 */
	@SafeVarargs
	public static final < K, V > Matcher< Map< K, V > > hasEntries( final Matcher< Entry< K, V >>... entryMatchers )
	{
		return new MapHasEntries<>( Matchers.hasItems( entryMatchers ) );
	}

}