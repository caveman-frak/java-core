/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.common;


import java.util.Locale;


/**
 * Service for handling {@link java.util.Locale}
 */
public interface LocaleService
{

	/**
	 * Get the current system locale
	 *
	 * @return the current system locale
	 */
	public Locale getSystemLocale();

	/**
	 * Get the current user locale
	 *
	 * @return the current user locale
	 */
	public Locale getUserLocale();

}
