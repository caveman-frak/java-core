package uk.co.bluegecko.core.builder;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.reflect.ConstructorUtils;


/**
 * Builder class supporting creating a populated {@link Collection}.
 *
 * @author tim
 *
 * @param <C>
 *            type of collection
 * @param <T>
 *            type of element
 */
public final class CollectionBuilder< C extends Collection< T >, T > implements Builder< C, CollectionBuilder< C, T > >
{

	private final C collection;

	private CollectionBuilder( final C collection )
	{
		this.collection = collection;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.builder.Builder#build()
	 */
	@Override
	public C build()
	{
		return collection;
	}

	/**
	 * Build a new collection using the passed instance.
	 *
	 * @param collection
	 *            to populate
	 * @return the populated collection
	 */
	public < C1 extends Collection< T >> C1 build( final C1 collection )
	{
		collection.addAll( this.collection );

		return collection;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.builder.Builder#copy()
	 */
	@Override
	@SuppressWarnings( "unchecked" )
	public CollectionBuilder< C, T > copy()
	{
		try
		{
			final C collection = ( C ) ConstructorUtils.invokeConstructor( this.collection.getClass() );
			collection.addAll( this.collection );
			return new CollectionBuilder<>( collection );
		}
		catch ( NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException ex )
		{
			throw new IllegalArgumentException( ex );
		}
	}

	/**
	 * Copy the collection builder, using a new internal collection.
	 *
	 * @param collection
	 *            the internal collection
	 * @return the new collection builder
	 */
	public < C1 extends Collection< T >> CollectionBuilder< C1, T > copy( final C1 collection )
	{
		collection.addAll( this.collection );
		return new CollectionBuilder<>( collection );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.builder.Builder#reset()
	 */
	@Override
	public CollectionBuilder< C, T > reset()
	{
		collection.clear();

		return this;
	}

	/**
	 * Add the value to the collection.
	 *
	 * @param value
	 *            value to add
	 * @return the updated collection builder
	 */
	public CollectionBuilder< C, T > with( final T value )
	{
		collection.add( value );

		return this;
	}

	/**
	 * Add the values to the collection.
	 *
	 * @param values
	 *            values to add
	 * @return the updated collection builder
	 */
	@SuppressWarnings( "unchecked" )
	public CollectionBuilder< C, T > with( final T... values )
	{
		for ( final T value : values )
		{
			collection.add( value );
		}

		return this;
	}

	/**
	 * Add the collection of values to the collection.
	 *
	 * @param values
	 *            collection of values to add
	 * @return the updated collection builder
	 */
	public CollectionBuilder< C, T > with( final Collection< T > values )
	{
		for ( final T value : values )
		{
			collection.add( value );
		}

		return this;
	}

	/**
	 * Create a new collection builder using the passed collection instance.
	 *
	 * @param collection
	 *            the collection to use internally
	 *
	 * @return a new collection builder
	 */
	public static < C extends Collection< T >, T > CollectionBuilder< C, T > collection( final C collection )
	{
		return new CollectionBuilder<>( collection );
	}

	/**
	 * Create a new collection builder using {@link List}.
	 *
	 * @return a new collection builder
	 */
	public static < T > CollectionBuilder< List< T >, T > list()
	{
		return collection( new ArrayList<>() );
	}

	/**
	 * Create a new {@link List}.
	 *
	 * @param values
	 *            the values for the list
	 *
	 * @return a new list
	 */
	@SuppressWarnings( "unchecked" )
	@SafeVarargs
	public static < T > List< T > list( final T... values )
	{
		return ( List< T > ) list().with( values ).build();
	}

	/**
	 * Create a new collection builder using {@link Set}.
	 *
	 * @return a new collection builder
	 */
	public static < T > CollectionBuilder< Set< T >, T > set()
	{
		return collection( new HashSet<>() );
	}

	/**
	 * Create a new {@link Set}.
	 *
	 * @param values
	 *            the values for the set
	 *
	 * @return a new set
	 */
	@SuppressWarnings( "unchecked" )
	@SafeVarargs
	public static < T > Set< T > set( final T... values )
	{
		return ( Set< T > ) set().with( values ).build();
	}

	/**
	 * Create a new collection builder using {@link SortedSet}.
	 *
	 * @return a new collection builder
	 */
	public static < T > CollectionBuilder< SortedSet< T >, T > sortedSet()
	{
		return collection( new TreeSet<>() );
	}

	/**
	 * Create a new collection builder using {@link Queue}.
	 *
	 * @return a new collection builder
	 */
	public static < T > CollectionBuilder< Queue< T >, T > queue()
	{
		return collection( new LinkedList<>() );
	}

	/**
	 * Create a new collection builder using {@link Deque}.
	 *
	 * @return a new collection builder
	 */
	public static < T > CollectionBuilder< Deque< T >, T > deque()
	{
		return collection( new LinkedList<>() );
	}

}
