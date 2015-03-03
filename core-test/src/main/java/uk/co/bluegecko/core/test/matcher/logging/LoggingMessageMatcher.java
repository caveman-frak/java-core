/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.matcher.logging;


import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import uk.org.lidalia.slf4jtest.LoggingEvent;


/**
 * Check if the passed log event matches the correct level, message and arguments.
 */
public class LoggingMessageMatcher extends TypeSafeMatcher< LoggingEvent >
{

	private final String message;

	/**
	 * Construct a new matcher, checking for the localised message.
	 *
	 * @param message
	 *            the message to compare
	 */
	protected LoggingMessageMatcher( final String message )
	{
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo( final Description description )
	{
		description.appendText( "" );
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
	 */
	@Override
	protected boolean matchesSafely( final LoggingEvent event )
	{
		return event.getMessage().equals( message );
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.hamcrest.TypeSafeMatcher#describeMismatchSafely(java.lang.Object, org.hamcrest.Description)
	 */
	@Override
	protected void describeMismatchSafely( final LoggingEvent model, final Description mismatchDescription )
	{
		mismatchDescription.appendText( "was \"" ).appendValue( model ).appendText( "\"" );
	}

	/**
	 * Construct a new localised logging message matcher.
	 *
	 * @param message
	 *            the message to compare
	 * @return the LoggingMessageMatcher
	 */
	public static final Matcher< LoggingEvent > message( final String message )
	{
		return new LoggingMessageMatcher( message );
	}

}