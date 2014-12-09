/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalQuery;

import org.junit.Before;
import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class WorkingDaysTest implements TemporalTestBase
{

	private TemporalQuery< Boolean > western;
	private TemporalQuery< Boolean > middleEastern;

	@Before
	public final void setUp()
	{
		western = WorkingDays.western();
		middleEastern = WorkingDays.middleEastern();
	}

	@Test
	public final void testSaturday()
	{
		final LocalDate date = LocalDate.of( YEAR, Month.MARCH, 17 );
		assertThat( date.getDayOfWeek(), is( DayOfWeek.SATURDAY ) );
		assertThat( western.queryFrom( date ), is( false ) );
		assertThat( middleEastern.queryFrom( date ), is( false ) );
	}

	@Test
	public final void testSunday()
	{
		final LocalDate date = LocalDate.of( YEAR, Month.MARCH, 18 );
		assertThat( date.getDayOfWeek(), is( DayOfWeek.SUNDAY ) );
		assertThat( western.queryFrom( date ), is( false ) );
		assertThat( middleEastern.queryFrom( date ), is( true ) );
	}

	@Test
	public final void testMonday()
	{
		final LocalDate date = LocalDate.of( YEAR, Month.MARCH, 19 );
		assertThat( date.getDayOfWeek(), is( DayOfWeek.MONDAY ) );
		assertThat( western.queryFrom( date ), is( true ) );
		assertThat( middleEastern.queryFrom( date ), is( true ) );
	}

	@Test
	public final void testTuesday()
	{
		final LocalDate date = LocalDate.of( YEAR, Month.MARCH, 20 );
		assertThat( date.getDayOfWeek(), is( DayOfWeek.TUESDAY ) );
		assertThat( western.queryFrom( date ), is( true ) );
		assertThat( middleEastern.queryFrom( date ), is( true ) );
	}

	@Test
	public final void testWednesday()
	{
		final LocalDate date = LocalDate.of( YEAR, Month.MARCH, 21 );
		assertThat( date.getDayOfWeek(), is( DayOfWeek.WEDNESDAY ) );
		assertThat( western.queryFrom( date ), is( true ) );
		assertThat( middleEastern.queryFrom( date ), is( true ) );
	}

	@Test
	public final void testThursday()
	{
		final LocalDate date = LocalDate.of( YEAR, Month.MARCH, 22 );
		assertThat( date.getDayOfWeek(), is( DayOfWeek.THURSDAY ) );
		assertThat( western.queryFrom( date ), is( true ) );
		assertThat( middleEastern.queryFrom( date ), is( true ) );
	}

	@Test
	public final void testFriday()
	{
		final LocalDate date = LocalDate.of( YEAR, Month.MARCH, 23 );
		assertThat( date.getDayOfWeek(), is( DayOfWeek.FRIDAY ) );
		assertThat( western.queryFrom( date ), is( true ) );
		assertThat( middleEastern.queryFrom( date ), is( false ) );
	}

}
