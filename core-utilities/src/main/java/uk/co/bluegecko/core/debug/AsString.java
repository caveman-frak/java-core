package uk.co.bluegecko.core.debug;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;


/**
 * Methods for converting objects to readable strings.
 *
 */
public final class AsString
{

	private AsString()
	{
		throw new IllegalStateException( "static-class" );
	}

	/**
	 * Turn an object into a readable string.
	 *
	 * @param object
	 *            the object
	 * @return readable string
	 */
	public static String asString( final Object object )
	{
		return object.toString();
	}

	/**
	 * Turn the collection into a readable string.
	 *
	 * @param collection
	 *            collection to print out
	 * @return readable string
	 */
	public static String asString( final Collection< ? > collection )
	{
		return wrap( '{', '}', StringUtils.join( collection, ',' ) );
	}

	/**
	 * Turn the map into a readable string.
	 *
	 * @param map
	 *            map to print out
	 * @return readable string
	 */
	public static String asString( final Map< ?, ? > map )
	{
		// list
		final List< String > entries = new ArrayList<>();
		for ( final Map.Entry< ?, ? > entry : map.entrySet() )
		{
			entries.add( asString( entry ) );
		}
		return wrap( '<', '>', StringUtils.join( entries, ',' ) );
	}

	/**
	 * Turn a map entry into a readable key : value pair.
	 *
	 * @param entry
	 *            map entry to print out
	 * @return readable string
	 */
	public static String asString( final Entry< ?, ? > entry )
	{
		return '(' + asString( entry.getKey() ) + ':' + asString( entry.getValue() ) + ')';
	}

	/**
	 * Turn the array into a readable string.
	 *
	 * @param array
	 *            array to print out
	 * @return readable string
	 */
	public static String asString( final Object[] array )
	{
		return wrap( '[', ']', StringUtils.join( array, ',' ) );
	}

	/**
	 * Wrap the contents string between prefix and suffix characters.
	 *
	 * @param prefix
	 *            prefix character
	 * @param suffix
	 *            suffix character
	 * @param contents
	 *            string to wrap
	 * @return wrapped string
	 */
	public static String wrap( final char prefix, final char suffix, final String contents )
	{
		return prefix + contents + suffix;
	}

}
