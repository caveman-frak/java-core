/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;

import org.junit.Before;
import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class TemporalRangeTest implements TemporalTestBase
{

	private Temporal start;
	private Temporal end;
	private TemporalAmount amount;
	private TemporalRange range;

	@Before
	public final void setUp()
	{
		start = LocalDate.of( YEAR, Month.JUNE, 12 );
		end = LocalDate.of( YEAR, Month.JULY, 16 );
		amount = Period.ofDays( 1 );
	}

	@Test
	public final void testInclusiveBefore()
	{
		range = TemporalRange.inclusive( start, end );
		assertThat( range.queryFrom( start ), is( true ) );
		assertThat( range.queryFrom( start.minus( amount ) ), is( false ) );
		assertThat( range.queryFrom( start.plus( amount ) ), is( true ) );
	}

	@Test
	public final void testInclusiveAfter()
	{
		range = TemporalRange.inclusive( start, end );
		assertThat( range.queryFrom( end ), is( true ) );
		assertThat( range.queryFrom( end.minus( amount ) ), is( true ) );
		assertThat( range.queryFrom( end.plus( amount ) ), is( false ) );
	}

	@Test
	public final void testExclusiveBefore()
	{
		range = TemporalRange.exclusive( start, end );
		assertThat( range.queryFrom( start ), is( false ) );
		assertThat( range.queryFrom( start.minus( amount ) ), is( false ) );
		assertThat( range.queryFrom( start.plus( amount ) ), is( true ) );
	}

	@Test
	public final void testExclusiveAfter()
	{
		range = TemporalRange.exclusive( start, end );
		assertThat( range.queryFrom( end ), is( false ) );
		assertThat( range.queryFrom( end.minus( amount ) ), is( true ) );
		assertThat( range.queryFrom( end.plus( amount ) ), is( false ) );
	}

	@Test
	public final void testFuture()
	{
		range = TemporalRange.future( start );
		assertThat( range.queryFrom( YEAR_START ), is( false ) );
		assertThat( range.queryFrom( YEAR_END ), is( true ) );
	}

	@Test
	public final void testExpiry()
	{
		range = TemporalRange.expiry( end );
		assertThat( range.queryFrom( YEAR_START ), is( true ) );
		assertThat( range.queryFrom( YEAR_END ), is( false ) );
	}

}
