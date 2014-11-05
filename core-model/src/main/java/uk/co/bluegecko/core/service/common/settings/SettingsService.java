/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.common.settings;


/**
 * Service to manage retrieving setting values
 */
public interface SettingsService
{

	/**
	 * @param setting
	 *            to retrieve
	 * @return the setting value
	 */
	public < E > E getSetting( Setting< E > setting );

	/**
	 * @param setting
	 *            to retrieve
	 * @param fallback
	 *            value to use if no entry exists
	 * @return the setting value
	 */
	public < E > E getSetting( Setting< E > setting, E fallback );

	/**
	 * @param setting
	 *            to check
	 * @return if the setting exists
	 */
	public boolean hasSetting( Setting< ? > setting );

}
