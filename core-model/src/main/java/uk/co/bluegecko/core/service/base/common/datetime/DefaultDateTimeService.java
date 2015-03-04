/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.common.datetime;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import uk.co.bluegecko.core.service.common.DateTimeService;


/**
 * Simple implementation of {@link DateTimeService} using {@link LocalDateTime#now()}
 */
@Service
public class DefaultDateTimeService implements DateTimeService
{

	/**
	 * Default zero arg constructor
	 */
	public DefaultDateTimeService()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.DateTimeService#getLocalDateTime()
	 */
	@Override
	public LocalDateTime getLocalDateTime()
	{
		return LocalDateTime.now();
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.DateTimeService#getLocalTime()
	 */
	@Override
	public LocalTime getLocalTime()
	{
		return LocalTime.now();
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.DateTimeService#getLocalDate()
	 */
	@Override
	public LocalDate getLocalDate()
	{
		return LocalDate.now();
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.DateTimeService#getTimeZone()
	 */
	@Override
	public ZoneId getTimeZone()
	{
		return ZoneId.systemDefault();
	}

}
