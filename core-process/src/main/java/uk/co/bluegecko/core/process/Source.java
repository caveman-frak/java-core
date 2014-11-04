/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.process;


/**
 * A source of objects, used to connect Processes.
 *
 * @param <T>
 *            the type of the object to provide
 *
 */
public interface Source< T >
{

	/**
	 * Return the next available object. Will block if not ready. Will throw an exception if finished.
	 *
	 * @return the next available object
	 */
	public T next();

	/**
	 * Return true if the source is ready to provide objects.
	 *
	 * @return true id ready
	 */
	public boolean isReady();

	/**
	 * Return true if the source has no more available objects.
	 *
	 * @return true if not more objects are available
	 */
	public boolean isFinished();

}
