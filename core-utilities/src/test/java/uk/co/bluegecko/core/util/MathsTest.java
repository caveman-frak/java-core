package uk.co.bluegecko.core.util;


import static org.junit.Assert.assertEquals;

import java.awt.Dimension;

import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class MathsTest
{

	@Test
	public final void testSumInt()
	{
		int[] array = null;
		assertEquals( "null", 0, Maths.sum( array ) );

		array = new int[0];
		assertEquals( "empty", 0, Maths.sum( array ) );

		array = new int[]
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		assertEquals( "zeros", 0, Maths.sum( array ) );

		array = new int[]
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		assertEquals( "positive", 45, Maths.sum( array ) );

		array = new int[]
			{ 0, -1, -2, -3, -4, -5, -6, -7, -8, -9 };
		assertEquals( "negative", -45, Maths.sum( array ) );

		array = new int[]
			{ -4, -3, -2, -1, 0, 1, 2, 3, 4 };
		assertEquals( "balanced", 0, Maths.sum( array ) );
	}

	@Test
	public final void testSumLong()
	{
		long[] array = null;
		assertEquals( "null", 0, Maths.sum( array ) );

		array = new long[0];
		assertEquals( "empty", 0, Maths.sum( array ) );

		array = new long[]
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		assertEquals( "zeros", 0, Maths.sum( array ) );

		array = new long[]
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		assertEquals( "positive", 45, Maths.sum( array ) );

		array = new long[]
			{ 0, -1, -2, -3, -4, -5, -6, -7, -8, -9 };
		assertEquals( "negative", -45, Maths.sum( array ) );

		array = new long[]
			{ -4, -3, -2, -1, 0, 1, 2, 3, 4 };
		assertEquals( "balanced", 0, Maths.sum( array ) );
	}

	@Test
	public final void testSumFloat()
	{
		float[] array = null;
		assertEquals( "null", 0.0, Maths.sum( array ), 0.0 );

		array = new float[0];
		assertEquals( "empty", 0.0, Maths.sum( array ), 0.0 );

		array = new float[]
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		assertEquals( "zeros", 0.0, Maths.sum( array ), 0.0 );

		array = new float[]
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		assertEquals( "positive", 45.0, Maths.sum( array ), 0.0 );

		array = new float[]
			{ 0, -1, -2, -3, -4, -5, -6, -7, -8, -9 };
		assertEquals( "negative", -45.0, Maths.sum( array ), 0.0 );

		array = new float[]
			{ -4, -3, -2, -1, 0, 1, 2, 3, 4 };
		assertEquals( "balanced", 0.0, Maths.sum( array ), 0.0 );
	}

	@Test
	public final void testSumDouble()
	{
		double[] array = null;
		assertEquals( "null", 0.0, Maths.sum( array ), 0.0 );

		array = new double[0];
		assertEquals( "empty", 0.0, Maths.sum( array ), 0.0 );

		array = new double[]
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		assertEquals( "zeros", 0.0, Maths.sum( array ), 0.0 );

		array = new double[]
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		assertEquals( "positive", 45.0, Maths.sum( array ), 0.0 );

		array = new double[]
			{ 0, -1, -2, -3, -4, -5, -6, -7, -8, -9 };
		assertEquals( "negative", -45.0, Maths.sum( array ), 0.0 );

		array = new double[]
			{ -4, -3, -2, -1, 0, 1, 2, 3, 4 };
		assertEquals( "balanced", 0.0, Maths.sum( array ), 0.0 );
	}

	@Test
	public final void testMaxInt()
	{
		int[] array = null;
		assertEquals( "null", 0, Maths.max( array ) );

		array = new int[0];
		assertEquals( "empty", 0, Maths.max( array ) );

		array = new int[]
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		assertEquals( "zeros", 0, Maths.max( array ) );

		array = new int[]
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		assertEquals( "positive", 9, Maths.max( array ) );

		array = new int[]
			{ 0, -1, -2, -3, -4, -5, -6, -7, -8, -9 };
		assertEquals( "negative", 0, Maths.max( array ) );

		array = new int[]
			{ -4, -3, -2, -1, 0, 1, 2, 3, 4 };
		assertEquals( "balanced", 4, Maths.max( array ) );
	}

	@Test
	public final void testMaxLong()
	{
		long[] array = null;
		assertEquals( "null", 0, Maths.max( array ) );

		array = new long[0];
		assertEquals( "empty", 0, Maths.max( array ) );

		array = new long[]
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		assertEquals( "zeros", 0, Maths.max( array ) );

		array = new long[]
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		assertEquals( "positive", 9, Maths.max( array ) );

		array = new long[]
			{ 0, -1, -2, -3, -4, -5, -6, -7, -8, -9 };
		assertEquals( "negative", 0, Maths.max( array ) );

		array = new long[]
			{ -4, -3, -2, -1, 0, 1, 2, 3, 4 };
		assertEquals( "balanced", 4, Maths.max( array ) );
	}

	@Test
	public final void testMaxFloat()
	{
		float[] array = null;
		assertEquals( "null", 0.0, Maths.max( array ), 0.0 );

		array = new float[0];
		assertEquals( "empty", 0.0, Maths.max( array ), 0.0 );

		array = new float[]
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		assertEquals( "zeros", 0.0, Maths.max( array ), 0.0 );

		array = new float[]
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		assertEquals( "positive", 9.0, Maths.max( array ), 0.0 );

		array = new float[]
			{ 0, -1, -2, -3, -4, -5, -6, -7, -8, -9 };
		assertEquals( "negative", 0.0, Maths.max( array ), 0.0 );

		array = new float[]
			{ -4, -3, -2, -1, 0, 1, 2, 3, 4 };
		assertEquals( "balanced", 4.0, Maths.max( array ), 0.0 );
	}

	@Test
	public final void testMaxDouble()
	{
		double[] array = null;
		assertEquals( "null", 0.0, Maths.max( array ), 0.0 );

		array = new double[0];
		assertEquals( "empty", 0.0, Maths.max( array ), 0.0 );

		array = new double[]
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		assertEquals( "zeros", 0.0, Maths.max( array ), 0.0 );

		array = new double[]
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		assertEquals( "positive", 9.0, Maths.max( array ), 0.0 );

		array = new double[]
			{ 0, -1, -2, -3, -4, -5, -6, -7, -8, -9 };
		assertEquals( "negative", 0.0, Maths.max( array ), 0.0 );

		array = new double[]
			{ -4, -3, -2, -1, 0, 1, 2, 3, 4 };
		assertEquals( "balanced", 4.0, Maths.max( array ), 0.0 );
	}

	@Test
	public final void testMaxDimension()
	{
		Dimension[] array = null;
		assertEquals( "null", new Dimension(), Maths.max( array ) );

		array = new Dimension[0];
		assertEquals( "empty", new Dimension(), Maths.max( array ) );

		array = new Dimension[]
			{ new Dimension( 100, 100 ), new Dimension( 50, 150 ) };
		assertEquals( "r.height", new Dimension( 100, 150 ), Maths.max( array ) );

		array = new Dimension[]
			{ new Dimension( 100, 100 ), new Dimension( 150, 50 ) };
		assertEquals( "r.width", new Dimension( 150, 100 ), Maths.max( array ) );

		array = new Dimension[]
			{ new Dimension( 100, 100 ), new Dimension( 150, 150 ) };
		assertEquals( "r.both", new Dimension( 150, 150 ), Maths.max( array ) );

		array = new Dimension[]
			{ new Dimension( 50, 150 ), new Dimension( 100, 100 ) };
		assertEquals( "l.height", new Dimension( 100, 150 ), Maths.max( array ) );

		array = new Dimension[]
			{ new Dimension( 150, 50 ), new Dimension( 100, 100 ) };
		assertEquals( "l.width", new Dimension( 150, 100 ), Maths.max( array ) );

		array = new Dimension[]
			{ new Dimension( 150, 150 ), new Dimension( 100, 100 ) };
		assertEquals( "l.both", new Dimension( 150, 150 ), Maths.max( array ) );
	}

	@Test
	public final void testMinInt()
	{
		int[] array = null;
		assertEquals( "null", 0, Maths.min( array ) );

		array = new int[0];
		assertEquals( "empty", 0, Maths.min( array ) );

		array = new int[]
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		assertEquals( "zeros", 0, Maths.min( array ) );

		array = new int[]
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		assertEquals( "positive", 0, Maths.min( array ) );

		array = new int[]
			{ 0, -1, -2, -3, -4, -5, -6, -7, -8, -9 };
		assertEquals( "negative", -9, Maths.min( array ) );

		array = new int[]
			{ -4, -3, -2, -1, 0, 1, 2, 3, 4 };
		assertEquals( "balanced", -4, Maths.min( array ) );
	}

	@Test
	public final void testMinLong()
	{
		long[] array = null;
		assertEquals( "null", 0, Maths.min( array ) );

		array = new long[0];
		assertEquals( "empty", 0, Maths.min( array ) );

		array = new long[]
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		assertEquals( "zeros", 0, Maths.min( array ) );

		array = new long[]
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		assertEquals( "positive", 0, Maths.min( array ) );

		array = new long[]
			{ 0, -1, -2, -3, -4, -5, -6, -7, -8, -9 };
		assertEquals( "negative", -9, Maths.min( array ) );

		array = new long[]
			{ -4, -3, -2, -1, 0, 1, 2, 3, 4 };
		assertEquals( "balanced", -4, Maths.min( array ) );
	}

	@Test
	public final void testMinFloat()
	{
		float[] array = null;
		assertEquals( "null", 0.0, Maths.min( array ), 0.0 );

		array = new float[0];
		assertEquals( "empty", 0.0, Maths.min( array ), 0.0 );

		array = new float[]
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		assertEquals( "zeros", 0.0, Maths.min( array ), 0.0 );

		array = new float[]
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		assertEquals( "positive", 0.0, Maths.min( array ), 0.0 );

		array = new float[]
			{ 0, -1, -2, -3, -4, -5, -6, -7, -8, -9 };
		assertEquals( "negative", -9.0, Maths.min( array ), 0.0 );

		array = new float[]
			{ -4, -3, -2, -1, 0, 1, 2, 3, 4 };
		assertEquals( "balanced", -4.0, Maths.min( array ), 0.0 );
	}

	@Test
	public final void testMinDouble()
	{
		double[] array = null;
		assertEquals( "null", 0.0, Maths.min( array ), 0.0 );

		array = new double[0];
		assertEquals( "empty", 0.0, Maths.min( array ), 0.0 );

		array = new double[]
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		assertEquals( "zeros", 0.0, Maths.min( array ), 0.0 );

		array = new double[]
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		assertEquals( "positive", 0.0, Maths.min( array ), 0.0 );

		array = new double[]
			{ 0, -1, -2, -3, -4, -5, -6, -7, -8, -9 };
		assertEquals( "negative", -9.0, Maths.min( array ), 0.0 );

		array = new double[]
			{ -4, -3, -2, -1, 0, 1, 2, 3, 4 };
		assertEquals( "balanced", -4.0, Maths.min( array ), 0.0 );
	}

	@Test
	public final void testMinDimension()
	{
		Dimension[] array = null;
		assertEquals( "null", new Dimension(), Maths.min( array ) );

		array = new Dimension[0];
		assertEquals( "empty", new Dimension(), Maths.min( array ) );

		array = new Dimension[]
			{ new Dimension( 100, 100 ), new Dimension( 50, 150 ) };
		assertEquals( "r.height", new Dimension( 50, 100 ), Maths.min( array ) );

		array = new Dimension[]
			{ new Dimension( 100, 100 ), new Dimension( 150, 50 ) };
		assertEquals( "r.width", new Dimension( 100, 50 ), Maths.min( array ) );

		array = new Dimension[]
			{ new Dimension( 100, 100 ), new Dimension( 50, 50 ) };
		assertEquals( "r.both", new Dimension( 50, 50 ), Maths.min( array ) );

		array = new Dimension[]
			{ new Dimension( 50, 150 ), new Dimension( 100, 100 ) };
		assertEquals( "l.height", new Dimension( 50, 100 ), Maths.min( array ) );

		array = new Dimension[]
			{ new Dimension( 150, 50 ), new Dimension( 100, 100 ) };
		assertEquals( "l.width", new Dimension( 100, 50 ), Maths.min( array ) );

		array = new Dimension[]
			{ new Dimension( 50, 50 ), new Dimension( 100, 100 ) };
		assertEquals( "l.both", new Dimension( 50, 50 ), Maths.min( array ) );
	}

}
