package uk.co.bluegecko.core.encoding;


/**
 * <a href="http://en.wikipedia.org/wiki/Ascii85">ASCII85</a> (Base-85) encoding using the PDF Specification.
 *
 * Ensures the encoded string can be used in source code and XML files.
 *
 */
public class Ascii85 implements Encoder
{

	private static final int ENCODING_STEPS = 4;
	private static final int ENCODING_BASE = 85;
	private static final int ENCODING_DIVISOR = 85 * 85 * 85 * 85;

	private static final int DECODING_STEPS = 5;
	private static final int DECODING_BYTES = 8;
	private static final int DECODING_BASE = 256;
	private static final int DECODING_DIVISOR = 256 * 256 * 256;

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.encoding.Encoder#encode(int[])
	 */
	@Override
	public char[] encode( final int[] data )
	{
		final int size = data.length;
		int steps = size / ENCODING_STEPS;
		if ( steps * ENCODING_STEPS < size )
		{
			steps++;
		}
		final char[] encoded = new char[steps * DECODING_STEPS];

		int ints = 0;
		int chars = 0;

		while ( ints < size )
		{
			long value = 0;
			for ( int i = 0; i < ENCODING_STEPS; i++ )
			{
				value <<= DECODING_BYTES;
				if ( ints < size )
				{
					value += data[ints++];
				}
			}
			long divisor = ENCODING_DIVISOR;
			for ( int i = 0; i < DECODING_STEPS; i++ )
			{
				encoded[chars++] = ( char ) ( 33 + value / divisor % ENCODING_BASE );
				divisor /= ENCODING_BASE;
			}
		}

		return encoded;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.encoding.Encoder#decode(char[])
	 */
	@Override
	public int[] decode( final char[] data )
	{
		final int size = data.length;
		int steps = size / DECODING_STEPS;
		if ( steps * DECODING_STEPS < size )
		{
			steps++;
		}
		final int[] decoded = new int[steps * ENCODING_STEPS];

		int ints = 0;
		int chars = 0;

		while ( chars < size )
		{
			long value = 0;
			for ( int i = 0; i < DECODING_STEPS; i++ )
			{
				value *= ENCODING_BASE;
				value += chars < size ? data[chars++] - 33 : 'u';
			}
			long divisor = DECODING_DIVISOR;
			for ( int i = 0; i < ENCODING_STEPS; i++ )
			{
				decoded[ints++] = ( int ) ( value / divisor % DECODING_BASE );
				divisor >>= DECODING_BYTES;
			}
		}

		return decoded;
	}

}
