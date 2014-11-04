/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.matcher;


import java.util.function.Predicate;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


/**
 * Check if the passed object matches the supplied test
 *
 * @param <V>
 *            the type of value to check
 */
public class SimpleMatcher< V > extends TypeSafeMatcher< V >
{

	private final Predicate< V > test;
	private final String description;

	/**
	 * Construct a new matcher, checking for the supplied test.
	 *
	 * @param test
	 *            the test to apply to the value
	 */
	protected SimpleMatcher( final Predicate< V > test, final String description )
	{
		this.test = test;
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo( final Description description )
	{
		description.appendText( this.description );
	}

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
	 */
	@Override
	protected boolean matchesSafely( final V obj )
	{
		return test.test( obj );
	}

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#describeMismatchSafely(java.lang.Object, org.hamcrest.Description)
	 */
	@Override
	protected void describeMismatchSafely( final V model, final Description mismatchDescription )
	{
		mismatchDescription.appendText( "was " ).appendValue( model ).appendText( " expected " )
		.appendText( description );
	}

	/**
	 * Construct a new matcher, checking for the supplied test.
	 *
	 * @param test
	 *            the test to match
	 * @param description
	 *            description of the test
	 * @return the SimpleMatcher
	 */
	public static final < V > Matcher< V > does( final Predicate< V > test, final String description )
	{
		return new SimpleMatcher<>( test, description );
	}

	/**
	 * Construct a new matcher, checking for the supplied test.
	 *
	 * @param test
	 *            the test to match
	 * @return the SimpleMatcher
	 */
	public static final < V > Matcher< V > does( final Predicate< V > test )
	{
		return does( test, "test expression" );
	}

}