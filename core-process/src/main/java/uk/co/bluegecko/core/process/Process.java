/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.process;


/**
 * @param <E>
 *            incoming type
 * @param <T>
 *            outgoing type
 *
 */
public interface Process< E, T >
{

	/**
	 * Process an object
	 * 
	 * @param in
	 *            incoming object
	 * @return outgoing object
	 */
	public T process( E in );

}
