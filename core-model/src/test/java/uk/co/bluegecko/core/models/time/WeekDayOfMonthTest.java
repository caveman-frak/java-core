/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalQuery;

import org.junit.Test;


public class WeekDayOfMonthTest implements TemporalTestBase
{

	@Test
	public final void testFirstMondayInMarch()
	{
		final TemporalAdjuster firstMondayInMarch = new WeekDayOfMonth( Month.MARCH, DayOfWeek.MONDAY );
		final Temporal date = firstMondayInMarch.adjustInto( YEAR_START );
		assertThat( date.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( date.get( ChronoField.MONTH_OF_YEAR ), is( Month.MARCH.getValue() ) );
		assertThat( date.get( ChronoField.DAY_OF_MONTH ), is( 5 ) );
		assertThat( date.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.MONDAY.getValue() ) );
	}

	@Test
	public final void testLastMondayInMarch()
	{
		final TemporalAdjuster lastMondayInMarch = new WeekDayOfMonth( Month.MARCH, DayOfWeek.MONDAY, -1 );
		final Temporal date = lastMondayInMarch.adjustInto( YEAR_START );
		assertThat( date.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( date.get( ChronoField.MONTH_OF_YEAR ), is( Month.MARCH.getValue() ) );
		assertThat( date.get( ChronoField.DAY_OF_MONTH ), is( 26 ) );
		assertThat( date.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.MONDAY.getValue() ) );
	}

	@Test
	public final void testFirstMondayInMarchQuery()
	{
		final TemporalQuery< Boolean > firstMondayInMarch = new WeekDayOfMonth( Month.MARCH, DayOfWeek.MONDAY );
		assertThat( firstMondayInMarch.queryFrom( LocalDate.of( YEAR, Month.MARCH, 5 ) ), is( true ) );
	}

	@Test
	public final void testLastMondayInMarchQuery()
	{
		final TemporalQuery< Boolean > lastMondayInMarch = new WeekDayOfMonth( Month.MARCH, DayOfWeek.MONDAY, -1 );
		assertThat( lastMondayInMarch.queryFrom( LocalDate.of( YEAR, Month.MARCH, 26 ) ), is( true ) );
	}

	@Test
	public final void testFirstWednesdayInFebruary()
	{
		final TemporalAdjuster firstWednesdayInFebruary = new WeekDayOfMonth( Month.FEBRUARY, DayOfWeek.WEDNESDAY );
		final Temporal date = firstWednesdayInFebruary.adjustInto( YEAR_START );
		assertThat( date.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( date.get( ChronoField.MONTH_OF_YEAR ), is( Month.FEBRUARY.getValue() ) );
		assertThat( date.get( ChronoField.DAY_OF_MONTH ), is( 1 ) );
		assertThat( date.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.WEDNESDAY.getValue() ) );
	}

	@Test
	public final void testLastWednesdayInFebruaryLeapYear()
	{
		final TemporalAdjuster lastWednesdayInFebruary = new WeekDayOfMonth( Month.FEBRUARY, DayOfWeek.WEDNESDAY, -1 );
		final Temporal date = lastWednesdayInFebruary.adjustInto( YEAR_START );
		assertThat( date.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( date.get( ChronoField.MONTH_OF_YEAR ), is( Month.FEBRUARY.getValue() ) );
		assertThat( date.get( ChronoField.DAY_OF_MONTH ), is( 29 ) );
		assertThat( date.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.WEDNESDAY.getValue() ) );
	}

}
