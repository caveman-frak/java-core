/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.helper;


import java.util.Collection;
import java.util.Iterator;


/**
 * Simple helper to pull values out of collections for testing.
 */
public interface CollectionHelper
{

	/**
	 * Return the first item from a collection, or null if empty.
	 *
	 * @param <T>
	 *            type of collection
	 * @param collection
	 *            the collections
	 * @return return first item
	 */
	@SuppressWarnings( "null" )
	public static < T > T first( final Collection< T > collection )
	{
		return collection.isEmpty() ? null : collection.iterator().next();
	}

	/**
	 * Return the last item from a collection, or null if empty.
	 *
	 * @param <T>
	 *            type of collection
	 * @param collection
	 *            the collections
	 * @return return last item
	 */
	@SuppressWarnings( "null" )
	public static < T > T last( final Collection< T > collection )
	{
		T item = null;
		final Iterator< T > iterator = collection.iterator();
		while ( iterator.hasNext() )
		{
			item = iterator.next();
		}
		return item;
	}

	/**
	 * Return the last item from a collection, or null if empty.
	 *
	 * @param <T>
	 *            type of collection
	 * @param collection
	 *            the collections
	 * @param count
	 *            the number of the item to return
	 * @return return the numbered item
	 */
	@SuppressWarnings( "null" )
	public static < T > T item( final Collection< T > collection, final int count )
	{
		T item = null;
		int i = 0;
		final Iterator< T > iterator = collection.iterator();
		while ( iterator.hasNext() )
		{
			item = iterator.next();
			if ( ++i > count )
			{
				break;
			}
		}
		return i <= count ? null : item;
	}

}
