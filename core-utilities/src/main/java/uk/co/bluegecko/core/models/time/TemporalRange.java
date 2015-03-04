/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time;


import java.time.chrono.ChronoLocalDate;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;


/**
 * Determines if a date is included between the start and end points.
 */
public class TemporalRange implements TemporalQuery< Boolean >
{

	private final ChronoLocalDate start;
	private final ChronoLocalDate end;
	private final boolean inclusiveStart;
	private final boolean inclusiveEnd;

	/**
	 * Build a range of dates.
	 *
	 * @param start
	 *            start date
	 * @param inclusiveStart
	 *            include start date
	 * @param end
	 *            end date
	 * @param inclusiveEnd
	 *            include end date
	 */
	public TemporalRange( final TemporalAccessor start, final boolean inclusiveStart, final TemporalAccessor end,
			final boolean inclusiveEnd )
	{
		super();

		this.start = start != null ? ChronoLocalDate.from( start ) : null;
		this.inclusiveStart = inclusiveStart;
		this.end = end != null ? ChronoLocalDate.from( end ) : null;
		this.inclusiveEnd = inclusiveEnd;
	}

	/*
	 * (non-Javadoc)
	 * @see java.time.temporal.TemporalQuery#queryFrom(java.time.temporal.TemporalAccessor)
	 */
	@Override
	public Boolean queryFrom( final TemporalAccessor temporal )
	{
		return chechStartDate( temporal ) && checkEndDate( temporal );
	}

	/**
	 * Check if the date is after the start.
	 * Always true if start is null.
	 *
	 * @param temporal
	 *            the date
	 * @return true if after start
	 */
	private boolean chechStartDate( final TemporalAccessor temporal )
	{
		if ( start == null )
			return true;
		final ChronoLocalDate instant = ChronoLocalDate.from( temporal );
		return inclusiveStart ? !start.isAfter( instant ) : start.isBefore( instant );
	}

	/**
	 * Check if the date is before the end.
	 * Always true if end is null.
	 *
	 * @param temporal
	 *            the date
	 * @return true if before end
	 */
	private boolean checkEndDate( final TemporalAccessor temporal )
	{
		if ( end == null )
			return true;
		final ChronoLocalDate instant = ChronoLocalDate.from( temporal );
		return inclusiveEnd ? !end.isBefore( instant ) : end.isAfter( instant );
	}

	/**
	 * Return an inclusive range between the start and end dates.
	 *
	 * @param start
	 *            start date
	 * @param end
	 *            end date
	 * @return true if within range
	 */
	public static TemporalRange inclusive( final TemporalAccessor start, final TemporalAccessor end )
	{
		return new TemporalRange( start, true, end, true );
	}

	/**
	 * Return an exclusive range between the start and end dates.
	 *
	 * @param start
	 *            start date
	 * @param end
	 *            end date
	 * @return true if within range
	 */
	public static TemporalRange exclusive( final TemporalAccessor start, final TemporalAccessor end )
	{
		return new TemporalRange( start, false, end, false );
	}

	/**
	 * Return an future range between the start date and the future.
	 *
	 * @param start
	 *            start date
	 * @return true if within range
	 */
	public static TemporalRange future( final TemporalAccessor start )
	{
		return new TemporalRange( start, true, null, true );
	}

	/**
	 * Return an expiry range between the past and the end date.
	 *
	 * @param end
	 *            end date
	 * @return true if within range
	 */
	public static TemporalRange expiry( final TemporalAccessor end )
	{
		return new TemporalRange( null, true, end, true );
	}
}
