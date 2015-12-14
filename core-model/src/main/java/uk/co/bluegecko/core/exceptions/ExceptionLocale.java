package uk.co.bluegecko.core.exceptions;


import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Locale;

import uk.co.bluegecko.core.convert.Convert;


/**
 * Holder interface for default locale for exceptions.
 *
 * @author tim
 *
 */
public interface ExceptionLocale
{

	/**
	 * The default locale to use for exceptions.
	 */
	public static final Locale LOCALE = Locale.ENGLISH;

	/**
	 * Helper method to convert java 8 date / time classes to be useable in message formatter.
	 *
	 * @param array
	 *            array to update
	 * @return updated array
	 */
	public static Object[] updateDateTimeToDate( final Object... array )
	{
		final Object[] result = Arrays.copyOf( array, array.length );
		for ( int i = 0; i < result.length; i++ )
		{
			final Object o = result[i];
			if ( o instanceof TemporalAccessor )
			{
				result[i] = Convert.fromDateTime( ( TemporalAccessor ) o );
			}
		}
		return result;
	}

}
