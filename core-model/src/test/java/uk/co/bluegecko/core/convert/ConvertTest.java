package uk.co.bluegecko.core.convert;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class ConvertTest
{

	private Calendar.Builder builder;
	private LocalDate localDate;
	private LocalTime localTime;

	@Before
	public final void setUp()
	{
		builder = new Calendar.Builder().setTimeZone( TimeZone.getTimeZone( "UTC" ) );
		localDate = LocalDate.of( 2001, Month.SEPTEMBER, 15 );
		localTime = LocalTime.of( 12, 30 );
	}

	@Test
	public final void testFromLocalDate()
	{
		assertThat( Convert.fromDateTime( localDate ), is( builder.setDate( 2001, 8, 15 ).build().getTime() ) );
	}

	@Test
	public final void testFromLocalTime()
	{
		assertThat( Convert.fromDateTime( localTime ), is( builder.setTimeOfDay( 12, 30, 0 ).build().getTime() ) );
	}

	@Test
	public final void testFromLocalDateTime()
	{
		assertThat( Convert.fromDateTime( LocalDateTime.of( localDate, localTime ) ),
				is( builder.setDate( 2001, 8, 15 ).setTimeOfDay( 12, 30, 0 ).build().getTime() ) );
	}

	@Test
	public final void testFromOffsetTime()
	{
		assertThat( Convert.fromDateTime( OffsetTime.of( localTime, ZoneOffset.UTC ) ),
				is( builder.setTimeOfDay( 12, 30, 0 ).build().getTime() ) );
	}

	@Test
	public final void testFromOffsetDateTime()
	{
		assertThat( Convert.fromDateTime( OffsetDateTime.of( localDate, localTime, ZoneOffset.UTC ) ),
				is( builder.setDate( 2001, 8, 15 ).setTimeOfDay( 12, 30, 0 ).build().getTime() ) );
	}

	@Test
	public final void testFromZonedDateTime()
	{
		assertThat( Convert.fromDateTime( ZonedDateTime.of( localDate, localTime, ZoneOffset.UTC ) ),
				is( builder.setDate( 2001, 8, 15 ).setTimeOfDay( 12, 30, 0 ).build().getTime() ) );
	}

}