/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.common;


import java.time.temporal.TemporalAccessor;


/**
 * Service for formatting numbers, currency, dates, etc. The implementation is expected to use the
 * {@link uk.co.bluegecko.core.service.common.LocaleService} to get the current locale.
 */
public interface InternationalisationService
{

	/**
	 * Format the date/time using the default formatting rules.
	 * 
	 * @param temporalAccessor
	 *            the date/time to format
	 * @return the formatted date/time
	 */
	public String format( TemporalAccessor temporalAccessor );

	/**
	 * Format the number using the default format rules.
	 * 
	 * @param number
	 *            the number to format
	 * @return the formatted number
	 */
	public String format( Number number );

}
