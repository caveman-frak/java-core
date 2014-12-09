/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time;


import java.time.DayOfWeek;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.util.EnumSet;
import java.util.Set;


/**
 * Determine if a date represents a working day.
 */
public class WorkingDays implements TemporalQuery< Boolean >
{

	private final Set< DayOfWeek > workingDays;

	/**
	 * Construct a new working day set using the passed set of days.
	 *
	 * @param workingDays
	 *            set of days to use
	 */
	public WorkingDays( final Set< DayOfWeek > workingDays )
	{
		super();

		this.workingDays = workingDays;
	}

	/*
	 * (non-Javadoc)
	 * @see java.time.temporal.TemporalQuery#queryFrom(java.time.temporal.TemporalAccessor)
	 */
	@Override
	public Boolean queryFrom( final TemporalAccessor temporal )
	{
		return workingDays.contains( DayOfWeek.from( temporal ) );
	}

	/**
	 * Construct a new working day set as used in the Western world, of monday to friday.
	 *
	 * @return working day set of monday to friday
	 */
	public static WorkingDays western()
	{
		return new WorkingDays( EnumSet.of( DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY, DayOfWeek.FRIDAY ) );
	}

	/**
	 * Construct a new working day set as used in the Middle Eastern world, of sunday to thursday.
	 *
	 * @return working day set of sunday to thursday
	 */
	public static WorkingDays middleEastern()
	{
		return new WorkingDays( EnumSet.of( DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY ) );
	}

}
