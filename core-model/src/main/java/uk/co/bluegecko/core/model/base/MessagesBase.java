/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.base;


import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import org.apache.commons.lang3.builder.ToStringStyle;

import uk.co.bluegecko.core.model.Message;
import uk.co.bluegecko.core.model.Messages;


/**
 * A wrapper for message text.
 *
 * Contains messages grouped by severity and a message key.
 *
 */
public class MessagesBase implements Messages
{

	private static final long serialVersionUID = -6477212798589984980L;

	private final Map< Severity, Map< String, Set< Message > > > messages;

	/**
	 * Create a new messages object.
	 */
	public MessagesBase()
	{
		messages = new EnumMap< >( Severity.class );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Messages#getSeverity()
	 */
	@Override
	public Severity getSeverity()
	{
		for ( final Severity severity : Severity.values() )
		{
			if ( hasMessages( severity ) )
			{
				return severity;
			}
		}
		return Severity.NONE;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Messages#hasMessages(uk.co.bluegecko.core.model.Messages.Severity)
	 */
	@Override
	public boolean hasMessages( final Severity severity )
	{
		if ( severity == Severity.NONE )
		{
			return !hasMessages( NOT_NONE );
		}
		final Map< String, Set< Message > > map = messages.get( severity );
		return map != null && !map.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Messages#hasMessages(uk.co.bluegecko.core.model.Messages.Severity, java.lang.String)
	 */
	@Override
	public boolean hasMessages( final Severity severity, final String key )
	{
		if ( hasMessages( severity ) )
		{
			final Map< String, Set< Message > > map = messages.get( severity );
			if ( map != null )
			{
				final Set< Message > set = map.get( key );
				return set != null && !set.isEmpty();
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Messages#hasMessages(java.util.Set)
	 */
	@Override
	public boolean hasMessages( final Set< Severity > severities )
	{
		if ( severities.contains( Severity.NONE ) )
		{
			throw new IllegalArgumentException( "set-cannot-include-none" );
		}
		for ( final Severity severity : severities )
		{
			if ( hasMessages( severity ) )
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Messages#getKeys(uk.co.bluegecko.core.model.Messages.Severity)
	 */
	@Override
	public Set< String > getKeys( final Severity severity )
	{
		if ( hasMessages( severity ) )
		{
			final Map< String, Set< Message > > map = messages.get( severity );
			if ( map != null )
			{
				return Collections.unmodifiableSet( map.keySet() );
			}
		}
		return Collections.emptySet();
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Messages#getMessages(uk.co.bluegecko.core.model.Messages.Severity, java.lang.String)
	 */
	@Override
	public Set< Message > getMessages( final Severity severity, final String key )
	{
		if ( hasMessages( severity, key ) )
		{
			final Set< Message > set = messages.get( severity ).get( key );
			if ( set != null )
			{
				return Collections.unmodifiableSet( set );
			}
		}
		return Collections.emptySet();
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Messages#addMessage(uk.co.bluegecko.core.model.Messages.Severity, java.lang.String, java.lang.String)
	 */
	@Override
	public Set< Message > addMessages( final Severity severity, final String key, final Message... text )
	{
		if ( !hasMessages( severity ) )
		{
			final Map< String, Set< Message > > map = new HashMap< >();
			messages.put( severity, map );
		}
		if ( !hasMessages( severity, key ) )
		{
			final Set< Message > set = new HashSet< >();
			messages.get( severity ).put( key, set );
		}
		final Set< Message > set = messages.get( severity ).get( key );

		set.addAll( Arrays.asList( text ) );
		return Collections.unmodifiableSet( set );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Messages#clear()
	 */
	@Override
	public void clear()
	{
		messages.clear();
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Messages#clear(uk.co.bluegecko.core.model.Messages.Severity)
	 */
	@Override
	public void clear( final Severity severity )
	{
		if ( hasMessages( severity ) )
		{
			messages.get( severity ).clear();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Messages#clear(uk.co.bluegecko.core.model.Messages.Severity, java.lang.String)
	 */
	@Override
	public void clear( final Severity severity, final String key )
	{
		if ( hasMessages( severity, key ) )
		{
			messages.get( severity ).get( key ).clear();
		}
	}

	@Override
	public String toString()
	{
		final StringBuffer buffer = new StringBuffer();

		final ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;
		style.appendStart( buffer, this );

		addSeverities( buffer );
		buffer.append( "]" );
		return buffer.toString();
	}

	private void addSeverities( final StringBuffer buffer )
	{
		boolean populated = false;
		for ( final Severity severity : NOT_NONE )
		{
			if ( hasMessages( severity ) )
			{
				populated = true;
				buffer.append( "\n\t" ).append( severity.name() );
				addKeys( buffer, severity, getKeys( severity ) );
			}
		}
		if ( populated )
		{
			buffer.append( "\n" );
		}
	}

	private void addKeys( final StringBuffer buffer, final Severity severity, final Set< String > keys )
	{
		final boolean multiLine = keys.size() > 1;

		for ( final String key : keys )
		{
			buffer.append( multiLine ? "\n\t\t" : "\t" ).append( key ).append( " : " )
					.append( addMessages( severity, key ) );
		}
	}

	private String addMessages( final Severity severity, final String key )
	{
		final StringJoiner joiner = new StringJoiner( "; " );
		for ( final Message message : getMessages( severity, key ) )
		{
			joiner.add( message.toString() );
		}
		return joiner.toString();
	}

}
