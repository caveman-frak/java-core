/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.swing.layout;


import org.junit.Assert;
import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class ScaleTest
{

	@Test
	public final void testConstructor_NONE()
	{
		Assert.assertEquals( 0.0, Scale.NONE.getFactor(), 0.0 );
	}

	@Test
	public final void testConstructor_ALL()
	{
		Assert.assertEquals( 1.0, Scale.ALL.getFactor(), 0.0 );
	}

	@Test
	public final void testConstructor()
	{
		Assert.assertEquals( 0.0, new Scale( 0.0 ).getFactor(), 0.0 );
		Assert.assertEquals( 0.5, new Scale( 0.5 ).getFactor(), 0.0 );
		Assert.assertEquals( 1.0, new Scale( 1.0 ).getFactor(), 0.0 );
		try
		{
			new Scale( -0.1 ).getFactor();
			Assert.fail( "-0.1 is out of range" );
		}
		catch ( final IllegalArgumentException ex )
		{
			Assert.assertEquals( "fill must be between 0.0 and 1.0 inclusive, was -0.1", ex.getMessage() );
		}
		try
		{
			new Scale( 1.1 ).getFactor();
			Assert.fail( "1.1 is out of range" );
		}
		catch ( final IllegalArgumentException ex )
		{
			Assert.assertEquals( "fill must be between 0.0 and 1.0 inclusive, was 1.1", ex.getMessage() );
		}
	}

	@Test
	public final void testScale_NONE()
	{
		Assert.assertEquals( 100, Scale.NONE.scale( 500, 100 ) );
		Assert.assertEquals( 100, Scale.NONE.scale( 100, 100 ) );
		Assert.assertEquals( 45, Scale.NONE.scale( 500, 45 ) );
	}

	@Test
	public final void testScale_ALL()
	{
		Assert.assertEquals( 500, Scale.ALL.scale( 500, 100 ) );
		Assert.assertEquals( 100, Scale.ALL.scale( 100, 100 ) );
		Assert.assertEquals( 500, Scale.ALL.scale( 500, 45 ) );
	}

	@Test
	public final void testScale25()
	{
		// test with 0.25
		Assert.assertEquals( 200, new Scale( 0.25 ).scale( 500, 100 ) );
		Assert.assertEquals( 100, new Scale( 0.25 ).scale( 100, 100 ) );
		Assert.assertEquals( 159, new Scale( 0.25 ).scale( 500, 45 ) );
	}

	@Test
	public final void testScale50()
	{
		// test with 0.5
		Assert.assertEquals( 300, new Scale( 0.50 ).scale( 500, 100 ) );
		Assert.assertEquals( 100, new Scale( 0.50 ).scale( 100, 100 ) );
		Assert.assertEquals( 273, new Scale( 0.50 ).scale( 500, 45 ) );
	}

	@Test
	public final void testScale75()
	{
		// test with 0.75
		Assert.assertEquals( 400, new Scale( 0.75 ).scale( 500, 100 ) );
		Assert.assertEquals( 100, new Scale( 0.75 ).scale( 100, 100 ) );
		Assert.assertEquals( 386, new Scale( 0.75 ).scale( 500, 45 ) );
	}

}
