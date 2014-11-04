/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.matcher.logging;


import java.util.Arrays;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import ch.qos.cal10n.MessageParameterObj;
import uk.org.lidalia.slf4jext.Level;
import uk.org.lidalia.slf4jtest.LoggingEvent;


/**
 * Check if the passed log event matches the correct level, message and arguments.
 *
 */
public class LoggingEventMatcher extends TypeSafeMatcher< LoggingEvent >
{

	private final Level level;
	private final Enum< ? > messageKey;
	private final Object[] args;

	/**
	 * Construct a new matcher, checking for the correct level, message and arguments.
	 *
	 * @param level
	 *            the level to compare
	 * @param message
	 *            the message to compare
	 * @param args
	 *            the arguments to compare
	 *
	 */
	protected LoggingEventMatcher( final Level level, final Enum< ? > messageKey, final Object... args )
	{
		this.level = level;
		this.messageKey = messageKey;
		this.args = args;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo( final Description description )
	{
		description.appendText( "" );
	}

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
	 */
	@Override
	protected boolean matchesSafely( final LoggingEvent event )
	{
		return matchLevel( event ) && matchMessage( event ) && matchArguments( event );
	}

	private boolean matchLevel( final LoggingEvent event )
	{
		return event.getLevel() == level;
	}

	private boolean matchMessage( final LoggingEvent event )
	{
		final List< Object > arguments = event.getArguments();
		if ( arguments.size() == 1 )
		{
			final Object obj = arguments.get( 0 );
			if ( obj instanceof MessageParameterObj )
			{
				final MessageParameterObj paramObj = ( MessageParameterObj ) obj;

				return paramObj.getKey() == messageKey;
			}
		}
		return false;
	}

	private boolean matchArguments( final LoggingEvent event )
	{
		final List< Object > arguments = event.getArguments();
		if ( arguments.size() == 1 )
		{
			final Object obj = arguments.get( 0 );
			if ( obj instanceof MessageParameterObj )
			{
				final MessageParameterObj paramObj = ( MessageParameterObj ) obj;

				return Arrays.deepEquals( paramObj.getArgs(), args );
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#describeMismatchSafely(java.lang.Object, org.hamcrest.Description)
	 */
	@Override
	protected void describeMismatchSafely( final LoggingEvent model, final Description mismatchDescription )
	{
		mismatchDescription.appendText( "was \"" ).appendValue( model ).appendText( "\"" );
	}

	/**
	 * Construct a new info logging matcher.
	 *
	 * @param message
	 *            the message to compare
	 * @param args
	 *            the arguments to compare
	 *
	 * @return the LoggingEventMatcher
	 */
	public static final Matcher< LoggingEvent > info( final Enum< ? > message, final Object... args )
	{
		return new LoggingEventMatcher( Level.INFO, message, args );
	}

}