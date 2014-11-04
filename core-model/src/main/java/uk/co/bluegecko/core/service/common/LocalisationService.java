/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.common;


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
	 * @param messageKey
	 *            the message key
	 * @param bundleName
	 *            the resource bundle name
	 * @param params
	 *            any message arguments
	 * @return the localised message
	 */
	public String getMessage( String messageKey, String bundleName, Object... params );

	/**
	 * Return a map of all localised message key, value pairs for a given bundle.
	 *
	 * @param bundleName
	 *            the bundle containing the messages
	 * @return a map of localised message key, value pairs
	 */
	public Map< String, Object > getMessages( String bundleName );

}
