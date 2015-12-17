package uk.co.bluegecko.core.model.base;


import java.util.StringJoiner;


/**
 * Base class for messages, including key and arguments.
 *
 * @param <T>
 *            type of key
 */
public abstract class LocalisedMessage< T > extends MessageBase
{

	private final T key;
	private final Object[] args;

	protected LocalisedMessage( final T key, final Object... args )
	{
		this.key = key;
		this.args = args;
	}

	/**
	 * Get the message key.
	 *
	 * @return message key
	 */
	public T getKey()
	{
		return key;
	}

	/**
	 * Get the message arguments.
	 *
	 * @return message arguments
	 */
	public Object[] getArgs()
	{
		return args;
	}

	@Override
	public String toString()
	{
		final StringJoiner joiner = new StringJoiner( ",", "[", "]" );
		for ( final Object arg : args )
		{
			joiner.add( arg.toString() );
		}
		return key.toString() + joiner.toString();
	}

}
