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
public class MapContains< K, V > extends TypeSafeMatcher< Map< K, V >>
{

	private final Matcher< Iterable< ? extends Entry< K, V >>> matcher;

	/**
	 * Construct a new matcher, checking for the supplied test.
	 *
	 * @param test
	 *            the test to apply to the value
	 * @param entries
	 *            a list of entry matchers
	 */
	protected MapContains( final Matcher< Iterable< ? extends Entry< K, V >>> matcher )
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
	 * Creates a matcher for {@link Map} that matches when a single pass over the
	 * examined {@link Map} yields a series of {@link java.util.Map.Entry}s, each satisfying
	 * the corresponding matcher in the specified matchers. For a positive match, the examined
	 * entry set must be of the same length as the number of specified matchers.
	 *
	 * @param entryMatchers
	 *            the matchers to apply to items provided by the examined entry set
	 * @return the Matcher
	 */
	@SafeVarargs
	public static final < K, V > Matcher< Map< K, V > > contains( final Matcher< Entry< K, V >>... entryMatchers )
	{
		return new MapContains<>( Matchers.contains( entryMatchers ) );
	}

	/**
	 * Creates an order agnostic matcher for {@link Map} that matches when a single pass over
	 * the examined {@link Map} yields a series of {@link java.util.Map.Entry}s, each satisfying one matcher anywhere
	 * in the specified matchers. For a positive match, the examined iterable must be of the same
	 * length as the number of specified matchers.
	 * <p/>
	 * N.B. each of the specified matchers will only be used once during a given examination, so be careful when
	 * specifying matchers that may be satisfied by more than one entry in an examined iterable.
	 *
	 * @param entryMatchers
	 *            the matchers to apply to items provided by the examined entry set
	 * @return the Matcher
	 */
	@SafeVarargs
	public static final < K, V > Matcher< Map< K, V > > containsInAnyOrder(
			final Matcher< Entry< K, V >>... entryMatchers )
	{
		return new MapContains<>( Matchers.containsInAnyOrder( entryMatchers ) );
	}

}