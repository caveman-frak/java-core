package uk.co.bluegecko.core.model.base;


import java.util.Locale;

import uk.co.bluegecko.core.service.common.LocalisationService;


/**
 * Message backed by a resource bundle.
 */
public class BundleMessage extends LocalisedMessage< String >
{

	/**
	 * Construct a new message using a resource bundle, key and set of arguments.
	 *
	 * @param bundleName
	 *            name of the resource bundle
	 * @param key
	 *            enum key for message
	 * @param args
	 *            message arguments
	 */
	public BundleMessage( final String bundleName, final String key, final Object... args )
	{
		super( bundleName, key, args );
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
			return localisationService.message( locale, getBundleName(), getKey(),
					localisedArgs( localisationService, locale ) );
		}
		else
		{
			return toString();
		}
	}

}
