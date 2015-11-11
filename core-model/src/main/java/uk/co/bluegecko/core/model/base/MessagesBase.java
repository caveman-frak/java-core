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

import org.apache.commons.lang3.builder.ToStringStyle;

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

	private final Map< Severity, Map< String, Set< String >> > messages;

	/**
	 * Create a new messages object.
	 */
	public MessagesBase()
	{
		messages = new EnumMap<>( Severity.class );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Messages#hasMessages(uk.co.bluegecko.core.model.Messages.Severity)
	 */
	@Override
	public boolean hasMessages( Severity severity )
	{
		final Map< String, Set< String >> map = messages.get( severity );
		return map != null && !map.isEmpty();
	}

	@Override
	public boolean hasMessages( Severity severity, String key )
	{
		if ( hasMessages( severity ) )
		{
			final Map< String, Set< String >> map = messages.get( severity );
			if ( map != null )
			{
				final Set< String > set = map.get( key );
				return set != null && !set.isEmpty();
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Messages#getKeys(uk.co.bluegecko.core.model.Messages.Severity)
	 */
	@Override
	public Set< String > getKeys( Severity severity )
	{
		if ( hasMessages( severity ) )
		{
			final Map< String, Set< String >> map = messages.get( severity );
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
	public Set< String > getMessages( Severity severity, String key )
	{
		if ( hasMessages( severity, key ) )
		{
			final Set< String > set = messages.get( severity ).get( key );
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
	public Set< String > addMessages( Severity severity, String key, String... text )
	{
		if ( !hasMessages( severity ) )
		{
			final Map< String, Set< String >> map = new HashMap<>();
			messages.put( severity, map );
		}
		if ( !hasMessages( severity, key ) )
		{
			final Set< String > set = new HashSet<>();
			messages.get( severity ).put( key, set );
		}
		final Set< String > set = messages.get( severity ).get( key );

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
	public void clear( Severity severity )
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
	public void clear( Severity severity, String key )
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

		boolean populated = false;
		for ( final Severity severity : Severity.values() )
		{
			if ( hasMessages( severity ) )
			{
				populated = true;
				buffer.append( "\n\t" ).append( severity.name() );
				final Set< String > keys = getKeys( severity );
				final boolean multiLine = keys.size() > 1;

				for ( final String key : keys )
				{
					if ( multiLine )
					{
						buffer.append( "\n\t\t" );
					}
					else
					{
						buffer.append( "\t" );
					}
					buffer.append( key ).append( " : " );

					boolean first = true;
					for ( final String text : getMessages( severity, key ) )
					{
						if ( !first )
						{
							buffer.append( "; " );
						}
						else
						{
							first = false;
						}
						buffer.append( text );
					}
				}
			}
		}
		if ( populated )
		{
			buffer.append( "\n" );
		}
		buffer.append( "]" );
		return buffer.toString();
	}

}
