/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time;


import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;


/**
 * Adjust a date to be a working day, by either moving forward or backwards.
 */
public class WorkingDayAdjuster implements TemporalAdjuster
{

	private final WorkingDays workingDay;
	private final TemporalAmount amount;
	private final boolean stayInMonth;

	/**
	 * Construct a new working day adjuster, using the set of working days and the amount.
	 *
	 * @param workingDay
	 *            working day set
	 * @param amount
	 *            amount to move date by
	 * @param stayInMonth
	 *            keep adjustment within same month
	 */
	public WorkingDayAdjuster( final WorkingDays workingDay, final TemporalAmount amount, final boolean stayInMonth )
	{
		super();

		this.workingDay = workingDay;
		this.amount = amount;
		this.stayInMonth = stayInMonth;
	}

	/*
	 * (non-Javadoc)
	 * @see java.time.temporal.TemporalAdjuster#adjustInto(java.time.temporal.Temporal)
	 */
	@Override
	public Temporal adjustInto( final Temporal temporal )
	{
		final Month month = Month.from( temporal );
		Temporal date = adjustInto( temporal, amount );
		if ( stayInMonth && month != Month.from( date ) )
		{
			date = adjustInto( temporal, reverseAmout() );
		}
		return date;
	}

	private Period reverseAmout()
	{
		return Period.ofDays( ( int ) ( -1 * amount.get( ChronoUnit.DAYS ) ) );
	}

	private Temporal adjustInto( final Temporal temporal, final TemporalAmount amount )
	{
		Temporal date = temporal;
		while ( !workingDay.queryFrom( date ) )
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
	public static WorkingDayAdjuster following( final WorkingDays workingDay )
	{
		return new WorkingDayAdjuster( workingDay, Period.ofDays( 1 ), false );
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
		return new WorkingDayAdjuster( workingDay, Period.ofDays( -1 ), false );
	}

	/**
	 * Calculate the next working day.
	 *
	 * @param workingDay
	 *            working day set to use
	 * @return next working day
	 */
	public static WorkingDayAdjuster modifiedFollowing( final WorkingDays workingDay )
	{
		return new WorkingDayAdjuster( workingDay, Period.ofDays( 1 ), true );
	}

	/**
	 * Calculate the previous working day.
	 *
	 * @param workingDay
	 *            working day set to use
	 * @return previous working day
	 */
	public static WorkingDayAdjuster modifiedPrevious( final WorkingDays workingDay )
	{
		return new WorkingDayAdjuster( workingDay, Period.ofDays( -1 ), true );
	}

}
