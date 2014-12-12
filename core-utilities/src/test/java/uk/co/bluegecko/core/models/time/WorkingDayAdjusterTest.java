/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;

import org.junit.Before;
import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class WorkingDayAdjusterTest implements TemporalTestBase
{

	private WorkingDays western;
	private WorkingDayAdjuster following;
	private WorkingDayAdjuster previous;
	private WorkingDayAdjuster modifiedFollowing;
	private WorkingDayAdjuster modifiedPrevious;

	@Before
	public final void setUp()
	{
		western = WorkingDays.western();
		following = WorkingDayAdjuster.following( western );
		previous = WorkingDayAdjuster.previous( western );
		modifiedFollowing = WorkingDayAdjuster.modifiedFollowing( western );
		modifiedPrevious = WorkingDayAdjuster.modifiedPrevious( western );
	}

	@Test
	public final void testFollowingUnadjusted()
	{
		final Temporal date = LocalDate.of( YEAR, Month.DECEMBER, 5 );
		assertThat( date.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( date.get( ChronoField.MONTH_OF_YEAR ), is( Month.DECEMBER.getValue() ) );
		assertThat( date.get( ChronoField.DAY_OF_MONTH ), is( 5 ) );
		assertThat( date.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.WEDNESDAY.getValue() ) );
		final Temporal result = following.adjustInto( date );
		assertThat( result.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( result.get( ChronoField.MONTH_OF_YEAR ), is( Month.DECEMBER.getValue() ) );
		assertThat( result.get( ChronoField.DAY_OF_MONTH ), is( 5 ) );
		assertThat( result.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.WEDNESDAY.getValue() ) );
	}

	@Test
	public final void testFollowing()
	{
		final Temporal date = LocalDate.of( YEAR, Month.DECEMBER, 1 );
		assertThat( date.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( date.get( ChronoField.MONTH_OF_YEAR ), is( Month.DECEMBER.getValue() ) );
		assertThat( date.get( ChronoField.DAY_OF_MONTH ), is( 1 ) );
		assertThat( date.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.SATURDAY.getValue() ) );
		final Temporal result = following.adjustInto( date );
		assertThat( result.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( result.get( ChronoField.MONTH_OF_YEAR ), is( Month.DECEMBER.getValue() ) );
		assertThat( result.get( ChronoField.DAY_OF_MONTH ), is( 3 ) );
		assertThat( result.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.MONDAY.getValue() ) );
	}

	@Test
	public final void testPreviousUnadjusted()
	{
		final Temporal date = LocalDate.of( YEAR, Month.DECEMBER, 5 );
		assertThat( date.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( date.get( ChronoField.MONTH_OF_YEAR ), is( Month.DECEMBER.getValue() ) );
		assertThat( date.get( ChronoField.DAY_OF_MONTH ), is( 5 ) );
		assertThat( date.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.WEDNESDAY.getValue() ) );
		final Temporal result = previous.adjustInto( date );
		assertThat( result.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( result.get( ChronoField.MONTH_OF_YEAR ), is( Month.DECEMBER.getValue() ) );
		assertThat( result.get( ChronoField.DAY_OF_MONTH ), is( 5 ) );
		assertThat( result.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.WEDNESDAY.getValue() ) );
	}

	@Test
	public final void testPrevious()
	{
		final Temporal date = LocalDate.of( YEAR, Month.DECEMBER, 1 );
		assertThat( date.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( date.get( ChronoField.MONTH_OF_YEAR ), is( Month.DECEMBER.getValue() ) );
		assertThat( date.get( ChronoField.DAY_OF_MONTH ), is( 1 ) );
		assertThat( date.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.SATURDAY.getValue() ) );
		final Temporal result = previous.adjustInto( date );
		assertThat( result.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( result.get( ChronoField.MONTH_OF_YEAR ), is( Month.NOVEMBER.getValue() ) );
		assertThat( result.get( ChronoField.DAY_OF_MONTH ), is( 30 ) );
		assertThat( result.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.FRIDAY.getValue() ) );
	}

	@Test
	public final void testModifiedPrevious()
	{
		final Temporal date = LocalDate.of( YEAR, Month.DECEMBER, 1 );
		assertThat( date.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( date.get( ChronoField.MONTH_OF_YEAR ), is( Month.DECEMBER.getValue() ) );
		assertThat( date.get( ChronoField.DAY_OF_MONTH ), is( 1 ) );
		assertThat( date.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.SATURDAY.getValue() ) );
		final Temporal result = modifiedPrevious.adjustInto( date );
		assertThat( result.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( result.get( ChronoField.MONTH_OF_YEAR ), is( Month.DECEMBER.getValue() ) );
		assertThat( result.get( ChronoField.DAY_OF_MONTH ), is( 3 ) );
		assertThat( result.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.MONDAY.getValue() ) );
	}

	@Test
	public final void testModifiedFollowing()
	{
		final Temporal date = LocalDate.of( YEAR, Month.SEPTEMBER, 30 );
		assertThat( date.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( date.get( ChronoField.MONTH_OF_YEAR ), is( Month.SEPTEMBER.getValue() ) );
		assertThat( date.get( ChronoField.DAY_OF_MONTH ), is( 30 ) );
		assertThat( date.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.SUNDAY.getValue() ) );
		final Temporal result = modifiedFollowing.adjustInto( date );
		assertThat( result.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( result.get( ChronoField.MONTH_OF_YEAR ), is( Month.SEPTEMBER.getValue() ) );
		assertThat( result.get( ChronoField.DAY_OF_MONTH ), is( 28 ) );
		assertThat( result.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.FRIDAY.getValue() ) );
	}

}
