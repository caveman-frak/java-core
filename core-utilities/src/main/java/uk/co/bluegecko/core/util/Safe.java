/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.util;


import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;


@SuppressWarnings( "javadoc" )
public final class Safe
{

	private Safe() throws IllegalAccessException
	{
		throw new IllegalAccessException( "static-class" );
	}

	public static < E > boolean equals( final E obj1, final E obj2 )
	{
		if ( obj1 == obj2 )
		{
			return true;
		}
		if ( obj1 == null || obj2 == null )
		{
			return false;
		}

		return obj1.equals( obj2 );
	}

	public static boolean equalsIgnoreCase( final String str1, final String str2 )
	{
		return equals( str1 != null ? str1.toLowerCase() : null, str2 != null ? str2.toLowerCase() : null );
	}

	public static < E > boolean same( final E obj1, final E obj2 )
	{
		return obj1 == obj2;
	}

	public static String toString( final Object obj )
	{
		if ( obj == null )
		{
			return null;
		}
		else if ( obj instanceof String )
		{
			return ( String ) obj;
		}
		return obj.toString();
	}

	public static int hashCode( final Object obj )
	{
		if ( obj == null )
		{
			return 0;
		}
		return obj.hashCode();
	}

	public static < E extends Comparable< E > > int compare( final E obj1, final E obj2 )
	{
		if ( obj1 == obj2 )
		{
			return 0;
		}
		if ( obj1 == null )
		{
			return -1;
		}
		if ( obj2 == null )
		{
			return 1;
		}
		return obj1.compareTo( obj2 );
	}

	public static < E > int compare( final E obj1, final E obj2, final Comparator< E > comparator )
	{
		if ( obj1 == obj2 )
		{
			return 0;
		}
		if ( obj1 == null )
		{
			return -1;
		}
		if ( obj2 == null )
		{
			return 1;
		}
		return comparator.compare( obj1, obj2 );
	}

	/**
	 * Get the length of the string.
	 *
	 * @param obj
	 *            the String to test
	 * @return the length of the string.
	 */
	public static int length( final String obj )
	{
		return obj != null ? obj.length() : 0;
	}

	public static < E > int length( final E[] obj )
	{
		return obj != null ? obj.length : 0;
	}

	public static int length( final Collection< ? > obj )
	{
		return obj != null ? obj.size() : 0;
	}

	public static int length( final Map< ?, ? > obj )
	{
		return obj != null ? obj.size() : 0;
	}

	/**
	 * Check if the string contains nothing (length == 0).
	 *
	 * @param obj
	 *            the String to test
	 * @return true if the string contains nothing.
	 */
	public static boolean isEmpty( final String obj )
	{
		return length( obj ) == 0;
	}

	public static boolean isEmpty( final Object obj )
	{
		return obj != null ? isEmpty( toString( obj ) ) : true;
	}

	public static < E > boolean isEmpty( final E[] obj )
	{
		return length( obj ) == 0;
	}

	public static boolean isEmpty( final Collection< ? > obj )
	{
		return length( obj ) == 0;
	}

	public static boolean isEmpty( final Map< ?, ? > obj )
	{
		return length( obj ) == 0;
	}

	public static boolean isBlank( final String obj )
	{
		return obj != null && length( obj.trim() ) == 0;
	}

	public static boolean isBlank( final Object obj )
	{
		return obj != null ? isBlank( toString( obj ) ) : false;
	}

	public static < E > boolean isBlank( final E[] obj )
	{
		return obj != null && length( obj ) == 0;
	}

	public static boolean isBlank( final Collection< ? > obj )
	{
		return obj != null && length( obj ) == 0;
	}

	public static boolean isBlank( final Map< ?, ? > obj )
	{
		return obj != null && length( obj ) == 0;
	}

	public static boolean isTrue( final String string )
	{
		if ( isEmpty( string ) )
		{
			return false;
		}
		final String value = string.toLowerCase();
		return value.equals( "true" ) || value.equals( "yes" );
	}

	public static boolean[] copy( final boolean[] array )
	{
		return array != null ? Arrays.copyOf( array, array.length ) : null;
	}

	public static byte[] copy( final byte[] array )
	{
		return array != null ? Arrays.copyOf( array, array.length ) : null;
	}

	public static char[] copy( final char[] array )
	{
		return array != null ? Arrays.copyOf( array, array.length ) : null;
	}

	public static short[] copy( final short[] array )
	{
		return array != null ? Arrays.copyOf( array, array.length ) : null;
	}

	public static int[] copy( final int[] array )
	{
		return array != null ? Arrays.copyOf( array, array.length ) : null;
	}

	public static long[] copy( final long[] array )
	{
		return array != null ? Arrays.copyOf( array, array.length ) : null;
	}

	public static float[] copy( final float[] array )
	{
		return array != null ? Arrays.copyOf( array, array.length ) : null;
	}

	public static double[] copy( final double[] array )
	{
		return array != null ? Arrays.copyOf( array, array.length ) : null;
	}

	public static < T > T[] copy( final T[] array )
	{
		return array != null ? Arrays.copyOf( array, array.length ) : null;
	}

}
