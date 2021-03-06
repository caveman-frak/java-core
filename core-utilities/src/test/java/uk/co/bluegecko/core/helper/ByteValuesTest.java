package uk.co.bluegecko.core.helper;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.helper.ByteValues.BYTE_SUFFIX;
import static uk.co.bluegecko.core.helper.ByteValues.scale;

import org.junit.Test;


public class ByteValuesTest
{

	@Test
	public void testUnknown()
	{
		assertThat( scale( -1, BYTE_SUFFIX ), is( "Unknown" ) );
	}

	@Test
	public void testBytes()
	{
		assertThat( scale( 128, BYTE_SUFFIX ), is( "128B" ) );
	}

	@Test
	public void testKiloBytes()
	{
		assertThat( scale( 1024, BYTE_SUFFIX ), is( "1KB" ) );
	}

	@Test
	public void testTwoKiloBytes()
	{
		assertThat( scale( 2048, BYTE_SUFFIX ), is( "2KB" ) );
	}

	@Test
	public void testMegaBytes()
	{
		assertThat( scale( 1_100_000, BYTE_SUFFIX ), is( "1MB" ) );
	}

	@Test
	public void testTeraBytes()
	{
		assertThat( scale( 1_100_000_000, BYTE_SUFFIX ), is( "1TB" ) );
	}

	@Test
	public void testTwoTeraBytes()
	{
		assertThat( scale( 2_200_000_000L, BYTE_SUFFIX ), is( "2TB" ) );
	}

	@Test
	public void testPetaBytes()
	{
		assertThat( scale( 1_100_000_000_000L, BYTE_SUFFIX ), is( "1PB" ) );
	}

	@Test
	public void testExceedSuffix()
	{
		assertThat( scale( 1_100_000_000_000_000L, BYTE_SUFFIX ), is( "1,000PB" ) );
	}

}
