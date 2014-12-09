/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQuery;


/**
 * Determine the particular weekday of the month (e.g. 2nd Tue of March). Negative count starts with last weekday and
 * moves backwards.
 */
public class WeekDayOfMonth implements TemporalQuery< Boolean >, TemporalAdjuster
{

	private final Month month;
	private final DayOfWeek weekDay;
	private final int count;

	/**
	 * Construct a day of the month.
	 *
	 * @param month
	 *            the month to use
	 * @param weekDay
	 *            the day to use
	 * @param count
	 *            the count of that day (e.g. 2nd Tue)
	 */
	public WeekDayOfMonth( final Month month, final DayOfWeek weekDay, final int count )
	{
		super();

		this.month = month;
		this.weekDay = weekDay;
		this.count = count;
	}

	/**
	 * Construct a day of the month.
	 *
	 * @param month
	 *            the month to use
	 * @param weekDay
	 *            the day to use
	 */
	public WeekDayOfMonth( final Month month, final DayOfWeek weekDay )
	{
		this( month, weekDay, 1 );
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
		final int weekDay = temporal.get( ChronoField.DAY_OF_WEEK );

		return month == this.month.getValue() && weekDay == this.weekDay.getValue() && day == calculateDay( temporal );
	}

	/**
	 * @return
	 */
	private int calculateDay( final TemporalAccessor temporal )
	{
		final Temporal date = calculateDate( temporal );
		return date.get( ChronoField.DAY_OF_MONTH );
	}

	/**
	 * @return
	 */
	private Temporal calculateDate( final TemporalAccessor temporal )
	{
		LocalDate date = LocalDate.of( temporal.get( ChronoField.YEAR ), month, 1 );
		if ( count > 0 )
		{
			date = date.with( TemporalAdjusters.firstDayOfMonth() );
			final int adjustment = weekDay.getValue() - date.getDayOfWeek().getValue();
			date = date.plusDays( adjustment < 0 ? 7 + adjustment : adjustment );
			if ( count > 1 )
			{
				date = date.plusWeeks( count - 1 );
			}
			return date;
		}
		else if ( count < 0 )
		{
			date = date.with( TemporalAdjusters.lastDayOfMonth() );
			final int adjustment = date.getDayOfWeek().getValue() - weekDay.getValue();
			date = date.minusDays( adjustment < 0 ? 7 + adjustment : adjustment );
			if ( count < -1 )
			{
				date = date.minusWeeks( Math.abs( count - 1 ) );
			}
			return date;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see java.time.temporal.TemporalAdjuster#adjustInto(java.time.temporal.Temporal)
	 */
	@Override
	public Temporal adjustInto( final Temporal temporal )
	{
		return calculateDate( temporal );
	}

}
