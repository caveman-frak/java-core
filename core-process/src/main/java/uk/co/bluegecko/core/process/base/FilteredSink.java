package uk.co.bluegecko.core.process.base;


import java.util.function.Predicate;

import uk.co.bluegecko.core.process.Sink;


/**
 * Wrap an existing sink with a filter.
 *
 * @param <T>
 *            type of input
 */
public class FilteredSink< T > implements Sink< T >
{

	private final Predicate< T > filter;
	private final Sink< T > sink;

	/**
	 * Create a sink wrapper for filtering contents.
	 *
	 * @param sink
	 *            the sink to wrap
	 * @param filter
	 *            the filter to apply
	 */
	public FilteredSink( final Sink< T > sink, final Predicate< T > filter )
	{
		this.filter = filter;
		this.sink = sink;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.Sink#push(java.lang.Object)
	 */
	@Override
	public void push( final T in )
	{
		if ( filter.test( in ) )
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
		sink.finished();
	}

}