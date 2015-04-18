package uk.co.bluegecko.core.encoding;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


@SuppressWarnings( "javadoc" )
@Ignore
public class Ascii85Test
{

	private static final int[] SMALL_DATA = new int[]
			{ 0x86, 0x4F, 0xD2, 0x6F, 0xB5, 0x59, 0xF7, 0x5B };

	private static final int[] LARGE_DATA = new int[]
			{ 0x8E, 0x0B, 0xDD, 0x69, 0x76, 0x28, 0xB9, 0x1D, 0x8F, 0x24, 0x55, 0x87, 0xEE, 0x95, 0xC5, 0xB0, 0x4D, 0x48,
				0x96, 0x3F, 0x79, 0x25, 0x98, 0x77, 0xB4, 0x9C, 0xD9, 0x06, 0x3A, 0xEA, 0xD3, 0xB7 };

	private Encoder encoder;

	@Before
	public void setUp()
	{
		encoder = new Ascii85();
	}

	@Test
	public void testEmpty()
	{
		final char[] encoded = encoder.encode( new int[0] );

		assertThat( encoded.length, is( 0 ) );

		final int[] decoded = encoder.decode( encoded );

		assertThat( decoded.length, is( 0 ) );
	}

	@Test
	public void testSmall()
	{
		final char[] encoded = encoder.encode( SMALL_DATA );

		assertThat( encoded.length, is( 10 ) );
		assertThat( String.valueOf( encoded ), is( "HelloWorld" ) );

		final int[] decoded = encoder.decode( encoded );

		assertThat( decoded.length, is( 8 ) );
		assertThat( decoded, is( SMALL_DATA ) );
	}

	@Test
	public void testLarge()
	{
		final char[] encoded = encoder.encode( LARGE_DATA );

		assertThat( encoded.length, is( 40 ) );
		assertThat( String.valueOf( encoded ), is( "JTKVSB%%)wK0E.X)V>+}o?pNmC{O&4W4b!Ni{Lh6" ) );
		final int[] decoded = encoder.decode( encoded );

		assertThat( decoded.length, is( 32 ) );
		assertThat( decoded, is( LARGE_DATA ) );
	}

	@Test
	public void testShortEncodingInput()
	{
		final char[] encoded = encoder.encode( new int[]
				{ 0x86, 0x4F, 0xD2, 0x6F, 0xB5 } );

		assertThat( encoded.length, is( 10 ) );
		assertThat( String.valueOf( encoded ), is( "HelloWeZgb" ) );

		final int[] decoded = encoder.decode( encoded );

		assertThat( decoded.length, is( 8 ) );
		assertThat( decoded, is( new int[]
			{ 0x86, 0x4F, 0xD2, 0x6F, 0xB5, 0x00, 0x00, 0x00 } ) );
	}

	@Test
	public void testShortDecodingInput()
	{
		final int[] decoded = encoder.decode( "HelloW".toCharArray() );

		assertThat( decoded.length, is( 8 ) );
		assertThat( decoded, is( new int[]
			{ 0x86, 0x4F, 0xD2, 0x6F, 0xB4, 0x76, 0x10, 0x1A } ) );
	}

}
