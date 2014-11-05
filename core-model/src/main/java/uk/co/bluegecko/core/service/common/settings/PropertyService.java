/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.common.settings;


/**
 * Service to manage retrieving property values
 */
public interface PropertyService
{

	/**
	 * @param property
	 *            to retrieve
	 * @return the property value
	 */
	public String getProperty( Enum< ? > property );

	/**
	 * @param property
	 *            to retrieve
	 * @param fallback
	 *            value to use if no entry exists
	 * @return the property value
	 */
	public String getProperty( Enum< ? > property, String fallback );

	/**
	 * @param property
	 *            to check
	 * @return if the property exists
	 */
	public boolean hasProperty( Enum< ? > property );

}
