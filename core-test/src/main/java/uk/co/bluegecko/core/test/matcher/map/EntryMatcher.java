/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.matcher.map;


import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


/**
 * Check if the passed entry has matching key and value
 *
 * @param <K>
 *            the type of the entry key
 * @param <V>
 *            the type of the entry value
 */
public class EntryMatcher< K, V > extends TypeSafeMatcher< Map.Entry< K, V > >
{

	private final Matcher< K > keyMatcher;
	private final Matcher< V > valueMatcher;

	/**
	 * Construct a new matcher, checking for the supplied test.
	 *
	 * @param keyMatcher
	 *            the matcher to apply to the entry key
	 * @param valueMatcher
	 *            the matcher to apply to the entry value
	 */
	protected EntryMatcher( final Matcher< K > keyMatcher, final Matcher< V > valueMatcher )
	{
		this.keyMatcher = keyMatcher;
		this.valueMatcher = valueMatcher;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo( final Description description )
	{
		description.appendDescriptionOf( keyMatcher ).appendText( "=" ).appendDescriptionOf( valueMatcher );
	}

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#describeMismatchSafely(java.lang.Object, org.hamcrest.Description)
	 */
	@Override
	protected void describeMismatchSafely( final Map.Entry< K, V > entry, final Description mismatchDescription )
	{
		mismatchDescription.appendText( "was " ).appendValue( entry.getKey() ).appendText( "=" )
		.appendValue( entry.getValue() ).appendText( " expected " ).appendDescriptionOf( this );
	}

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
	 */
	@Override
	protected boolean matchesSafely( final Map.Entry< K, V > entry )
	{
		return keyMatcher.matches( entry.getKey() ) && valueMatcher.matches( entry.getValue() );
	}

	/**
	 * Construct a new matcher, checking for the supplied test.
	 *
	 * @param <K>
	 *            the type of the map key
	 * @param <V>
	 *            the type of map value
	 * @param keyMatcher
	 *            the matcher to apply to the entry key
	 * @param valueMatcher
	 *            the matcher to apply to the entry value
	 * @return a new {@link EntryMatcher}
	 */
	public static final < K, V > Matcher< Map.Entry< K, V > > entry( final Matcher< K > keyMatcher,
			final Matcher< V > valueMatcher )
	{
		return new EntryMatcher<>( keyMatcher, valueMatcher );
	}

	/**
	 * Construct a new matcher, checking for the supplied test.
	 *
	 * @param <K>
	 *            the type of the map key
	 * @param <V>
	 *            the type of map value
	 * @param key
	 *            the expected value of the entry key
	 * @param value
	 *            the expected value of the entry value
	 * @return a new {@link EntryMatcher}
	 */
	public static final < K, V > Matcher< Map.Entry< K, V > > entry( final K key, final V value )
	{
		return new EntryMatcher<>( equalTo( key ), equalTo( value ) );
	}

}