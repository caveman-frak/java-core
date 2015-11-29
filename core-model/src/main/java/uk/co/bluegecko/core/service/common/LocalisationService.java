/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.common;


import java.util.Locale;
import java.util.Map;


/**
 * Service for localising messages. The implementation is expected to use the
 * {@link uk.co.bluegecko.core.service.common.LocaleService} to get the current locale.
 */
public interface LocalisationService
{

	/**
	 * Return a localised message using resource bundle and message formatters
	 *
	 * @param locale
	 *            the locale for the bundle
	 * @param bundleName
	 *            the resource bundle name
	 * @param messageKey
	 *            the message key
	 * @param params
	 *            any message arguments
	 * @return the localised message
	 */
	public String getMessage( final Locale locale, final String bundleName, final String messageKey,
			final Object... params );

	/**
	 * Return a map of all localised message key, value pairs for a given bundle.
	 *
	 * @param locale
	 *            the locale for the message
	 * @param bundleName
	 *            the bundle containing the messages
	 *
	 * @return a map of localised message key, value pairs
	 */
	public Map< String, Object > getMessages( final Locale locale, final String bundleName );

	/**
	 * Return a localised message using the enum.
	 * 
	 * @param locale
	 *            the locale for the bundle
	 * @param value
	 *            the enum to localise
	 * @param params
	 *            any message arguments
	 * @return the localised message
	 */
	public String getMessage( final Locale locale, final Enum< ? > value, final Object... params );

}
