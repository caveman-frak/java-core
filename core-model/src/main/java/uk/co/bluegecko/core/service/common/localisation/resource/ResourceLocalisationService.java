/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.common.localisation.resource;


import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

import uk.co.bluegecko.core.service.base.AbstractService;
import uk.co.bluegecko.core.service.common.LocaleService;
import uk.co.bluegecko.core.service.common.LocalisationService;


/**
 *
 */
@Service
public class ResourceLocalisationService extends AbstractService implements LocalisationService
{

	@BaseName( "uk.co.bluegecko.core.service.common.localisation.resource.ResourceLocalisationService$Log" )
	@LocaleData(
		{ @Locale( "en" ) } )
	protected enum Log
	{
		MISSING_KEY, MISSING_BUNDLE
	}

	private static final String MISSING_BUNDLE_INDICATOR = "--";
	private static final String MISSING_KEY_INDICATOR = "**";

	/**
	 * @param localeService
	 *            used to determine the current locale
	 *
	 */
	@Autowired
	public ResourceLocalisationService( final LocaleService localeService )
	{
		super( localeService );
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.LocalisationService#getMessage(java.lang.String, java.lang.String, java.lang.Object[])
	 */
	@Override
	public String getMessage( final String messageKey, final String bundleName, final Object... params )
	{
		try
		{
			final ResourceBundle bundle = getBundle( bundleName );
			try
			{
				final String message = bundle.getString( messageKey );
				return MessageFormat.format( message, params );
			}
			catch ( final MissingResourceException ex )
			{
				getLogger().info( Log.MISSING_KEY, bundleName, messageKey );
				return MISSING_KEY_INDICATOR + messageKey + MISSING_KEY_INDICATOR;
			}
		}
		catch ( final MissingResourceException ex )
		{
			getLogger().info( Log.MISSING_BUNDLE, bundleName );
			return MISSING_BUNDLE_INDICATOR + messageKey + MISSING_BUNDLE_INDICATOR;
		}
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.common.LocalisationService#getMessages(java.lang.String)
	 */
	@Override
	public Map< String, Object > getMessages( final String bundleName )
	{
		final Map< String, Object > messages = new HashMap<>();
		try
		{
			final ResourceBundle bundle = getBundle( bundleName );

			for ( final String key : bundle.keySet() )
			{
				messages.put( key, bundle.getObject( key ) );
			}
		}
		catch ( final MissingResourceException ex )
		{
			getLogger().info( Log.MISSING_BUNDLE, bundleName );
		}

		return messages;
	}

	/**
	 * @param bundleName
	 * @return
	 */
	private ResourceBundle getBundle( final String bundleName )
	{
		final ResourceBundle bundle = ResourceBundle.getBundle( bundleName, getLocaleService().getUserLocale() );
		return bundle;
	}

}
