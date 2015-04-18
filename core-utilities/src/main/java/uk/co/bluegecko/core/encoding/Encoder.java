package uk.co.bluegecko.core.encoding;


/**
 * Simple interface for encoding and decoding operations.
 *
 */
public interface Encoder
{

	/**
	 * Encode the int array of data.
	 *
	 * @param data
	 *            array of data to encode
	 * @return encoded data
	 */
	public char[] encode( int[] data );

	/**
	 * Decode the char array of data.
	 *
	 * @param data
	 *            array of data to decode
	 * @return decoded data
	 */
	public int[] decode( char[] data );

}
