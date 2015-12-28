package uk.co.bluegecko.core.model.base;


import java.util.Locale;

import uk.co.bluegecko.core.service.common.LocalisationService;


/**
 * Message backed by enum key.
 */
public class EnumMessage extends LocalisedMessage< Enum< ? > >
{

	/**
	 * Construct a new message using an enum key and set of arguments.
	 *
	 * @param bundleName
	 *            name of the resource bundle
	 * @param key
	 *            enum key for message
	 * @param args
	 *            message arguments
	 */
	public EnumMessage( final String bundleName, final Enum< ? > key, final Object... args )
	{
		super( bundleName, key, args );
	}

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
		this( null, key, args );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Message#getText(java.util.Locale)
	 */
	@Override
	public String text( final LocalisationService localisationService, final Locale locale )
	{
		if ( localisationService != null )
		{
			return localisationService.message( locale, getKey(), localisedArgs( localisationService, locale ) );
		}
		else
		{
			return toString();
		}
	}

}
