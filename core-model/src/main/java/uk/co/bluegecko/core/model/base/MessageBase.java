package uk.co.bluegecko.core.model.base;


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

}