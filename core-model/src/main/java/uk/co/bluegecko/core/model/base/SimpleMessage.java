package uk.co.bluegecko.core.model.base;


import java.util.Locale;


/**
 * Simple implementation of Message that contains fixed text.
 */
public class SimpleMessage extends MessageBase
{

	private final String text;

	/**
	 * Construct a simple message with fixed text.
	 *
	 * @param text
	 *            the fixed text
	 */
	public SimpleMessage( final String text )
	{
		super();

		this.text = text;
	}

	@Override
	public String getText( final Locale locale )
	{
		return text;
	}

	@Override
	public String toString()
	{
		return text;
	}

}
