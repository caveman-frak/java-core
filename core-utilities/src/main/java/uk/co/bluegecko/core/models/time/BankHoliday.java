/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time;


import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;


/**
 * Adjusts a date to fall on the next working day.
 */
public class BankHoliday implements TemporalAdjuster
{

	private final WorkingDayAdjuster workingDays;
	private final TemporalAdjuster holiday;

	/**
	 * Construct a new BankHoliday instance.
	 *
	 * @param workingDays
	 *            working day adjuster
	 * @param holiday
	 *            holiday adjuster
	 */
	public BankHoliday( final WorkingDayAdjuster workingDays, final TemporalAdjuster holiday )
	{
		super();

		this.workingDays = workingDays;
		this.holiday = holiday;
	}

	/**
	 * Construct a new BankHoliday instance.
	 *
	 * @param workingDays
	 *            working day set
	 * @param holiday
	 *            holiday adjuster
	 */
	public BankHoliday( final WorkingDays workingDays, final TemporalAdjuster holiday )
	{
		this( WorkingDayAdjuster.following( workingDays ), holiday );
	}

	/*
	 * (non-Javadoc)
	 * @see java.time.temporal.TemporalAdjuster#adjustInto(java.time.temporal.Temporal)
	 */
	@Override
	public Temporal adjustInto( final Temporal temporal )
	{
		return holiday.adjustInto( temporal ).with( workingDays );
	}

}
