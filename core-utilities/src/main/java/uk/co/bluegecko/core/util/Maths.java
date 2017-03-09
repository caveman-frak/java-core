/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.util;


import java.awt.Dimension;


public final class Maths
{

	private Maths() throws IllegalAccessException
	{
		throw new IllegalAccessException( "static-class" );
	}

	public static int sum( final int... args )
	{
		if ( args == null || args.length == 0 )
		{
			return 0;
		}

		int total = 0;
		for ( final int x : args )
		{
			total += x;
		}
		return total;
	}

	public static long sum( final long... args )
	{
		if ( args == null || args.length == 0 )
		{
			return 0;
		}

		long total = 0;
		for ( final long x : args )
		{
			total += x;
		}
		return total;
	}

	public static float sum( final float... args )
	{
		if ( args == null || args.length == 0 )
		{
			return 0.0f;
		}

		float total = 0.0f;
		for ( final float x : args )
		{
			total += x;
		}
		return total;
	}

	public static double sum( final double... args )
	{
		if ( args == null || args.length == 0 )
		{
			return 0.0;
		}

		double total = 0.0;
		for ( final double x : args )
		{
			total += x;
		}
		return total;
	}

	public static int max( final int... args )
	{
		if ( args == null || args.length == 0 )
		{
			return 0;
		}

		int result = args[0];
		for ( final int x : args )
		{
			result = Math.max( result, x );
		}
		return result;
	}

	public static long max( final long... args )
	{
		if ( args == null || args.length == 0 )
		{
			return 0;
		}

		long result = args[0];
		for ( final long x : args )
		{
			result = Math.max( result, x );
		}
		return result;
	}

	public static float max( final float... args )
	{
		if ( args == null || args.length == 0 )
		{
			return 0.0f;
		}

		float result = args[0];
		for ( final float x : args )
		{
			result = Math.max( result, x );
		}
		return result;
	}

	public static double max( final double... args )
	{
		if ( args == null || args.length == 0 )
		{
			return 0.0;
		}

		double result = args[0];
		for ( final double x : args )
		{
			result = Math.max( result, x );
		}
		return result;
	}

	public static Dimension max( final Dimension... args )
	{
		if ( args == null || args.length == 0 )
		{
			return new Dimension();
		}

		final Dimension result = new Dimension( args[0] );
		for ( final Dimension x : args )
		{
			result.height = Math.max( result.height, x.height );
			result.width = Math.max( result.width, x.width );
		}
		return result;
	}

	public static int min( final int... args )
	{
		if ( args == null || args.length == 0 )
		{
			return 0;
		}

		int result = args[0];
		for ( final int x : args )
		{
			result = Math.min( result, x );
		}
		return result;
	}

	public static long min( final long... args )
	{
		if ( args == null || args.length == 0 )
		{
			return 0;
		}

		long result = args[0];
		for ( final long x : args )
		{
			result = Math.min( result, x );
		}
		return result;
	}

	public static float min( final float... args )
	{
		if ( args == null || args.length == 0 )
		{
			return 0;
		}

		float result = args[0];
		for ( final float x : args )
		{
			result = Math.min( result, x );
		}
		return result;
	}

	public static double min( final double... args )
	{
		if ( args == null || args.length == 0 )
		{
			return 0;
		}

		double result = args[0];
		for ( final double x : args )
		{
			result = Math.min( result, x );
		}
		return result;
	}

	public static Dimension min( final Dimension... args )
	{
		if ( args == null || args.length == 0 )
		{
			return new Dimension();
		}

		final Dimension result = new Dimension( args[0] );
		for ( final Dimension x : args )
		{
			result.height = Math.min( result.height, x.height );
			result.width = Math.min( result.width, x.width );
		}
		return result;
	}

}
