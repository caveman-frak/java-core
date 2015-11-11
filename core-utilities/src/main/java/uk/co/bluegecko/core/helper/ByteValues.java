package uk.co.bluegecko.core.helper;


import java.text.NumberFormat;


/**
 * Utility class for scaling large byte values.
 */
public final class ByteValues
{

	/**
	 * Byte multipliers
	 */
	public static final String[] BYTE_SUFFIX =
		{ "B", "KB", "MB", "TB", "PB" };

	private ByteValues()
	{}

	/**
	 * Convert long to a string, of just the significant portion, including a scalar
	 *
	 * @param amount
	 *            amount to scale
	 * @param suffix
	 * @return scaled amount as string
	 */
	public static String scale( final long amount, final String... suffix )
	{
		if ( amount < 0 )
		{
			return "Unknown";
		}
		long l = amount;
		int index = 0;
		while ( l > 1023 && index++ < suffix.length )
		{
			l >>= 10;
		}

		return NumberFormat.getIntegerInstance().format( l ) + suffix[index];
	}
}
