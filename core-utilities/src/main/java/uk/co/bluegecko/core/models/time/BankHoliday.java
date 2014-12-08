/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time;


import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;


/**
 *
 */
public class BankHoliday implements TemporalAdjuster
{

	private final WorkingDayAdjuster workingDays;
	private final TemporalAdjuster adjuster;

	/**
	 * @param workingDays
	 * @param adjuster
	 */
	public BankHoliday( final WorkingDayAdjuster workingDays, final TemporalAdjuster adjuster )
	{
		super();

		this.workingDays = workingDays;
		this.adjuster = adjuster;
	}

	/**
	 * @param workingDays
	 * @param adjuster
	 */
	public BankHoliday( final WorkingDays workingDays, final TemporalAdjuster adjuster )
	{
		super();

		this.workingDays = WorkingDayAdjuster.next( workingDays );
		this.adjuster = adjuster;
	}

	/*
	 * (non-Javadoc)
	 * @see java.time.temporal.TemporalAdjuster#adjustInto(java.time.temporal.Temporal)
	 */
	@Override
	public Temporal adjustInto( final Temporal temporal )
	{
		return workingDays.adjustInto( adjuster.adjustInto( temporal ) );
	}

}
