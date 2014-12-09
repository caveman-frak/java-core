/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time;


import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalQuery;


/**
 * Determine the day of the month (e.g. Christmas Day).
 */
public class DayOfMonth implements TemporalQuery< Boolean >, TemporalAdjuster
{

	private final MonthDay monthDay;

	/**
	 * Construct a day of the month.
	 *
	 * @param monthDay
	 *            the month and day to use
	 */
	public DayOfMonth( final MonthDay monthDay )
	{
		super();

		this.monthDay = monthDay;
	}

	/**
	 * Construct a day of the month.
	 *
	 * @param month
	 *            the month to use
	 * @param day
	 *            the day to use
	 */
	public DayOfMonth( final Month month, final int day )
	{
		this( MonthDay.of( month, day ) );
	}

	/*
	 * (non-Javadoc)
	 * @see java.time.temporal.TemporalAdjuster#adjustInto(java.time.temporal.Temporal)
	 */
	@Override
	public Boolean queryFrom( final TemporalAccessor temporal )
	{
		final int month = temporal.get( ChronoField.MONTH_OF_YEAR );
		final int day = temporal.get( ChronoField.DAY_OF_MONTH );

		return month == monthDay.getMonthValue() && day == monthDay.getDayOfMonth();
	}

	/*
	 * (non-Javadoc)
	 * @see java.time.temporal.TemporalAdjuster#adjustInto(java.time.temporal.Temporal)
	 */
	@Override
	public Temporal adjustInto( final Temporal temporal )
	{
		final int year = temporal.get( ChronoField.YEAR );
		final int daysInMonth = monthDay.getMonth().length( Year.isLeap( year ) );
		return LocalDate.of( year, monthDay.getMonth(), Math.min( monthDay.getDayOfMonth(), daysInMonth ) );
	}

}
