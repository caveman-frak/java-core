package uk.co.bluegecko.core.builder;


import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.reflect.ConstructorUtils;


/**
 * Builder class supporting creating a populated {@link Map}.
 *
 * @author tim
 *
 * @param <K>
 *            type of map key
 * @param <V>
 *            type of map value
 */
public final class MapBuilder< K, V > implements Builder< Map< K, V >, MapBuilder< K, V > >
{

	private final Map< K, V > map;

	private MapBuilder( final Map< K, V > map )
	{
		this.map = map;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.builder.Builder#build()
	 */
	@Override
	public Map< K, V > build()
	{
		return map;
	}

	/**
	 * Build a new map using the passed instance.
	 *
	 * @param map
	 *            to populate
	 * @return the populated map
	 */
	public Map< K, V > build( final Map< K, V > map )
	{
		map.putAll( this.map );

		return map;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.builder.Builder#copy()
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public MapBuilder< K, V > copy()
	{
		try
		{
			final Map< K, V > map = ConstructorUtils.invokeConstructor( this.map.getClass() );
			map.putAll( this.map );
			return new MapBuilder<>( map );
		}
		catch ( NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException ex )
		{
			throw new IllegalArgumentException( ex );
		}
	}

	/**
	 * Copy the map builder, using a new internal map.
	 *
	 * @param map
	 *            the internal map
	 * @return the new map builder
	 */
	public MapBuilder< K, V > copy( final Map< K, V > map )
	{
		map.putAll( this.map );
		return new MapBuilder<>( map );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.builder.Builder#reset()
	 */
	@Override
	public MapBuilder< K, V > reset()
	{
		map.clear();

		return this;
	}

	/**
	 * Store a key / value pair.
	 *
	 * @param key
	 *            key to store
	 * @param value
	 *            value to store
	 * @return the updated map builder
	 */
	public MapBuilder< K, V > with( final K key, final V value )
	{
		map.put( key, value );

		return this;
	}

	/**
	 * Store a sequence of key / value pairs.
	 *
	 * @param keys
	 *            the sequence of keys
	 * @param values
	 *            the sequence of values
	 * @return the updated map builder
	 */
	public MapBuilder< K, V > with( final K[] keys, final V[] values )
	{
		if ( keys.length != values.length ) { throw new IllegalArgumentException(
				"must have same number of keys and values" ); }

		for ( int i = 0; i < keys.length; i++ )
		{
			with( keys[i], values[i] );
		}

		return this;
	}

	/**
	 * Store a sequence of key / value pairs.
	 *
	 * @param keys
	 *            the sequence of keys
	 * @param values
	 *            the sequence of values
	 * @return the updated map builder
	 */
	public MapBuilder< K, V > with( final Collection< K > keys, final Collection< V > values )
	{
		if ( keys.size() != values.size() ) { throw new IllegalArgumentException(
				"must have same number of keys and values" ); }

		final Iterator< K > keyIterator = keys.iterator();
		final Iterator< V > valueIterator = values.iterator();

		for ( ; keyIterator.hasNext() && valueIterator.hasNext(); )
		{
			with( keyIterator.next(), valueIterator.next() );
		}

		return this;
	}

	/**
	 * Create a new map builder using the default map class.
	 *
	 * @param <K>
	 *            type of map key
	 * @param <V>
	 *            type of map value
	 * @return a new map builder
	 */
	public static < K, V > MapBuilder< K, V > map()
	{
		return new MapBuilder<>( new HashMap<>() );
	}

	/**
	 * Create a new map builder using the passed map instance.
	 *
	 * @param <K>
	 *            type of map key
	 * @param <V>
	 *            type of map value
	 * @param map
	 *            the map to use internally
	 *
	 * @return a new map builder
	 */
	public static < K, V > MapBuilder< K, V > map( final Map< K, V > map )
	{
		return new MapBuilder<>( map );
	}

	/**
	 * Create a new {@link Map}.
	 *
	 * @param <K>
	 *            type of map key
	 * @param <V>
	 *            type of map value
	 * @param keys
	 *            the sequence of keys
	 * @param values
	 *            the sequence of values
	 *
	 * @return a new map
	 */
	@SuppressWarnings( "unchecked" )
	public static < K, V > Map< K, V > map( final K[] keys, final V[] values )
	{
		return ( Map< K, V > ) map().with( keys, values ).build();
	}

}
