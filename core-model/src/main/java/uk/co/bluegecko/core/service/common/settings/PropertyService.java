/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.common.settings;


import uk.co.bluegecko.core.model.Key;


/**
 * Service to manage retrieving property values
 */
public interface PropertyService
{

	/**
	 * @param <T>
	 *            key must be an {@link Enum} extending {@link Key}
	 * @param key
	 *            to retrieve
	 * @return the property value
	 */
	public < T extends Enum< ? > & Key > String getProperty( T key );

	/**
	 * @param <T>
	 *            key must be an {@link Enum} extending {@link Key}
	 * @param key
	 *            to retrieve
	 * @param fallback
	 *            value to use if no entry exists
	 * @return the property value
	 */
	public < T extends Enum< ? > & Key > String getProperty( T key, String fallback );

	/**
	 * @param <T>
	 *            key must be an {@link Enum} extending {@link Key}
	 * @param key
	 *            to check
	 * @return if the property exists
	 */
	public < T extends Enum< ? > & Key > boolean hasProperty( T key );

}
