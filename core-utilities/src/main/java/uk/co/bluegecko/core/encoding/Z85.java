package uk.co.bluegecko.core.encoding;


/**
 * ASCII85 (Base-85) encoding using the <a href="http://rfc.zeromq.org/spec:32">ZeroMQ encoding algorithm</a>.
 *
 * Ensures the encoded string can be used in source code and XML files.
 *
 */
public class Z85 implements Encoder
{

	private static final int ENCODING_STEPS = 4;
	private static final int ENCODING_BASE = 85;
	private static final int ENCODING_DIVISOR = 85 * 85 * 85 * 85;

	private static final int DECODING_STEPS = 5;
	private static final int DECODING_BYTES = 8;
	private static final int DECODING_BASE = 256;
	private static final int DECODING_DIVISOR = 256 * 256 * 256;

	// Maps base 256 to base 85
	private static char[] ENCODER =
		{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
				'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
				'.', '-', ':', '+', '=', '^', '!', '/', '*', '?', '&', '<', '>', '(', ')', '[', ']', '{', '}', '@',
				'%', '$', '#', '"' };

	// Maps base 85 to base 256
	// We chop off lower 32 and higher 128 ranges
	private static int[] DECODER =
		{ 0x00, 0x44, 0x00, 0x54, 0x53, 0x52, 0x48, 0x00, 0x4B, 0x4C, 0x46, 0x41, 0x00, 0x3F, 0x3E, 0x45, 0x00, 0x01,
				0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x40, 0x00, 0x49, 0x42, 0x4A, 0x47, 0x51, 0x24, 0x25,
				0x26, 0x27, 0x28, 0x29, 0x2A, 0x2B, 0x2C, 0x2D, 0x2E, 0x2F, 0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36,
				0x37, 0x38, 0x39, 0x3A, 0x3B, 0x3C, 0x3D, 0x4D, 0x00, 0x4E, 0x43, 0x00, 0x00, 0x0A, 0x0B, 0x0C, 0x0D,
				0x0E, 0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x1A, 0x1B, 0x1C, 0x1D, 0x1E,
				0x1F, 0x20, 0x21, 0x22, 0x23, 0x4F, 0x00, 0x50, 0x00, 0x00 };

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.encoding.Encoder#encode(int[])
	 */
	@Override
	public char[] encode( final int[] data )
	{
		/*
			char * Z85_encode (byte *data, size_t size)
			{
			    //  Accepts only byte arrays bounded to 4 bytes
			    if (size % 4)
			        return NULL;

			    size_t encoded_size = size * 5 / 4;
			    char *encoded = malloc (encoded_size + 1);
			    uint char_nbr = 0;
			    uint byte_nbr = 0;
			    uint32_t value = 0;
			    while (byte_nbr < size) {
			        //  Accumulate value in base 256 (binary)
			        value = value * 256 + data [byte_nbr++];
			        if (byte_nbr % 4 == 0) {
			            //  Output value in base 85
			            uint divisor = 85 * 85 * 85 * 85;
			            while (divisor) {
			                encoded [char_nbr++] = encoder [value / divisor % 85];
			                divisor /= 85;
			            }
			            value = 0;
			        }
			    }
			    assert (char_nbr == encoded_size);
			    encoded [char_nbr] = 0;
			    return encoded;
			}
		 */
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
				encoded[chars++] = ENCODER[( int ) ( value / divisor % ENCODING_BASE )];
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
		/*
			byte * Z85_decode (char *string)
			{
			    //  Accepts only strings bounded to 5 bytes
			    if (strlen (string) % 5)
			        return NULL;

			    size_t decoded_size = strlen (string) * 4 / 5;
			    byte *decoded = malloc (decoded_size);

			    uint byte_nbr = 0;
			    uint char_nbr = 0;
			    uint32_t value = 0;
			    while (char_nbr < strlen (string)) {
			        //  Accumulate value in base 85
			        value = value * 85 + decoder [(byte) string [char_nbr++] - 32];
			        if (char_nbr % 5 == 0) {
			            //  Output value in base 256
			            uint divisor = 256 * 256 * 256;
			            while (divisor) {
			                decoded [byte_nbr++] = value / divisor % 256;
			                divisor /= 256;
			            }
			            value = 0;
			        }
			    }
			    assert (byte_nbr == decoded_size);
			    return decoded;
			}
		 */
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
				final int index = chars < size ? data[chars++] - 32 : 0;
				value += DECODER[index];
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
