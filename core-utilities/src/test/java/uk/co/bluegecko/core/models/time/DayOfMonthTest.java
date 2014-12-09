/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalQuery;

import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class DayOfMonthTest implements TemporalTestBase
{

	@Test
	public final void testChristmasDayFromStart()
	{
		final MonthDay christmasDay = MonthDay.of( Month.DECEMBER, 25 );
		final Temporal date = new DayOfMonth( christmasDay ).adjustInto( YEAR_START );
		assertThat( date.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( date.get( ChronoField.MONTH_OF_YEAR ), is( Month.DECEMBER.getValue() ) );
		assertThat( date.get( ChronoField.DAY_OF_MONTH ), is( 25 ) );
		assertThat( date.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.TUESDAY.getValue() ) );
	}

	@Test
	public final void testChristmasDayFromEnd()
	{
		final MonthDay christmasDay = MonthDay.of( Month.DECEMBER, 25 );
		final Temporal date = new DayOfMonth( christmasDay ).adjustInto( YEAR_END );
		assertThat( date.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( date.get( ChronoField.MONTH_OF_YEAR ), is( Month.DECEMBER.getValue() ) );
		assertThat( date.get( ChronoField.DAY_OF_MONTH ), is( 25 ) );
		assertThat( date.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.TUESDAY.getValue() ) );
	}

	@Test
	public final void testChristmasDayQuery()
	{
		final MonthDay christmasDay = MonthDay.of( Month.DECEMBER, 25 );
		final TemporalQuery< Boolean > isChristmas = new DayOfMonth( christmasDay );
		assertThat( isChristmas.queryFrom( LocalDate.of( YEAR, Month.DECEMBER, 25 ) ), is( true ) );
		assertThat( isChristmas.queryFrom( LocalDate.of( YEAR, Month.DECEMBER, 26 ) ), is( false ) );
	}

	@Test
	public final void testLastDayOfFebLeapYear()
	{
		final MonthDay lastDayOfFeb = MonthDay.of( Month.FEBRUARY, 29 );
		final Temporal date = new DayOfMonth( lastDayOfFeb ).adjustInto( YEAR_START );
		assertThat( date.get( ChronoField.YEAR ), is( YEAR ) );
		assertThat( date.get( ChronoField.MONTH_OF_YEAR ), is( Month.FEBRUARY.getValue() ) );
		assertThat( date.get( ChronoField.DAY_OF_MONTH ), is( 29 ) );
		assertThat( date.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.WEDNESDAY.getValue() ) );
	}

	@Test
	public final void testLastDayOfFeb()
	{
		final MonthDay lastDayOfFeb = MonthDay.of( Month.FEBRUARY, 29 );
		final Temporal date = new DayOfMonth( lastDayOfFeb ).adjustInto( YEAR_START.minusDays( 1 ) );
		assertThat( date.get( ChronoField.YEAR ), is( 2011 ) );
		assertThat( date.get( ChronoField.MONTH_OF_YEAR ), is( Month.FEBRUARY.getValue() ) );
		assertThat( date.get( ChronoField.DAY_OF_MONTH ), is( 28 ) );
		assertThat( date.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.MONDAY.getValue() ) );
	}

	@Test
	public final void testFeb29thQuery()
	{
		final MonthDay lastDayOfFeb = MonthDay.of( Month.FEBRUARY, 29 );
		final TemporalQuery< Boolean > isFeb29th = new DayOfMonth( lastDayOfFeb );
		assertThat( isFeb29th.queryFrom( LocalDate.of( YEAR, Month.FEBRUARY, 29 ) ), is( true ) );
		assertThat( isFeb29th.queryFrom( LocalDate.of( YEAR - 1, Month.FEBRUARY, 28 ) ), is( false ) );
	}

}
