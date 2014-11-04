/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.common;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;


/**
 * Service for handling Date and Time instances
 */
public interface DateTimeService
{

	/**
	 * Return the current local date time
	 *
	 * @return current local date time
	 */
	public LocalDateTime getLocalDateTime();

	/**
	 * Return the current local time
	 *
	 * @return current local time
	 */
	public LocalTime getLocaleTime();

	/**
	 * Return the current local date
	 *
	 * @return current local date
	 */
	public LocalDate getLocalDate();

	/**
	 * Return the current time zone
	 *
	 * @return current time zone
	 */
	public ZoneId getTimeZone();

}
