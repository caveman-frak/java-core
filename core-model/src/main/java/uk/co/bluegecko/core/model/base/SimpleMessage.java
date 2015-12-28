package uk.co.bluegecko.core.model.base;


import java.util.Locale;

import uk.co.bluegecko.core.service.common.LocalisationService;


/**
 * Simple implementation of Message that contains fixed text.
 */
public class SimpleMessage extends MessageBase
{

	private final String text;

	/**
	 * Construct a simple message with fixed text.
	 *
	 * @param key
	 *            key for message
	 * @param args
	 *            message arguments
	 *
	 */
	public SimpleMessage( final String key, final Object... args )
	{
		super();

		text = asText( key, args );
	}

	@Override
	public String text( final LocalisationService localisationService, final Locale locale )
	{
		return text;
	}

	@Override
	public String toString()
	{
		return text;
	}

}
