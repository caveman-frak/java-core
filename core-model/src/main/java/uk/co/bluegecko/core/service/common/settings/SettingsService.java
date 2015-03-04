/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.common.settings;


import uk.co.bluegecko.core.model.TypedKey;


/**
 * Service to manage retrieving setting values
 */
public interface SettingsService
{

	/**
	 * Return the setting value.
	 * 
	 * @param <E>
	 *            type of setting value
	 * @param setting
	 *            to retrieve
	 * @return the setting value
	 */
	public < E > E getSetting( TypedKey< E > setting );

	/**
	 * Return the setting value, or the fallback value if it does not exists.
	 * 
	 * @param <E>
	 *            type of setting value
	 * @param setting
	 *            to retrieve
	 * @param fallback
	 *            value to use if no entry exists
	 * @return the setting value
	 */
	public < E > E getSetting( TypedKey< E > setting, E fallback );

	/**
	 * Return if the setting exists.
	 *
	 * @param setting
	 *            to check
	 * @return if the setting exists
	 */
	public boolean hasSetting( TypedKey< ? > setting );

}
