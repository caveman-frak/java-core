/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.process.base;


import uk.co.bluegecko.core.process.Sink;
import uk.co.bluegecko.core.process.Source;


/**
 * @param <T>
 *            type of object to pass
 */
public interface Connector< T > extends Source< T >, Sink< T >
{

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.Sink#push(java.lang.Object)
	 */
	@Override
	public void push( T in );

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.Sink#finished()
	 */
	@Override
	public void finished();

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.Source#next()
	 */
	@Override
	public T next();

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.Source#isReady()
	 */
	@Override
	public boolean isReady();

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.Source#isFinished()
	 */
	@Override
	public boolean isFinished();

	/**
	 * Reset the connection
	 */
	public void reset();

}