package uk.co.bluegecko.core.model.base;


import java.util.Locale;


/**
 * Message backed by enum key.
 */
public class EnumMessage extends LocalisedMessage< Enum< ? > >
{

	/**
	 * Construct a new message using an enum key and set of arguments.
	 *
	 * @param key
	 *            enum key for message
	 * @param args
	 *            message arguments
	 */
	public EnumMessage( final Enum< ? > key, final Object... args )
	{
		super( key, args );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Message#getText(java.util.Locale)
	 */
	@Override
	public String getText( final Locale locale )
	{
		return toString();
	}

}
