/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.ChronoField;

import org.junit.Before;
import org.junit.Test;


public class BankHolidayTest implements TemporalTestBase
{

	private BankHoliday christmasHoliday;
	private DayOfMonth christmasDay;

	@Before
	public final void setUp()
	{
		christmasDay = new DayOfMonth( MonthDay.of( Month.DECEMBER, 25 ) );
		christmasHoliday = new BankHoliday( WorkingDays.western(), christmasDay );
	}

	@Test
	public final void testHolidayInWeek()
	{
		assertThat( christmasDay.adjustInto( YEAR_START )
				.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.TUESDAY.getValue() ) );
		assertThat( christmasHoliday.adjustInto( YEAR_START )
				.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.TUESDAY.getValue() ) );
	}

	@Test
	public final void testHolidayInWeekend()
	{
		final LocalDate yearStart2010 = YEAR_START.minusYears( 2 );
		assertThat( christmasDay.adjustInto( yearStart2010 )
				.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.SATURDAY.getValue() ) );
		assertThat( christmasHoliday.adjustInto( yearStart2010 )
				.get( ChronoField.DAY_OF_WEEK ), is( DayOfWeek.MONDAY.getValue() ) );
	}

}
