package uk.co.bluegecko.core.process.base;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import uk.co.bluegecko.core.process.Sink;


/**
 * Sink that internally redirects each input to a particular wrapped sink.
 *
 * @param <T>
 *            type of input
 */
public class DistributingSink< T > implements Sink< T >
{

	private final List< Sink< T >> collection;

	/**
	 * Create a collection of sinks to distribute to.
	 *
	 * @param collection
	 *            collection of sinks to distribute to
	 */
	public DistributingSink( final Collection< Sink< T >> collection )
	{
		this.collection = new ArrayList<>( collection );
	}

	/**
	 * Create a collection of sinks to distribute to.
	 *
	 * @param sinks
	 *            array of sinks to distribute to
	 */
	@SafeVarargs
	public DistributingSink( final Sink< T >... sinks )
	{
		this( Arrays.asList( sinks ) );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.Sink#push(java.lang.Object)
	 */
	@Override
	public void push( final T in )
	{
		for ( final Sink< T > sink : collection )
		{
			sink.push( in );
		}
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.Sink#finished()
	 */
	@Override
	public void finished()
	{
		for ( final Sink< T > sink : collection )
		{
			sink.finished();
		}
	}

}
