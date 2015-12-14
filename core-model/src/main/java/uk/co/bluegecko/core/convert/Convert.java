package uk.co.bluegecko.core.convert;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Date;


/**
 * Various object conversions.
 */
public interface Convert
{

	/**
	 * Convert java 8 date time objects to a simple {@code Date}
	 *
	 * @param t
	 *            the date time
	 * @return a date
	 */
	public static Date fromDateTime( final TemporalAccessor t )
	{
		if ( t instanceof LocalDate )
		{
			final Instant instant = Instant.from( ( ( LocalDate ) t ).atStartOfDay( ZoneOffset.UTC ) );
			return java.util.Date.from( instant );
		}
		else if ( t instanceof LocalTime )
		{
			final Instant instant = Instant
					.from( ( ( LocalTime ) t ).atOffset( ZoneOffset.UTC ).atDate( LocalDate.ofEpochDay( 0 ) ) );
			return java.util.Date.from( instant );
		}
		else if ( t instanceof LocalDateTime )
		{
			final Instant instant = Instant.from( ( ( LocalDateTime ) t ).atOffset( ZoneOffset.UTC ) );
			return java.util.Date.from( instant );
		}
		else if ( t instanceof OffsetTime )
		{
			final Instant instant = Instant.from( ( ( OffsetTime ) t ).atDate( LocalDate.ofEpochDay( 0 ) ) );
			return java.util.Date.from( instant );
		}
		else if ( t.isSupported( ChronoField.INSTANT_SECONDS ) )
		{
			final Instant instant = Instant.from( t );
			return java.util.Date.from( instant );
		}
		throw new IllegalArgumentException();
	}

}
