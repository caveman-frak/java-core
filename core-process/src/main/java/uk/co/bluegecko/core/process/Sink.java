/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.process;


/**
 * A sink for objects, used to connect Processes.
 *
 * @param <T>
 *            the type of object to accept
 */
public interface Sink< T >
{

	/**
	 * Push an object into the sink.
	 *
	 * @param in
	 *            new object
	 */
	public void push( T in );

	/**
	 * Mark the sink as finished processing, it will accept no more objects.
	 */
	public void finished();

}
