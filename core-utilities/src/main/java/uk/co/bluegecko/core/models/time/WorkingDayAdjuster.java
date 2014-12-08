/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time;


import java.time.Period;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;


/**
 * Adjust a date to a working day.
 */
public class WorkingDayAdjuster implements TemporalAdjuster
{

	private final WorkingDays workingDay;
	private final TemporalAmount amount;

	/**
	 * Construct a new working day adjuster, using the set of working days and the amount.
	 * 
	 * @param workingDay
	 *            working day set
	 * @param amount
	 *            amount to move date by
	 */
	public WorkingDayAdjuster( final WorkingDays workingDay, final TemporalAmount amount )
	{
		super();

		this.workingDay = workingDay;
		this.amount = amount;
	}

	/*
	 * (non-Javadoc)
	 * @see java.time.temporal.TemporalAdjuster#adjustInto(java.time.temporal.Temporal)
	 */
	@Override
	public Temporal adjustInto( final Temporal temporal )
	{
		Temporal date = temporal;
		while ( !workingDay.queryFrom( temporal ) )
		{
			date = date.plus( amount );
		}
		return date;
	}

	/**
	 * Calculate the next working day.
	 *
	 * @param workingDay
	 *            working day set to use
	 * @return next working day
	 */
	public static WorkingDayAdjuster next( final WorkingDays workingDay )
	{
		return new WorkingDayAdjuster( workingDay, Period.ofDays( 1 ) );
	}

	/**
	 * Calculate the previous working day.
	 *
	 * @param workingDay
	 *            working day set to use
	 * @return previous working day
	 */
	public static WorkingDayAdjuster previous( final WorkingDays workingDay )
	{
		return new WorkingDayAdjuster( workingDay, Period.ofDays( -1 ) );
	}

}
