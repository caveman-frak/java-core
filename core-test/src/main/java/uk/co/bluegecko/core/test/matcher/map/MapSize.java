/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.matcher.map;


import java.util.Map;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


/**
 * Check if the map has the expected size.
 *
 */
public class MapSize extends TypeSafeMatcher< Map< ?, ? >>
{

	private final int expected;

	/**
	 * Construct a new matcher, checking for the expected size.
	 *
	 * @param expected
	 *            the expected size
	 */
	protected MapSize( final int expected )
	{
		this.expected = expected;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo( final Description description )
	{
		description.appendText( " size of" ).appendValue( expected );
	}

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
	 */
	@Override
	protected boolean matchesSafely( final Map< ?, ? > map )
	{
		return map.size() == expected;
	}

	/**
	 * Creates a matcher for {@link Map} that matches map size.
	 *
	 * @param size
	 *            the expected size of the map
	 * @return the Map Matcher
	 */
	public static final Matcher< Map< ?, ? > > hasMapSize( final int size )
	{
		return new MapSize( size );
	}

}