package uk.co.bluegecko.core.model.base;


import java.util.StringJoiner;

import uk.co.bluegecko.core.model.Message;


/**
 * Base class for messages.
 */
public abstract class MessageBase implements Message
{

	protected MessageBase()
	{
		super();
	}

	@Override
	public final int hashCode()
	{
		return toString().hashCode();
	}

	@Override
	public final boolean equals( final Object obj )
	{
		if ( obj instanceof Message )
		{
			return toString().equals( obj.toString() );
		}
		return false;
	}

	protected String asText( final String key, final Object... args )
	{
		if ( args.length > 0 )
		{
			final StringJoiner joiner = new StringJoiner( ",", "[", "]" );
			for ( final Object arg : args )
			{
				joiner.add( arg.toString() );
			}
			return key.toString() + joiner.toString();
		}
		return key.toString();
	}

}