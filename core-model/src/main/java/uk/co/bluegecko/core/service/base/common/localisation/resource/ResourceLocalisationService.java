/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.common.localisation.resource;


import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import uk.co.bluegecko.core.service.common.LocaleService;
import uk.co.bluegecko.core.service.common.LocalisationService;


/**
 * {@link ResourceBundle} implementation of {@link LocalisationService}
 */
@Service
public class ResourceLocalisationService extends BaseLocalisationService
{

	/**
	 * Default constructor, referencing the {@link LocaleService}
	 *
	 * @param applicationContext
	 *            used to set the application context
	 * @param localeService
	 *            used to determine the current locale
	 */
	@Autowired
	public ResourceLocalisationService( final ApplicationContext applicationContext, final LocaleService localeService )
	{
		super( applicationContext, localeService );
	}

	protected String getMessage( final Locale locale, final String bundleName, final String messageKey,
			final boolean flagged, final Object... params )
	{
		try
		{
			final ResourceBundle bundle = getBundle( locale, bundleName );
			try
			{
				final String message = bundle.getString( messageKey );
				return MessageFormat.format( message, params );
			}
			catch ( final MissingResourceException ex )
			{
				getLogger().info( Log.MISSING_KEY, getLocale( locale ), bundleName, messageKey, ex.getMessage() );

				return flagged( flagged, messageKey, MISSING_KEY_INDICATOR );
			}
		}
		catch ( final MissingResourceException ex )
		{
			getLogger().info( Log.MISSING_BUNDLE, getLocale( locale ), bundleName, ex.getMessage() );
			return flagged( flagged, messageKey, MISSING_BUNDLE_INDICATOR );
		}
	}

	protected String flagged( final boolean flagged, final String messageKey, final String marker )
	{
		if ( flagged )
		{
			return marker + messageKey + marker;
		}
		return messageKey;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.LocalisationService#getMessage(java.lang.String, java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public String message( final Locale locale, final String bundleName, final String messageKey,
			final Object... params )
	{
		return getMessage( locale, bundleName, messageKey, true, params );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.LocalisationService#getRawMessage(java.lang.String, java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public String rawMessage( final Locale locale, final String bundleName, final String messageKey,
			final Object... params )
	{
		return getMessage( locale, bundleName, messageKey, false, params );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.common.LocalisationService#getMessages(java.lang.String)
	 */
	@Override
	public Map< String, Object > messages( final Locale locale, final String bundleName )
	{
		final Map< String, Object > messages = new HashMap< >();
		try
		{
			final ResourceBundle bundle = getBundle( locale, bundleName );

			for ( final String key : bundle.keySet() )
			{
				messages.put( key, bundle.getObject( key ) );
			}
		}
		catch ( final MissingResourceException ex )
		{
			getLogger().info( Log.MISSING_BUNDLE, getLocale( locale ), bundleName, ex.getMessage() );
		}

		return messages;
	}

}
